package com.tlf.apache.item;

import com.tlf.apache.entity.EntityApache;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemApache extends Item
{
	@Override
	public IIcon getIconFromDamage(int par1)
    {
		//return this.itemIcon;
        return Items.ghast_tear.getIconFromDamage(par1);
    }
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if (!par3World.isRemote)
		{
			EntityApache entityApache = new EntityApache(par3World);
			entityApache.setLocationAndAngles((double)par4 + 0.5D, (double)par5 + 1.0D, (double)par6 + 0.5D, 0.0F, 0.0F);
	        par3World.spawnEntityInWorld(entityApache);
	        return true;
		}
		
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		//this.itemIcon = par1IIconRegister.registerIcon(Apache.MODID + ":apache");
	}
}
