package com.someguyssoftware.classicmobs.entity.monster;

public interface IGhoulCapability {

	/**
	 * @return the attacks
	 */
	int getAttacks();

	/**
	 * @param attacks the attacks to set
	 */
	void setAttacks(int attacks);

	void addAttack(int add);

}