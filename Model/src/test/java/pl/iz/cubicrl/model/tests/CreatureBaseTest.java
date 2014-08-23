package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import pl.iz.cubicrl.model.creature.Creature;

/**
 *
 * @author Ivo
 */
public class CreatureBaseTest {

	public Creature testCreature;
	public Random random;

	public CreatureBaseTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testCreature = new Creature("test", new int[]{5, 5, 5, 5, 5, 5}, //Attributes 
			new int[]{1, 1, 1, 1, 1, 1, 1}, //Skills
			new int[]{100, 100, 100, 100}, // LifeStatLimits
			new int[]{10, 10, 10, 10, 10, 10, 10}, //Resistances
			new int[]{0}, //Secondary Stats
			null); 
		random = new Random();
	}

	@After
	public void tearDown() {
	}

}
