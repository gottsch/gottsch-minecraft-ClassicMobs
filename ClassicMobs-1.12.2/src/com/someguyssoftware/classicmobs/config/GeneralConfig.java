/**
 * 
 */
package com.someguyssoftware.classicmobs.config;

import java.io.File;

import com.someguyssoftware.gottschcore.config.AbstractConfig;
import com.someguyssoftware.gottschcore.mod.IMod;

import net.minecraftforge.common.config.Configuration;

/**
 * 
 * @author Mark Gottschling onDec 22, 2017
 *
 */
public class GeneralConfig extends AbstractConfig {

	/*
	 *  IDs
	 */

	/*
	 * Enablements
	 */
	public static Boolean enableMobs;
	public static Boolean enableGhoul;

			
	/**
	 * @param mod
	 * @param configDir
	 * @param modDir
	 * @param filename
	 */
	public GeneralConfig(IMod mod, File configDir, String modDir, String filename) {
		super(mod, configDir, modDir, filename);
	}

	/**
	 * 
	 */
	@Override
	public Configuration load(File file) {
		// load the config file
		Configuration config = super.load(file);
		
		// add mod specific settings here
        // enable mobs settings
        config.setCategoryComment("03-mobs", "General Mobs! mod properties.");        
        enableMobs = config.getBoolean("enableMobs", "03-mobs", true, "Enable/disable the generation of mobs.");
        enableGhoul = config.getBoolean("enableGhoul", "03-mobs", true, "Enable/disable the ghoul mob.");
//        enableWight = config.getBoolean("enableWight", "03-mobs", true, "Enable/disable the wight mob.");
//        enableShade = config.getBoolean("enableShade", "03-mobs", true, "Enable/disable the shade mob.");
        
        // the the default values
       if(config.hasChanged()) {
    	   config.save();
       }
       
		return config;
	}
}
