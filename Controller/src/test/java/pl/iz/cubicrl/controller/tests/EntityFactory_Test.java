/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.tests;

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
import pl.iz.cubicrl.controller.core.NewGameModule;
import pl.iz.cubicrl.controller.factory.EntityFactory;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.field.Portal;
import pl.iz.cubicrl.model.items.Item;

/**
 *
 * @author Ivo
 */
public class EntityFactory_Test {

	@Inject
	IDao dao;

	@Inject
	EntityFactory entityFactory;

	@Inject
	TestFactory factory;

	public EntityFactory_Test() {
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
	public void testLoadingItem() {
		Item entity = factory.getGenericWeapon();
		try {
			dao.save(entity);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Item loadedItem1 = null ;
		try {
			loadedItem1 = entityFactory.loadItem(entity.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Item loadedItem2 = null;
		try {
			loadedItem2 = entityFactory.loadItem(entity.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Items/"+entity.getName()+".xml");
			file.delete();
		}
		assertNotNull(loadedItem1);
		assertNotNull(loadedItem2);
		assertTrue(loadedItem1!=loadedItem2);
	}
	
	

}
