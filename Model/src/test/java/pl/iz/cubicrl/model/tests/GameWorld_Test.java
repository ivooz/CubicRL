package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.core.Coordinates3D;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.util.PropertyLoader;

/**
 *
 * @author Ivo
 */
public class GameWorld_Test {

	GameWorld gameWorld;
	PropertyLoader propLoader;

	public GameWorld_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		resetGameWorldField();
		propLoader = PropertyLoader.getInstance();
	}

	@After
	public void tearDown() {
	}

	private void resetGameWorldField() {
		try {
			gameWorld = GameWorld.getInstance();
		} catch (IOException ex) {
			System.out.println("Failed to create GameWorld! Reason:" + ex.getMessage());
			fail();
		}
	}

	public void testGameDateTime_validatingInitialSettings() {
		LocalDateTime initialGameTime = gameWorld.getGameDateTime();
		int year = Integer.parseInt(propLoader.loadProperty("startYear"));
		int month = Integer.parseInt(propLoader.loadProperty("startMonth"));
		int day = Integer.parseInt(propLoader.loadProperty("startDay"));
		assert (initialGameTime.getMonth() == Month.of(month));
		assert (initialGameTime.getYear() == year);
		assert (initialGameTime.getDayOfMonth() == day);
		assert (initialGameTime.getHour() == 0);
		assert (initialGameTime.getMinute() == 0);
		assert (gameWorld.getTurnCount() == 0);
	}

	@Test
	public void testNextTurnNotification_registeringObserversNotifyingAndUnregistering() {
		int initialTurn = gameWorld.getTurnCount();
		gameWorld.nextTurnNotify();
		assert (gameWorld.getTurnCount() == (initialTurn + 1));
	}

	@Test
	public void testNextTurnTimeUnit_nextingTurnAndComparingTimeDifferences() {
		LocalDateTime gameTime = gameWorld.getGameDateTime();
		LocalDateTime initialTime = LocalDateTime.of(gameTime.toLocalDate(), gameTime.toLocalTime());
		int secondsPerTurn = Integer.parseInt(propLoader.loadProperty("secondsPerTurn"));
		gameWorld.nextTurnNotify();
		assert (initialTime.plusSeconds(secondsPerTurn).equals(gameWorld.getGameDateTime()));
	}

	@Test
	public void testResettingGameState_resettingAndCheckingIfInitializedProperly() {
		try {
			gameWorld.reset();
		} catch (IOException ex) {
			System.out.println("Failed to reset GameState! Reason:" + ex.getMessage());
			fail();
		}
		resetGameWorldField();
		testGameDateTime_validatingInitialSettings();
	}

	@Test
	public void testRoomInitialization_checkingIfRoomInstancesAreCreatedProperly() {
		int cubeEdgeSize = Integer.parseInt(propLoader.loadProperty("cubeEdgeSize"));
		IntStream.range(0, cubeEdgeSize).parallel().forEach(
			x -> IntStream.range(0, cubeEdgeSize).parallel().forEach(
				y -> IntStream.range(0, cubeEdgeSize).parallel().forEach(
					z -> assertNull(gameWorld.getRoom(new Coordinates3D(x, y, z))))));
	}

}
