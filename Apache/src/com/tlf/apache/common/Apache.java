package com.tlf.apache.common;

import com.tlf.apache.entity.EntityApache;
import com.tlf.apache.item.ItemApache;
import com.tlf.apache.proxy.ServerProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Apache.MODID, name = Apache.NAME, version = Apache.VERSION)
public class Apache
{
	public static final String MODID = "apache";
	public static final String NAME = "Apache";
	public static final String VERSION = "0.0.1 pre-alpha";
	
	@Instance(Apache.MODID)
	public static Apache instance;
	
	@SidedProxy(clientSide = "com.tlf.apache.proxy.ClientProxy", serverSide = "com.tlf.apache.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	private ModMetadata metadata;
	
	public static Item itemApache;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		itemApache = new ItemApache().setUnlocalizedName("itemApache").setCreativeTab(CreativeTabs.tabTransport);
		GameRegistry.registerItem(itemApache, "itemApache");
		
		metadata = event.getModMetadata();
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		proxy.registerThings();
		EntityRegistry.registerModEntity(EntityApache.class, "EntityApache", 0, instance, 80, 3, true);
	}
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		System.out.println(metadata.name + " " + metadata.version + " loaded!");
	}
}
