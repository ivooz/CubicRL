/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.core;

import java.io.Serializable;

/**
 * A container for rooms representing in-game world.
 *
 * @author Ivo
 */
public class Cube implements Serializable {

	Room rooms[][][];

	/**
	 * 
	 * @param edgeSize world edge in 3d
	 */
	public Cube(int edgeSize) {
		rooms = new Room[edgeSize][edgeSize][edgeSize];
	}

	public Room getRoomAt(Coordinates3D coords) {
		return rooms[coords.x][coords.y][coords.z];
	}
}
