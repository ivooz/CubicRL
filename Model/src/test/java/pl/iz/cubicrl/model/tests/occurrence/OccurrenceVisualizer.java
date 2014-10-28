package pl.iz.cubicrl.model.tests.occurrence;

import java.util.stream.IntStream;
import pl.iz.cubicrl.model.core.Room;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ivo
 */
public class OccurrenceVisualizer {

	private final Room room;

	public OccurrenceVisualizer(Room room) {
		this.room = room;
	}

	public void visualize() {
		int size = room.getFields().length;
		IntStream.range(0, size).forEach(x -> {
			IntStream.range(0, size).forEach(y -> {
				if (y == 0) {
					System.out.print("\n");
				}
				System.out.print(room.getFields()[x][y].hasOccurrence() ? "*" : ".");
			});
		});
		System.out.println();
	}

}
