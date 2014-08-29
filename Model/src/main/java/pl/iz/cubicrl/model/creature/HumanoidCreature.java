/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.creature;

import pl.iz.cubicrl.model.items.ItemHolder;
import java.util.ArrayList;
import java.util.HashMap;
import javax.validation.constraints.NotNull;
import pl.iz.cubicrl.model.attack.Attack;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.ItemSlot;
import pl.iz.cubicrl.model.items.EquipableItem;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.items.Weapon;
import pl.iz.cubicrl.model.util.PropertyLoader;

/**
 *
 * @author Ivo
 */
public class HumanoidCreature extends Creature {

	private final ItemHolder itemHolder;

	/**
	 * Initial values of statistics should be passed as parameters. Values
	 * in array corresponds to the ordering of Enum values. Humanoid's
	 * damage is computed from its stats and weapons. They can also equip
	 * items and have an inventory.
	 *
	 * @param name of the creature, must be unique
	 * @param attri initial values of attributes
	 * [STRNGTH,DXTRT,SPD,CNST,INTL,CHA]
	 * @param sklls initial values of skills
	 * [DDG,THRWNG,PRJCTL,SPCH,MATH,SNEAK]
	 * @param lifeStatsLimits initial values of lifeStats
	 * [MAXHP,MAXSAN,MAXHUNGER,MAXTHRST]
	 * @param rsistancs initial values of resistances
	 * [BLNT,PRCNG,SLSHNG,HT,CLD,CHMCL,PSCHC]
	 * @param secondaries value of secondary stats [AC]
	 */
	public HumanoidCreature(@NotNull String name,
				@NotNull int[] attri,
				@NotNull int[] sklls,
				@NotNull int[] lifeStatsLimits,
				@NotNull int[] rsistancs,
				@NotNull int[] secondaries) {
		super(name, attri, sklls, lifeStatsLimits, rsistancs, secondaries, null);
		itemHolder = new ItemHolder();
	}
	
	@Override
	public int getEffectiveStat(Enum e) {
		return super.getEffectiveStat(e) + itemHolder.getStatModification(e);
	}

	public EquipableItem getEquippedItem(ItemSlot itemSlot) {
		return itemHolder.getEquippedItem(itemSlot);
	}

	public HashMap<ItemSlot, EquipableItem> getEquippedItems() {
		return itemHolder.getEquippedItems();
	}

	public void equip(EquipableItem equipableItem) {
		itemHolder.equip(equipableItem);
	}

	public ArrayList<Item> getInventory() {
		return itemHolder.getInventory();
	}

	public void addItemToInventory(Item item) {
		itemHolder.addItemToInventory(item);
	}

	public void removeItemFromInventory(Item item) {
		itemHolder.removeItemFromInventory(item);
	}

	public void unequip(EquipableItem testItem) {
		itemHolder.unequip(testItem);
	}

	@Override
	protected Attack computeAttack() {
		Weapon weapon = itemHolder.getWeapon();
		int damage = getEffectiveStat(Attribute.STRENGTH);
		if (weapon != null) {
			damage += weapon.getDamage();
		}
		DamageType damageType = weapon.getDamageType();
		Attack attack = new Attack(computeAccuracy(), damage, damageType);
		weapon.getEffects().forEach(e -> attack.addEffect(e));
		return attack;
	}

	private int computeAccuracy() {
		return getEffectiveStat(Attribute.DEXTERITY)
			* Integer.parseInt(PropertyLoader.getInstance().loadProperty("accuracyPerDex"));
	}

}
