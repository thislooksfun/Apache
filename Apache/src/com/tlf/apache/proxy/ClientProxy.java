package com.tlf.apache.proxy;

import com.tlf.apache.client.render.RenderApache;
import com.tlf.apache.entity.EntityApache;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy
{
	@Override
	public void registerThings()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityApache.class, new RenderApache());
	}
}
