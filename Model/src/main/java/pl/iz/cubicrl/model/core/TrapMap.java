/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author Ivo
 */
@Singleton
public class TrapMap implements Serializable {

	private final HashMap<Coords3D, String> mappedTraps;
	private final HashMap<Coords3D, String> mappedRooms;
	private final PropertyLoader propertyLoader;

	@Inject
	public TrapMap(PropertyLoader propertyLoader) {
		this.propertyLoader = propertyLoader;
		mappedRooms = new HashMap<>();
		mappedTraps = new HashMap<>();
	}

	/**
	 * Call only once
	 *
	 * @param traps
	 * @param uniqueRooms
	 */
	public void initialize(ArrayList<String> traps, ArrayList<String> uniqueRooms) {
		if (mappedTraps.isEmpty()) {
			int trapsPerType = Integer.parseInt(propertyLoader
				.loadProperty("trapsPerType"));
			int cubeEdgeSize = Integer.parseInt(propertyLoader
				.loadProperty("cubeEdgeSize"));
			//Array for choosing random coords
			ArrayList<Coords3D> coords = new ArrayList<>(cubeEdgeSize
				* cubeEdgeSize * cubeEdgeSize);
			Random random = new Random();
			IntStream.range(0, cubeEdgeSize).parallel().forEach(
				x -> IntStream.range(0, cubeEdgeSize).parallel().forEach(
					y -> IntStream.range(0, cubeEdgeSize).parallel().forEach(
						z -> coords.add(new Coords3D(x, y, z)))));
			//Mapping Traps
			traps.forEach(t -> IntStream.range(0, trapsPerType).forEach(i -> {
				Coords3D coord = coords.get(random.nextInt(coords.size()));
				mappedTraps.put(coord, t);
				coords.remove(coord);
			})
			);
			//Mapping unique Rooms
			uniqueRooms.forEach(r -> {
				Coords3D coord = coords.get(random.nextInt(coords.size()));
				mappedRooms.put(coord, r);
				coords.remove(coord);
			});
		}
	}

	public HashMap<Coords3D, String> getMappedTraps() {
		return mappedTraps;
	}

	public HashMap<Coords3D, String> getMappedRooms() {
		return mappedRooms;
	}
	
	public boolean roomIsSpecial(Coords3D coords) {
		return mappedTraps.keySet().contains(coords) 
			|| mappedRooms.keySet().contains(coords);
	}
	
	
}
