/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.creature.Player;

/**
 *
 * @author Ivo
 */
@Singleton
public class GameController {
	
	private final Player player;
	private final GameWorld gameWorld;
	private final GameEventBus gameEventBus;

	@Inject
	public GameController(Player player, GameWorld gameWorld, GameEventBus gameEventBus) {
		this.player = player;
		this.gameWorld = gameWorld;
		this.gameEventBus = gameEventBus;
	}

	public Player getPlayer() {
		return player;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public GameEventBus getGameEventBus() {
		return gameEventBus;
	}

	
}
