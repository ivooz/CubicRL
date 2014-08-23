package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pl.iz.cubicrl.model.tests.TestFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.items.ItemWithEffects;

/**
 *
 * @author Ivo
 */
public class PenetrableField_Test {

	PenetrableField testField;

	public PenetrableField_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testField = new PenetrableField("test", null, null);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void initializationTest_checkingIfAllFieldsAreInitializedAsExpected() {
		assert (testField.getName().equals("test"));
		assertFalse(testField.hasResident());
		assertNotNull(testField.getItems());
		assertNull(testField.getResident());
	}

	@Test
	public void addingRemovingItemTest_checkingItemPlacementSystem() {
		Item testItem = new ItemWithEffects("test", null);
		testField.addItem(testItem);
		assert (testField.hasItems());
		assert (testField.getTopItem() == testItem);
		testField.removeItem(testItem);
		assertFalse(testField.hasItems());
	}

	@Test
	public void addingRemovingResidentTest_checkingProperResidentFieldBehavior() {
		Creature testCreature = TestFactory.getInstance().getGenericCreature();
		testField.addResident(testCreature);
		assert (testField.hasResident());
		assert (testField.getResident() == testCreature);
		testField.removeResident();
		assertFalse(testField.hasResident());
	}
}
