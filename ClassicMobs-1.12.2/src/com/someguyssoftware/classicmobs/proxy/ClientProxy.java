/**
 * 
 */
package com.someguyssoftware.classicmobs.proxy;

import com.someguyssoftware.classicmobs.ClassicMobs;
import com.someguyssoftware.classicmobs.client.render.entity.GhoulRenderer;
import com.someguyssoftware.classicmobs.entity.monster.GhoulEntity;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author Mark Gottschling on Jan 24, 2019
 *
 */
@Mod.EventBusSubscriber(modid=ClassicMobs.MODID, value = Side.CLIENT)
public class ClientProxy {

	/**
	 * 
	 */
	public ClientProxy() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void registerRenderers(@SuppressWarnings("rawtypes") final RegistryEvent.Register event) {
		/*
		 *  register tile entity special renderers
		 */
		
		/*
		 *  register the entity render handlers
		 */		
		RenderingRegistry.registerEntityRenderingHandler(GhoulEntity.class, 	
				new GhoulRenderer(
						Minecraft.getMinecraft().getRenderManager()
						));
//						new GhoulModel(),
//						new ResourceLocation(ClassicMobs.MODID + ":textures/entity/mob/ghoul.png")));
	}
	
}
