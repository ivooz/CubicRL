package pl.iz.cubicrl.model.tests.item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pl.iz.cubicrl.model.tests.TestFactory;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.creature.HumanoidCreature;
import pl.iz.cubicrl.model.items.Weapon;
import pl.iz.cubicrl.model.tests.TestFactory;

/**
 *
 * @author Ivo
 */
public class Weapon_Test {
	
	HumanoidCreature testCreature;
	Weapon testWeapon;
	
	public Weapon_Test() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		testCreature=TestFactory.getInstance().getGenericHumanoidCreature();
		testWeapon=TestFactory.getInstance().getGenericWeapon();
	}
	
	@After
	public void tearDown() {
	}

        @Test
	public void damageTest_testingWeaponDamage() {
		int lowerBound = testWeapon.getLowerDamageBound();
		int upperBound = testWeapon.getUpperDamageBound();
		IntStream.range(1, 100).forEach(
			i -> {
				int damage = testWeapon.getDamage();
				assertTrue(damage>=lowerBound && damage <=upperBound);
			}
		);
	}
}
