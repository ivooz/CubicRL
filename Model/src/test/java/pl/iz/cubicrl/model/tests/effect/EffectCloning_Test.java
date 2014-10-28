package pl.iz.cubicrl.model.tests.effect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.effects.DamagingTimedEffect;
import pl.iz.cubicrl.model.enums.DamageType;

/**
 *
 * @author Ivo
 */
public class EffectCloning_Test {

	DamagingTimedEffect testEffect;

	public EffectCloning_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testEffect = new DamagingTimedEffect(null, DamageType.BLUNT, 1, 2);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCloning() {
		DamagingTimedEffect clone = null;
		clone = (DamagingTimedEffect) testEffect.copy();
		Assert.assertEquals(clone.getTimer(), testEffect.getTimer());
	}
}
