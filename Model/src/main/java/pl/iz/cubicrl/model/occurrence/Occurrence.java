/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.occurrence;

import java.util.logging.Level;
import java.util.logging.Logger;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.api.VisitorAdapter;
import pl.iz.cubicrl.model.core.Coords2D;

/**
 *
 * @author Ivo
 */
public class Occurrence extends VisitorAdapter implements Cloneable, TurnObserver {

	protected final String name;
	protected final Coords2D spriteSheetCoordinates;
	protected boolean expired;

	public Occurrence(String name, Coords2D spriteSheetCoordinates) {
		this.name = name;
		this.spriteSheetCoordinates = spriteSheetCoordinates;
		expired = false;
	}

	public boolean isExpired() {
		return expired;
	}

	public String getName() {
		return name;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Occurrence copy() {
		try {
			return (Occurrence) super.clone();
		} catch (CloneNotSupportedException ex) {
			Logger.getLogger(SpreadingOccurrence.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public void nextTurnNotify() {
	}

}
