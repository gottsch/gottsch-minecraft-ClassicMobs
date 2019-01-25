/**
 * 
 */
package com.someguyssoftware.classicmobs.entity.monster;

import java.util.Iterator;
import java.util.List;

import com.someguyssoftware.classicmobs.entity.ai.GhoulAIHeal;
import com.someguyssoftware.classicmobs.entity.ai.GhoulAINearestAttackableTarget;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * 
 * @author Mark Gottschling on Jan 24, 2019
 *
 */
public class GhoulEntity extends EntityMob {

	@CapabilityInject(GhoulCapability.class)
	public static Capability<IGhoulCapability> GHOUL_CAPABILITY = null;

	private static final int MAX_MEAT_STACK_SIZE = 3;
	public static final int MAX_ATTACKS_PER_DAY = 2;

	private final IGhoulCapability ghoulCapability = new GhoulCapability();

	/**
	 * 
	 * @param world
	 */
	public GhoulEntity(World world) {
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new GhoulAIHeal(this));
		this.tasks.addTask(2, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIBreakDoor(this));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		// param4 = checkSight; param5 = nearbyOnly
		// this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
		// EntityPlayer.class, 0, true, true, null));
		// param3 = checkSight; nearbyOnly = false
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] { GhoulEntity.class, EntityZombie.class, EntitySkeleton.class }));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new GhoulAINearestAttackableTarget(this, EntityVillager.class, true));
		this.targetTasks.addTask(4, new GhoulAINearestAttackableTarget(this, EntityCow.class, true));
		this.targetTasks.addTask(4, new GhoulAINearestAttackableTarget(this, EntityHorse.class, true));
		this.targetTasks.addTask(4, new GhoulAINearestAttackableTarget(this, EntitySheep.class, true));
		this.targetTasks.addTask(4, new GhoulAINearestAttackableTarget(this, EntityPig.class, true));
		this.setSize(0.6F, 1.95F);
	}

	/**
	 * Setup ghouls attributes
	 */
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22.0D);
	}

	public static void registerFixes(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, GhoulEntity.class);
	}
	
	/**
	 * Check if the ghoul
	 * 
	 * @return
	 */
//	public boolean hasMeatInventory() {
//		for (int index = 0; index < GhoulExtendedProperties.get(this).getItems().length; index++) {
//			ItemStack stack = GhoulExtendedProperties.get(this).getItems()[index];
//			if (stack != null && stack.stackSize > 0) {
//				Mobs.logger.info("Has meat inventory at index: " + index);
//				return true;
//			}
//		}
//		return false;
//	}

	/**
	 * Locates the first non-empty item stack in the ghoul's inventory and removes
	 * an item.
	 */
//	public void removeFirstMeatFromInventory() {
//		for (int index = 0; index < GhoulExtendedProperties.get(this).getItems().length; index++) {
//			ItemStack stack = GhoulExtendedProperties.get(this).getItems()[index];
//			if (stack != null && stack.stackSize > 0) {
//				Mobs.logger.info("Removing meat at index: " + index);
//				stack.stackSize--;
//				if (stack.stackSize == 0) {
//					Mobs.logger.info("Removing meat stack at index:" + index);
//					GhoulExtendedProperties.get(this).getItems()[index] = null;
//				}
//			}
//		}
//	}

	/**
	 * Add a meat item stack to the ghoul's inventory if available space
	 */
//	public boolean addMeatToInventory(ItemStack stack) {
//		int firstFreeIndex = -1;
//		for (int index = 0; index < GhoulExtendedProperties.get(this).getItems().length; index++) {
//			ItemStack inventoryStack = GhoulExtendedProperties.get(this).getItems()[index];
//			if (inventoryStack != null) {
//				// found a stack of the same kind
//				if (stack.getItem() == inventoryStack.getItem()) {
//					if (inventoryStack.stackSize < MAX_MEAT_STACK_SIZE) {
//						inventoryStack.stackSize = Math.min(MAX_MEAT_STACK_SIZE, inventoryStack.stackSize + stack.stackSize);
//						Mobs.logger.info("Setting meat inventory stack size at [" + index + "] to :" + inventoryStack.stackSize);
//						return true;
//					}
//				}
//			} else if (firstFreeIndex == -1) {
//				firstFreeIndex = index;
//			}
//		}
//
//		// add a new stack to the first free inventory slot
//		if (firstFreeIndex != -1) {
//			stack.stackSize = Math.min(MAX_MEAT_STACK_SIZE, stack.stackSize);
//			GhoulExtendedProperties.get(this).getItems()[firstFreeIndex] = stack;
//			Mobs.logger.info("Adding meat stack to inventory at [" + firstFreeIndex + "] with size of :" + stack.stackSize);
//			return true;
//		}
//
//		return false;
//	}

	/**
	 * 
	 * @param capability
	 * @param facing
	 * @return
	 */
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == GHOUL_CAPABILITY && facing == EnumFacing.UP) {
			return GHOUL_CAPABILITY.cast(this.ghoulCapability);
		}
		return null;
	}

	/**
	 * 
	 * @param capability
	 * @param facing
	 * @return
	 */
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == GHOUL_CAPABILITY && facing == EnumFacing.UP) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param num
	 */
	public void addAttacks(int num) {

	}

	/**
	 * 
	 * @return
	 */
	public int getAttacks() {
		int a = 0;
		if (hasCapability(GHOUL_CAPABILITY, EnumFacing.UP)) {
			a = getCapability(GHOUL_CAPABILITY, EnumFacing.UP).getAttacks();
		}
		return a;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getReset() {
		return false;
	}

	/**
	 * 
	 * @param flag
	 */
	public void setReset(boolean flag) {
	}

	/**
	 * 
	 * @param attacks
	 */
	public void setAttacks(int attacks) {
		// TODO update the Capability
	}

	/**
	 * Ghouls cannot pick up loot in the default manner - this functionality is
	 * overridden in onLivingUpdate()
	 */
	@Override
	public boolean canPickUpLoot() {
		return false;
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	/**
	 * Returns the current armor value as determined by a call to
	 * InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue() {
		int i = super.getTotalArmorValue() + 2;

		if (i > 20) {
			i = 20;
		}
		return i;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required.
	 * For example, zombies, skeletons, wights, shades use this to react to sunlight
	 * and start to burn.
	 */
	@Override
	public void onLivingUpdate() {

		/*
		 * Reset Attack count Algorithm
		 */
		// if daytime on the client side
		if (!this.getEntityWorld().isRemote) {
			if (this.getEntityWorld().isDaytime()) {
				// if the flag hasn't been set yet
				if (!getReset()) {
					setAttacks(0);
					setReset(true);
				}
			} else {
				setReset(false);
			}
		}

		/*
		 * Pickup Meat(Loot) Algorithm
		 */
		// this.worldObj.theProfiler.startSection("looting");

		// if entity is not dead and on client-side
		if (!this.getEntityWorld().isRemote && !this.dead) {
			// get a list of all item entities within at 3x3 cube around mob
			// TODO should use grow here and not expand
			List list = this.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(1.0D, 1.0D, 1.0D));
			Iterator iterator = list.iterator();

			// process each item
			while (iterator.hasNext()) {
				EntityItem entityItem = (EntityItem) iterator.next();
				// only pickup items: rotten flesh, porkchop, beef, mutton
				if (entityItem.getItem() != null) {
					Item item = entityItem.getItem().getItem();
					// if the items is one of the meats
					// TODO add the cooked meats
					if (item == Items.ROTTEN_FLESH || item == Items.BEEF || item == Items.MUTTON|| item == Items.PORKCHOP || item == Items.CHICKEN) {
						// Mobs.logger.info("Picking up item...");
						boolean meatAdded = false; //addMeatToInventory(entityItem.getItem());
						if (!meatAdded) {
							// TODO throw back
						}
						// handle on pickup
						this.onItemPickup(entityItem, 1);
						entityItem.setDead();
					}
				}
			}
		}

		// this.worldObj.theProfiler.endSection();

		super.onLivingUpdate();
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
   protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}
	
	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}

	@Override
	protected void playHurtSound(DamageSource source) {
		this.playSound(getHurtSound(source), 0.15F, 0.8F);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
	}
}
