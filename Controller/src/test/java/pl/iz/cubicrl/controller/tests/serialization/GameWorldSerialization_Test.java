/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.tests.serialization;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provides;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.iz.cubicrl.controller.dao.DaoXStream;
import pl.iz.cubicrl.controller.factory.EntityFactory;
import pl.iz.cubicrl.controller.tests.DummySubscriber;
import pl.iz.cubicrl.model.api.events.GameEvent;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.creature.Player;
import pl.iz.cubicrl.model.core.PropertyLoader;

/**
 *
 * @author Ivo
 */
public class GameWorldSerialization_Test {

	public GameWorldSerialization_Test() {
	}

	@Inject
	private GameWorld gameWorld;
	@Inject
	private DaoXStream dao;

	@Before
	public void setUp() {
		Guice.createInjector(getTestModule()).injectMembers(this);
	}

	private Module getTestModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
			}
		};
	}
	
	

	@Test
	public void testSaving_SerializingDeserializingComparingDeleting() {
		String path = "../Saves/test.xml";
		DummySubscriber dummy = new DummySubscriber();
		try {
			gameWorld.getEventBus().subscribe(dummy);
			dao.save(gameWorld, path);
			GameWorld deserializedWorld = dao.loadWorld(path);
			deserializedWorld.getEventBus().publish(new GameEvent(null));
			assertTrue(((DummySubscriber)deserializedWorld.getEventBus().getSubscribers().get(0)).wasNotified);
		} catch (IOException ex) {
			System.out.println("fail! "+ex.getMessage());
			fail();
		}
		new File(path).delete();
	}

}
