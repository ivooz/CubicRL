/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.field;

import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.enums.Direction;

/**
 *
 * @author Ivo
 */
public class Portal extends PenetrableField {

	private final Direction direction;
	private final Room destination;

	/**
	 * Serves as a passage between rooms. Otherwise it is a normal Penetrable
	 * Field.
	 * @param direction
	 * @param destination
	 * @param name
	 * @param roomCoordinates
	 * @param spriteSheetCoordinates
	 * @param eventBus
	 */
	public Portal(Direction direction, Room destination, String name, Coords2D roomCoordinates, Coords2D spriteSheetCoordinates, GameEventBus eventBus) {
		super(name, roomCoordinates, spriteSheetCoordinates,eventBus);
		this.direction = direction;
		this.destination = destination;
	}

	public void descend() {
		destination.welcomeCreature(resident, direction);
	}
}
