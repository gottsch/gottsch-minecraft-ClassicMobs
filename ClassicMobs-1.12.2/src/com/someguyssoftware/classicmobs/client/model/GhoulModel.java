package com.someguyssoftware.classicmobs.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * 
 * @author Mark Gottschling on Mar 3, 2016
 *
 */
public class GhoulModel extends ModelZombie {

	/**
	 * Ghoul constructor
	 */
	public GhoulModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		
		float scale = 0.0F;
		float rotation = 0.0F;
		
//		// override the head
//        this.bipedHead = new ModelRenderer(this, 0, 0);
//        this.bipedHead.addBox(-4.0F, -7.0F, -4.0F, 8, 7, 8, scale);
//        this.bipedHead.setRotationPoint(0.0F, 0.0F, -2.0F);
//        
//        // override the body rotation
//        this.bipedBody = new ModelRenderer(this, 16, 16);
//        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
//        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        
		// override the right arm (made skinnier)
//		this.bipedRightArm = new ModelRenderer(this, 40, 16);
//		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 6, 4);
//		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
//		
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 3, 12, 3, scale);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        
		// override the left arm
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 3);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
//    
//        // override the right leg
//        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
//        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
//        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 3.0F);
//        
//        // override the left leg
//        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
//        this.bipedLeftLeg.mirror = true;
//        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
//        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 3.5F);
	}
	
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        // call the base rotation angles
    	super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
 
    	float f6 = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f7 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        
        // override body rotation
        setRotation(this.bipedBody, 0.25F, 0F, 0F);
        
        // override left arm rotation
        //setRotation(this.bipedLeftArm, 0.0F, 0.0F, -0.25F);
        this.bipedLeftArm.rotateAngleZ = -0.25F;
        this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
        this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F);
        this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        
        // set legs rotation points
        this.bipedRightLeg.rotationPointZ = 3.0F;
        this.bipedLeftLeg.rotationPointZ = 3.5F;
        this.bipedRightLeg.rotationPointY = 12.0F;
        this.bipedLeftLeg.rotationPointY = 12.0F;
    }
    
	/**
	 * 
	 * @param model
	 * @param x
	 * @param y
	 * @param z
	 */
	private void setRotation(ModelRenderer model, float x, float y, float z)	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
