/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.occurrence;

import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.field.PenetrableField;

/**
 * Permanent occurance that is initially hidden, can be detected with a skill
 * check. To be added to penetrablefield with PenetrableField::addTrap method,
 * as it is evaluated differently by the gui (avoiding casting to FieldTtrap).
 *
 * @author Ivo
 */
public class FieldTrap extends OccurrenceWithEffects {

	private boolean visible;
	final private int difficulty;

	/**
	 * Creates a permanent trap, initially invisible
	 *
	 * @param name
	 * @param spriteSheetCoordinates
	 * @param difficulty of skill check
	 */
	public FieldTrap(String name, Coords2D spriteSheetCoordinates, int difficulty) {
		super(name, spriteSheetCoordinates);
		this.difficulty = difficulty;
		visible = false;
	}

	public boolean isVisible() {
		return visible;
	}

	/**
	 * Makes itself visible if it damages resident
	 *
	 * @param field
	 */
	@Override
	public void visit(PenetrableField field) {
		super.visit(field);
		if (field.hasResident()) {
			//If stepped into it reaveals itself to the player
			//TODO: visibility of traps by creatures other than player
			visible = true;
		}
	}

}
