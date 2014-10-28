/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.creature;

import java.io.Serializable;
import java.util.HashMap;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.LifeStat;
import pl.iz.cubicrl.model.enums.LifeStatsUpperLimit;
import pl.iz.cubicrl.model.enums.SecondaryStat;
import pl.iz.cubicrl.model.enums.Skill;

/**
 * Container used to delegate stat management for Creatures. Maps enumerated
 * stats to their base values.
 *
 * @author Ivo
 */
public class StatHolder implements Serializable {

	private final EnumMap<Attribute> attributes;
	private final EnumMap<Skill> skills;
	private final EnumMap<LifeStat> lifeStats;
	private final EnumMap<LifeStatsUpperLimit> lifeStatsLimits;
	private final EnumMap<DamageType> resistances;
	private final EnumMap<SecondaryStat> secondaryStats;
	/**
	 * Specific stat containers are mapped to class objects of Enums they
	 * map. This allows for less coding.
	 */
	private final HashMap<Object, EnumMap> classMap;

	/**
	 * Initial values of statistics should be passed as parameters. Values
	 * in array corresponds to the ordering of Enum values.
	 *
	 * @param attri initial values of attributes
	 * [STRNGTH,DXTRT,SPD,CNST,INTL,CHA]
	 * @param sklls initial values of skills
	 * [DDG,THRWNG,PRJCTL,SPCH,MATH,SNEAK]
	 * @param lifeStatsLimits initial values of secondaryStats
	 * [MAXHP,MAXSAN,MAXHUNGER,MAXTHRST]
	 * @param rsistancs initial values of resistances
	 * [BLNT,PRCNG,SLSHNG,HT,CLD,CHMCL,PSCHC]
	 * @param secondaries [AC]
	 */
	public StatHolder(int[] attri, int[] sklls, int[] lifeStatsLimits,
		int[] rsistancs, int[] secondaries) {
		attributes = new EnumMap<>(Attribute.class, attri);
		skills = new EnumMap<>(Skill.class, sklls);
		this.lifeStatsLimits = new EnumMap<>(LifeStatsUpperLimit.class, lifeStatsLimits);
		resistances = new EnumMap<>(DamageType.class, rsistancs);
		secondaryStats = new EnumMap<>(SecondaryStat.class, secondaries);
		lifeStats = new EnumMap<>(LifeStat.class, lifeStatsLimits);
		classMap = new HashMap<>();
		classMap.put(Attribute.class, attributes);
		classMap.put(DamageType.class, resistances);
		classMap.put(Skill.class, skills);
		classMap.put(LifeStatsUpperLimit.class, this.lifeStatsLimits);
		classMap.put(LifeStat.class, lifeStats);
		classMap.put(SecondaryStat.class, secondaryStats);
	}

	/**
	 * Add or substract a value from the base stat, cannot be increased
	 * higher than it is specified by the corrseponding limit.
	 *
	 * @param lifeStat to be changed
	 * @param change
	 */
	public void modifyStatValue(LifeStat lifeStat, int change) {
		int proposedNewValue = this.lifeStats.getStatValue(lifeStat) + change;
		int correspondingLimit = lifeStatsLimits
			.getStatValue(LifeStatsUpperLimit.values()[lifeStat.ordinal()]);
		lifeStats.modifyStat(lifeStat,
			proposedNewValue > correspondingLimit ? correspondingLimit : change);
	}

	/**
	 * Add or substract a value from the base stat
	 *
	 * @param e the stat
	 * @param change
	 */
	public void modifyStatValue(Enum e, int change) {
		classMap.get(e.getClass()).modifyStat(e, change);
	}

	/**
	 * Return base value mapped to the Creature's stat
	 *
	 * @param e enumerated statistic
	 * @return it's value
	 */
	public int getStatValue(Enum e) {
		return classMap.get(e.getClass()).getStatValue(e);
	}
}
