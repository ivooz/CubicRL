/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.items;

import java.util.Random;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.ItemSlot;

/**
 *
 * @author Ivo
 */
public class Weapon extends EquipableItem {

	private final int lowerDamageBound;
	private final int upperDamageBound;
	private final DamageType damageType;

	public Weapon(DamageType damageType, int lowerDamageBound, int upperDamageBound, String name, Coords2D onCharacterSpriteSheetCoords, Coords2D spriteSheetCoords) {
		super(name, onCharacterSpriteSheetCoords, ItemSlot.WEAPON, spriteSheetCoords);
		this.lowerDamageBound = lowerDamageBound;
		this.upperDamageBound = upperDamageBound;
		this.damageType = damageType;
	}

	public int getDamage() {
		Random random = new Random();
		return lowerDamageBound + random.nextInt(upperDamageBound - lowerDamageBound);
	}

	public int getLowerDamageBound() {
		return lowerDamageBound;
	}

	public int getUpperDamageBound() {
		return upperDamageBound;
	}

	public DamageType getDamageType() {
		return damageType;
	}

}
