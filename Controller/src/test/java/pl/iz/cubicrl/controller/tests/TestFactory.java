/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.tests;

import com.google.inject.Inject;
import java.util.stream.IntStream;
import javafx.util.Pair;
import pl.iz.cubicrl.model.attack.Attack;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.GameEventBus;
import pl.iz.cubicrl.model.core.GameWorld;
import pl.iz.cubicrl.model.core.PropertyLoader;
import pl.iz.cubicrl.model.core.Room;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.creature.CreatureStats;
import pl.iz.cubicrl.model.creature.HumanoidCreature;
import pl.iz.cubicrl.model.creature.Player;
import pl.iz.cubicrl.model.effects.DamagingTimedEffect;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.DamageType;
import pl.iz.cubicrl.model.enums.Direction;
import pl.iz.cubicrl.model.enums.ItemSlot;
import pl.iz.cubicrl.model.field.Field;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.field.Portal;
import pl.iz.cubicrl.model.items.EquipableItem;
import pl.iz.cubicrl.model.items.Weapon;
import pl.iz.cubicrl.model.occurrence.FieldTrap;
import pl.iz.cubicrl.model.occurrence.Occurrence;
import pl.iz.cubicrl.model.occurrence.OccurrenceWithEffects;
import pl.iz.cubicrl.model.occurrence.TimedOccurrenceWithEffects;
import pl.iz.cubicrl.model.trap.RoomTrap;

/**
 *
 * @author Ivo
 */
public class TestFactory {

	GameEventBus eventBus;
	PropertyLoader propertyLoader;
	GameWorld gameWorld;

	@Inject
	public TestFactory(GameEventBus eventBus, GameWorld gameWorld) {
		this.eventBus = eventBus;
		this.gameWorld = gameWorld;
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

	public Room getGenericRoom() {
		Room room = new Room(10, "test");
		Field[][] fields = room.getFields();
		IntStream.range(0, 10).forEach(x
			-> IntStream.range(0, 10).forEach(y
				-> fields[x][y] = new PenetrableField("test",
					new Coords2D(x, y), new Coords2D(x, y), eventBus)));
		Portal portalNORTH = new Portal(Direction.NORTH, "testPortal",
			new Coords2D(0, 0), new Coords2D(1, 1), eventBus);
		Portal portalSOUTH = new Portal(Direction.SOUTH, "testPortal",
			new Coords2D(1, 1), new Coords2D(1, 1), eventBus);
		Portal portalWEST = new Portal(Direction.WEST, "testPortal",
			new Coords2D(2, 2), new Coords2D(1, 1), eventBus);
		Portal portalEAST = new Portal(Direction.EAST, "testPortal",
			new Coords2D(3, 3), new Coords2D(1, 1), eventBus);
		Portal portalUP = new Portal(Direction.UP, "testPortal",
			new Coords2D(4, 4), new Coords2D(1, 1), eventBus);
		Portal portalDOWN = new Portal(Direction.DOWN, "testPortal",
			new Coords2D(5, 5), new Coords2D(1, 1), eventBus);
		fields[0][0] = portalNORTH;
		fields[1][1] = portalSOUTH;
		fields[2][2] = portalWEST;
		fields[3][3] = portalEAST;
		fields[4][4] = portalUP;
		fields[5][5] = portalDOWN;
		room.resolveEntrances();
		return room;
	}
	
	/**
	 * Should take away 1HP each turn given standard resistances
	 *
	 * @return
	 */
	public DamagingTimedEffect getWeakDamagingEffect() {
		return new DamagingTimedEffect("testwde", DamageType.BLUNT, 2, 1);
	}

	public FieldTrap getGenericFieldTrap(int diff) {
		return new FieldTrap("testFieldTrap", new Coords2D(1, 1), diff);
	}
	
	public Field getGenericField() {
		return new Field("testField", new Coords2D(1,1),new Coords2D(1,1), eventBus);
	}

	public Creature getGenericCreature() {
		Creature creature = new Creature("test", new int[]{5, 5, 5, 5, 5, 5}, //Attributes 
			new int[]{1, 1, 1, 1, 1, 1, 1}, //Skills
			new int[]{100, 100, 100, 100}, // LifeStatLimits
			new int[]{10, 10, 10, 10, 10, 10, 10}, //Resistances
			new int[]{0}, //Secondary Stats
			new Attack(10, 10, DamageType.BLUNT));
		creature.setPropertyLoader(propertyLoader);
		return creature;
	}

	public HumanoidCreature getGenericHumanoidCreature() {
		HumanoidCreature crea = new HumanoidCreature("test", new int[]{5, 5, 5, 5, 5, 5}, //Attributes 
			new int[]{1, 1, 1, 1, 1, 1, 1}, //Skills
			new int[]{100, 100, 100, 100}, // LifeStatLimits
			new int[]{10, 10, 10, 10, 10, 10, 10},//Resistances
			new int[]{0} // SecondaryStats
		);
		crea.setPropertyLoader(propertyLoader);
		return crea;
	}

	
	public Player getGenericPlayer() {
		return new Player(new CreatureStats(propertyLoader));
	}

	public PenetrableField getGenericPenetrableField() {
		return new PenetrableField("testPF", new Coords2D(1, 1), new Coords2D(1, 1),eventBus);
	}

	public Occurrence getGenericOccurrence() {
		return new Occurrence("testGO", new Coords2D(1, 1));
	}

	public OccurrenceWithEffects getGenericOccurrenceWithEffects() {
		return new OccurrenceWithEffects("testOWE", new Coords2D(1, 1));
	}

	public TimedOccurrenceWithEffects getGenericTimedOccurrenceWithEffects(int timer) {
		return new TimedOccurrenceWithEffects("testGTWE", new Coords2D(1, 1), timer);
	}

	public Attack getKillingAttack(DamageType damageType) {
		return new Attack(Integer.MAX_VALUE, Integer.MAX_VALUE, damageType);
	}

	/**
	 * Should take 1HP each time.
	 *
	 * @param damageType
	 * @return
	 */
	public Attack getWeakAttack(DamageType damageType) {
		return new Attack(Integer.MAX_VALUE, 2, damageType);
	}
	
	public RoomTrap getGenericRoomTrap() {
		return new RoomTrap(1, Integer.MAX_VALUE, Attribute.STRENGTH, 2, 2, 0, 0,"testTrap");
	}

	public GameEventBus getEventBus() {
		return eventBus;
	}
	
	
}

