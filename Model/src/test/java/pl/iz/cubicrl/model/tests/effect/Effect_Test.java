package pl.iz.cubicrl.model.tests.effect;

import java.util.Random;
import javafx.util.Pair;
import org.junit.Test;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.tests.CreatureBaseTest;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ivo
 */
public class Effect_Test extends CreatureBaseTest {

	public Effect_Test() {
	}

	@Test
	public void testEffectAddingAndRemoving() {
		Random random = new Random();
		assert (testCreature.getEffects().isEmpty());
		int initialCharima = testCreature.getEffectiveStat(Attribute.CHARISMA);
		int modification = random.nextInt(20);
		Effect testEffect1 = new Effect("test1");
		testEffect1.addModifier(new Pair<>(Attribute.CHARISMA, modification))
			.addModifier(new Pair<>(Attribute.CHARISMA, modification));
		Effect testEffect2 = new Effect("test2");
		testEffect2.addModifier(new Pair<>(Attribute.CHARISMA, modification))
			.addModifier(new Pair<>(Attribute.CHARISMA, modification))
			.addModifier(new Pair<>(Attribute.CHARISMA, modification));
		testCreature.addEffect(testEffect1);
		testCreature.addEffect(testEffect2);
		assert (testCreature.getEffectiveStat(Attribute.CHARISMA) == initialCharima + 5 * modification);
		testCreature.removeEffect(testEffect2);
		testCreature.nextTurnNotify();
		assert (testCreature.getEffectiveStat(Attribute.CHARISMA) == initialCharima + 2 * modification);
		testCreature.removeEffect(testEffect1);
		assert (testCreature.getEffectiveStat(Attribute.CHARISMA) == initialCharima);
	}
}
