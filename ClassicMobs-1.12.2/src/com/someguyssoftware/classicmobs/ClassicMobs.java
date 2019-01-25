/**
 * 
 */
package com.someguyssoftware.classicmobs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.someguyssoftware.classicmobs.config.GeneralConfig;
import com.someguyssoftware.classicmobs.entity.monster.GhoulCapability;
import com.someguyssoftware.classicmobs.entity.monster.GhoulStorage;
import com.someguyssoftware.classicmobs.entity.monster.IGhoulCapability;
import com.someguyssoftware.classicmobs.eventhandler.EntityEventHandler;
import com.someguyssoftware.gottschcore.annotation.Credits;
import com.someguyssoftware.gottschcore.command.ShowVersionCommand;
import com.someguyssoftware.gottschcore.config.IConfig;
import com.someguyssoftware.gottschcore.config.ILoggerConfig;
import com.someguyssoftware.gottschcore.mod.AbstractMod;
import com.someguyssoftware.gottschcore.mod.IMod;
import com.someguyssoftware.gottschcore.version.BuildVersion;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 
 * @author Mark Gottschling on Jan 23, 2019
 *
 */
@Mod(
		modid=ClassicMobs.MODID,
		name=ClassicMobs.NAME,
		version=ClassicMobs.VERSION,
		dependencies="required-after:gottschcore@[1.7.0,)",
		acceptedMinecraftVersions = "[1.12.2]",
		updateJSON = ClassicMobs.UPDATE_JSON_URL
		)
@Credits(values={
		"ClassicMobs was first developed by Mark Gottschling on March 3, 2016."
		})
public class ClassicMobs extends AbstractMod {

	// constants
	public static final String MODID = "classicmobs";
	protected static final String NAME = "Classic Mobs";
	protected static final String VERSION = "0.0.1";
	public static final String UPDATE_JSON_URL = "https://raw.githubusercontent.com/gottsch/gottsch-minecraft-ClassicMobs/master/Treasure2-1.12.2/update.json";

	private static final String VERSION_URL = "";
	private static final BuildVersion MINECRAFT_VERSION = new BuildVersion(1, 12, 2);
	
	// latest version
	private static BuildVersion latestVersion;
	
	// logger
	public static Logger logger = LogManager.getLogger(ClassicMobs.NAME);
	
	public static GeneralConfig config;
	
	@Instance(value=ClassicMobs.MODID)
	public static ClassicMobs instance;
	
	/*
	 *  ClassicMobs Creative Tab
	 *  Must be initialized <b>before</b> any registry events so that it is available to assign to blocks and items.
	 */
//	public static CreativeTabs TREASURE_TAB = new CreativeTabs(CreativeTabs.getNextID(), ClassicMobs.MODID + ":" + GeneralConfig.TREASURE_TAB_ID) {
//		@SideOnly(Side.CLIENT)
//		public ItemStack getTabIconItem() {
//			return new ItemStack(TreasureItems.TREASURE_TAB, 1);
//		}
//	};

	/**
	 * 
	 */
	public ClassicMobs() {
	}
	
	/**
	 * 
	 * @param event
	 */
	@Override
	@EventHandler
	public void preInt(FMLPreInitializationEvent event) {
		super.preInt(event);
		
		// register additional events
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());

		// create and load the config files
		config = new GeneralConfig(this, event.getModConfigurationDirectory(), ClassicMobs.MODID, "general.cfg");
		
		// configure logging
		addRollingFileAppenderToLogger(ClassicMobs.NAME, ClassicMobs.NAME + "Appender", (ILoggerConfig) getConfig());
		
		// register the GUI handler
//		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		// register capabilities
		CapabilityManager.INSTANCE.register(IGhoulCapability.class, new GhoulStorage(), GhoulCapability::new);
	}
	
	/**
	 * 
	 * @param event
	 */
	@EventHandler
    public void serverStarted(FMLServerStartingEvent event) {
		if (!getConfig().isModEnabled()) return;
		
    	// add a show version command
    	event.registerServerCommand(new ShowVersionCommand(this));
    	
		/*
		 *  register additional commands
		 */

    }
	
	/**
	 * 
	 */
	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// don't process is mod is disabled
		if (!getConfig().isModEnabled()) return;
		
		super.init(event);

		// register world generators
	}
	
	/**
	 * 
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (!getConfig().isModEnabled()) return;		

		// perform any post init
		super.postInit(event);
		
		// register to the ore dictionary
		
	}
		
	/* (non-Javadoc)
	 * @see com.someguyssoftware.gottschcore.mod.IMod#getConfig()
	 */
	@Override
	public IConfig getConfig() {
		return config;
	}

	/* (non-Javadoc)
	 * @see com.someguyssoftware.gottschcore.mod.IMod#getMinecraftVersion()
	 */
	@Override
	public BuildVersion getMinecraftVersion() {
		return ClassicMobs.MINECRAFT_VERSION;
	}

	/* (non-Javadoc)
	 * @see com.someguyssoftware.gottschcore.mod.IMod#getVerisionURL()
	 */
	@Override
	public String getVerisionURL() {
		return ClassicMobs.VERSION_URL;
	}

	/* (non-Javadoc)
	 * @see com.someguyssoftware.gottschcore.mod.IMod#getName()
	 */
	@Override
	public String getName() {
		return ClassicMobs.NAME;
	}

	/* (non-Javadoc)
	 * @see com.someguyssoftware.gottschcore.mod.IMod#getId()
	 */
	@Override
	public String getId() {
		return ClassicMobs.MODID;
	}

	/* (non-Javadoc)
	 * @see com.someguyssoftware.gottschcore.mod.AbstractMod#getInstance()
	 */
	@Override
	public IMod getInstance() {
		return ClassicMobs.instance;
	}

	/* (non-Javadoc)
	 * @see com.someguyssoftware.gottschcore.mod.AbstractMod#getVersion()
	 */
	@Override
	public String getVersion() {
		return ClassicMobs.VERSION;
	}

	@Override
	public BuildVersion getModLatestVersion() {
		return latestVersion;
	}

	@Override
	public void setModLatestVersion(BuildVersion version) {
		ClassicMobs.latestVersion = version;		
	}
	
	@Override
	public String getUpdateURL() {
		return ClassicMobs.UPDATE_JSON_URL;
	}

}
