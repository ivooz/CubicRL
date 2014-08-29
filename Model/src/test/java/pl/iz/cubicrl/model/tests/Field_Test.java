package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.occurence.Occurence;

/**
 *
 * @author Ivo
 */
public class Field_Test {

	Field testField;

	public Field_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testField = new Field("test", new Coords2D(6, 6), new Coords2D(7, 7),TestFactory.getInstance().eventBus);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testInitialization_creatingInstanceAndCheckingIfFieldsAreInitializedAsExpected() {
		assert (testField.getRoomCoords().x == 6 && testField.getRoomCoords().y == 6);
		assert (testField.getSpriteSheetCoords().x == 7 && testField.getSpriteSheetCoords().y == 7);
		assert (testField.getOccurences().size() == 0);
		assert (testField.getName().equals("test"));
		assertFalse(testField.isVisible());
		assertFalse(testField.isVisited());
	}

	@Test
	public void testMakeVisible_makingFieldVisibleAndCheckingIfItWasAlsoVisited() {
		testField.makeVisible();
		assert (testField.isVisible() && testField.isVisited());
	}

	@Test
	public void testFieldOccurence_() {
		int initialOccurenceCount = testField.getOccurences().size();
		Occurence occurence = new Occurence(null, null);
		testField.addOccurence(occurence);
		assert (testField.getOccurences().size() == initialOccurenceCount + 1);
		assert (testField.getTopOccurence().equals(occurence));
		testField.removeOccurence(occurence);
		assert (testField.getOccurences().size() == initialOccurenceCount);
	}
}
