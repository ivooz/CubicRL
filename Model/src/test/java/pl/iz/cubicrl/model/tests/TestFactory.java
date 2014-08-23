package pl.iz.cubicrl.model.tests;


import javafx.util.Pair;
import pl.iz.cubicrl.model.core.Attack;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.creature.HumanoidCreature;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.ItemSlot;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.items.EquipableItem;
import pl.iz.cubicrl.model.items.Weapon;
import pl.iz.cubicrl.model.occurence.Occurence;
import pl.iz.cubicrl.model.occurence.OccurenceWithEffects;
import pl.iz.cubicrl.model.occurence.TimedOccurenceWithEffects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ivo
 */
public class TestFactory {

	private TestFactory() {
	}

	public static TestFactory getInstance() {
		return TestFactoryHolder.INSTANCE;
	}

	public Weapon getGenericWeapon() {
		return new Weapon(DamageType.BLUNT, 5, 10, "testWeapon", new Coords2D(1, 1), new Coords2D(1, 1));
	}

	public EquipableItem getGenericEquipableItem() {
		EquipableItem testArmor = new EquipableItem("test", new Coords2D(1, 1), ItemSlot.BODY, new Coords2D(1, 1));
		Effect effect = new Effect("testEffect");
		effect.addModifier(new Pair(Attribute.STRENGTH, 2));
		testArmor.addEffect(effect);
		return testArmor;
	}

	private static class TestFactoryHolder {

		private static final TestFactory INSTANCE = new TestFactory();
	}

	public Creature getGenericCreature() {
		return new Creature("test", new int[]{5, 5, 5, 5, 5, 5}, //Attributes 
			new int[]{1, 1, 1, 1, 1, 1, 1}, //Skills
			new int[]{100, 100, 100, 100}, // LifeStatLimits
			new int[]{10, 10, 10, 10, 10, 10, 10}, //Resistances
			new int[]{0}, //Secondary Stats
			new Attack(10,10,DamageType.BLUNT));
	}

	public HumanoidCreature getGenericHumanoidCreature() {
		return new HumanoidCreature("test", new int[]{5, 5, 5, 5, 5, 5}, //Attributes 
			new int[]{1, 1, 1, 1, 1, 1, 1}, //Skills
			new int[]{100, 100, 100, 100}, // LifeStatLimits
			new int[]{10, 10, 10, 10, 10, 10, 10},//Resistances
			new int[]{0} // SecondaryStats
		);
	}
	
	public PenetrableField getGenericPenetrableField() {
		return new PenetrableField("testPF", new Coords2D(1,1), new Coords2D(1,1));
	}
	
	public Occurence getGenericOccurence() {
		return new Occurence("testGO", new Coords2D(1,1));
	}
	
	public OccurenceWithEffects getGenericOccurenceWithEffects() {
		return new OccurenceWithEffects("testOWE", new Coords2D(1,1));
	}
	
	
	public TimedOccurenceWithEffects getGenericTimedOccurenceWithEffects(int timer) {
		return new TimedOccurenceWithEffects("testGTWE", new Coords2D(1,1), timer);
	}
	
	public Attack getKillingAttack(DamageType damageType) {
		return new Attack(Integer.MAX_VALUE, Integer.MAX_VALUE, damageType);
	}
	
	/**
	 * Should take 1HP each time.
	 * @param damageType
	 * @return 
	 */
	public Attack getWeakAttack(DamageType damageType) {
		return new Attack(Integer.MAX_VALUE, 2, damageType);
	}
}
