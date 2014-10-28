/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import javax.script.ScriptException;
import pl.iz.cubicrl.controller.factory.EntityFactory;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.api.events.RoomChangeEvent;
import pl.iz.cubicrl.model.core.Coords3D;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.creature.Player;
import pl.iz.cubicrl.model.enums.Direction;

/**
 * -Get player input<br/>
 * -Execute the movement<br/>
 * -Other creatures execute movement through AI, same command chain used<br/>
 * -Intermediate processes are executed (lines of sight, global events, etc.)<br/>
 * -Rooms are nextTurnNotified so that other game mechanics like effects,
 * occurences etc. are executed down the data model hierarchy.<br/>
 * @author Ivo
 */
@Singleton
public class GameController {

	private final Player player;
	private final GameWorld gameWorld;
	private final GameEventBus gameEventBus;
	private final IDao dao;
	private final RoomLoader roomLoader;
	private final EntityFactory entityFactory;
	private final ScriptLoader scriptLoader;

	@Inject
	public GameController(Player player, GameWorld gameWorld, GameEventBus gameEventBus, 
		IDao dao, RoomLoader roomLoader,EntityFactory entityFactory,
		ScriptLoader scriptLoader) {
		this.scriptLoader=scriptLoader;
		this.player = player;
		this.gameWorld = gameWorld;
		this.gameEventBus = gameEventBus;
		this.dao = dao;
		this.roomLoader=roomLoader;
		this.entityFactory=entityFactory;
		//Must be at the end
		gameEventBus.setGameController(this);

	}

	public Player getPlayer() {
		return player;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public GameEventBus getGameEventBus() {
		return gameEventBus;
	}

	/**
	 * Call when new game starts. <br/>- Loads names of existing unique rooms and
	 * traps, initializes trapMap
	 */
	public void initializeNewGame() throws IOException, ScriptException {
		initializeTraps();
		initializePlayerPosition();
		scriptLoader.initializeScripts();
	}
	
	private void initializeTraps() throws IOException {
		ArrayList<String> traps = new ArrayList<>();
		Files.walk(Paths.get("../Assets/Traps")).filter(file -> file.endsWith("xml"))
			.forEach( f -> traps.add(f.getFileName().toString().split(".")[0]));
		ArrayList<String> rooms = new ArrayList<>();
		Files.walk(Paths.get("../Assets/Rooms")).filter(file -> file.endsWith("xml"))
			.forEach(f -> traps.add(f.getFileName().toString().split(".")[0]));
		gameWorld.getTrapMap().initialize(traps, rooms);
	}

	private void initializePlayerPosition() throws IOException {
		//Getting random room from stream
		Object[] rooms = gameWorld.getCube().getRoomCoordsAsParallelStream().filter(
			//Make sure it is a plain room (not special, no traps)
			coord -> !gameWorld.getTrapMap().roomIsSpecial(coord)
		).toArray();
		Random rn = new Random();
		Coords3D initialPlayerPosition = (Coords3D)rooms[rn.nextInt(rooms.length)];
		roomLoader.initializeGameWorld(initialPlayerPosition);
		gameWorld.getCube().getRoomAt(initialPlayerPosition).accept(player);
	}
}
