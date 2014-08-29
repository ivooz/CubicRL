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
import com.google.inject.Provides;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.iz.cubicrl.controller.dao.DaoXStream;
import pl.iz.cubicrl.controller.factory.CreatureFactory;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.creature.Player;
import pl.iz.cubicrl.model.util.PropertyLoader;

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
		dao.save(gameWorld,path);
		GameWorld deserializedWorld = dao.loadWorld(path);
		
	}

}
