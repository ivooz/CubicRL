/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.creature.HumanoidCreature;
import pl.iz.cubicrl.model.enums.Direction;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.field.Portal;

/**
 *
 * @author Ivo
 */
public class CreatureRoom_Test {

	private Creature testCreature;
	private HumanoidCreature testHumanoid;
	private Room testRoom;

	public CreatureRoom_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testCreature = TestFactory.getInstance().getGenericCreature();
		testHumanoid = TestFactory.getInstance().getGenericHumanoidCreature();
		testRoom = TestFactory.getInstance().getGenericRoom();
	}

	@After
	public void tearDown() {
	}
//TODO: redo these test
//	@Test
//	public void testMovingAroundRoom() {
//		Portal portal1 = new Portal(Direction.SOUTH, null, null, null, new GameEventBus());
//		portal1.accept(testCreature);
//		portal1.d
//		testRoom.welcomeCreature(testCreature, Direction.SOUTH);
//		assertTrue(testRoom.getEntrances().get(Direction.NORTH) == testCreature.getField());
//		testRoom.welcomeCreature(testHumanoid, Direction.NORTH);
//		assertTrue(testRoom.getEntrances().get(Direction.SOUTH) == testHumanoid.getField());
//		testCreature.visit((PenetrableField) testRoom.getFieldAt(new Coords2D(7, 7)));
//		assertTrue(testCreature.getField() == testRoom.getFieldAt(new Coords2D(7, 7)));
//		assertTrue(((PenetrableField) testRoom.getFieldAt(new Coords2D(7, 7))).getResident() == testCreature);
//		testHumanoid.visit((PenetrableField) testRoom.getFieldAt(new Coords2D(8, 8)));
//		assertTrue(testHumanoid.getField() == testRoom.getFieldAt(new Coords2D(8, 8)));
//		assertTrue(((PenetrableField) testRoom.getFieldAt(new Coords2D(8, 8))).getResident() == testHumanoid);
//	}

//	@Test
//	public void testMovingBetweenRooms() {
//		testRoom.welcomeCreature(testCreature, Direction.NORTH);
//		assertTrue(testCreature.getRoom() == testRoom);
//		testRoom = TestFactory.getInstance().getGenericRoom();
//		testRoom.welcomeCreature(testCreature, Direction.NORTH);
//		assertTrue(testCreature.getRoom() == testRoom);
//	}
}
