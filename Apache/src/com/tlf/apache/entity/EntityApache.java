package com.tlf.apache.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import cpw.mods.fml.common.FMLCommonHandler;

import java.util.Iterator;
import java.util.List;

public class EntityApache extends EntityFlying
{
	public final float rotor1RotationIncrement = 0.628F;
	
	public float rotor1Rotation = 0.0F;
	
	public int updateCooldown = 0;
	private int particleCooldown = 0;
	
	//private float customRotationYaw = 0.0F;
	
	private IUpdatePlayerListBox soundUpdater;
	
	//private AxisAlignedBB collisionBox;
	//private AxisAlignedBB rotor1Box;
	
	private class DWIDS {
		private static final int FUEL = 12;
		private static final int ENGINEPOWER = 13;
		private static final int THROTTLE = 14;
		private static final int BITMAP = 15;
	}
	
	private enum Bools {
		ISON(1 << 0),
		SHOULDSTART(1 << 1),
		SHOULDSTOP(1 << 2),
		ISSTARTING(1 << 3),
		ISSTOPPING(1 << 4),
		OUTOFFUEL(1 << 5);
		
		private int bit;
		
		private Bools(int i) {
			this.bit = i;
		}
		
		public int bit() {
			return this.bit;
		}
	}
	
	public EntityApache(World par1World)
	{
		super(par1World);
		this.setSize(0.4F, 1.8F);
		//this.soundUpdater = par1World != null ? new SoundUpdaterApache(Minecraft.getMinecraft().sndManager, this, Minecraft.getMinecraft().thePlayer) : null;
		this.setHealth(1.0F);
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		
		this.dataWatcher.addObject(DWIDS.FUEL, 1); //TODO set to 0
		this.dataWatcher.addObject(DWIDS.ENGINEPOWER, 0.0F);
		this.dataWatcher.addObject(DWIDS.THROTTLE, 0.5F);
		this.dataWatcher.addObject(DWIDS.BITMAP, 0);
	}
	
	@Override
	public boolean isAIEnabled()
	{
		return false;
	}
	
	@Override
	public boolean canBePushed()
	{
		return false;
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		int fuel = this.dataWatcher.getWatchableObjectInt(DWIDS.FUEL);
		boolean outOfFuel = this.getBool(Bools.OUTOFFUEL);
		if (fuel <= 0 && !outOfFuel) {
			this.setBool(Bools.OUTOFFUEL, outOfFuel = true);
		} else if (fuel > 0 && outOfFuel) {
			this.setBool(Bools.OUTOFFUEL, outOfFuel = false);
		}
		
		if (this.getBool(Bools.SHOULDSTART))
		{
			System.out.println("Starting!");
			this.setBool(Bools.SHOULDSTART, false);
			this.setBool(Bools.ISSTARTING, true);
			this.setBool(Bools.ISSTOPPING, false);
			this.setBool(Bools.ISON, true);
		} 
		
		if (this.getBool(Bools.SHOULDSTOP) || outOfFuel)
		{
			System.out.println("Stopping");
			this.setBool(Bools.SHOULDSTOP, false);
			this.setBool(Bools.ISSTARTING, false);
			this.setBool(Bools.ISSTOPPING, true);
			this.setBool(Bools.ISON, false);
		}
		
		if (this.updateCooldown > 0) {
			this.updateCooldown--;
		} else {
			this.updateCooldown = 10;
			this.updateRotationIncrement();
		}
		
		if (this.rotor1Rotation > 6.283F) {
			this.rotor1Rotation -= 6.283F;
		}
		
		if (this.soundUpdater != null)
		{
			if (this.isDead) { System.out.println(":D"); }
			this.soundUpdater.update();
		}
		
		this.dataWatcher.updateObject(DWIDS.THROTTLE, 0.5F);
		
		float enginePower = this.dataWatcher.getWatchableObjectFloat(DWIDS.ENGINEPOWER);
		float throttle = this.dataWatcher.getWatchableObjectFloat(DWIDS.THROTTLE);
		
		this.rotor1Rotation += this.rotor1RotationIncrement*enginePower + ((0.5F - throttle)/2);
		
		this.rotationYaw = 0.0F;
		
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			if (this.getHealth() < this.getMaxHealth()/10) {
				if (this.particleCooldown <= 0) {
					for (int i = 0; i < this.rand.nextInt(5); i++)
					{
						double x = (this.rand.nextInt(4)-2) * this.width;
						double y = (this.rand.nextFloat()-0.5F);
						double z = (this.rand.nextInt(4)-2) * this.width;
						this.worldObj.spawnParticle("smoke", this.posX + x, this.posY + this.height + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
					}
					
					this.particleCooldown = this.rand.nextInt(3);
				} else {
					this.particleCooldown--;
				}
			}
		}
	}
	
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
		{
			if (this.riddenByEntity == par1EntityPlayer) {
				if (this.getBool(Bools.ISON)) {
					this.setBool(Bools.SHOULDSTOP, true);
				} else {
					this.setBool(Bools.SHOULDSTART, true);
				}
			}
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				if (par1EntityPlayer.isSneaking()) {
					if (this.getBool(Bools.ISON)) {
						this.setBool(Bools.SHOULDSTOP, true);
					} else {
						this.setBool(Bools.SHOULDSTART, true);
					}
				} else {
					this.setBool(Bools.SHOULDSTART, true);
					par1EntityPlayer.mountEntity(this);
				}
			}
		}
		
		return true;
	}
	
	private boolean getBool(Bools key) {
		return (this.dataWatcher.getWatchableObjectInt(DWIDS.BITMAP) & key.bit()) != 0;
	}
	private void setBool(Bools key, boolean value) {
		int mask = this.dataWatcher.getWatchableObjectInt(DWIDS.BITMAP);
		if (value) {
			mask |= key.bit();
		} else {
			mask &= ~key.bit();
		}
		this.dataWatcher.updateObject(DWIDS.BITMAP, mask);
	}
	
	private void updateRotationIncrement()
	{
		float enginePower = this.dataWatcher.getWatchableObjectFloat(DWIDS.ENGINEPOWER);
		enginePower += this.getBool(Bools.ISSTARTING) ? 0.01F : (this.getBool(Bools.ISSTOPPING) ? -0.01F : 0.0F);
		if (enginePower >= 1.0F) {
			enginePower = 1.0F;
			this.setBool(Bools.ISSTARTING, false);
		} else if (enginePower <= 0.0F) {
			enginePower = 0.0F;
			this.setBool(Bools.ISSTOPPING, false);
		}
		
		if (enginePower > 0.5F) {
			//this.motionY += (double)(0.5 * (this.rotor1RotationIncrement - 0.5F)/0.5F) ;
		}
		
		this.dataWatcher.updateObject(DWIDS.ENGINEPOWER, enginePower);
		//this.rotor1EnginePower = 0.0F;
	}
	
	@Override
	public double getMountedYOffset()
	{
		return -0.15;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setInteger("fuel", this.dataWatcher.getWatchableObjectInt(DWIDS.FUEL));
		
		par1NBTTagCompound.setBoolean("isStarting", this.getBool(Bools.ISSTARTING));
		par1NBTTagCompound.setBoolean("isOn", this.getBool(Bools.ISON));
		par1NBTTagCompound.setBoolean("isStopping", this.getBool(Bools.ISSTOPPING));
		
		par1NBTTagCompound.setFloat("enginePower", this.dataWatcher.getWatchableObjectFloat(DWIDS.ENGINEPOWER));
		
		par1NBTTagCompound.setFloat("rotor1Rotation", this.rotor1Rotation);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		
		this.dataWatcher.updateObject(DWIDS.FUEL, par1NBTTagCompound.getInteger("fuel"));
		
		this.setBool(Bools.ISSTARTING, par1NBTTagCompound.getBoolean("isStarting"));
		this.setBool(Bools.ISON, par1NBTTagCompound.getBoolean("isOn"));
		this.setBool(Bools.ISSTOPPING, par1NBTTagCompound.getBoolean("isStopping"));
		
		this.dataWatcher.updateObject(DWIDS.ENGINEPOWER, par1NBTTagCompound.getFloat("enginePower"));
		
		this.rotor1Rotation = par1NBTTagCompound.getFloat("rotor1Rotation");
	}
	
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		double f = 5/2.0D;
		AxisAlignedBB boundingBoxRotor1 = AxisAlignedBB.getBoundingBox(this.posX - f, this.posY - 1.6D, this.posZ - f, this.posX + f, this.posY + 1.7D, this.posZ + f);
		
		return boundingBoxRotor1;
		//return this.boundingBox;
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		//double f = 5/2.0D;
		//AxisAlignedBB boundingBoxRotor1 = AxisAlignedBB.getBoundingBox(this.posX - f, this.posY - 1.6D, this.posZ - f, this.posX + f, this.posY + 1.7D, this.posZ + f);
		
		//return boundingBoxRotor1;
		//return this.boundingBox;
		return par1Entity.boundingBox;
	}
	
	@Override
	public void setPosition(double par1, double par2, double par3)
	{
		AxisAlignedBB b = this.boundingBox;
		double boxSX = b.maxX - b.minX;
		double boxSY = b.maxY - b.minY;
		double boxSZ = b.maxZ - b.minZ;
		this.boundingBox.setBounds(posX - boxSX/2D, posY, posZ - boxSZ/2D, posX + boxSX/2D, posY + boxSY, posZ + boxSZ/2D);
	}
	
	@Override
	protected void collideWithEntity(Entity par1Entity)
    {
		System.out.println("Collided!");
		
		boolean temp = true;
		if (temp) {
			super.collideWithEntity(par1Entity);
		}
		
		double f = 5/2.0D;
		AxisAlignedBB boundingBoxRotor1 = AxisAlignedBB.getBoundingBox(this.posX - f, this.posY - 1.6D, this.posZ - f, this.posX + f, this.posY + 1.7D, this.posZ + f);
		
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBoxRotor1);
		Iterator iterator = list.iterator();
		
		while (iterator.hasNext()) {
			Entity entity = ((Entity)iterator.next());
			if (!(entity instanceof EntityPlayer && entity == this.riddenByEntity)) {
				entity.attackEntityFrom(DamageSource.generic, 5.0F);
				System.out.println("Damaged!");
			}
		}
    }
}