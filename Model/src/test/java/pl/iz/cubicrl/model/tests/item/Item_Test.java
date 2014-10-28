package pl.iz.cubicrl.model.tests.item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.items.Item;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.items.ItemWithEffects;
import pl.iz.cubicrl.model.tests.TestFactory;

/**
 *
 * @author Ivo
 */
public class Item_Test {

	public Item_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testItemOnField_addingAndRemovingItemsFromFieldVerifyingBehavior() {
		Item testItem1 = new ItemWithEffects("test", null);
		Item testItem2 = new ItemWithEffects("test", null);
		PenetrableField field = TestFactory.getInstance().getGenericPenetrableField();
		field.addItem(testItem1);
		field.addItem(testItem2);
		assert (field.getTopItem() == testItem2);
		field.removeItem(testItem2);
		assert (field.getTopItem() == testItem1);
		field.removeItem(testItem1);
		assert (field.getItems().isEmpty());
	}
}
