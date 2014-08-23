/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.occurence;

import java.util.ArrayList;
import pl.iz.cubicrl.model.core.Attack;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.effects.TimedEffect;

/**
 * Occurences that affect creatures in a visited field somehow. Occurences
 * with effects should only be added to PenetrableFields or they will no work 
 * properly.
 *
 * @author Ivo
 */
public class OccurenceWithEffects extends Occurence {

	private final ArrayList<Effect> effects;
	private final ArrayList<Attack> attacks;

	public OccurenceWithEffects(String name, Coords2D spriteSheetCoordinates) {
		super(name, spriteSheetCoordinates);
		effects = new ArrayList<>();
		attacks = new ArrayList<>();
	}

	/**
	 * To be used in a builder pattern. Each effect will apply to Creature
	 * residing in the field where the Occurence is in. These must be timed
	 * effects.
	 *
	 * @param effect
	 * @return Occurence being built
	 */
	public OccurenceWithEffects addEffect(TimedEffect effect) {
		effects.add(effect);
		return this;
	}

	/**
	 * To be used in a builder pattern
	 *
	 * @param attack
	 * @return
	 */
	public OccurenceWithEffects addAttack(Attack attack) {
		attacks.add(attack);
		return this;
	}
	
	@Override
	public void visit(PenetrableField field) {
		if (field.hasResident()) {
			effects.forEach(e -> field.getResident().addEffect(e.copy()));
			attacks.forEach(a -> field.getResident().attack(a));
		}
	}
}
