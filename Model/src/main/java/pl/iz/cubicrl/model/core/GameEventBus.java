/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.core;

import com.google.common.eventbus.EventBus;
import com.google.inject.Singleton;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import pl.iz.cubicrl.model.api.events.GameEvent;

/**
 * Wrapper for google EventBus
 *
 * @author Ivo
 */
@Singleton
public class GameEventBus implements Serializable {

	transient private final EventBus eventBus;
	private final ArrayList<Object> subscribers;
	transient private Object gameController;

	public GameEventBus() {
		eventBus = new EventBus();
		subscribers = new ArrayList<>();
	}

	/**
	 * To be used only by classes from the model
	 * @param o 
	 */
	public void subscribe(Object o) {
		eventBus.register(o);
		subscribers.add(o);
	}
	
	/**
	 * To be used for subscribtion of items that shouldn't be serialized
	 * @param o 
	 */
	public void subscribeMechanic(Object o) {
		eventBus.register(o);
	}

	public void publish(GameEvent e) {
		e.setGameController(gameController);
		eventBus.post(e);
	}

	private void readObject(java.io.ObjectInputStream in)
		throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		subscribers.forEach(s -> eventBus.register(s));
	}

	public ArrayList<Object> getSubscribers() {
		return subscribers;
	}
	
	public Object getGameController() {
		return gameController;
	}

	public void setGameController(Object gameController) {
		this.gameController = gameController;
	}

}
