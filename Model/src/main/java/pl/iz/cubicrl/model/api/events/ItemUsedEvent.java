/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.api.events;

import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.items.Item;

/**
 * Fired on item used
 * @author Ivo
 */
public class ItemUsedEvent extends GameEvent {
	
	private final Item item;
	private final Creature creature;
	private final PenetrableField field;

	public ItemUsedEvent(Item item, Creature creature, PenetrableField field, String message) {
		super(message);
		this.item = item;
		this.creature=creature;
		this.field = field;
	}

	public Item getItem() {
		return item;
	}

	public Creature getCreature() {
		return creature;
	}
	
	public PenetrableField getField() {
		return field;
	}

	public boolean sameField() {
		return creature.getField()==field;
	}
}
