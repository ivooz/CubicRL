package pl.iz.cubicrl.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.core.Room;

/**
 *
 * @author Ivo
 */
public class RoomBaseTest {

	public Room room;

	public RoomBaseTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		room = new Room(10,"test");
		Field[][] fields = room.getFields();
		IntStream.range(0, 10).forEach(x
			-> IntStream.range(0, 10).forEach(y 
				-> fields[x][y] = new PenetrableField("test", new Coords2D(x, y),new Coords2D(x, y))));
	}

	@After
	public void tearDown() {
	}

}
