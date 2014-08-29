/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.api;

/**
 * Allows for implementation of Observer pattern in regard to passing of turns.
 * @author Ivo Z
 */
@FunctionalInterface
public interface TurnObserver {
	/**
	 * Notifies the observing object about the passing of a turn
	 * @Inherit
	 */
	public void nextTurnNotify();
}
