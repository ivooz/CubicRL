/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.iz.cubicrl.model.core.TrapMap;
import pl.iz.cubicrl.model.core.PropertyLoader;

/**
 *
 * @author Ivo
 */
public class TrapMap_Test {

	TrapMap trapMap;
	PropertyLoader propertyLoader;

	public TrapMap_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		this.propertyLoader = new PropertyLoader();
		trapMap = new TrapMap(propertyLoader);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testTrapMapInitialization() {
		ArrayList<String> traps = new ArrayList<>();
		ArrayList<String> uniques = new ArrayList<>();
		traps.add("1");
		traps.add("2");
		traps.add("3");
		traps.add("4");
		uniques.add("5");
		uniques.add("6");
		uniques.add("7");
		uniques.add("8");
		trapMap.initialize(traps, uniques);
		int trapsPerType = Integer.parseInt(propertyLoader
			.loadProperty("trapsPerType"));
		assertEquals(trapsPerType * 4, trapMap.getMappedTraps().keySet().size());
		assertEquals(4, trapMap.getMappedRooms().keySet().size());
	}

}
