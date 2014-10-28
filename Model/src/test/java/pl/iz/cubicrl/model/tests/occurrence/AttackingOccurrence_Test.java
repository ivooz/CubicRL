/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests.occurrence;

import java.util.stream.IntStream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.occurrence.OccurrenceWithEffects;
import pl.iz.cubicrl.model.occurrence.TimedOccurrenceWithEffects;

/**
 *
 * @author Ivo
 */
public class AttackingOccurrence_Test extends OccurrenceTestBase {

	OccurrenceWithEffects testOccurrence;
	OccurrenceWithEffects testOccurrence2;
	TimedOccurrenceWithEffects testTimedOccurrence;

	@Before
	@Override
	public void setUp() {
		super.setUp();
		testOccurrence = factory.getGenericOccurrenceWithEffects();
		testOccurrence2 = factory.getGenericOccurrenceWithEffects();
		testTimedOccurrence = factory.getGenericTimedOccurrenceWithEffects(10);
	}

	@Test
	public void testOccurrenceAttackingHumanoidAndCreature() {
		testOccurrence.addAttack(factory.getKillingAttack(DamageType.BLUNT));
		testOccurrence2.addAttack(factory.getKillingAttack(DamageType.BLUNT));
		testField1.addOccurrence(testOccurrence);
		testField2.addOccurrence(testOccurrence2);
		testField1.nextTurnNotify();
		testField2.nextTurnNotify();
		assertEquals(0, testCreature.getEffectiveStat(LifeStat.HP));
		assertEquals(0, testHumanoid.getEffectiveStat(LifeStat.HP));
	}

	@Test
	public void testOccurrenceAttackingHumanoidAndCreatureOverTime() {
		System.out.println("start");
		testOccurrence.addAttack(factory.getWeakAttack(DamageType.BLUNT));
		testOccurrence2.addAttack(factory.getWeakAttack(DamageType.BLUNT));
		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
		testField1.addOccurrence(testOccurrence);
		testField2.addOccurrence(testOccurrence2);
		IntStream.range(1, 20).forEach(i -> {
			testField1.nextTurnNotify();
			testField2.nextTurnNotify();
			assertEquals(initialHP - i, testCreature.getEffectiveStat(LifeStat.HP));
			assertEquals(initialHP - i, testHumanoid.getEffectiveStat(LifeStat.HP));
		});
	}

}
