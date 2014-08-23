/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.core;

/**
 * Simple encapsulating class for integer coordinates in 3d space.
 * @author Ivo
 */
public class Coordinates3D {
	public final int x,y,z;

	public Coordinates3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
