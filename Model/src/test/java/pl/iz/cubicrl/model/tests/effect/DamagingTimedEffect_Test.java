package pl.iz.cubicrl.model.tests.effect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pl.iz.cubicrl.model.effects.DamagingTimedEffect;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.enums.LifeStatsUpperLimit;
import pl.iz.cubicrl.model.tests.CreatureBaseTest;

/**
 *
 * @author Ivo
 */
public class DamagingTimedEffect_Test extends CreatureBaseTest {

	public DamagingTimedEffect_Test() {
	}

	@Test
	public void testDamaginTimeEffects_addingEffectMovingTurnsAndVerifyingBehavior() {
		int damagePerTurn = 9;
		int effectDuration = 5;
		DamageType damageType = DamageType.HEAT;
		int resistance = testCreature.getEffectiveStat(damageType);
		int expectedDamagePerTurn = (int) (damagePerTurn * (resistance / 100d));
		DamagingTimedEffect damagingTimedEffect = new DamagingTimedEffect(null, damageType, damagePerTurn, effectDuration);
		testCreature.accept(damagingTimedEffect);
		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
		for (int i = 0; i < effectDuration; i++) {
			testCreature.nextTurnNotify();
			int expectedHP = initialHP - expectedDamagePerTurn;
			expectedHP = expectedHP < 0 ? 0 : expectedHP > testCreature.getEffectiveStat(LifeStatsUpperLimit.MAXHP) ? testCreature.getEffectiveStat(LifeStatsUpperLimit.MAXHP) : expectedHP;
			assertEquals(testCreature.getEffectiveStat(LifeStat.HP), expectedHP);
		}
		testCreature.nextTurnNotify();
		assert (!testCreature.getEffects().contains(damagingTimedEffect));
	}
}
