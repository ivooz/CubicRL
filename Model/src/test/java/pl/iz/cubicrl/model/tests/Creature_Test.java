package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.enums.LifeStatsUpperLimit;
import pl.iz.cubicrl.model.enums.Skill;

/**
 *
 * @author Ivo
 */
public class Creature_Test extends CreatureBaseTest {

	@Test
	public void testProperInitialization() {
		assert (testCreature.getName().equals("test"));
	}

	@Test
	public void testCreatureStats() {
		assert (testCreature.getEffectiveStat(Attribute.STRENGTH) == 5);
		assert (testCreature.getEffectiveStat(Skill.MATHEMATICS) == 1);
		assert (testCreature.getEffectiveStat(LifeStatsUpperLimit.MAXSANITY) == 100);
		assert (testCreature.getEffectiveStat(DamageType.BLUNT) == 10);
	}

	@Test
	public void testCreatureEffects() {

	}

	@Test
	public void testCreatureDamage_dealingSanityAndHealthDamage() {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int magnitude = random.nextInt(5);
			int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
			testCreature.takeDamage(DamageType.BLUNT, magnitude);
			int expectedHP = initialHP - (int) (magnitude * 0.9);
			expectedHP = expectedHP < 0 ? 0 : expectedHP > testCreature.getEffectiveStat(LifeStatsUpperLimit.MAXSANITY) ? testCreature.getEffectiveStat(LifeStatsUpperLimit.MAXHP) : expectedHP;
			assertEquals(testCreature.getEffectiveStat(LifeStat.HP), expectedHP);
			int initialSanity = testCreature.getEffectiveStat(LifeStat.SANITY);
			testCreature.takeDamage(DamageType.PSYCHIC, magnitude);
			int expectedSanity = initialSanity - (int) (magnitude * 0.9);
			expectedSanity = expectedSanity < 0 ? 0 : expectedSanity > testCreature.getEffectiveStat(LifeStatsUpperLimit.MAXSANITY) ? testCreature.getEffectiveStat(LifeStatsUpperLimit.MAXSANITY) : expectedSanity;
			assertEquals(testCreature.getEffectiveStat(LifeStat.SANITY), expectedSanity);
		}
	}

}
