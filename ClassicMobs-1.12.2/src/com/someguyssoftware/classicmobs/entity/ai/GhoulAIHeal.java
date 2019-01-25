/**
 * 
 */
package com.someguyssoftware.classicmobs.entity.ai;


import com.someguyssoftware.classicmobs.entity.monster.GhoulEntity;

import net.minecraft.entity.ai.EntityAIBase;

/**
 * TODO move into GhoulEntity as an inner Class
 * @author Mark Gottschling on Mar 4, 2016
 *
 */
public class GhoulAIHeal extends EntityAIBase {

	private GhoulEntity ghoul;
	
	/**
	 * 
	 * @param ghoul
	 */
	public GhoulAIHeal(GhoulEntity ghoul) {
		setGhoul(ghoul);
       // this.setMutexBits(8); // TODO set to whatever attack/walk ?? is.. can't fight at same time as healing
	}
	
	/* (non-Javadoc)
	 * @see net.minecraft.entity.ai.EntityAIBase#shouldExecute()
	 */
	@Override
	public boolean shouldExecute() {
        if (!getGhoul().isEntityAlive()) {
            return false;
        }
        else if (!getGhoul().onGround) {
            return false;
        }
        else if (getGhoul().velocityChanged) {
            return false;
        }
        else {
        	// check health; if less than 3/4 && has some meat in inventory
        	if (getGhoul().getHealth() < (0.75F * getGhoul().getMaxHealth())) {
 //       		Mobs.logger.info("Attempting to active GhoulAIHeal because health is at. " + getGhoul().getHealth());
//        		if (getGhoul().hasMeatInventory()) {
//            		return true;       			
//        		}
        	}
        }
        return false;
	}
	
    /**
     * Execute a one shot task or start executing a continuous task
     */
	@Override
    public void startExecuting() {
		// heal 2 "hearts" (or 4 health points)
		getGhoul().setHealth(getGhoul().getHealth() + 4.0F);
//		getGhoul().removeFirstMeatFromInventory();
    }

	public GhoulEntity getGhoul() {
		return ghoul;
	}

	public void setGhoul(GhoulEntity ghoul) {
		this.ghoul = ghoul;
	}
}
