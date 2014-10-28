package pl.iz.cubicrl.model.tests.occurrence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.effects.DamagingTimedEffect;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.occurrence.OccurrenceWithEffects;
import pl.iz.cubicrl.model.tests.CreatureBaseTest;

/**
 *
 * @author Ivo
 */
public class OccurrenceWithEffects_Test extends CreatureBaseTest {

	PenetrableField testField;
	OccurrenceWithEffects testOccurrence;

	@Override
	public void setUp() {
		super.setUp();
		testField = new PenetrableField(null, new Coords2D(1, 1), null, null);
		testOccurrence = new OccurrenceWithEffects("test1", null);
		DamagingTimedEffect timedEffect = new DamagingTimedEffect("test1", DamageType.HEAT, 2, 5);
		testOccurrence.addEffect(timedEffect);

	}

	@Test
	public void testAddingOccurrenceToField_CheckingItsEffectOnResidingCreature() {
		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
		testField.addResident(testCreature);
		testField.addOccurrence(testOccurrence);
		testField.nextTurnNotify();
		assertFalse(testCreature.getEffects().isEmpty());
		testCreature.nextTurnNotify();
		assertTrue(testCreature.getEffectiveStat(LifeStat.HP) < initialHP);
	}

	@Test
	public void checkingOccurrenceDuplicationSafeguard_checkingIfResidentHasDuplicateEffects() {
		testField.addResident(testCreature);
		testField.addOccurrence(testOccurrence);
		testField.addOccurrence(testOccurrence);
		testField.nextTurnNotify();
		assertTrue(testCreature.getEffects().size() == 1);
	}

	@Test
	public void checkingResidentEnteringField_CheckingEffectOnCreature() {
		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
		testField.addOccurrence(testOccurrence);
		testField.addResident(testCreature);
		testField.nextTurnNotify();
		assertTrue(!testCreature.getEffects().isEmpty());
		testCreature.nextTurnNotify();
		assertTrue(testCreature.getEffectiveStat(LifeStat.HP) < initialHP);
	}

}
