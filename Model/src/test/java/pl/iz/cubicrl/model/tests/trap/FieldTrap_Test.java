/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests.trap;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.creature.HumanoidCreature;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.occurence.FieldTrap;
import pl.iz.cubicrl.model.tests.TestFactory;

/**
 *
 * @author Ivo
 */
public class FieldTrap_Test {

	private FieldTrap testTrap;
	private Creature testCreature;
	private HumanoidCreature testHumanoid;
	private TestFactory factory;
	private PenetrableField testField;

	public FieldTrap_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		factory = TestFactory.getInstance();
		testField = factory.getGenericPenetrableField();
		testTrap = factory.getGenericFieldTrap(0);
		testCreature = factory.getGenericCreature();
		testHumanoid = factory.getGenericHumanoidCreature();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testSteppingIntoTrap() {
		testTrap.addAttack(factory.getWeakAttack(DamageType.BLUNT));
		testField.addTrap(testTrap);
		int initialHP = testCreature.getEffectiveStat(LifeStat.HP);
		testField.accept(testCreature);
		testField.nextTurnNotify();
		assertEquals(initialHP - 1, testCreature.getEffectiveStat(LifeStat.HP));
	}

	@Test
	public void testHumanoidSteppingIntoTrap() {
		testTrap.addAttack(factory.getWeakAttack(DamageType.BLUNT));
		testField.addTrap(testTrap);
		int initialHP = testHumanoid.getEffectiveStat(LifeStat.HP);
		testField.accept(testHumanoid);
		testField.nextTurnNotify();
		assertEquals(initialHP - 1, testHumanoid.getEffectiveStat(LifeStat.HP));
	}
}
