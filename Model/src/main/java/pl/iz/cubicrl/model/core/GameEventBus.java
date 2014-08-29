/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.core;

import com.google.common.eventbus.EventBus;
import com.google.inject.Singleton;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Wrapper for google EventBus
 * @author Ivo
 */
@Singleton
public class GameEventBus implements Serializable {
	private final EventBus eventBus;

	public GameEventBus() {
		eventBus = new EventBus();
	}
	
	public void subscribe(Object o) {
		eventBus.register(o);
	}
	
	public void publish(Object o) {
		eventBus.post(o);
	}

}
