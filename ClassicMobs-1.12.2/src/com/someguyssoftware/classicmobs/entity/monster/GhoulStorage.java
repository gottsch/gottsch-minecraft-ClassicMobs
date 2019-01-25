/**
 * 
 */
package com.someguyssoftware.classicmobs.entity.monster;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * @author Mark Gottschling on Jan 23, 2019
 *
 */
public class GhoulStorage implements Capability.IStorage<IGhoulCapability> {
	private static final String ATTACKS = "attacks";
	
	/**
	 * 
	 */
	public GhoulStorage() {
	}

	@Override
	public NBTBase writeNBT(Capability<IGhoulCapability> capability, IGhoulCapability instance, EnumFacing side) {
        final NBTTagCompound tag = new NBTTagCompound();           
        tag.setInteger(ATTACKS, instance.getAttacks());          
        return tag;
	}

	@Override
	public void readNBT(Capability<IGhoulCapability> capability, IGhoulCapability instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setAttacks(tag.getInteger(ATTACKS));
	}

}
