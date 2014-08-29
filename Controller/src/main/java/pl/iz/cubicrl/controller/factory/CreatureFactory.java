/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.factory;

import javax.inject.Inject;
import pl.iz.cubicrl.model.core.GameEventBus;

/**
 *
 * @author Ivo
 */
public class CreatureFactory {
	private final GameEventBus eventBus;

	@Inject
	public CreatureFactory(GameEventBus eventBus) {
		this.eventBus = eventBus;
	}
}
