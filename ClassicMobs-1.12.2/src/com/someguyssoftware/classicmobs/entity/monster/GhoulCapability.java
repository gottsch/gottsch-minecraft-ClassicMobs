/**
 * 
 */
package com.someguyssoftware.classicmobs.entity.monster;

/**
 * @author Mark Gottschling on Jan 23, 2019
 *
 */
public class GhoulCapability implements IGhoulCapability {
	// the current count of animal attack
	private int attacks;
	
	/**
	 * 
	 */
	public GhoulCapability() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.someguyssoftware.classicmobs.entity.monster.IGhoulCapability#getAttacks()
	 */
	@Override
	public int getAttacks() {
		return attacks;
	}

	/* (non-Javadoc)
	 * @see com.someguyssoftware.classicmobs.entity.monster.IGhoulCapability#setAttacks(int)
	 */
	@Override
	public void setAttacks(int attacks) {
		this.attacks = attacks;
	}
	
	/**
	 * 
	 * @param add
	 */
	@Override
	public void addAttack(int add) {
		this.attacks += add;
	}

}
