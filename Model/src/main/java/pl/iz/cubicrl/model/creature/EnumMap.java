/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.creature;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Collection holding chosenumerated statistic and allowing for easy access to
 * associated value.
 *
 * @author Ivo
 * @param <E> type of enumerated statistic
 */
public class EnumMap<E extends Enum<E>> implements Serializable {

	/**
	 * Maps enumerated statistics to their values
	 */
	Map<E, Integer> attributeMap;

	/**
	 * Enum type should be specified by the first argument.
	 *
	 * @param classe type of enumerated statistic to be held
	 * @param values that should be initially associated with parameters.
	 * Cannot be negative.
	 */
	public EnumMap(Class<E> classe, int values[]) {
		attributeMap = new HashMap<>();
		for (E e : classe.getEnumConstants()) {
			attributeMap.put(e, values[e.ordinal()]);
		}
	}

	/**
	 * Returns base value associated with a given statistic
	 *
	 * @param e
	 * @return
	 */
	public int getStatValue(E e) {
		return attributeMap.get(e);
	}

	/**
	 * Modifies the chosen stat by a given value. It will never go below
	 * zero
	 *
	 * @param e chose statistic
	 * @param change that should be applied to statistic
	 */
	public void modifyStat(E e, int change) {
		int oldValue = getStatValue(e);
		int proposedValue = oldValue + change;
		attributeMap.replace(e, proposedValue < 0 ? 0 : proposedValue);
	}

}
