/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.occurrence;

import java.util.ArrayList;
import java.util.Random;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.PenetrableField;

/**
 *
 * @author Ivo
 */
public class SpreadingOccurrence extends TimedOccurrenceWithEffects {

	private final Room room;
	private final double spreadChance;
	private final int initialTimer;
	private final int degradationRate;
	/**
	 * Occurrences added this turn should start spreading next turn
	 */
	private boolean addedThisTurn;

	/**
	 *
	 * @param name
	 * @param spriteSheetCoordinates
	 * @param room where it is spreading
	 * @param spreadChance 0 - 100
	 * @param initialTimer
	 * @param degradationRate new spawned effects will last
	 * initialTimer-degradationRate turns
	 */
	public SpreadingOccurrence(String name, Coords2D spriteSheetCoordinates, Room room, double spreadChance, int initialTimer, int degradationRate) {
		super(name, spriteSheetCoordinates, initialTimer);
		this.room = room;
		this.spreadChance = spreadChance;
		this.initialTimer = initialTimer;
		this.degradationRate = degradationRate;
		addedThisTurn = true;
	}

	@Override
	public void visit(PenetrableField field) {
		super.visit(field);
		if (addedThisTurn) {
			addedThisTurn = false;
		} else {
			Random random = new Random();
			ArrayList<Field> neighbouringFields = room.getNeighbouringFields(field);
			neighbouringFields.stream().forEach(f -> {
				if (f instanceof PenetrableField && random.nextInt(100) < spreadChance) {
					f.addOccurrence(new SpreadingOccurrence(name, spriteSheetCoordinates, room, spreadChance, initialTimer - degradationRate, degradationRate));
				}
			});
		}
	}

}
