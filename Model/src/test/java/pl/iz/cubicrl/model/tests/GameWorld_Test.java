package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.core.Coords3D;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.core.PropertyLoader;
import pl.iz.cubicrl.model.core.TrapMap;

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
		propLoader = new PropertyLoader();
		try {
			gameWorld = new GameWorld(TestFactory.getInstance().getGenericPlayer(), new GameEventBus(), propLoader, new TrapMap(propLoader));
		} catch (IOException ex) {
			Logger.getLogger(GameWorld_Test.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testNextTurnNotification_registeringObserversNotifyingAndUnregistering() {
		int initialTurn = gameWorld.getTurnCount();
		gameWorld.nextTurnNotify();
		assert (gameWorld.getTurnCount() == (initialTurn + 1));
	}

	@Test
	public void testRoomInitialization_checkingIfRoomInstancesAreCreatedProperly() {
		int cubeEdgeSize = Integer.parseInt(propLoader.loadProperty("cubeEdgeSize"));
		IntStream.range(0, cubeEdgeSize).parallel().forEach(
			x -> IntStream.range(0, cubeEdgeSize).parallel().forEach(
				y -> IntStream.range(0, cubeEdgeSize).parallel().forEach(
					z -> assertNull(gameWorld.getRoomAt(new Coords3D(x, y, z))))));
	}

}
