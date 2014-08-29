/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.model.creature;

import java.util.Arrays;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.Skill;
import pl.iz.cubicrl.model.util.PropertyLoader;

/**
 * Wrappar class containing default stats for new Creatures.
 * @author Ivo
 */
public class CreatureStats {
	
	public String defaultName;
	public int[] attributes;
	public int[] skills;
	public int[] resistances;
	public int[] lifestats;
	public int[] secondaries;
	
	public CreatureStats() {
		int defaultAttribute = Integer.parseInt(PropertyLoader.getInstance().loadProperty("defaultAttr"));
		int defaultRes = Integer.parseInt(PropertyLoader.getInstance().loadProperty("defaultRes"));
		int defaultHP = Integer.parseInt(PropertyLoader.getInstance().loadProperty("defaultHP"));
		int defaultSanity = Integer.parseInt(PropertyLoader.getInstance().loadProperty("defaultSanity"));
		int defaultHunger = Integer.parseInt(PropertyLoader.getInstance().loadProperty("defaultHunger"));
		int defaultThirst = Integer.parseInt(PropertyLoader.getInstance().loadProperty("defaultThirst"));
		int defaultSkill = Integer.parseInt(PropertyLoader.getInstance().loadProperty("defaultSkill"));
		int defaultAC = Integer.parseInt(PropertyLoader.getInstance().loadProperty("defaultAC"));
		defaultName = PropertyLoader.getInstance().loadProperty("defaultName");
		int attributes[] = new int[Attribute.values().length];
		int skills[] = new int[Skill.values().length];
		int resistances[] = new int[DamageType.values().length];
		int lifestats[] = new int[]{defaultHP, defaultSanity, defaultHunger, defaultThirst};
		int secondaries[] = new int[]{defaultAC};
		Arrays.stream(attributes).forEach(a -> a = defaultAttribute);
		Arrays.stream(skills).forEach(a -> a = defaultSkill);
		Arrays.stream(resistances).forEach(a -> a = defaultRes);
		this.attributes=attributes;
		this.skills=skills;
		this.resistances=resistances;
		this.lifestats=lifestats;
		this.secondaries=secondaries;
	}
}
