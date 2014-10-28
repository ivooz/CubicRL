package pl.iz.cubicrl.model.tests.occurrence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.stream.IntStream;
import javafx.util.Pair;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import pl.iz.cubicrl.model.effects.TimedEffect;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.occurrence.TimedOccurrenceWithEffects;
import pl.iz.cubicrl.model.tests.CreatureBaseTest;
import pl.iz.cubicrl.model.tests.TestFactory;

/**
 *
 * @author Ivo
 */
public class TimedOccurrenceWithEffects_Test extends CreatureBaseTest {

	private PenetrableField penetrableField;

	@Before
	public void setUp() {
		super.setUp();
		penetrableField = TestFactory.getInstance().getGenericPenetrableField();
	}

	@Test
	public void testAddingOccurrence_addingOccurrenceAndCheckingItsLifetime() {
		int timer = random.nextInt(10) + 15;
		TimedOccurrenceWithEffects testOcc = new TimedOccurrenceWithEffects("fog", null, timer);
		TimedEffect testEffect = new TimedEffect("test", 2);
		testEffect.addModifier(new Pair(Attribute.STRENGTH, 5));
		testOcc.addEffect(testEffect);
		penetrableField.addOccurrence(testOcc);
		assertEquals(1, penetrableField.getOccurrences().size());
		IntStream.range(0, timer + 1).forEach(i -> penetrableField.nextTurnNotify());
		assertFalse(penetrableField.hasOccurrence());
	}

}
