/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.creature.Player;

/**
 * Abstraction for the entire gameWorld, including its state
 *
 * @author Ivo
 */
@Singleton
public class GameWorld implements TurnObserver {

	private Calendar gameTime;
	private Player player;
	private GameEventBus eventBus;
	private int turnCount;
	private int secondsPerTurn;
	private Cube cube;
	private PropertyLoader propertyLoader;
	private TrapMap trapMap;

	/**
	 * Loads configuration from the properties.config and initializes game
	 * world. It does not create the rooms, just the skeleton of the world.
	 * Implements Singleton pattern.
	 *
	 * @param player
	 * @param eventBus
	 * @param propertyLoader
	 * @param trapMap
	 * @throws IOException if properties are not loaded properly from the
	 * config file
	 */
	@Inject
	public GameWorld(Player player, GameEventBus eventBus, PropertyLoader propertyLoader,
		TrapMap trapMap) throws IOException {
		this.propertyLoader = propertyLoader;
		this.eventBus = eventBus;
		this.player = player;
		this.trapMap=trapMap;
		int year = Integer.parseInt(propertyLoader.loadProperty("startYear"));
		int month = Integer.parseInt(propertyLoader.loadProperty("startMonth"));
		int day = Integer.parseInt(propertyLoader.loadProperty("startDay"));
		gameTime = GregorianCalendar.getInstance();
		gameTime.set(GregorianCalendar.YEAR, year);
		gameTime.set(GregorianCalendar.MONTH, month);
		gameTime.set(GregorianCalendar.DATE, day);
		secondsPerTurn = Integer.parseInt(propertyLoader.loadProperty("secondsPerTurn"));
		turnCount = 0;
		cube = new Cube(Integer.parseInt(propertyLoader.loadProperty("cubeEdgeSize")));
	}

	private GameWorld() {
	}

	/**
	 * Do not change the time :)
	 *
	 * @return current world gamve and time
	 */
	public Calendar getGameDateTime() {
		return gameTime;
	}

	public int getTurnCount() {
		return turnCount;
	}

	@Override
	public void nextTurnNotify() {
		gameTime.add(GregorianCalendar.SECOND, secondsPerTurn);
		turnCount++;
	}

	public Room getRoomAt(Coords3D coords) {
		return cube.getRoomAt(coords);
	}
	
	public void setRoomAt(Coords3D coords, Room room) {
		cube.setRoomAt(coords, room);
	}

	public Player getPlayer() {
		return player;
	}

	public GameEventBus getEventBus() {
		return eventBus;
	}

	public Cube getCube() {
		return cube;
	}

	public TrapMap getTrapMap() {
		return trapMap;
	}

	public void setTrapMap(TrapMap trapMap) {
		this.trapMap = trapMap;
	}
}
