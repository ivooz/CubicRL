/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests.attack;

import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.creature.HumanoidCreature;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.enums.SecondaryStat;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.items.Weapon;
import pl.iz.cubicrl.model.tests.TestFactory;

/**
 *
 * @author Ivo
 */
public class Attack_Test {

	private HumanoidCreature testHumanoid1;
	private Creature testCreature1;
	private PenetrableField testField1;
	private PenetrableField testField2;
	private Weapon weapon;

	public Attack_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		TestFactory factory = TestFactory.getInstance();
		testCreature1 = factory.getGenericCreature();
		testHumanoid1 = factory.getGenericHumanoidCreature();
		testField1 = factory.getGenericPenetrableField();
		testField2 = factory.getGenericPenetrableField();
		testField1.accept(testHumanoid1);
		testField2.accept(testCreature1);
		weapon = new Weapon(DamageType.BLUNT, 1000, Integer.MAX_VALUE, "superWeapon", null, null);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testAttackingWithSuperWeapon() {
		testHumanoid1.equip(weapon);
		testHumanoid1.modifyBaseStat(Attribute.DEXTERITY, 10000);
		testHumanoid1.visit(testField2);
		//This attack must kill instantly
		assertEquals(0, testCreature1.getEffectiveStat(LifeStat.HP));
	}

	@Test
	public void testAttackingSomeoneWithSuperDodge() {
		//Super dodge capabilities
		testHumanoid1.modifyBaseStat(Attribute.SPEED, 10000);
		int initialHP = testHumanoid1.getEffectiveStat(LifeStat.HP);
		IntStream.range(0, 1000).forEach(
			i -> {
				testCreature1.visit(testField1);
				//This attack must always miss
				assertEquals(initialHP, testHumanoid1.getEffectiveStat(LifeStat.HP));
			}
		);
	}

	@Test
	public void testAttackingSomeoneWithSuperAC() {
		//Super AC
		testHumanoid1.modifyBaseStat(SecondaryStat.AC, 10000);
		int initialHP = testHumanoid1.getEffectiveStat(LifeStat.HP);
		IntStream.range(0, 1000).forEach(
			i -> {
				testCreature1.visit(testField1);
				//This attack must always be deflected
				assertEquals(initialHP, testHumanoid1.getEffectiveStat(LifeStat.HP));
			}
		);
	}

	@Test
	public void testWeaponEffectHumanoidvsCrea() {
		Effect effect = new Effect("test");
		weapon.addEffect(effect);
		testHumanoid1.equip(weapon);
		//Attack must hit
		testHumanoid1.modifyBaseStat(Attribute.DEXTERITY, 10000);
		testHumanoid1.visit(testField2);
		//This attack must kill instantly
		assertTrue(testCreature1.getEffects().contains(effect));
	}

	@Test
	public void testWeaponEffectHumanoidVsHumanoid() {
		HumanoidCreature testHumanoid2 = TestFactory.getInstance().getGenericHumanoidCreature();
		PenetrableField testField3 = TestFactory.getInstance().getGenericPenetrableField();
		testField3.accept(testHumanoid2);
		Effect effect = new Effect("test");
		weapon.addEffect(effect);
		testHumanoid1.equip(weapon);
		//Attack must hit
		testHumanoid1.modifyBaseStat(Attribute.DEXTERITY, 10000);
		testHumanoid1.visit(testField3);
		//This attack must kill instantly
		assertTrue(testHumanoid2.getEffects().contains(effect));
	}

}
