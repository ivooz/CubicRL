/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.creature;

import pl.iz.cubicrl.model.items.ItemHolder;
import java.util.ArrayList;
import java.util.HashMap;
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

	public HumanoidCreature(String name, int[] attri, int[] sklls, int[] lifeStatsLimits, int[] rsistancs, int[] secondaries) {
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
		return getEffectiveStat(Attribute.DEXTERITY) * 
			Integer.parseInt(PropertyLoader.getInstance().loadProperty("accuracyPerDex"));
	}

}
