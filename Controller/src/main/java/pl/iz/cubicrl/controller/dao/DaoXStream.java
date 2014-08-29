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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.occurence.Occurence;

/**
 *
 * @author Ivo
 */
@Singleton
public class DaoXStream implements IDao {

	XStream xStream;

	@Inject
	public DaoXStream(XStream xStream) {
		this.xStream = xStream;
	}

	@Override
	public void save(Creature creature) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void save(Field field) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void save(Occurence occurence) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void save(Item item) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void save(GameWorld world, String path) throws IOException {
		writeStringToFile(path, xStream.toXML(world));

	}

	@Override
	public Creature loadCreature(String name) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Field loadField(String name) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Occurence loadOccurence(String name) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Item loadItem(String name) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public GameWorld loadWorld(String path) throws IOException {
		return (GameWorld) xStream.fromXML(readStringFromFile(path));
	}

	private void writeStringToFile(String path, String content) throws FileNotFoundException, IOException {
		OutputStreamWriter char_output = new OutputStreamWriter(
			new FileOutputStream(path),
			Charset.forName("UTF-8").newEncoder()
		);
		char_output.write(content);
	}

	private String readStringFromFile(String path) throws IOException {
		return Files.toString(new File(path), Charset.forName("UTF-8"));
	}

}
