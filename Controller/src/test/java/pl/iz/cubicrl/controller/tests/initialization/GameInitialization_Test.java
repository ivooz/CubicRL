/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.tests.initialization;

import com.google.inject.Guice;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.iz.cubicrl.controller.core.GameController;
import pl.iz.cubicrl.controller.core.NewGameModule;
import pl.iz.cubicrl.model.core.Coords3D;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.creature.Player;
import pl.iz.cubicrl.model.enums.Direction;

/**
 *
 * @author Ivo
 */
public class GameInitialization_Test {

	@Inject
	GameController gameController;

	@Inject
	Player player;

	@Inject
	GameWorld gameWorld;

	public GameInitialization_Test() {
	}

	@Before
	public void setUp() {
		try {
			Guice.createInjector(new NewGameModule()).injectMembers(this);
			gameController.initializeNewGame();
		} catch (IOException | ScriptException ex) {
			fail(ex.toString());
			Logger.getLogger(GameInitialization_Test.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void testInitialization() {
		player.getRoom().getFieldsAsParallelStream().forEach(f -> assertNotNull(f));
	}

	@Test
	public void testRoomInitialization() {
		recurrentlyTestRooms(2, player.getRoom().getCubeCoords());
	}

	public void recurrentlyTestRooms(int depth, Coords3D roomCoords) {
		if (depth == 0) {
			return;
		}
		if (gameWorld.getCube().checkIfWithinCubeBoundary(roomCoords)) {
			assertNotNull(gameWorld.getRoomAt(roomCoords));
		}
		Arrays.stream(Direction.values()).parallel().forEach(
			d -> recurrentlyTestRooms(depth - 1, new Coords3D(
					roomCoords.x + d.xDir,
					roomCoords.y + d.yDir,
					roomCoords.z + d.zDir))
		);
	}

}
