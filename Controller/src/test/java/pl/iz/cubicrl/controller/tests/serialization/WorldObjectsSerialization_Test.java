/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.tests.serialization;

import com.google.inject.Guice;
import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.iz.cubicrl.controller.core.GameController;
import pl.iz.cubicrl.controller.core.NewGameModule;
import pl.iz.cubicrl.controller.tests.TestFactory;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.PenetrableField;

/**
 *
 * @author Ivo
 */
public class WorldObjectsSerialization_Test {
	
	@Inject
	TestFactory factory; 
	@Inject
	IDao dao;
	@Inject
	GameController gameController;
	
	public WorldObjectsSerialization_Test() {
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
	public void testFieldSerialization_SavingLoadingComparing() {
		Field field = factory.getGenericField();
		try {
			dao.save(field);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Field deserializedField=null;
		try {
			deserializedField = dao.loadField(field.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Fields/"+field.getName()+".xml");
			file.delete();
		}
		assertNotNull(deserializedField.getEventBus());
		assertTrue(deserializedField.getEventBus()==gameController.getGameEventBus());
	}
	@Test
	public void testCreatureSerialization_SavingLoadingComparing() {
		Creature creature = factory.getGenericCreature();
		try {
			dao.save(creature);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Creature deserializedCreature=null;
		try {
			deserializedCreature = dao.loadCreature(creature.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Creatures/"+creature.getName()+".xml");
			file.delete();
		}
	}
	
	@Test
	public void testPenetrablePenetrableFieldSerialization_SavingLoadingComparing() {
		PenetrableField field = factory.getGenericPenetrableField();
		try {
			dao.save(field);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		PenetrableField deserializedPenetrableField=null;
		try {
			deserializedPenetrableField = (PenetrableField) dao.loadField(field.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Fields/"+field.getName()+".xml");
			file.delete();
		}
		assertNotNull(deserializedPenetrableField.getEventBus());
		assertTrue(deserializedPenetrableField.getEventBus()==gameController.getGameEventBus());
	}
}
