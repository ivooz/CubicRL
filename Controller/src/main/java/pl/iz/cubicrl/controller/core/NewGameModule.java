/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.core;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import java.util.Arrays;
import pl.iz.cubicrl.controller.dao.DaoXStream;
import pl.iz.cubicrl.model.api.IDao;
import pl.iz.cubicrl.model.creature.Player;
import pl.iz.cubicrl.model.util.PropertyLoader;

/**
 *
 * @author Ivo
 */
public class NewGameModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDao.class).to(DaoXStream.class);
	}
	
	
}
