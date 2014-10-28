/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.occurrence;

import java.util.ArrayList;
import pl.iz.cubicrl.model.attack.Attack;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.effects.TimedEffect;

/**
 * Occurrences that affect creatures in a visited field somehow. Occurrences
 * with effects should only be added to PenetrableFields or they will no work
 * properly.
 *
 * @author Ivo
 */
public class OccurrenceWithEffects extends Occurrence {

	private final ArrayList<Effect> effects;
	private final ArrayList<Attack> attacks;
	protected boolean actedThisTurn;

	public OccurrenceWithEffects(String name, Coords2D spriteSheetCoordinates) {
		super(name, spriteSheetCoordinates);
		effects = new ArrayList<>();
		attacks = new ArrayList<>();
		actedThisTurn = false;
	}

	/**
	 * To be used in a builder pattern. Each effect will apply to Creature
	 * residing in the field where the Occurrence is in. These must be timed
	 * effects.
	 *
	 * @param effect
	 * @return Occurrence being built
	 */
	public OccurrenceWithEffects addEffect(TimedEffect effect) {
		effects.add(effect);
		return this;
	}

	/**
	 * To be used in a builder pattern
	 *
	 * @param attack
	 * @return
	 */
	public OccurrenceWithEffects addAttack(Attack attack) {
		attacks.add(attack);
		return this;
	}

	@Override
	public void visit(Creature creature) {
		if (!actedThisTurn) {
			actedThisTurn = true;
			effects.forEach(e -> creature.addEffect(e.copy()));
			attacks.forEach(a -> creature.attack(a));
		}
	}

	@Override
	public void visit(PenetrableField field) {
		if (field.hasResident()) {
			visit(field.getResident());
		}
	}

	@Override
	public void nextTurnNotify() {
		super.nextTurnNotify();
		actedThisTurn = false;
	}
}
