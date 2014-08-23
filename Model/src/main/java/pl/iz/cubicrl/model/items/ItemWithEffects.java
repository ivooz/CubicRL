/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.items;

import java.util.ArrayList;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.effects.Effect;

/**
 * Items that can influence player stats by consuming/wearing etc. The stat
 * change manifests in form of adding an Effect, be it permanent or timed.
 *
 * @author Ivo
 */
public class ItemWithEffects extends Item {

	ArrayList<Effect> effects;

	public ItemWithEffects(String name, Coords2D spriteSheetCoords) {
		super(name, spriteSheetCoords);
		effects = new ArrayList<>();
	}

	/**
	 * To be used in builder/factory pattern
	 *
	 * @param effect
	 * @return item being constructed
	 */
	public ItemWithEffects addEffect(Effect effect) {
		effects.add(effect);
		return this;
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}

	public int getStatModification(Enum e) {
		return effects.parallelStream().mapToInt(
			eff -> eff.getEffectiveModifiersOf(e)).sum();
	}

}
