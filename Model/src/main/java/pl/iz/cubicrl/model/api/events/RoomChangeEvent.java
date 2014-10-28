/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.api.events;

import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.enums.Direction;

/**
 * This event is fired by Portal class just before Creature descends to another
 * Room.
 */
public class RoomChangeEvent extends GameEvent {

	private final Room room;
	private final Direction direction;
	private final Creature creature;

	/**
	 * 
	 * @param room
	 * @param direction
	 * @param creature
	 * @param message 
	 */
	public RoomChangeEvent(Room room, Direction direction, Creature creature,
		String message) {
		super(message);
		this.creature = creature;
		this.room = room;
		this.direction = direction;
	}

	public Room getRoom() {
		return room;
	}

	public Direction getDirection() {
		return direction;
	}

	public Creature getCreature() {
		return creature;
	}

}
