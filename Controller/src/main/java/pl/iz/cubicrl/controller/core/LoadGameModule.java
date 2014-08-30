/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.core;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.iz.cubicrl.controller.dao.DaoXStream;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.creature.Player;

/**
 *
 * @author Ivo
 */
public class LoadGameModule extends AbstractModule {

	private GameWorld gameWorld;
	
	public LoadGameModule(String pathToSave) {
		try {
			//TODO: think about avoiding call to concrete Dao class
			gameWorld = new DaoXStream(new XStream(),null).loadWorld(pathToSave);
		} catch (IOException ex) {
			Logger.getLogger(LoadGameModule.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void configure() {
		bind(IDao.class).to(DaoXStream.class);
	}
	
	@Provides
	GameWorld provideGameWorld() {
		return gameWorld;
	}
	
	@Provides
	Player providePlayer() {
		return gameWorld.getPlayer();
	}
	
	@Provides
	GameEventBus provideGameEventBus() {
		return gameWorld.getEventBus();
	}
}
