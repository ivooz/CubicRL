/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.api;

import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.occurrence.Occurrence;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.trap.Trap;

/**
 * Allows for implementation of visitor pattern for various game entities
 *
 * @author Ivo
 */
public interface Visitor {

	/**
	 * Allows various objects to visit Fields.
	 *
	 * @param field to be visited
	 */
	public void visit(Field field);

	/**
	 * Allows various objects to visit PenetrableFields.
	 *
	 * @param penetrableField to be visited
	 */
	public void visit(PenetrableField penetrableField);

	/**
	 * Allows various objects to visit Rooms.
	 *
	 * @param room to be visited
	 */
	public void visit(Room room);

	/**
	 * Allows various objects to visit .
	 *
	 * @param item to be visited
	 */
	public void visit(Item item);

	/**
	 * Allows various objects to Occurrences .
	 *
	 * @param Occurrence to be visited
	 */
	public void visit(Occurrence Occurrence);

	/**
	 * Allows various objects to visit Traps.
	 *
	 * @param trap to be visited
	 */
	public void visit(Trap trap);

	/**
	 * Allows various objects to visit Creatures.
	 *
	 * @param creature to be visited
	 */
	public void visit(Creature creature);
}
