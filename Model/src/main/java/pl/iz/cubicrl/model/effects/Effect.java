/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.effects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import pl.iz.cubicrl.model.api.VisitorAdapter;

/**
 * Abstractaion of various stat-modifying effects that can affect Creatures.
 * Creature can have multiple Effects affecting it - it's effect on Creature's
 * stats can consist of multiple modifiers.
 *
 * @author Ivo
 */
public class Effect extends VisitorAdapter implements Cloneable, Serializable {

	private final ArrayList<Pair<Enum, Integer>> modifierList;
	private final String name;
	boolean expired;

	/**
	 *
	 * @param name must be unique, identifies the type
	 */
	public Effect(String name) {
		this.name = name;
		modifierList = new ArrayList<>();
		expired = false;
	}

	/**
	 * Adds modifier to Effect. To be used in a Builder or a Factory method.
	 *
	 * @param modifier to stats, key is the enum stat, modifier can be
	 * positive or negative
	 * @return
	 */
	public Effect addModifier(Pair<Enum, Integer> modifier) {
		modifierList.add(modifier);
		return this;
	}

	/**
	 * Returns Effect's net influence on a stat in parameter.
	 *
	 * @param e
	 * @return
	 */
	public int getEffectiveModifiersOf(Enum e) {
		return modifierList.stream().parallel().filter(p -> p.getKey() == e)
			.mapToInt(p -> p.getValue()).sum();
	}

	public ArrayList<Pair<Enum, Integer>> getModifierList() {
		return modifierList;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Effect copy() {
		try {
			return (Effect) super.clone();
		} catch (CloneNotSupportedException ex) {
			Logger.getLogger(Effect.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public String getName() {
		return name;
	}

}
