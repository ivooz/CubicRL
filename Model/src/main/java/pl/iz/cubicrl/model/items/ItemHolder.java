/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import pl.iz.cubicrl.model.enums.ItemSlot;

/**
 * Manages Creature's items
 *
 * @author Ivo
 */
public class ItemHolder implements Serializable {

	private final HashMap<ItemSlot, EquipableItem> equippedItems;
	private final ArrayList<Item> inventory;
	private EquipableItem leftHand;
	private EquipableItem rightHand;

	public ItemHolder() {
		equippedItems = new HashMap<>();
		inventory = new ArrayList<>();
	}

	public EquipableItem getEquippedItem(ItemSlot itemSlot) {
		return equippedItems.get(itemSlot);
	}

	public int getStatModification(Enum e) {
		return equippedItems.values().parallelStream().mapToInt(i -> i.getStatModification(e)).sum();
	}

	/**
	 * Puts item into the slot it fits and removed from inventory. 
	 * If there is already an item in that slot, it will be moved to inventory.
	 * @param equipableItem
	 */
	public void equip(EquipableItem equipableItem) {
		EquipableItem currentlyEquippedItem = equippedItems.get(equipableItem.getItemSlot());
		if (currentlyEquippedItem != null) {
			inventory.add(currentlyEquippedItem);
		}
		inventory.remove(equipableItem);
		equippedItems.put(equipableItem.getItemSlot(), equipableItem);
	}

	public void addItemToInventory(Item item) {
		inventory.add(item);
	}

	public void removeItemFromInventory(Item item) {
		inventory.remove(item);
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public EquipableItem getLeftHand() {
		return leftHand;
	}

	public EquipableItem getRightHand() {
		return rightHand;
	}

	public HashMap<ItemSlot, EquipableItem> getEquippedItems() {
		return equippedItems; 
	}

	public void unequip(EquipableItem testItem) {
		equippedItems.remove(testItem.getItemSlot());
		inventory.add(testItem);
	}

	public Weapon getWeapon() {
		return (Weapon) equippedItems.get(ItemSlot.WEAPON);
	}

}
