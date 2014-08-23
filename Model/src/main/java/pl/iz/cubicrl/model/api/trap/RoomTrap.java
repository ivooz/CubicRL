/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.api.trap;

import pl.iz.cubicrl.model.api.VisitorAdapter;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.occurence.Occurence;

/**
 *
 * @author Ivo
 */
public class RoomTrap extends VisitorAdapter {
	
	Occurence occurence;
	private final int xModuloFactor;
	private final int yModuloFactor;
	private final int frequency;
	private final int mobility;
	private int timer;

	public RoomTrap(Occurence occurence, int xModuloFactor, int yModuloFactor, int frequency, int mobility) {
		this.occurence = occurence;
		this.xModuloFactor = xModuloFactor;
		this.yModuloFactor = yModuloFactor;
		this.frequency = frequency;
		this.mobility = mobility;
		timer = 0;
	}

	@Override
	public void visit(Room room) {
		if(timer%frequency==0) {
			//Fields[][] fields = room.getFields();
		}
		timer++;
	}
	
	
	
}
