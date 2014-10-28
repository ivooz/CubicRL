/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.tests;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
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
import pl.iz.cubicrl.controller.core.LoadGameModule;
import pl.iz.cubicrl.controller.dao.DaoXStream;
import pl.iz.cubicrl.controller.factory.EntityFactory;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.core.GameWorld;

/**
 *
 * @author Ivo
 */
public class GameLoading_Test {

	public GameLoading_Test() {
	}

	@Inject
	private GameWorld gameWorld;
	@Inject
	private IDao dao;
	String path = "../Saves/test.xml";

	@Before
	public void setUp() {
		Guice.createInjector(getTestModule()).injectMembers(this);
	}
	
	private void saveWorld() throws IOException {
		dao.save(gameWorld, path);
	}

	private Module getTestModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(IDao.class).to(DaoXStream.class);
			}
		};
	}

	@Test
	public void testLoading() {
		try {
			saveWorld();
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		GameController controller = Guice.createInjector(new LoadGameModule(path))
			.getInstance(GameController.class);
		GameWorld deserializedWorld = controller.getGameWorld();
		assertFalse(deserializedWorld==gameWorld);
		assertNotNull(deserializedWorld.getCube());
		assertNotNull(deserializedWorld.getEventBus());
		assertNotNull(deserializedWorld.getPlayer());
		assertTrue(deserializedWorld.getPlayer()==controller.getPlayer());
		assertTrue(deserializedWorld.getEventBus()==controller.getGameEventBus());
	}
}
