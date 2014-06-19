package com.tlf.apache.client.sound;

import com.tlf.apache.common.Apache;
import com.tlf.apache.entity.EntityApache;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MovingSoundApache extends MovingSound
{
    private final EntityApache entityApache;
    private float field_147669_l = 0.0F;

    public MovingSoundApache(EntityApache entityApache)
    {
        super(new ResourceLocation(Apache.MODID + ":run"));
        this.entityApache = entityApache;
        this.repeat = true;
        this.field_147665_h = 0;
    }

    /**
     * Updates the JList with a new model.
     */
    @Override
	public void update()
    {
        if (this.entityApache.isDead)
        {
            this.donePlaying = true;
        }
        else
        {
            this.xPosF = (float)this.entityApache.posX;
            this.yPosF = (float)this.entityApache.posY;
            this.zPosF = (float)this.entityApache.posZ;
            float f = MathHelper.sqrt_double(this.entityApache.motionX * this.entityApache.motionX + this.entityApache.motionZ * this.entityApache.motionZ);

            if ((double)f >= 0.01D)
            {
                this.field_147669_l = MathHelper.clamp_float(this.field_147669_l + 0.0025F, 0.0F, 1.0F);
                this.volume = 0.0F + MathHelper.clamp_float(f, 0.0F, 0.5F) * 0.7F;
            }
            else
            {
                this.field_147669_l = 0.0F;
                this.volume = 0.0F;
            }
        }
    }
}