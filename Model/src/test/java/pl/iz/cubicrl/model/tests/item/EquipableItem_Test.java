package pl.iz.cubicrl.model.tests.item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.util.Pair;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pl.iz.cubicrl.model.effects.Effect;
import pl.iz.cubicrl.model.enums.Attribute;
import pl.iz.cubicrl.model.enums.ItemSlot;
import pl.iz.cubicrl.model.items.EquipableItem;
import pl.iz.cubicrl.model.tests.HumanoidCreatureBaseTest;
import pl.iz.cubicrl.model.tests.TestFactory;

/**
 *
 * @author Ivo
 */
public class EquipableItem_Test extends HumanoidCreatureBaseTest {

	@Test
	public void testEquippingItems_verifyingTheirEffectsOnStats() {
		int statIncrease = random.nextInt(10);
		int initialCharisma = testCreature.getEffectiveStat(Attribute.CHARISMA);
		EquipableItem charismaCloak = new EquipableItem("test",
			null, ItemSlot.BODY, null);
			Effect effect = new Effect(null);
		effect.addModifier(new Pair(Attribute.CHARISMA, statIncrease));
		charismaCloak.addEffect(effect);
		testCreature.addItemToInventory(charismaCloak);
		testCreature.equip(charismaCloak);
		assertFalse(testCreature.getInventory().contains(charismaCloak));
		EquipableItem charismaHat = new EquipableItem("test",
			null, ItemSlot.HEAD, null);
		Effect effect2 = new Effect(null);
		effect2.addModifier(new Pair(Attribute.CHARISMA, statIncrease));
		charismaHat.addEffect(effect2);
		testCreature.addItemToInventory(charismaHat);
		testCreature.equip(charismaHat);
		assertFalse(testCreature.getInventory().contains(charismaHat));
		assertEquals(initialCharisma + 2 * statIncrease, testCreature.getEffectiveStat(Attribute.CHARISMA));
		EquipableItem charismaCloak2 = new EquipableItem("test",null, ItemSlot.BODY,null);
		Effect effect3 = new Effect(null);
		effect3.addModifier(new Pair(Attribute.CHARISMA, 2));
		charismaCloak2.addEffect(effect3);
		testCreature.addItemToInventory(charismaCloak2);
		testCreature.equip(charismaCloak2);
		assertFalse(testCreature.getInventory().contains(charismaCloak2));
		assertEquals (initialCharisma + statIncrease + 2,testCreature.getEffectiveStat(Attribute.CHARISMA));
		assertTrue (testCreature.getInventory().contains(charismaCloak));
		assertTrue (testCreature.getEquippedItems().values().contains(charismaCloak2));
		assertTrue (!testCreature.getEquippedItems().values().contains(charismaCloak));
		assertTrue (charismaCloak != testCreature.getEquippedItem(ItemSlot.BODY));
		assertTrue (testCreature.getEquippedItem(ItemSlot.HEAD) == charismaHat);
		assertTrue (testCreature.getEquippedItem(ItemSlot.BODY) == charismaCloak2);
	}
	
	@Test
	public void testUnequipingItems() {
		EquipableItem testItem = TestFactory.getInstance().getGenericEquipableItem();
		testCreature.addItemToInventory(testItem);
		testCreature.equip(testItem);
		assertTrue(testCreature.getEquippedItems().values().contains(testItem));
		assertFalse(testCreature.getInventory().contains(testItem));
		testCreature.unequip(testItem);
		assertTrue(testCreature.getInventory().contains(testItem));
		assertFalse(testCreature.getEquippedItems().values().contains(testItem));
		assertEquals(null,testCreature.getEquippedItem(testItem.getItemSlot()));
	}
}
