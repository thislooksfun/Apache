package com.tlf.apache.client;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SoundUpdaterApache implements IUpdatePlayerListBox
{
//	private final SoundManager theSoundManager;
//	
//	/** Heli which sound is being updated. */
//	private final EntityApache theHeli;
//	
//	/** The player that is getting the heli sound updates. */
//	private final EntityPlayerSP thePlayer;
//	private boolean playerSPRidingHeli;
//	private boolean heliIsDead;
//	private boolean heliIsMoving;
//	private boolean silent;
//	private double heliSpeed;
//	
//	public SoundUpdaterApache(SoundManager par1SoundManager, EntityApache par2EntityApache, EntityPlayerSP par3EntityPlayerSP)
//	{
//		this.theSoundManager = par1SoundManager;
//		this.theHeli = par2EntityApache;
//		this.thePlayer = par3EntityPlayerSP;
//	}
//	
	/**
	 * Updates the JList with a new model.
	 */
	@Override
	public void update()
	{
//		boolean flag = false;
//		boolean flag1 = this.playerSPRidingHeli;
//		boolean flag2 = this.heliIsDead;
//		boolean flag3 = this.heliIsMoving;
//		double d0 = this.heliSpeed;
//		this.playerSPRidingHeli = this.thePlayer != null && this.theHeli.riddenByEntity == this.thePlayer;
//		this.heliIsDead = this.theHeli.isDead;
//		this.heliSpeed = (double)MathHelper.sqrt_double(this.theHeli.motionX * this.theHeli.motionX + this.theHeli.motionZ * this.theHeli.motionZ);
//		this.heliIsMoving = this.heliSpeed >= 0.01D;
//		
//		if (flag1 && !this.playerSPRidingHeli)
//		{
//			this.theSoundManager.stopEntitySound(this.thePlayer);
//		}
//		
//		if (this.heliIsDead && !this.silent)
//		{
//			System.out.println("Hello!");
//			if (!flag2)
//			{
//				this.theSoundManager.stopEntitySound(this.theHeli);
//				
//				if (flag1 || this.playerSPRidingHeli)
//				{
//					this.theSoundManager.stopEntitySound(this.thePlayer);
//				}
//			}
//			
//			this.silent = true;
//			
//			if (this.heliIsDead)
//			{
//				System.out.println("Hello again!");
//				return;
//			}
//		}
//		
//		if (!this.theSoundManager.isEntitySoundPlaying(this.theHeli))
//		{
//			System.out.println("*sneaky sneaky*");
//			this.theSoundManager.playEntitySound("apache:run", this.theHeli, 1.0F, 1.0F, true);
//			this.silent = false;
//		}
//		
//		this.theSoundManager.updateSoundLocation(this.theHeli);
//		
//		if (this.playerSPRidingHeli && false)
//		{
//			this.theSoundManager.updateSoundLocation(this.thePlayer, this.theHeli);
//		}
	}
}