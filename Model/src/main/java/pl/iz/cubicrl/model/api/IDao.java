/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.api;
import java.io.IOException;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.occurence.Occurence;

/**
 *
 * @author Ivo
 */
public interface IDao {

	void save(Creature creature)throws IOException;
	void save(Field field)throws IOException;
	void save(Occurence occurence)throws IOException;
	void save(Item item)throws IOException;
	void save(GameWorld session, String path)throws IOException;
	Creature loadCreature(String name)throws IOException;
	Field loadField(String name)throws IOException;
	Occurence loadOccurence(String name)throws IOException;
	Item loadItem(String name)throws IOException;
	GameWorld loadWorld(String path)throws IOException;
}
