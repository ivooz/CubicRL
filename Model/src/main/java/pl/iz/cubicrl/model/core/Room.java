/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.api.trap.RoomTrap;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.enums.Direction;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.Portal;

/**
 *
 * @author Ivo
 */
public class Room implements TurnObserver {

	private final String name;
	private final Field[][] fields;
	private final HashMap<Direction, Portal> entrances;
	private final ArrayList<Creature> visitingCreatures;
	private final ArrayList<RoomTrap> roomTraps;

	public Room(int edgeSize, String name) {
		fields = new Field[edgeSize][edgeSize];
		this.name = name;
		entrances = new HashMap<>();
		visitingCreatures = new ArrayList<>();
		roomTraps = new ArrayList<>();
	}

	public Field getFieldAt(Coords2D coords) {
		return fields[coords.x][coords.y];
	}

	public Field[][] getFields() {
		return fields;
	}

	@Override
	public void nextTurnNotify() {
		//Test trap activation
		roomTraps.stream().forEach(t -> visitingCreatures.stream()
			.forEach(c -> t.visit(c)));
		//Traps do their magic
		roomTraps.stream().forEach(t -> t.visit(this));
		IntStream.range(0, fields.length).forEach(
			x -> IntStream.range(0, fields.length).forEach(
				y -> fields[x][y].nextTurnNotify()));
	}

	/**
	 * Returns neigboring fields, currently used by spreading effects.
	 * @param field
	 * @return
	 */
	public ArrayList<Field> getNeighbouringFields(@NotNull Field field) {
		Coords2D fieldCoords = field.getRoomCoords();
		ArrayList<Field> returnedFields = new ArrayList<>();
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				if (fieldCoords.x + x >= 0 && fieldCoords.x + x < fields.length
					//Exclude fields out of x axis
					&& fieldCoords.y + y >= 0 && fieldCoords.y + y < fields.length
					//Excude fields out of y axis
					&& !(y == 0 && x == 0)) { //Exclude the same field
					returnedFields.add(fields[fieldCoords.x + x][fieldCoords.y + y]);
				}
			}
		}
		return returnedFields;
	}

	private Portal getEntrance(Direction direction) {
		return entrances.get(Direction.class.getEnumConstants()[(direction.ordinal() + 3) % 6]);
	}

	public HashMap<Direction, Portal> getEntrances() {
		return entrances;
	}

	public void welcomeCreature(Creature creature, Direction direction) {
		//Set current room to this room, will be useful in gui
		creature.visit(this);
		//TODO: trap activation
		getEntrance(direction).accept(creature);
	}

	public void addCreature(Creature creature) {
		visitingCreatures.add(creature);
	}

	public void removeCreture(Creature creature) {
		visitingCreatures.remove(creature);
	}

	public void addRoomTrap(RoomTrap RoomTrap) {
		roomTraps.add(RoomTrap);
	}
	
	/**
	 * Only for thread-safe operations
	 * @return 
	 */
	public Stream<Field> getFieldsAsParallelStream() {
		return Arrays.stream(fields).flatMap(fArr -> Arrays.stream(fArr)).parallel();
	}
	
	/**
	 * For thread unsafe operations, if thread safe use parallel stream version.
	 * @return 
	 */
	public Stream<Field> getFieldsAsStream() {
		return Arrays.stream(fields).flatMap(fArr -> Arrays.stream(fArr));
	}
}
