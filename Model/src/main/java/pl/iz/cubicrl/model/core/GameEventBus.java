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

/**
 * Wrapper for google EventBus
 *
 * @author Ivo
 */
@Singleton
public class GameEventBus implements Serializable {

	transient private final EventBus eventBus;
	private final ArrayList<Object> subscribers;

	public GameEventBus() {
		eventBus = new EventBus();
		subscribers = new ArrayList<>();
	}

	public void subscribe(Object o) {
		eventBus.register(o);
		subscribers.add(o);
	}

	public void publish(Object o) {
		eventBus.post(o);
	}

	private void readObject(java.io.ObjectInputStream in)
		throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		subscribers.forEach(s -> eventBus.register(s));
	}

	public ArrayList<Object> getSubscribers() {
		return subscribers;
	}
	
	
}
