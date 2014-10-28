/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.enums;

/**
 *
 * @author Ivo
 */
public enum Direction {

	NORTH(1, 0, 0),
	DOWN(0, 0, -1),
	WEST(0, 1, 0),
	SOUTH(-1, 0, 0),
	UP(0, 0, 1),
	EAST(0, -1, 0);

	public final int xDir;
	public final int yDir;
	public final int zDir;

	Direction(int xDir, int yDir, int zDir) {
		this.xDir = xDir;
		this.yDir = yDir;
		this.zDir = zDir;
	}
}
