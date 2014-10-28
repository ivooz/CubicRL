/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.creature;

import pl.iz.cubicrl.model.creature.EnumMap;

/**
 * UNUSED CLASS, DELETE IN NEXT RELEASE
 *
 * @author Ivo
 * @param <E>
 */
public class ConstrainedBaseStatMap<E extends Enum<E>> extends EnumMap<E> {

	public ConstrainedBaseStatMap(Class<E> classe, int[] values) {
		super(classe, values);
	}

}
