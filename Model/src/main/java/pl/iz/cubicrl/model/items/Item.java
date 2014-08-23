/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.items;

import pl.iz.cubicrl.model.api.VisitorAdapter;
import pl.iz.cubicrl.model.core.Coords2D;

/**
 *
 * @author Ivo
 */
public abstract class Item extends VisitorAdapter {
	
	private final String name;
	private final Coords2D onGroundSpirteSheetCoords;

	public Item(String name, Coords2D onGroundSpirteSheetCoords) {
		this.name = name;
		this.onGroundSpirteSheetCoords = onGroundSpirteSheetCoords;
	}

	public String getName() {
		return name;
	}

	public Coords2D gCoords2DrteSheetCoords() {
		return onGroundSpirteSheetCoords;
	}
}
