package com.someguyssoftware.classicmobs.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.someguyssoftware.classicmobs.ClassicMobs;
import com.someguyssoftware.classicmobs.client.model.GhoulModel;
import com.someguyssoftware.classicmobs.entity.monster.GhoulEntity;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *  Note: Have to extend RenderBiped in order to display held items.
 * @author Mark Gottschling on Mar 3, 2016
 *
 */
@SideOnly(Side.CLIENT)
public class GhoulRenderer extends RenderBiped<GhoulEntity> {

	private static final ResourceLocation mobTexture = new ResourceLocation(ClassicMobs.MODID + ":textures/entity/ghoul.png");
	
	/**
	 * 
	 * @param renderManager
	 */
    public GhoulRenderer(RenderManager renderManager) {
     	//super(renderManager, new GhoulModel(0.0F, true), 0.5F); // super.param3 = shadowSize
    	super(renderManager, new GhoulModel(), 0.5F);
    }    

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(GhoulEntity entity, float partialTick) {
    	// make the warrior slightly bigger than standard skeleton 
    	GL11.glScalef(1F, 1F, 1F);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(GhoulEntity entity) {
    	return mobTexture;
    }

}
