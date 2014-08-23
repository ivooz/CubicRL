/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.api.trap;

import java.util.Random;
import pl.iz.cubicrl.model.api.VisitorAdapter;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.occurence.Occurence;

/**
 *
 * @author Ivo
 */
public class RoomTrap extends VisitorAdapter {

	private Occurence occurence;
	private final Enum testedStatistic;
	private final int testFrequency;
	private final int testDifficulty;
	private final int moduloFactor;
	private final int frequency;
	private final int mobility;
	private final int instability;
	private boolean activated;
	private int deviation;

	private int timer;

	/**
	 * Vicious trap capable of moving spreading an Occurence. Tests all the
	 * creatures in the Room - once it activates it works indefinitely.
	 * Occurence must be added manually.
	 *
	 * @param testFrequency once per how many turns activation tests are
	 * performed
	 * @param testDifficulty activation probability = (100 -
	 * (statistic-difficulty))%
	 * @param testedStatistic statistic to be test, can be anything, from HP
	 * to Mathematics
	 * @param moduloFactor traps appear on a field where
	 * (field.x+field.y)%factor==0
	 * @param frequency how often traps appear
	 * @param mobility by how much the spawn places are shifting
	 * (effectively this parameter is adding to sum of coordinates each
	 * turn)
	 * @param instability modeulo factor is mo number between [-instability;instability]
	 * is su
	 */
	public RoomTrap(int testFrequency, int testDifficulty, Enum testedStatistic,
		int moduloFactor, int frequency, int mobility, int instability) {
		this.testFrequency = testFrequency;
		this.testDifficulty = testDifficulty;
		this.testedStatistic = testedStatistic;
		this.moduloFactor = moduloFactor;
		this.frequency = frequency;
		this.mobility = mobility;
		this.instability = instability;
		deviation = 0;
		activated = false;
		timer = 0;
	}

	/**
	 * Override in superclass for more creative patterns
	 *
	 * @param field
	 * @param disturbance
	 * @return
	 */
	protected boolean isEligibleForTrap(Field field, int disturbance) {
		return (field instanceof PenetrableField      
			&& (field.getRoomCoords().x + field.getRoomCoords().y
			+ deviation) % (moduloFactor + disturbance) == 0);
	}

	@Override
	public void visit(Room room) {
		if (timer % frequency == 0) {
			Random random = new Random();
			int disturbance = instability > 0 ? random.nextInt(instability+1) - instability : 0;
			room.getFieldsAsParallelStream()
				.filter(f -> isEligibleForTrap(f, disturbance))
				.forEach(f -> f.addOccurence(occurence.copy()));
			deviation += mobility;
		}
		timer++;
	}

	@Override
	public void visit(Creature creature) {
		if (!activated && timer % testFrequency == 0) {
			Random random = new Random();
			int successChance = creature.getEffectiveStat(testedStatistic) - testDifficulty;
			activated = random.nextInt(100) >= successChance;
			//TODO SEND TRAP ACTIVATED EVENT OR SOMETHING
		}
	}

	public Occurence getOccurence() {
		return occurence;
	}

	public void setOccurence(Occurence occurence) {
		this.occurence = occurence;
	}

	public boolean isActivated() {
		return activated;
	}
}
