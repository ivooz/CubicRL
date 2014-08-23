package pl.iz.cubicrl.model.tests.item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.stream.IntStream;
import javafx.util.Pair;
import org.junit.Test;
import pl.iz.cubicrl.model.effects.TimedEffect;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.items.ConsumableItem;
import pl.iz.cubicrl.model.tests.CreatureBaseTest;

/**
 *
 * @author Ivo
 */
public class ConsumableItem_Test extends CreatureBaseTest {

	@Test
	public void testModifyingBaseStatsTest() {
		int statIncrease = random.nextInt(10);
		int initialCharisma = testCreature.getEffectiveStat(Attribute.CHARISMA);
		ConsumableItem charismaBoostPotion = new ConsumableItem("test", null);
		TimedEffect effect = new TimedEffect(null, 4);
		effect.addModifier(new Pair(Attribute.CHARISMA, statIncrease));
		testCreature.accept(charismaBoostPotion.addEffect(effect));
		assert (testCreature.getEffectiveStat(Attribute.CHARISMA) == initialCharisma + statIncrease);
		IntStream.range(0, 4).forEach(i -> testCreature.nextTurnNotify());
		assert (testCreature.getEffectiveStat(Attribute.CHARISMA) == initialCharisma);
		assert (!testCreature.getEffects().contains(effect));
	}
}
