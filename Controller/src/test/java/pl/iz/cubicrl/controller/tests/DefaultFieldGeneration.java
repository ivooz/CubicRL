/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.tests;

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
import pl.iz.cubicrl.controller.factory.EntityFactory;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.field.Portal;

/**
 *
 * @author Ivo
 */
public class DefaultFieldGeneration {

	@Inject
	IDao dao;

	@Inject
	EntityFactory entityFactory;
	
	@Inject
	TestFactory factory;

	public DefaultFieldGeneration() {
	}

	@Before
	public void setUp() {
		Guice.createInjector(new NewGameModule()).injectMembers(this);
	}

	@Test
	public void createDefaultFields() {
		Field field = new Field("standardWall", new Coords2D(1, 1), null);
		PenetrableField penetrableField = new PenetrableField("standardFloor", null, new Coords2D(1, 2), null);
		Portal portal = new Portal(null, "standardPortal", null, new Coords2D(1, 3), null);
		try {
			dao.save(field);
			dao.save(penetrableField);
			dao.save(portal);
		} catch (IOException ex) {
			System.out.println(ex);
			fail();
		}
	}
	
	

}
