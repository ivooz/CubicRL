/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.factory;

import com.google.inject.Singleton;
import com.rits.cloning.Cloner;
import java.io.IOException;
import java.util.HashMap;
import javax.inject.Inject;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.occurrence.Occurrence;
import pl.iz.cubicrl.model.trap.RoomTrap;

/**
 *
 * @author Ivo
 */
@Singleton
public class EntityFactory {

	private final GameEventBus eventBus;
	private final IDao dao;
	private final HashMap<String, Occurrence> loadedOccurrences;
	private final HashMap<String, Field> loadedFields;
	private final HashMap<String, Item> loadedItems;
	private final HashMap<String, Creature> loadedCreatures;
	private final HashMap<String, Effect> loadedEffects;
	private final HashMap<String, Room> loadedRooms;
	private final HashMap<String, RoomTrap> loadedRoomTraps;

	@Inject
	public EntityFactory(GameEventBus eventBus, IDao dao) {
		this.dao = dao;
		this.eventBus = eventBus;
		loadedCreatures = new HashMap<>();
		loadedEffects = new HashMap<>();
		loadedItems = new HashMap<>();
		loadedOccurrences = new HashMap<>();
		loadedFields = new HashMap<>();
		loadedRooms = new HashMap<>();
		loadedRoomTraps = new HashMap<>();
	}

	//TODO: think about a way to avoid repetition
	
	public Occurrence loadOccurrence(String name) throws IOException {
		if (!loadedOccurrences.values().parallelStream().anyMatch(v -> v.getName().equals(name))) {
			loadedOccurrences.put(name, dao.loadOccurrence(name));
		}
		return new Cloner().deepClone(loadedOccurrences.get(name));
	}

	public Field loadField(String name) throws IOException {
		if (!loadedFields.values().parallelStream().anyMatch(v -> v.getName().equals(name))) {
			loadedFields.put(name, dao.loadField(name));
		}
		Field field = new Cloner().deepClone(loadedFields.get(name));
		//Game event bus is null for prototypes
		field.setEventBus(eventBus);
		return field;
	}

	public Item loadItem(String name) throws IOException {
		if (!loadedItems.values().parallelStream().anyMatch(v -> v.getName().equals(name))) {
			loadedItems.put(name, dao.loadItem(name));
		}
		return new Cloner().deepClone(loadedItems.get(name));
	}

	public Creature loadCreature(String name) throws IOException {
		if (!loadedCreatures.values().parallelStream().anyMatch(v -> v.getName().equals(name))) {
			loadedCreatures.put(name, dao.loadCreature(name));
		}
		//Game event bus is null for prototypes
		Creature creature = new Cloner().deepClone(loadedCreatures.get(name));
		creature.setEventBus(eventBus);
		return creature;
	}

	public Effect loadEffect(String name) throws IOException {
		if (!loadedEffects.values().parallelStream().anyMatch(v -> v.getName().equals(name))) {
			loadedEffects.put(name, dao.loadEffect(name));
		}
		return new Cloner().deepClone(loadedEffects.get(name));
	}
	
	public Room loadRoom(String name) throws IOException {
		if (!loadedRooms.values().parallelStream().anyMatch(v -> v.getName().equals(name))) {
			loadedRooms.put(name, dao.loadRoom(name));
		}
		return new Cloner().deepClone(loadedRooms.get(name));
	}
	public RoomTrap loadRoomTrap(String name) throws IOException {
		if (!loadedRoomTraps.values().parallelStream().anyMatch(v -> v.getName().equals(name))) {
			loadedRoomTraps.put(name, dao.loadRoomTrap(name));
		}
		return new Cloner().deepClone(loadedRoomTraps.get(name));
	}
}
