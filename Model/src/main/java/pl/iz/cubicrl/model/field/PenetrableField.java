/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.field;

import java.util.ArrayList;
import pl.iz.cubicrl.model.items.Item;
import java.util.Stack;
import javax.validation.constraints.NotNull;
import pl.iz.cubicrl.model.api.Visitor;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.occurrence.FieldTrap;
import pl.iz.cubicrl.model.occurrence.Occurrence;

/**
 * Specific Field, which can contain Items and Creatures
 *
 * @author Ivo
 */
public class PenetrableField extends Field {

	Creature resident;
	Stack<Item> items;
	ArrayList<FieldTrap> traps;

	/**
	 * Field that can hold items, timedOccurrences and Creatures
	 *
	 * @param name
	 * @param roomCoordinates
	 * @param spriteSheetCoordinates
	 * @param eventBus
	 */
	public PenetrableField(@NotNull String name,
		@NotNull Coords2D roomCoordinates,
		Coords2D spriteSheetCoordinates,
		@NotNull GameEventBus eventBus) {
		super(name, roomCoordinates, spriteSheetCoordinates, eventBus);
		items = new Stack<>();
		traps = new ArrayList<>();
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
		Occurrences.forEach(o -> o.visit(resident));
	}

	public void removeResident() {
		resident = null;
	}

	@Override
	public void addOccurrence(Occurrence Occurrence) {
		super.addOccurrence(Occurrence);
		if (hasResident()) {
			Occurrence.visit(resident);
		}
	}

	@Override
	public void nextTurnNotify() {
		Occurrences.removeIf(o -> o.isExpired());
		Occurrences.forEach(o -> o.visit(this));
		if (hasResident()) {
			resident.nextTurnNotify();
		}
		Occurrences.forEach(o -> o.nextTurnNotify());
	}

	public void addTrap(FieldTrap trap) {
		traps.add(trap);
		Occurrences.add(trap);
	}

	public ArrayList<FieldTrap> getTraps() {
		return traps;
	}
}
