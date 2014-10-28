package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.creature.EnumMap;
import pl.iz.cubicrl.model.enums.Attribute;

/**
 *
 * @author Ivo
 */
public class BaseMap_Test {

	EnumMap testAttrubuteMap;

	public BaseMap_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testAttrubuteMap = new EnumMap<>(Attribute.class, new int[]{5, 5, 5, 5, 5, 5});
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testProperInitialization() {
		assert (testAttrubuteMap.getStatValue(Attribute.STRENGTH) == 5);
		assert (testAttrubuteMap.getStatValue(Attribute.DEXTERITY) == 5);
		assert (testAttrubuteMap.getStatValue(Attribute.SPEED) == 5);
		assert (testAttrubuteMap.getStatValue(Attribute.CONSTITUTION) == 5);
		assert (testAttrubuteMap.getStatValue(Attribute.INTELLIGENCE) == 5);
		assert (testAttrubuteMap.getStatValue(Attribute.CHARISMA) == 5);
	}

	@Test
	public void testModifyAttributeValue() {
		testAttrubuteMap.modifyStat(Attribute.STRENGTH, -5);
		assert (testAttrubuteMap.getStatValue(Attribute.STRENGTH) == 0);
		testAttrubuteMap.modifyStat(Attribute.DEXTERITY, 5);
		assert (testAttrubuteMap.getStatValue(Attribute.DEXTERITY) == 10);
	}
}
