/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.api;

import java.io.Serializable;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.occurrence.Occurrence;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.trap.Trap;

/**
 * Simple adapter class for visitor interface
 *
 * @author Ivo
 */
public class VisitorAdapter implements Visitor, Serializable {

	@Override
	public void visit(Field field) {
	}

	@Override
	public void visit(Creature creature) {
	}

	@Override
	public void visit(Room room) {
	}

	@Override
	public void visit(Item item) {
	}

	@Override
	public void visit(Occurrence Occurrence) {
	}

	@Override
	public void visit(Trap trap) {
	}

	@Override
	public void visit(PenetrableField field) {
	}
}
