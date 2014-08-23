/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.core;

import java.util.ArrayList;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.enums.DamageType;

/**
 *
 * @author Ivo
 */
public class Attack {
	private final int accuracy;
	private final int damage;
	private final DamageType damageType;
	private final ArrayList<Effect> effects;

	public Attack(int accuracy, int damage, DamageType damageType) {
		this.accuracy = accuracy;
		this.damage = damage;
		this.damageType = damageType;
		effects = new ArrayList<>();
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getDamage() {
		return damage;
	}

	public DamageType getDamageType() {
		return damageType;
	}
	/**
	 * To be used in a builder pattern
	 * @param effect
	 * @return 
	 */
	public Attack addEffect(Effect effect) {
		effects.add(effect);
		return this;
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}
	
	
	
}
