/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.tests;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import pl.iz.cubicrl.controller.factory.CreatureFactory;

/**
 *
 * @author Ivo
 */
public class CreatureFactory_Test {

	public CreatureFactory_Test() {
	}

	@Inject
	private CreatureFactory creatureFactory;

	@Before
	public void setUp() {
		Guice.createInjector(getTestModule()).injectMembers(this);
	}

	private Module getTestModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
			}
		};
	}

	@Test
	public void testBehavior() {
		assertNotNull(creatureFactory);
	}

}
