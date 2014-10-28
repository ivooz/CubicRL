/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.field;

import pl.iz.cubicrl.model.api.events.RoomChangeEvent;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.Coords3D;
import pl.iz.cubicrl.model.core.Cube;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.enums.Direction;

/**
 *
 * @author Ivo
 */
public class Portal extends PenetrableField {

	private Direction direction;
	private Cube cube;
	private Coords3D destination;

	/**
	 * Serves as a passage between rooms. Otherwise it is a normal
	 * Penetrable Field.
	 *
	 * @param direction
	 * @param name
	 * @param roomCoordinates
	 * @param spriteSheetCoordinates
	 * @param eventBus
	 */
	public Portal(Direction direction, String name, Coords2D roomCoordinates,
		Coords2D spriteSheetCoordinates, GameEventBus eventBus) {
		super(name, roomCoordinates, spriteSheetCoordinates, eventBus);
		this.direction = direction;
	}

	public void descend() {
		eventBus.publish(new RoomChangeEvent(resident.getRoom(), direction,resident,
			resident.getName() + " moved to the next room."));
		cube.getRoomAt(destination).accept(resident);
	}

	public void setCube(Cube cube) {
		this.cube = cube;
	}

	public void setDestination(Coords3D destination) {
		this.destination = destination;
	}

	public Direction getDirection() {
		return direction;
	}

	public Cube getCube() {
		return cube;
	}

	public Coords3D getDestination() {
		return destination;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
