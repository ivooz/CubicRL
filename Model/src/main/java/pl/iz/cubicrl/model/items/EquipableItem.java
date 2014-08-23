/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.items;

import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.enums.ItemSlot;

/**
 *
 * @author Ivo
 */
public class EquipableItem extends ItemWithEffects {

	private final ItemSlot itemSlot;
	private final Coords2D onCharacterSpirteSheetCoords;

	/**
	 *
	 * @param name
	 * @param spriteSheetCoords
	 * @param itemSlot
	 * @param onCharacterSpriteSheetCoords to be displayed on character model
	 */
	public EquipableItem(String name, Coords2D onCharacterSpriteSheetCoords,
		ItemSlot itemSlot, Coords2D spriteSheetCoords) {
		super(name, spriteSheetCoords);
		this.itemSlot = itemSlot;
		this.onCharacterSpirteSheetCoords = onCharacterSpriteSheetCoords;
	}

	public ItemSlot getItemSlot() {
		return itemSlot;
	}

	public Coords2D getOnCCoords2DeSheetCoords() {
		return onCharacterSpirteSheetCoords;
	}
}
