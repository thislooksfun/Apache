package com.tlf.apache.client.render;

import com.tlf.apache.client.model.ModelApache;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderApache extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation("apache", "textures/entity/apache.png");

    public RenderApache()
    {
        super(new ModelApache(), 0.5F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return texture;
    }
}
