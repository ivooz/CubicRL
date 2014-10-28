/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.core;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.iz.cubicrl.controller.factory.EntityFactory;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.api.events.RoomChangeEvent;
import pl.iz.cubicrl.model.core.Coords3D;
import pl.iz.cubicrl.model.core.Cube;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.core.PropertyLoader;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.core.TrapMap;
import pl.iz.cubicrl.model.enums.Direction;
import pl.iz.cubicrl.model.trap.RoomTrap;

/**
 *
 * @author Ivo
 */
@Singleton
public class RoomLoader {

	private final GameWorld gameWorld;
	private final IDao dao;
	private final GameEventBus eventBus;
	private final PropertyLoader propertyLoader;
	private final EntityFactory entityFactory;
	private final RoomGenerator roomGenerator;

	/**
	 *
	 * @param gameWorld
	 * @param dao
	 * @param eventBus
	 * @param propertyLoader
	 * @param entityFactory
	 * @param roomGenerator
	 */
	@Inject
	public RoomLoader(GameWorld gameWorld, IDao dao, GameEventBus eventBus,
		PropertyLoader propertyLoader, EntityFactory entityFactory,
		RoomGenerator roomGenerator) {
		this.gameWorld = gameWorld;
		this.dao = dao;
		this.eventBus = eventBus;
		this.propertyLoader = propertyLoader;
		this.entityFactory = entityFactory;
		this.roomGenerator = roomGenerator;
		eventBus.subscribeMechanic(this); //Should be at the end
	}

	/**
	 *
	 *
	 * @param event
	 */
	@Subscribe
	public void generateRooms(RoomChangeEvent event) {
		int simulationRadius = Integer.parseInt(propertyLoader
			.loadProperty("simulatedRoomRadius"));
		if (event.getCreature() == gameWorld.getPlayer()) {
			Direction heading = event.getDirection();
			Coords3D currentRoomCoords = event.getRoom().getCubeCoords();
			Coords3D vistedRoomCoords = new Coords3D(
				currentRoomCoords.x + heading.xDir,
				currentRoomCoords.y + heading.yDir,
				currentRoomCoords.z + heading.zDir);
			try {
				recurrentlyGenerate(simulationRadius, vistedRoomCoords);
			} catch (IOException ex) {
				Logger.getLogger(RoomLoader.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void initializeGameWorld(Coords3D firstRoomCoords) throws IOException {
		int simulationRadius = Integer.parseInt(propertyLoader
			.loadProperty("simulatedRoomRadius"));
		recurrentlyGenerate(simulationRadius, firstRoomCoords);
	}

	private void recurrentlyGenerate(int degree, Coords3D roomCoords) throws IOException {
		generateRoom(roomCoords);
		if (degree == 0) {
			return;
		}
		for (Direction dir : Direction.values()) {
			Coords3D newRoomCoords = new Coords3D(
				roomCoords.x + dir.xDir,
				roomCoords.y + dir.yDir,
				roomCoords.z + dir.zDir);
			recurrentlyGenerate(degree - 1, newRoomCoords);
		}
	}

	private void generateRoom(Coords3D roomCoords) throws IOException {
		if (gameWorld.getCube().checkIfWithinCubeBoundary(roomCoords) && 
			gameWorld.getRoomAt(roomCoords) == null) {
			Room room;
			if (gameWorld.getTrapMap().getMappedRooms().containsKey(roomCoords)) {
				room = entityFactory.loadRoom(gameWorld.getTrapMap()
					.getMappedRooms().get(roomCoords));
			} else {
				room = roomGenerator.generateStandardRoom(roomCoords);
				if (gameWorld.getTrapMap().getMappedTraps().containsKey(roomCoords)) {
					RoomTrap trap = entityFactory.loadRoomTrap(gameWorld.getTrapMap()
						.getMappedTraps().get(roomCoords));
					room.addRoomTrap(trap);
				}
			}
			room.setCubeCoords(roomCoords);
			gameWorld.getCube().setRoomAt(roomCoords, room);
		}
	}
}
