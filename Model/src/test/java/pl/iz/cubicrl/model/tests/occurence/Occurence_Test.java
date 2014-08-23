package pl.iz.cubicrl.model.tests.occurence;

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
import pl.iz.cubicrl.model.occurence.Occurence;

/**
 *
 * @author Ivo
 */
public class Occurence_Test {
	
	private Field testField;
	
	public Occurence_Test() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		testField = new Field(null, null, null);
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testAddingRemovingOccurence() {
		Occurence testOccurence1 = new Occurence("test2",null);
		Occurence testOccurence2 = new Occurence("test1",null);
		testField.addOccurence(testOccurence1);
		testField.addOccurence(testOccurence2);
		assertTrue(testField.getTopOccurence()==testOccurence2);
		assertEquals(testField.getOccurences().size(),2);
	}
}
