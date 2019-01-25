package com.someguyssoftware.classicmobs.entity.ai;


import com.someguyssoftware.classicmobs.entity.monster.GhoulEntity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

/**
 * 
 * @author Mark Gottschling on Mar 5, 2016
 *
 */
public class GhoulAINearestAttackableTarget extends EntityAINearestAttackableTarget {

	private GhoulEntity ghoul;
	
	/**
	 * 
	 * @param entity
	 * @param entityClass
	 * @param checkSight
	 */
	public GhoulAINearestAttackableTarget(EntityCreature entity, Class entityClass, boolean checkSight) {
		super(entity, entityClass, checkSight);
		setGhoul((GhoulEntity) entity);
	}

	/**
	 * 
	 */
	@Override
	public boolean shouldExecute() {
		if (getGhoul().getAttacks() >= GhoulEntity.MAX_ATTACKS_PER_DAY) {
			return false;
		}
		return super.shouldExecute();
	}

	/**
	 * @return the ghoul
	 */
	public GhoulEntity getGhoul() {
		return ghoul;
	}

	/**
	 * @param ghoul the ghoul to set
	 */
	public void setGhoul(GhoulEntity ghoul) {
		this.ghoul = ghoul;
	}
}
