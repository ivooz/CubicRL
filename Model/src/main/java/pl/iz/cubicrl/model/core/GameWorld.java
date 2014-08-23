/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.core;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.util.PropertyLoader;

/**
 * Abstraction for the entire gameWorld, including its state
 *
 * @author Ivo
 */
public class GameWorld implements TurnObserver {

	private static GameWorld instance;
	private LocalDateTime gameDateTime;
	private int turnCount;
	private final int secondsPerTurn;
	private final Cube cube;

	/**
	 * Loads configuration from the properties.config and initializes game
	 * world. It does not create the rooms, just the skeleton of the world.
	 * Implements Singleton pattern.
	 *
	 * @throws IOException if properties are not loaded properly from the
	 * config file
	 */
	private GameWorld() throws IOException {
		PropertyLoader propLoader = PropertyLoader.getInstance();
		int year = Integer.parseInt(propLoader.loadProperty("startYear"));
		int month = Integer.parseInt(propLoader.loadProperty("startMonth"));
		int day = Integer.parseInt(propLoader.loadProperty("startDay"));
		gameDateTime = LocalDateTime.of(year, month, day, 0, 0);
		secondsPerTurn = Integer.parseInt(propLoader.loadProperty("secondsPerTurn"));
		turnCount = 0;
		cube = new Cube(Integer.parseInt(propLoader.loadProperty("cubeEdgeSize")));
	}

	/**
	 * Returns the only instance of GameWorld
	 *
	 * @return the only instance
	 * @throws IOException
	 */
	public static GameWorld getInstance() throws IOException {
		if (instance == null) {
			instance = new GameWorld();
		}
		return instance;
	}

	/**
	 * 
	 * @return current world game and time
	 */
	public LocalDateTime getGameDateTime() {
		return gameDateTime;
	}

	public int getTurnCount() {
		return turnCount;
	}

	@Override
	public void nextTurnNotify() {
		gameDateTime = gameDateTime.plus(secondsPerTurn, ChronoUnit.SECONDS);
		turnCount++;
	}

	public void reset() throws IOException {
		instance = new GameWorld();
	}

	public Room getRoom(Coordinates3D coords) {
		return cube.getRoomAt(coords);
	}

}
