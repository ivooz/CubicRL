/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.field;

import pl.iz.cubicrl.model.items.Item;
import java.util.Stack;
import pl.iz.cubicrl.model.api.Visitor;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.creature.Creature;

/**
 * Specific Field, which can contain Items and Creatures
 *
 * @author Ivo
 */
public class PenetrableField extends Field {

	Creature resident;
	Stack<Item> items;

	public PenetrableField(String name, Coords2D roomCoordinates, Coords2D spriteSheetCoordinates) {
		super(name, roomCoordinates, spriteSheetCoordinates);
		items = new Stack<>();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public boolean hasItems() {
		return !items.isEmpty();
	}

	/**
	 * Returns uppermost item (from the top of the pile, if there is one),
	 * to be used for drawing
	 *
	 * @return uppermost Item
	 */
	public Item getTopItem() {
		return items.peek();
	}

	public Stack<Item> getItems() {
		return items;
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public Creature getResident() {
		return resident;
	}

	public boolean hasResident() {
		return resident != null;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public void addResident(Creature creature) {
		resident = creature;
		processNewResident();
	}

	private void processNewResident() {
	}

	public void removeResident() {
		resident = null;
	}

	@Override
	public void nextTurnNotify() {
		occurences.removeIf(o -> o.isExpired());
		occurences.forEach(o -> o.visit(this));
		if(hasResident()) {
			resident.nextTurnNotify();
		}
	}
}
