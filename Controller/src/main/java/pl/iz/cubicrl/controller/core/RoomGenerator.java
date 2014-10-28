/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import pl.iz.cubicrl.controller.factory.EntityFactory;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.Coords3D;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.core.PropertyLoader;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.enums.Direction;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.Portal;

/**
 *
 * @author Ivo
 */
@Singleton
public class RoomGenerator {

	private final PropertyLoader propertyLoader;
	private final EntityFactory entityFactory;
	private final GameWorld gameWorld;

	@Inject
	public RoomGenerator(PropertyLoader propertyLoader, EntityFactory entityFactory,
		GameWorld gameWorld) {
		this.propertyLoader = propertyLoader;
		this.entityFactory = entityFactory;
		this.gameWorld = gameWorld;
	}

	public Room generateStandardRoom(Coords3D roomCoords) throws IOException {
		int edgeSize = Integer.parseInt(propertyLoader.loadProperty("roomEdgeSize"));
		Room room = new Room(edgeSize, "standardRoom");
		room.setCubeCoords(roomCoords);
		IntStream.range(0, edgeSize).parallel().forEach(x -> {
			IntStream.range(0, edgeSize).parallel().forEach(y -> {
				Field field = null;
				try {
					Coords2D fieldCoords = new Coords2D(x, y);
					field = entityFactory.loadField(determineType(fieldCoords, edgeSize));
					field.setRoomCoordinates(fieldCoords);
				} catch (IOException ex) {
					//TODO EXCEPTION HANDLING
					Logger.getLogger(RoomGenerator.class.getName()).log(Level.SEVERE, null, ex);
				}
				room.getFields()[x][y] = field;
			});
		});
		//Generating exits and entrances
		HashMap<Direction, Coords2D> directionMap = new HashMap<>();
		directionMap.put(Direction.UP, new Coords2D(edgeSize / 2, edgeSize / 2));
		directionMap.put(Direction.DOWN, new Coords2D(edgeSize / 2, edgeSize / 2 + 1));
		directionMap.put(Direction.WEST, new Coords2D(1, edgeSize / 2));
		directionMap.put(Direction.EAST, new Coords2D(edgeSize - 2, edgeSize / 2));
		directionMap.put(Direction.NORTH, new Coords2D(edgeSize / 2, 1));
		directionMap.put(Direction.SOUTH, new Coords2D(edgeSize / 2, edgeSize - 2));

		for (Direction dir : directionMap.keySet()) {
			if (gameWorld.getCube().checkIfWithinCubeBoundary(roomCoords, dir)) {
				Portal portal = (Portal) entityFactory.loadField("standardPortal");
				Coords2D coordsForPortal = directionMap.get(dir);
				portal.setRoomCoordinates(coordsForPortal);
				portal.setDirection(dir);
				portal.setCube(gameWorld.getCube());
				setPortalDestination(portal, dir, roomCoords);
				room.getFields()[coordsForPortal.x][coordsForPortal.y] = portal;
			}
		}
		room.resolveEntrances();
		return room;
	}

	private String determineType(Coords2D fieldCoords, int edgeSize) {
		if (fieldCoords.x <= 1 || fieldCoords.y <= 1 || fieldCoords.x >= (edgeSize - 2)
			|| fieldCoords.y >= (edgeSize - 2)) {
			return "standardWall";
		} else {
			return "standardFloor";
		}
	}

	private void setPortalDestination(Portal portal, Direction direction, Coords3D roomCoords) {
		portal.setDestination(new Coords3D(
			roomCoords.x + direction.xDir,
			roomCoords.y + direction.yDir,
			roomCoords.z + direction.zDir));
	}
}
