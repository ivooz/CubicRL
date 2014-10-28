package pl.iz.cubicrl.model.tests.occurrence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.occurrence.Occurrence;

/**
 *
 * @author Ivo
 */
public class Occurrence_Test {

	private Field testField;

	public Occurrence_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testField = new Field(null, null, null, null);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testAddingRemovingOccurrence() {
		Occurrence testOccurrence1 = new Occurrence("test2", null);
		Occurrence testOccurrence2 = new Occurrence("test1", null);
		testField.addOccurrence(testOccurrence1);
		testField.addOccurrence(testOccurrence2);
		assertTrue(testField.getTopOccurrence() == testOccurrence2);
		assertEquals(testField.getOccurrences().size(), 2);
	}
}
