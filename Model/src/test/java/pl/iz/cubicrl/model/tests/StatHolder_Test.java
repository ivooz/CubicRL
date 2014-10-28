package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.Test;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.enums.LifeStatsUpperLimit;

/**
 *
 * @author Ivo
 */
public class StatHolder_Test extends CreatureBaseTest {

	@Test
	public void testAlteringBaseStat_increasingStatAndVerifyingNewValue() {
		int initialStrength = testCreature.getEffectiveStat(Attribute.STRENGTH);
		int statIncrease = random.nextInt(20);
		testCreature.modifyBaseStat(Attribute.STRENGTH, statIncrease);
		assert (testCreature.getEffectiveStat(Attribute.STRENGTH) == initialStrength + statIncrease);
	}

	@Test
	public void testAlteringLifeStats_increasingStatAndVerifyingNewValue() {
		testCreature.takeDamage(DamageType.BLUNT, Integer.MAX_VALUE);
		assert (testCreature.getEffectiveStat(LifeStat.HP) == 0);
		testCreature.modifyBaseStat(LifeStat.HP, Integer.MAX_VALUE);
		assert (testCreature.getEffectiveStat(LifeStat.HP) == testCreature.getEffectiveStat(LifeStatsUpperLimit.MAXHP));
	}
}
