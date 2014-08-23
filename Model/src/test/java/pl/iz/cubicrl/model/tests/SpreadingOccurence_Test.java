package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pl.iz.cubicrl.model.tests.TestFactory;
import pl.iz.cubicrl.model.tests.RoomBaseTest;
import pl.iz.cubicrl.model.tests.occurence.OccurenceVisualizer;
import java.util.stream.IntStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.effects.DamagingTimedEffect;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.occurence.SpreadingOccurence;

/**
 *
 * @author Ivo
 */
public class SpreadingOccurence_Test extends RoomBaseTest {

	public SpreadingOccurence_Test() {
	}

	@Test
	public void testCloning() {
		int timer = 55;
		SpreadingOccurence testOccurence = new SpreadingOccurence(null, null, null, 0, timer, 0);
		SpreadingOccurence testOccurence2 = (SpreadingOccurence) testOccurence.copy();
		assertEquals(testOccurence.getTimer(), testOccurence2.getTimer());
	}

	@Test
	public void testWithVisualizer() {
		PenetrableField field = (PenetrableField)room.getFieldAt(new Coords2D(3, 3));
		SpreadingOccurence testOccurence = new SpreadingOccurence("fire", null, room, 25, 15, 1);
		testOccurence.addEffect(new DamagingTimedEffect("test1", DamageType.BLUNT, 5,2));
		field.addOccurence(testOccurence);
		Creature testCreature = TestFactory.getInstance().getGenericCreature();
		field.addResident(testCreature);
		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
		OccurenceVisualizer visualizer = new OccurenceVisualizer(room);
		IntStream.range(0, 10).forEach(i -> {
			room.nextTurnNotify();
			visualizer.visualize();
			IntStream.range(0, 10).forEach(x -> IntStream.range(0, 10).forEach(y -> assertTrue(room.getFieldAt(new Coords2D(x, y)).getOccurences().size() <= 1)));
		});
		assertTrue(testCreature.getEffectiveStat(LifeStat.HP)<initialHP);
	}
}
