/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.tests.generation;

import com.google.inject.Guice;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.iz.cubicrl.controller.core.NewGameModule;
import pl.iz.cubicrl.controller.core.RoomGenerator;
import pl.iz.cubicrl.controller.factory.EntityFactory;
import pl.iz.cubicrl.controller.tests.TestFactory;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.core.Coords3D;
import pl.iz.cubicrl.model.core.Room;

/**
 *
 * @author Ivo
 */
public class RoomGenerator_Test {
	
	@Inject
	IDao dao;

	@Inject
	EntityFactory entityFactory;

	@Inject
	TestFactory factory;
	
	@Inject
	RoomGenerator roomGenerator;
	
	public RoomGenerator_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		Guice.createInjector(new NewGameModule()).injectMembers(this);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void generatingRoomAndCheckingFields() {
		Coords3D newRoomCoords = new Coords3D(5,5,5);
		Room room = null;
		try {
			room = roomGenerator.generateStandardRoom(newRoomCoords);
		} catch (IOException ex) {
			Logger.getLogger(RoomGenerator_Test.class.getName()).log(Level.SEVERE, null, ex);
			fail(ex+ex.getMessage());
		}
		room.getFieldsAsParallelStream().forEach(f -> assertNotNull(f));
	}
}
