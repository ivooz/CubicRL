/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.dao;

import com.google.common.io.Files;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.GameWorld;
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
public class DaoXStream implements IDao {

	XStream xStream;
	GameEventBus eventBus;

	@Inject
	public DaoXStream(XStream xStream, GameEventBus eventBus) {
		this.xStream = xStream;
		this.eventBus = eventBus;
	}

	@Override
	public void save(Creature creature) throws IOException {
		writeStringToFile("../Assets/Creatures/" + creature.getName() + ".xml",
			xStream.toXML(creature));
	}

	@Override
	public void save(Field field) throws IOException {
		writeStringToFile("../Assets/Fields/" + field.getName() + ".xml",
			xStream.toXML(field));
	}

	@Override
	public void save(Occurrence occurrence) throws IOException {
		writeStringToFile("../Assets/Occurrences/" + occurrence.getName() + ".xml",
			xStream.toXML(occurrence));
	}

	@Override
	public void save(Item item) throws IOException {
		writeStringToFile("../Assets/Items/" + item.getName() + ".xml",
			xStream.toXML(item));
	}

	@Override
	public void save(GameWorld world, String path) throws IOException {
		writeStringToFile(path, xStream.toXML(world));
	}

	@Override
	public void save(Effect effect) throws IOException {
		writeStringToFile("../Assets/Effects/" + effect.getName() + ".xml",
			xStream.toXML(effect));
	}

	@Override
	public void save(Room room) throws IOException {
		writeStringToFile("../Assets/Rooms/" + room.getName() + ".xml",
			xStream.toXML(room));
	}

	@Override
	public void save(RoomTrap roomTrap) throws IOException {
		writeStringToFile("../Assets/Traps/" + roomTrap.getName() + ".xml",
			xStream.toXML(roomTrap));
	}

	@Override
	public Effect loadEffect(String name) throws IOException {
		return (Effect) xStream.fromXML(readStringFromFile("../Assets/Effects/" + name + ".xml"));
	}

	@Override
	public Creature loadCreature(String name) throws IOException {
		return (Creature) xStream.fromXML(readStringFromFile("../Assets/Creatures/" + name + ".xml"));
	}

	@Override
	public Field loadField(String name) throws IOException {
		return (Field) xStream.fromXML(readStringFromFile("../Assets/Fields/" + name + ".xml"));
	}

	@Override
	public RoomTrap loadRoomTrap(String name) throws IOException {
		return (RoomTrap) xStream.fromXML(readStringFromFile("../Assets/Traps/" + name + ".xml"));
	}

	@Override
	public Room loadRoom(String name) throws IOException {
		return (Room) xStream.fromXML(readStringFromFile("../Assets/Rooms/" + name + ".xml"));
	}
	
	@Override
	public Occurrence loadOccurrence(String name) throws IOException {
		return (Occurrence) xStream.fromXML(readStringFromFile("../Assets/Occurrences/" + name + ".xml"));
	}

	@Override
	public Item loadItem(String name) throws IOException {
		return (Item) xStream.fromXML(readStringFromFile("../Assets/Items/" + name + ".xml"));
	}

	@Override
	public GameWorld loadWorld(String path) throws IOException {
		return (GameWorld) xStream.fromXML(readStringFromFile(path));
	}

	private void writeStringToFile(String path, String content) throws FileNotFoundException, IOException {
		try (BufferedWriter writer = Files.newWriter(new File(path), Charset.forName("UTF-8"))) {
			writer.append(content);
		}
	}

	private String readStringFromFile(String path) throws IOException {
		return Files.toString(new File(path), Charset.forName("UTF-8"));
	}

	public GameEventBus getEventBus() {
		return eventBus;
	}
}
