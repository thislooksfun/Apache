package com.tlf.apache.client.model;

import com.tlf.apache.entity.EntityApache;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelApache extends ModelBase
{
	public ModelRenderer rotor1DriveShaft;
	public ModelRenderer rotor1Blade1;
	public ModelRenderer rotor1Blade2;
	public ModelRenderer rotor1Blade3;
	public ModelRenderer rotor1Blade4;
	
	public ModelRenderer rotor2DriveShaft;
	public ModelRenderer rotor2Blade1;
	public ModelRenderer rotor2Blade2;
	public ModelRenderer rotor2Blade3;
	public ModelRenderer rotor2Blade4;
	
	public ModelApache()
	{
		this.registerModelBoxes();
	}
	
	private void registerModelBoxes()
	{
		this.rotor1DriveShaft = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1DriveShaft.addBox(-3F, -6F, -3F, 6, 12, 6, 0.0F);
		
		this.rotor1Blade1 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1Blade1.addBox(2.9F, -4.5F, -1.5F, 40, 1, 6, -0.2F);
		this.rotor1Blade1.rotateAngleX = -0.2F;
		this.rotor1Blade1.rotateAngleY = 0.0F;
		
		this.rotor1Blade2 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1Blade2.addBox(2.9F, -4.5F, -1.5F, 40, 1, 6, -0.2F);
		this.rotor1Blade2.rotateAngleX = -0.2F;
		this.rotor1Blade2.rotateAngleY = 1.571F;
		
		this.rotor1Blade3 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1Blade3.addBox(-42.9F, -4.5F, -4.5F, 40, 1, 6, -0.2F);
		this.rotor1Blade3.rotateAngleX = 0.2F;
		this.rotor1Blade3.rotateAngleY = 0.0F;
		
		this.rotor1Blade4 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1Blade4.addBox(-42.9F, -4.5F, -4.5F, 40, 1, 6, -0.2F);
		this.rotor1Blade4.rotateAngleX = 0.2F;
		this.rotor1Blade4.rotateAngleY = 1.571F;
		
		
		this.rotor2Blade1 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor2Blade1.addBox(-20.0F, -4.5F, -2.0F, 20, 1, 4, 0);
		
		this.rotor2Blade2 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor2Blade2.addBox(-2.0F, -4.5F, 0.0F, 4, 1, 20, 0);
		
		/*
		this.rotor1DriveShaft = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1DriveShaft.addBox(-3F, -6F, -3F, 6, 12, 6, 0.0F);
		
		this.rotor1Blade1 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1Blade1.addBox(2.9F, -4.5F, -1.5F, 40, 1, 6, 0.0F);
		this.rotor1Blade1.rotateAngleX = -0.2F;
		this.rotor1Blade1.rotateAngleY = 0.0F;
		
		this.rotor1Blade2 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1Blade2.addBox(2.9F, -4.5F, -1.5F, 40, 1, 6, 0.0F);
		this.rotor1Blade2.rotateAngleX = -0.2F;
		this.rotor1Blade2.rotateAngleY = 1.571F;
		
		this.rotor1Blade3 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1Blade3.addBox(-42.9F, -4.5F, -4.5F, 40, 1, 6, 0.0F);
		this.rotor1Blade3.rotateAngleX = 0.2F;
		this.rotor1Blade3.rotateAngleY = 0.0F;
		
		this.rotor1Blade4 = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		this.rotor1Blade4.addBox(-42.9F, -4.5F, -4.5F, 40, 1, 6, 0.0F);
		this.rotor1Blade4.rotateAngleX = 0.2F;
		this.rotor1Blade4.rotateAngleY = 1.571F;
		*/
	}
	
	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		float rotor1Rotation = ((EntityApache)par7Entity).rotor1Rotation;
		//float rotor2Rotation = ((EntityApache)par7Entity).rotor2Rotation;
		
		this.rotor1DriveShaft.rotateAngleY = rotor1Rotation;
		this.rotor1Blade1.rotateAngleY = rotor1Rotation;
		this.rotor1Blade2.rotateAngleY = rotor1Rotation + 1.571F;
		this.rotor1Blade3.rotateAngleY = rotor1Rotation;
		this.rotor1Blade4.rotateAngleY = rotor1Rotation + 1.571F;
		
		//this.rotor2Blade1.rotateAngleX = rotor2Rotation;
	}
	
	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.registerModelBoxes(); //TODO REMOVE THIS FUNCTION
		
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		//main.render(par7);
		
		rotor1DriveShaft.render(par7);
		
		rotor1Blade1.render(par7);
		rotor1Blade2.render(par7);
		rotor1Blade3.render(par7);
		rotor1Blade4.render(par7);
		
		//rotor2Blade1.render(par7);
		//rotor2Blade2.render(par7);
		//rotor2Blade3.render(par7);
		//rotor2Blade4.render(par7);
	}
}
