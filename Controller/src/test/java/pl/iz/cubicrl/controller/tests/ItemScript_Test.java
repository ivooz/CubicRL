/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.tests;

import com.google.common.io.Files;
import com.google.inject.Guice;
import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.iz.cubicrl.controller.core.GameController;
import pl.iz.cubicrl.controller.core.ItemActionExecutor;
import pl.iz.cubicrl.controller.core.NewGameModule;
import pl.iz.cubicrl.controller.tests.initialization.GameInitialization_Test;
import pl.iz.cubicrl.model.api.events.ItemUsedEvent;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.creature.Player;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.items.ConsumableItem;
import pl.iz.cubicrl.model.items.Item;

/**
 *
 * @author Ivo
 */
public class ItemScript_Test {

	@Inject
	GameController gameController;
	@Inject
	ItemActionExecutor actionExecutor;
	@Inject
	GameEventBus eventBus;
	@Inject
	Player player;

	String testScript = "function processSimpleItem(GameEvent) {\n"
		+ "	GameEvent.getField().getResident().modifyBaseStat(\"HP\",-10);\n"
		+ "}\n"
		+ "registerItemAction(\"simpleItem\",processSimpleItem);	";

	public ItemScript_Test() {
	}

	@Before
	public void setUp() {
		try {
			File file = new File("../Scripts/test.js");
			Files.write(testScript, file, Charset.forName("UTF-8"));
			Guice.createInjector(new NewGameModule()).injectMembers(this);
			gameController.initializeNewGame();
//			file.delete();
		} catch (IOException | ScriptException ex) {
			fail(ex.toString());
			Logger.getLogger(ItemScript_Test.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void testSimpleItem() {
		Item item = new ConsumableItem("simpleItem", null);
		int initialHP = player.getEffectiveStat(LifeStat.HP);
		eventBus.publish(new ItemUsedEvent(item, player, player.getField(),
			"Testing simpleItem script"));
		assertTrue(player.getField().getResident() == player);
		assertEquals(initialHP - 10, player.getEffectiveStat(LifeStat.HP));
	}
}
