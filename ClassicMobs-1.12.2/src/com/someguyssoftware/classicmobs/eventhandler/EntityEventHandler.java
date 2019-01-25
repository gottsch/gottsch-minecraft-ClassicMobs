/**
 * 
 */
package com.someguyssoftware.classicmobs.eventhandler;

import com.someguyssoftware.classicmobs.entity.monster.GhoulEntity;
import com.someguyssoftware.gottschcore.world.WorldInfo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author Mark Gottschling on Mar 4, 2016
 *
 */
public class EntityEventHandler {

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		// only load from server
		if (WorldInfo.isServerSide(event.getEntity().getEntityWorld())) {
//			Entity entity = event.entity;
//			if (entity instanceof GhoulEntity) {
//				GhoulEntity hermit = (GhoulEntity)entity;
//				GhoulExtendedProperties props = GhoulExtendedProperties.get(hermit);
//			}
		}
	}
	
	/**
	 * Register all custome entities' extended properties
	 * @param event
	 */
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
//		if (event.getEntity() instanceof GhoulEntity && GhoulExtendedProperties.get((GhoulEntity) event.getEntity()) == null) {
//			ClassicMobs.logger.info("Registering Ghoul Extended Props with Ghoul");
//			// register the ghoul's extended properties
//			GhoulExtendedProperties.register((GhoulEntity)event.geEntity());
//		}
//		else if (event.getEntity() instanceof WightEntity && WightExtendedProperties.get((WightEntity) event.getEntity()) == null) {
//			Mobs.logger.info("Registering Wight Extended Props with Wight");
//			// register the wight's extended properties
//			WightExtendedProperties.register((WightEntity)event.getEntity());			
//		}
//		else if (event.getEntity() instanceof ShadeEntity && ShadeExtendedProperties.get((ShadeEntity) event.getEntity()) == null) {
//			Mobs.logger.info("Registering Shade Extended Props with Wight");
//			// register the shade's extended properties
//			ShadeExtendedProperties.register((ShadeEntity)event.getEntity());		
//		}
	}
	
	/**
	 * Perform checks when an entity dies
	 * @param event
	 */
	@SubscribeEvent
	public void onDeathEvent(LivingDeathEvent event) {
		
		if (event.getSource().getTrueSource() instanceof GhoulEntity && !(event.getEntity() instanceof EntityPlayer)) {
			// get the attacking ghoul
			GhoulEntity ghoul = (GhoulEntity) event.getSource().getTrueSource();
			// increment the attack count
			ghoul.addAttacks(1);
		}
//		
//		if (event.entity instanceof ShadeEntity) {
//			// TODO on death of Shade, reset ligh levels to original
//			Mobs.logger.info("Shade died.");
//		}
	}
}
