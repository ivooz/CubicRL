/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.core;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.HashMap;
import pl.iz.cubicrl.model.api.events.GameEvent;
import pl.iz.cubicrl.model.api.events.ItemUsedEvent;
import pl.iz.cubicrl.model.api.events.ItemUsedEventListener;
import pl.iz.cubicrl.model.core.GameEventBus;

/**
 *
 * @author Ivo
 */
@Singleton
public class ItemActionExecutor {

	private final GameEventBus eventBus;
	private final HashMap<String, ItemUsedEventListener> itemActionMap;

	@Inject
	public ItemActionExecutor(GameEventBus eventBus) {
		this.eventBus = eventBus;
		itemActionMap = new HashMap<>();

		//-------------------
		eventBus.subscribeMechanic(this);
	}

	@Subscribe
	public void processEvent(ItemUsedEvent itemUsedEvent) {
		String itemName = itemUsedEvent.getItem().getName();
		if (itemActionMap.keySet().contains(itemName)) {
			itemActionMap.get(itemName).process(itemUsedEvent);
		} else {
			eventBus.publish(new GameEvent("Nothing happens"));
		}
	}

	public void registerItemAction(String name, ItemUsedEventListener itemAction) {
		itemActionMap.put(name, itemAction);
	}

}
