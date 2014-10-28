/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.core;

import com.google.inject.Singleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import pl.iz.cubicrl.model.enums.Direction;

/**
 * A container for rooms representing in-game world.
 *
 * @author Ivo
 */
@Singleton
public class Cube implements Serializable {

	Room rooms[][][];

	/**
	 *
	 * @param edgeSize world edge in 3d
	 */
	public Cube(int edgeSize) {
		rooms = new Room[edgeSize][edgeSize][edgeSize];
	}

	public Room getRoomAt(Coords3D coords) {
		return rooms[coords.x][coords.y][coords.z];
	}

	public void setRoomAt(Coords3D coords, Room room) {
		rooms[coords.x][coords.y][coords.z] = room;
	}

	public Stream<Coords3D> getRoomCoordsAsStream() {
		ArrayList<Coords3D> coords = new ArrayList<>();
		IntStream.range(0, rooms.length).parallel().forEach(x
			-> IntStream.range(0, rooms.length).parallel().forEach(y
				-> IntStream.range(0, rooms.length).parallel().forEach(z
					-> {
				synchronized (coords) {
					coords.add(new Coords3D(x, y, z));
				}
			})));
		return coords.stream();
	}

	/**
	 * For thread safe use
	 *
	 * @return
	 */
	public Stream<Coords3D> getRoomCoordsAsParallelStream() {
		return getRoomCoordsAsStream().parallel();
	}

	/**
	 * Returns true if room should exist in direction specified
	 *
	 * @param roomCoords
	 * @param direction
	 * @return
	 */
	public boolean checkIfWithinCubeBoundary(Coords3D roomCoords, Direction direction) {
		return checkIfWithinCubeBoundary(new Coords3D(
			roomCoords.x + direction.xDir,
			roomCoords.y + direction.yDir,
			roomCoords.z + direction.zDir));
	}

	public boolean checkIfWithinCubeBoundary(Coords3D roomCoords) {
		return roomCoords.x >= 0 && roomCoords.x < rooms.length
			&& roomCoords.y >= 0 && roomCoords.y < rooms.length
			&& roomCoords.z >= 0 && roomCoords.z < rooms.length;
	}

}
