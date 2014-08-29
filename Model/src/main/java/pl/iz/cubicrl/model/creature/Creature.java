/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.creature;

import pl.iz.cubicrl.model.effects.Effect;
import java.util.ArrayList;
import java.util.Random;
import javax.validation.constraints.NotNull;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.api.Visitor;
import pl.iz.cubicrl.model.api.VisitorAdapter;
import pl.iz.cubicrl.model.attack.Attack;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.enums.SecondaryStat;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.util.PropertyLoader;

/**
 * Represents a being in the game.
 *
 * @author Ivo
 */
public class Creature extends VisitorAdapter implements TurnObserver {

	private final StatHolder statHolder;
	private final String name;
	private final ArrayList<Effect> effects;
	private final Attack attack;
	private Room room;
	private PenetrableField field;

	/**
	 * Initial values of statistics should be passed as parameters. Values
	 * in array corresponds to the ordering of Enum values.
	 *
	 * @param name of the creature, must be unique
	 * @param attri initial values of attributes
	 * [STRNGTH,DXTRT,SPD,CNST,INTL,CHA]
	 * @param sklls initial values of skills
	 * [DDG,THRWNG,PRJCTL,SPCH,MATH,SNEAK]
	 * @param lifeStatsLimits initial values of lifeStats
	 * [MAXHP,MAXSAN,MAXHUNGER,MAXTHRST]
	 * @param rsistancs initial values of resistances
	 * [BLNT,PRCNG,SLSHNG,HT,CLD,CHMCL,PSCHC]
	 * @param secondaries value of secondary stats [AC]
	 * @param attack creature's attack
	 */
	public Creature(@NotNull String name,
			@NotNull int[] attri,
			@NotNull int[] sklls,
			@NotNull int[] lifeStatsLimits,
			@NotNull int[] rsistancs,
			@NotNull int[] secondaries,
			Attack attack) {
		this.name = name;
		effects = new ArrayList<>();
		statHolder = new StatHolder(attri, sklls, lifeStatsLimits, rsistancs, secondaries);
		this.attack = attack;
	}

	public String getName() {
		return name;
	}

	/**
	 * Allows for interaction of Items, Effects, Traps and Occurences with
	 * the creature.
	 *
	 * @param visitor
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Adds an Effect to the list of Creature's currently active Effects.
	 * Does not allow duplicate effects (no two effects with the same name
	 * can be present simulataneously).
	 *
	 * @param effect
	 */
	public void addEffect(Effect effect) {
		if (!effects.parallelStream().anyMatch(e -> e.getName().equals(effect.getName()))) {
			effects.add(effect);
		}
	}

	/**
	 * Returns value of enumerated statistic (base + fromEffects)
	 *
	 * @param e Enum statistic
	 * @return net value
	 */
	public int getEffectiveStat(Enum e) {
		return statHolder.getStatValue(e) //BASE
			+ effects.stream().parallel().mapToInt( //EFFECTS
				p -> p.getEffectiveModifiersOf(e)).sum(); //TODO: ITEMS
	}

	/**
	 * Executes visitor pattern for all effects (they visit the creature),
	 * removes expired effects afterwards.
	 */
	private void processEffects() {
		effects.forEach(e -> e.visit(this));
		effects.removeIf(e -> e.isExpired());
	}

	/**
	 * Deals damage to the Creature. Amount of damage is reduced (or
	 * increase) by resistances.
	 *
	 * @param damageType
	 * @param magnitude
	 */
	public void takeDamage(DamageType damageType, int magnitude) {
		double damage = magnitude * (100d - getEffectiveStat(damageType)) / 100d;
		if (damageType == DamageType.PSYCHIC) {
			statHolder.modifyStatValue(LifeStat.SANITY, (int) -damage);
		} else {
			statHolder.modifyStatValue(LifeStat.HP, ((int) -damage));
		}
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}

	@Override
	public void nextTurnNotify() {
		processEffects();
	}

	/**
	 * Used to manually removed effects
	 *
	 * @param effect to be removed
	 */
	public void removeEffect(Effect effect) {
		effects.remove(effect);
	}

	/**
	 *
	 * @param e
	 * @param change
	 * @see StatHolder#modifyStatValue(Enum,int)
	 */
	public void modifyBaseStat(Enum e, int change) {
		statHolder.modifyStatValue(e, change);
	}

	public Room getRoom() {
		return room;
	}

	public PenetrableField getField() {
		return field;
	}
	
	/**
	 *
	 * @param e
	 * @param change
	 * @see StatHolder#modifyStatValue(LifeStats,int)
	 */
	public void modifyBaseStat(LifeStat e, int change) {
		statHolder.modifyStatValue(e, change);
	}

	@Override
	public void visit(PenetrableField field) {
		if (field.hasResident()) {
			//TODO: CHECK IF ENEMY
			field.getResident().attack(computeAttack());
		} else {
			this.field=field;
			field.addResident(this);
		}
	}

	public void attack(Attack attack)  {
		//Trying to dodge
		Random random = new Random();
		int dodgingCapability = getEffectiveStat(Attribute.SPEED)
			* Integer.parseInt(PropertyLoader.getInstance().loadProperty("dodgePerSpeed"));
		//Armor deflection
		int deflectingCapability = getEffectiveStat(SecondaryStat.AC)
			* Integer.parseInt(PropertyLoader.getInstance().loadProperty("deflectionPerAc"));
		int effectiveDodge = dodgingCapability - attack.getAccuracy();
		if (random.nextInt(100) < effectiveDodge) {
			// Dodged!
		} else if (random.nextInt(100) < deflectingCapability) {
			// Deflected!
		} else {
			// Hurt :(
			attack.getEffects().forEach(e -> effects.add(e));
			takeDamage(attack.getDamageType(), attack.getDamage());
		}
	}

	protected Attack computeAttack() {
		return attack;
	}

	@Override
	public void visit(Room room) {
		if(room!=null) {
			room.getVisitingCreatures().remove(this);
		}
		this.room = room;
	}

}
