package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import pl.iz.cubicrl.model.tests.TestFactory;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import pl.iz.cubicrl.model.creature.HumanoidCreature;

/**
 *
 * @author Ivo
 */
public class HumanoidCreatureBaseTest {

	public HumanoidCreature testCreature;
	public Random random;

	public HumanoidCreatureBaseTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testCreature = TestFactory.getInstance().getGenericHumanoidCreature();
		random = new Random();
	}

	@After
	public void tearDown() {
	}
}
