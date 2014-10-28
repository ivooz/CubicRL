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
import javafx.scene.input.KeyCode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.iz.cubicrl.controller.core.GameController;
import pl.iz.cubicrl.controller.core.NewGameModule;
import pl.iz.cubicrl.controller.dao.DaoXStream;
import pl.iz.cubicrl.controller.tests.DefaultFieldGeneration;
import pl.iz.cubicrl.controller.tests.TestFactory;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.api.events.GameEvent;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.creature.HumanoidCreature;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.occurrence.FieldTrap;
import pl.iz.cubicrl.model.occurrence.Occurrence;

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
		assertTrue(factory.getEventBus()==gameController.getGameEventBus());
		assertTrue(gameController.getGameEventBus()==((DaoXStream)dao).getEventBus());
		try {
			field.getEventBus().getSubscribers().forEach(i -> System.out.println(i));
			dao.save(field);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Field deserializedField = null;
		try {
			deserializedField = dao.loadField(field.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Fields/" + field.getName() + ".xml");
			file.delete();
		}
		assertNotNull(deserializedField.getEventBus());
	}

	@Test
	public void testEffectSerialization_SavingLoadingComparing() {
		Effect effect = factory.getWeakDamagingEffect();
		try {
			dao.save(effect);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Effect deserializedEffect = null;
		try {
			deserializedEffect = dao.loadEffect(effect.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Effects/" + effect.getName() + ".xml");
			file.delete();
		}
	}

	@Test
	public void testItemSerialization_SavingLoadingComparing() {
		Item field = factory.getGenericEquipableItem();
		try {
			dao.save(field);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Item deserializedItem = null;
		try {
			deserializedItem = dao.loadItem(field.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Items/" + field.getName() + ".xml");
			file.delete();
		}
	}

	@Test
	public void testOccurrenceSerialization_SavingLoadingComparing() {
		Occurrence occurrence = factory.getGenericOccurrenceWithEffects();
		try {
			dao.save(occurrence);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Occurrence deserializedOccurrence = null;
		try {
			deserializedOccurrence = dao.loadOccurrence(occurrence.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Occurrences/" + occurrence.getName() + ".xml");
			file.delete();
		}
	}

	@Test
	public void testCreatureSerialization_SavingLoadingComparing() {
		Creature creature = factory.getGenericCreature();
		try {
			dao.save(creature);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Creature deserializedCreature = null;
		try {
			deserializedCreature = dao.loadCreature(creature.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Creatures/" + creature.getName() + ".xml");
			file.delete();
		}
	}

	@Test
	public void testHumanoidCreatureSerialization_SavingLoadingComparing() {
		HumanoidCreature creature = factory.getGenericHumanoidCreature();
		try {
			dao.save(creature);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		HumanoidCreature deserializedHumanoidCreature = null;
		try {
			deserializedHumanoidCreature
				= (HumanoidCreature) dao.loadCreature(creature.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Creatures/" + creature.getName() + ".xml");
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
		PenetrableField deserializedPenetrableField = null;
		try {
			deserializedPenetrableField = (PenetrableField) dao.loadField(field.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Fields/" + field.getName() + ".xml");
			file.delete();
		}
		assertNotNull(deserializedPenetrableField.getEventBus());
	}

	@Test
	public void testRoomSerialization_SavingLoadingComparing() {
		Coords2D testedCoords = new Coords2D(0,0);
		Room room = factory.getGenericRoom();
		HumanoidCreature humanoid = factory.getGenericHumanoidCreature();
		Item item = factory.getGenericEquipableItem();
		humanoid.addItemToInventory(item);
		room.getFieldAt(testedCoords).accept(humanoid);
		FieldTrap trap = factory.getGenericFieldTrap(20);
		room.getFieldAt(testedCoords).addOccurrence(trap);
		room.addRoomTrap(factory.getGenericRoomTrap());
		try {
			dao.save(room);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		Room deserializedRoom = null;
		try {
			deserializedRoom = (Room) dao.loadRoom(room.getName());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} finally {
			File file = new File("../Assets/Rooms/" + room.getName() + ".xml");
			//file.delete();
		}
		//Checking if all fields have the same reference to eventBus
		GameEventBus bus = deserializedRoom.getFieldAt(new Coords2D(0,0)).getEventBus();
		deserializedRoom.getFieldsAsParallelStream()
			.forEach( f -> assertTrue(f.getEventBus() == bus));
	}
	
	@Test
	public void createDefaultTrap() {
		try {
			dao.save(factory.getGenericRoomTrap());
		} catch (IOException ex) {
			fail(ex.toString());
			Logger.getLogger(DefaultFieldGeneration.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Test
	public void createDefaultRoom() {
		try {
			dao.save(factory.getGenericRoom());
		} catch (IOException ex) {
			fail(ex.toString());
			Logger.getLogger(DefaultFieldGeneration.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
