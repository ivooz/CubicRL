/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.api.events;

import com.google.common.eventbus.Subscribe;

/**
 *
 * @author Ivo
 */
public interface RoomChangeEventListener {

	@Subscribe
	public void process(RoomChangeEvent event);
}
