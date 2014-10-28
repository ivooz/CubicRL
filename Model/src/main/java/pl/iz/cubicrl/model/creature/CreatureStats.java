/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.creature;

import com.google.inject.Inject;
import java.util.Arrays;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.Skill;
import pl.iz.cubicrl.model.core.PropertyLoader;

/**
 * Wrappar class containing default stats for new Creatures.
 *
 * @author Ivo
 */
public class CreatureStats {

	private final PropertyLoader propertyLoader;
	public String defaultName;
	public int[] attributes;
	public int[] skills;
	public int[] resistances;
	public int[] lifestats;
	public int[] secondaries;

	@Inject
	public CreatureStats(PropertyLoader propertyLoader) {
		this.propertyLoader = propertyLoader;
		int defaultAttribute = Integer.parseInt(propertyLoader.loadProperty("defaultAttr"));
		int defaultRes = Integer.parseInt(propertyLoader.loadProperty("defaultRes"));
		int defaultHP = Integer.parseInt(propertyLoader.loadProperty("defaultHP"));
		int defaultSanity = Integer.parseInt(propertyLoader.loadProperty("defaultSanity"));
		int defaultHunger = Integer.parseInt(propertyLoader.loadProperty("defaultHunger"));
		int defaultThirst = Integer.parseInt(propertyLoader.loadProperty("defaultThirst"));
		int defaultSkill = Integer.parseInt(propertyLoader.loadProperty("defaultSkill"));
		int defaultAC = Integer.parseInt(propertyLoader.loadProperty("defaultAC"));
		defaultName = propertyLoader.loadProperty("defaultName");
		int attributes[] = new int[Attribute.values().length];
		int skills[] = new int[Skill.values().length];
		int resistances[] = new int[DamageType.values().length];
		int lifestats[] = new int[]{defaultHP, defaultSanity, defaultHunger, defaultThirst};
		int secondaries[] = new int[]{defaultAC};
		Arrays.stream(attributes).forEach(a -> a = defaultAttribute);
		Arrays.stream(skills).forEach(a -> a = defaultSkill);
		Arrays.stream(resistances).forEach(a -> a = defaultRes);
		this.attributes = attributes;
		this.skills = skills;
		this.resistances = resistances;
		this.lifestats = lifestats;
		this.secondaries = secondaries;
	}
}
