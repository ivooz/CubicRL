/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.occurrence;

import java.util.ArrayList;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.effects.TimedEffect;
import pl.iz.cubicrl.model.field.PenetrableField;

/**
 *
 * @author Ivo
 */
public class TimedOccurrenceWithEffects extends OccurrenceWithEffects {

	private final ArrayList<Effect> effects;
	protected int timer;

	public TimedOccurrenceWithEffects(String name, Coords2D spriteSheetCoordinates, int timer) {
		super(name, spriteSheetCoordinates);
		effects = new ArrayList<>();
		this.timer = timer;
	}

	public int getTimer() {
		return timer;
	}

	@Override
	public void visit(PenetrableField field) {
		super.visit(field);
		timer--;
		if (timer <= 0) {
			expired = true;
		}
	}

}
