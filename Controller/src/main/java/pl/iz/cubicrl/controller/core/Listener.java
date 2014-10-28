/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.core;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Guice;
import pl.iz.cubicrl.model.api.events.GameEvent;

/**
 * Exaplucates gameController for nashorn scripts
 * @author Ivo
 */
public abstract class Listener {
	private GameController gameController;

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}
	
	@Subscribe
	public abstract void process(GameEvent e);
	
	public static void main(String[] args) {
		Guice.createInjector(new NewGameModule()).getInstance(GameController.class);
	}
}
