package pl.iz.cubicrl.model.tests.effect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import java.util.stream.IntStream;
import javafx.util.Pair;
import org.junit.Test;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.effects.TimedEffect;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.tests.CreatureBaseTest;

/**
 *
 * @author Ivo
 */
public class TimedEffect_Test extends CreatureBaseTest {

	@Test
	public void testTimedEffectBehavior_addingEffectsPassingTurnsCheckingStatValues() {
		Random random = new Random();
		int initialCharima = testCreature.getEffectiveStat(Attribute.CHARISMA);
		int modification = random.nextInt(20);
		TimedEffect testTimedEffect1 = new TimedEffect("test0",5);
		TimedEffect testTimedEffect2 = new TimedEffect("test2",10);
		Effect testEffect = new Effect("test1");
		testCreature.addEffect(testEffect);
		testTimedEffect1.addModifier(new Pair<>(Attribute.CHARISMA, modification))
			.addModifier(new Pair<>(Attribute.CHARISMA, modification));
		testTimedEffect2.addModifier(new Pair<>(Attribute.CHARISMA, modification))
			.addModifier(new Pair<>(Attribute.CHARISMA, modification))
			.addModifier(new Pair<>(Attribute.CHARISMA, modification));
		testCreature.addEffect(testTimedEffect1);
		testCreature.addEffect(testTimedEffect2);
		assert (testCreature.getEffectiveStat(Attribute.CHARISMA) == initialCharima + 5 * modification);
		IntStream.range(0, 5).forEach(i -> testCreature.nextTurnNotify());
		assert (!testCreature.getEffects().contains(testTimedEffect1));
		assert (testCreature.getEffectiveStat(Attribute.CHARISMA) == initialCharima + 3 * modification);
		IntStream.range(0, 5).forEach(i -> testCreature.nextTurnNotify());
		assert (!testCreature.getEffects().contains(testTimedEffect2));
		assert (testCreature.getEffectiveStat(Attribute.CHARISMA) == initialCharima);
	}
}
