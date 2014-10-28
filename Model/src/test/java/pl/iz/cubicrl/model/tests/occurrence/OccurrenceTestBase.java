/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests.occurrence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import pl.iz.cubicrl.model.creature.Creature;
import pl.iz.cubicrl.model.creature.HumanoidCreature;
import pl.iz.cubicrl.model.field.PenetrableField;
import pl.iz.cubicrl.model.tests.TestFactory;

/**
 *
 * @author Ivo
 */
public class OccurrenceTestBase {

	TestFactory factory;
	Creature testCreature;
	HumanoidCreature testHumanoid;
	PenetrableField testField1;
	PenetrableField testField2;

	@Before
	public void setUp() {
		factory = TestFactory.getInstance();
		testCreature = factory.getGenericCreature();
		testHumanoid = factory.getGenericHumanoidCreature();
		testField1 = factory.getGenericPenetrableField();
		testField2 = factory.getGenericPenetrableField();
		testField1.accept(testCreature);
		testField2.accept(testHumanoid);
	}

}
