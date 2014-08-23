/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.core;

/**
 * Simple encapsulating class for iteger coordinates on 2d plane.
 * @author Ivo
 */
public class Coords2D {

	public final int x,y;

	public Coords2D (int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 23 * hash + this.x;
		hash = 23 * hash + this.y;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Coords2D other = (Coords2D)obj;
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		return true;
	}
}
