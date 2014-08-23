/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.enums.Direction;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.Portal;

/**
 *
 * @author Ivo
 */
public class Room implements TurnObserver {

	private final Field[][] fields;
	private final HashMap<Direction,Portal> entrances;

	public Room(int edgeSize) {
		fields = new Field[edgeSize][edgeSize];
		entrances = new HashMap<>();
	}

	public Field getFieldAt(Coords2D coords) {
		return fields[coords.x][coords.y];
	}

	public Field[][] getFields() {
		return fields;
	}

	@Override
	public void nextTurnNotify() {
		IntStream.range(0, fields.length).forEach(
			x -> IntStream.range(0, fields.length).forEach(
				y -> fields[x][y].nextTurnNotify()));
	}

	public ArrayList<Field> getNeighbouringFields(Field field) {
		Coords2D fieldCoords = field.getRoomCoords();
		ArrayList<Field> returnedFields = new ArrayList<>();
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				if (fieldCoords.x + x >= 0 && fieldCoords.x + x < fields.length
					//Exclude fields out of x axis
					&& fieldCoords.y + y >= 0 && fieldCoords.y + y < fields.length
					//Excude fields out of y axis
					&& !(y == 0 && x==0)) { //Exclude the same field
					returnedFields.add(fields[fieldCoords.x + x][fieldCoords.y + y]);
				}
			}
		}
		return returnedFields;
	}

	private Portal getEntrance(Direction direction) {
		return entrances.get(Direction.class.getEnumConstants()[(direction.ordinal()+3)%6]);
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
	
}
