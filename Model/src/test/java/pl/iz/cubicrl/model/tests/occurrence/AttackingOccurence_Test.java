/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests.occurence;

import java.util.stream.IntStream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.occurence.OccurenceWithEffects;
import pl.iz.cubicrl.model.occurence.TimedOccurenceWithEffects;

/**
 *
 * @author Ivo
 */
public class AttackingOccurence_Test extends OccuranceTestBase {

	OccurenceWithEffects testOccurence;
	OccurenceWithEffects testOccurence2;
	TimedOccurenceWithEffects testTimedOccurence;

	@Before
	@Override
	public void setUp() {
		super.setUp();
		testOccurence = factory.getGenericOccurenceWithEffects();
		testOccurence2 = factory.getGenericOccurenceWithEffects();
		testTimedOccurence = factory.getGenericTimedOccurenceWithEffects(10);
	}

	@Test
	public void testOccurenceAttackingHumanoidAndCreature() {
		testOccurence.addAttack(factory.getKillingAttack(DamageType.BLUNT));
		testOccurence2.addAttack(factory.getKillingAttack(DamageType.BLUNT));
		testField1.addOccurence(testOccurence);
		testField2.addOccurence(testOccurence2);
		testField1.nextTurnNotify();
		testField2.nextTurnNotify();
		assertEquals(0, testCreature.getEffectiveStat(LifeStat.HP));
		assertEquals(0, testHumanoid.getEffectiveStat(LifeStat.HP));
	}

	@Test
	public void testOccurenceAttackingHumanoidAndCreatureOverTime() {
		System.out.println("start");
		testOccurence.addAttack(factory.getWeakAttack(DamageType.BLUNT));
		testOccurence2.addAttack(factory.getWeakAttack(DamageType.BLUNT));
		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
		testField1.addOccurence(testOccurence);
		testField2.addOccurence(testOccurence2);
		IntStream.range(1, 20).forEach(i -> {
			testField1.nextTurnNotify();
			testField2.nextTurnNotify();
			assertEquals(initialHP - i, testCreature.getEffectiveStat(LifeStat.HP));
			assertEquals(initialHP - i, testHumanoid.getEffectiveStat(LifeStat.HP));
		});
	}

}
