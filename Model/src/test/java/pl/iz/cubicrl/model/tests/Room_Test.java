package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.util.PropertyLoader;

/**
 *
 * @author Ivo
 */
public class Room_Test {

	private int roomEdgeSize;
	private Room testRoom;

	public Room_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
		Integer.parseInt(PropertyLoader.getInstance().loadProperty("cubeEdgeSize"));
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testRoom = new Room(roomEdgeSize,"test");
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testProperInitialization_checkingIfAllFieldsAreInitialized() {
		IntStream.range(0, roomEdgeSize).parallel().forEach(
			x -> IntStream.range(0, roomEdgeSize).parallel().forEach(
				y -> assertNull(testRoom.getFieldAt(new Coords2D(x, y)))));
	}

}
