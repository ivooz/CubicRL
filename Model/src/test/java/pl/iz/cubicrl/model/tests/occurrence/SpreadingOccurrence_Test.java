package pl.iz.cubicrl.model.tests.occurrence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import pl.iz.cubicrl.model.occurrence.SpreadingOccurrence;
import pl.iz.cubicrl.model.tests.RoomBaseTest;
import pl.iz.cubicrl.model.tests.TestFactory;

/**
 *
 * @author Ivo
 */
public class SpreadingOccurrence_Test extends RoomBaseTest {

	public SpreadingOccurrence_Test() {
	}

	@Test
	public void testCloning() {
		int timer = 55;
		SpreadingOccurrence testOccurrence = new SpreadingOccurrence(null, null, null, 0, timer, 0);
		SpreadingOccurrence testOccurrence2 = (SpreadingOccurrence) testOccurrence.copy();
		assertEquals(testOccurrence.getTimer(), testOccurrence2.getTimer());
	}

	@Test
	public void testWithVisualizer() {
		PenetrableField field = (PenetrableField) room.getFieldAt(new Coords2D(3, 3));
		SpreadingOccurrence testOccurrence = new SpreadingOccurrence("fire", null, room, 25, 15, 1);
		testOccurrence.addEffect(new DamagingTimedEffect("test1", DamageType.BLUNT, 5, 2));
		field.addOccurrence(testOccurrence);
		Creature testCreature = TestFactory.getInstance().getGenericCreature();
		field.addResident(testCreature);
		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
		OccurrenceVisualizer visualizer = new OccurrenceVisualizer(room);
		IntStream.range(0, 10).forEach(i -> {
			room.nextTurnNotify();
			//visualizer.visualize();
			IntStream.range(0, 10).forEach(x -> IntStream.range(0, 10).forEach(y -> assertTrue(room.getFieldAt(new Coords2D(x, y)).getOccurrences().size() <= 1)));
		});
		assertTrue(testCreature.getEffectiveStat(LifeStat.HP) < initialHP);
	}
}
