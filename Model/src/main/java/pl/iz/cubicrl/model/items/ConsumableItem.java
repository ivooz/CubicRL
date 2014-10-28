/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.items;

import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.creature.Creature;

/**
 *
 * @author Ivo
 */
public class ConsumableItem extends ItemWithEffects {

	public ConsumableItem(String name, Coords2D spriteSheetCoords) {
		super(name, spriteSheetCoords);
	}

	/**
	 * Adds all the item effects via visitor pattern. Effects can be timed
	 * or permanent.
	 *
	 * @param creature
	 */
	@Override
	public void visit(Creature creature) {
		effects.stream().parallel().forEach(e -> creature.addEffect(e));
	}

}
