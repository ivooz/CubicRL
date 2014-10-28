/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.effects;

import pl.iz.cubicrl.model.creature.Creature;

/**
 * Effect with in-built timer. When a creature is notified about next turn
 * passing, it accepts all it's effects as visitors.
 *
 * @author Ivo
 */
public class TimedEffect extends Effect {

	private int timer;

	public int getTimer() {
		return timer;
	}

	public TimedEffect(String name, int timer) {
		super(name);
		this.timer = timer;
	}

	/**
	 * TimedEffect is removed from the Effects list when the timer is up.
	 *
	 * @param creature which the effect concerns
	 */
	@Override
	public void visit(Creature creature) {
		super.visit(creature);
		timer--;
		if (timer == 0) {
			expired = true;
		}
	}

}
