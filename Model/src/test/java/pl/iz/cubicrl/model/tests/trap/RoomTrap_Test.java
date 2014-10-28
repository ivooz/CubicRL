/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests.trap;

import java.util.stream.IntStream;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import pl.iz.cubicrl.model.trap.RoomTrap;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.Direction;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.occurrence.TimedOccurrenceWithEffects;
import pl.iz.cubicrl.model.tests.TestFactory;
import pl.iz.cubicrl.model.tests.occurrence.OccurrenceVisualizer;

/**
 *
 * @author Ivo
 */
public class RoomTrap_Test {

	TestFactory factory;
	Room testRoom;
//	Creature testCreature;
//	OccurrenceVisualizer visualizer;
//
//	public RoomTrap_Test() {
//	}
//
//	@Before
//	public void setUp() {
//		factory = TestFactory.getInstance();
//		testRoom = factory.getGenericRoom();
//		testCreature = factory.getGenericCreature();
//		testRoom.welcomeCreature(testCreature, Direction.SOUTH);
//		visualizer = new OccurrenceVisualizer(testRoom);
//	}
//
//	@After
//	public void tearDown() {
//	}
//
//	@Test
//	public void testRoomTrapActivation() {
//		//This trap should be activated immediately and leave occurances
//		//at every other field
//		RoomTrap testTrap = new RoomTrap(1, Integer.MAX_VALUE, Attribute.STRENGTH, 2, 2, 0, 0, "testTrap");
//		assertTrue(testRoom.getEntrance(Direction.SOUTH) instanceof PenetrableField);
//		//Occurrence should last only one turn
//		TimedOccurrenceWithEffects testOccurrence = factory.getGenericTimedOccurrenceWithEffects(1);
//		testOccurrence.addEffect(factory.getWeakDamagingEffect());
//		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
//		testTrap.setOccurrence(testOccurrence);
//		testRoom.addRoomTrap(testTrap);
//		IntStream.range(0, 20).forEach(i -> {
//			testRoom.nextTurnNotify();
//			//Uncomment for effect spread visualization in output
//			//visualizer.visualize();
//			assertTrue(testTrap.isActivated());
//			if (i % 2 == 0) {
//				testRoom.getFieldsAsParallelStream().filter(
//					f -> (f.getRoomCoords().x + f.getRoomCoords().y) % 2 == 0)
//					.forEach(f -> assertTrue(f.hasOccurrence()));
//				assertEquals(initialHP - (i + 2) / 2, testCreature.getEffectiveStat(LifeStat.HP));
//			}
//			if ((i + 1) % 2 == 0) {
//				testRoom.getFieldsAsParallelStream().forEach(f -> assertFalse(f.hasOccurrence()));
//			}
//		}
//		);
//	}
//
//	@Test
//	public void testRoomTrapActivationCrazy_justForFun() {
//		//This trap should be activated immediately and leave occurances
//		//at god-knows-where
//		RoomTrap testTrap = new RoomTrap(1, Integer.MAX_VALUE, Attribute.STRENGTH, 2, 5, 5, 25, "testTrap2");
//		//Occurrence should last only one turn
//		TimedOccurrenceWithEffects testOccurrence = factory.getGenericTimedOccurrenceWithEffects(1);
//		testTrap.setOccurrence(testOccurrence);
//		testRoom.addRoomTrap(testTrap);
//		IntStream.range(0, 20).forEach(
//			i -> {
//				testRoom.nextTurnNotify();
//				//Uncomment for effect spread visualization in output
//				//visualizer.visualize();
//			}
//		);
//	}
}
