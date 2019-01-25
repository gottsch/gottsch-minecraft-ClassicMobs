/**
 * 
 */
package com.someguyssoftware.classicmobs.entity;

import com.someguyssoftware.classicmobs.ClassicMobs;
import com.someguyssoftware.classicmobs.entity.monster.GhoulEntity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * @author Mark Gottschling on Jan 24, 2019
 *
 */
public class ClassicMobsEntities {

	/**
	 * 
	 */
	public ClassicMobsEntities() {
		// TODO Auto-generated constructor stub
	}

	@Mod.EventBusSubscriber(modid = ClassicMobs.MODID)
	public static class RegistrationHandler {
		/**
		 * 
		 * @param event
		 */
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
			int ID = 0;
			// register the entities
			EntityEntry entry = EntityEntryBuilder.create()
				    .entity(GhoulEntity.class)
				    .id(new ResourceLocation("classicmobs:ghoul"), ID++)
				    .name("ghoul")
				    .egg(0xEEDBB7, 0xCAEEB7)
				    .tracker(30, 20, false)
				    .spawn(EnumCreatureType.MONSTER, 10, 1, 2, ForgeRegistries.BIOMES.getValues())
				    .build();
			event.getRegistry().register(entry);	
			
			// add data fixers
			DataFixer dataFixer = new DataFixer(1343);
	        dataFixer = new net.minecraftforge.common.util.CompoundDataFixer(dataFixer);
			GhoulEntity.registerFixes(dataFixer);
			
			// TODO add other entities here
		}
	}
}
