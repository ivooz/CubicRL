/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.effects;

import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.enums.DamageType;

/**
 * TimedEffect that is also damagin the creature each turn.
 *
 * @author Ivo
 */
public class DamagingTimedEffect extends TimedEffect {

	private final DamageType damageType;
	private final int damagePerTurn;

	public DamagingTimedEffect(String name, DamageType damageType, int damagePerTurn, int effectDuration) {
		super(name, effectDuration);
		this.damageType = damageType;
		this.damagePerTurn = damagePerTurn;
	}

	/**
	 * Creature is dealt damage every time it is visited by this effect.
	 *
	 * @param creature
	 */
	@Override
	public void visit(Creature creature) {
		super.visit(creature);
		creature.takeDamage(damageType, damagePerTurn);
	}

}
