/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.api.events;

import pl.iz.cubicrl.model.core.Room;

/**
 *
 * @author Ivo
 */
public class GameEvent {

	private final String message;
	private Object gameController;

	public GameEvent(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public Object getGameController() {
		return gameController;
	}

	public void setGameController(Object gameController) {
		this.gameController = gameController;
	}
}
