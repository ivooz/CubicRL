/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.tests;

import com.google.common.io.Files;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.controller.core.GameController;
import pl.iz.cubicrl.controller.core.ItemActionExecutor;
import pl.iz.cubicrl.controller.core.ScriptLoader;
import pl.iz.cubicrl.controller.dao.DaoXStream;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.api.events.GameEvent;
import pl.iz.cubicrl.model.api.events.GameEventListener;
import pl.iz.cubicrl.model.api.events.RoomChangeEvent;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.enums.Direction;

/**
 *
 * @author Ivo
 */
public class GenericEventBus_Test {

	@Inject
	GameEventBus eventBus;

	@Inject
	TestFactory factory;

	@Inject
	GameController gameController;

	@Inject
	ItemActionExecutor itemActionExecutor;

	@Inject
	ScriptLoader scriptLoader;

	public GenericEventBus_Test() {
	}
	String testScript = "function process1(GameEvent) { \n"
		+ "	GameEvent.getGameController().getGameWorld().nextTurnNotify();\n"
		+ "};\n"
		+ "\n"
		+ "registerListener(getListenerClass(\"GameEvent\"),process1);\n"
		+ "function process2(GameEvent) {  \n"
		+ "	GameEvent.getGameController().getGameWorld().nextTurnNotify();\n"
		+ "};\n"
		+ "registerListener(getListenerClass(\"RoomChangeEvent\"),process2);";

	@Before
	public void setUp() throws IOException {
		File file = new File("../Scripts/test.js");
		Files.write(testScript, file, Charset.forName("UTF-8"));
		Guice.createInjector(getTestModule()).injectMembers(this);
		try {
			scriptLoader.initializeScripts();
		} catch (IOException | ScriptException ex) {
			Logger.getLogger(GenericEventBus_Test.class.getName()).log(Level.SEVERE, null, ex);
			fail(ex + ex.getMessage());
		}
		file.delete();
	}

	private Module getTestModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(IDao.class).to(DaoXStream.class);
			}
		};
	}

	class DummyFlag {

		private boolean flag = false;

		public void set() {
			flag = true;
		}

		public void reset() {
			flag = false;
		}

		public boolean wasNotified() {
			return flag;
		}
	}

	@Test
	public void testNashornCustomListeners() {
		Room room = factory.getGenericRoom();
		int initialTurnCount = gameController.getGameWorld().getTurnCount();
		eventBus.publish(new GameEvent(null));
		assertEquals(initialTurnCount + 1, gameController.getGameWorld().getTurnCount());
		eventBus.publish(new RoomChangeEvent(room, Direction.NORTH, null, null));
		assertEquals(initialTurnCount + 3, gameController.getGameWorld().getTurnCount());
	}
}
