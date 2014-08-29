/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.tests;

import com.google.common.eventbus.Subscribe;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iz.cubicrl.model.core.GameEventBus;

/**
 *
 * @author Ivo
 */
public class GameEventBus_Test {

	GameEventBus eventBus;

	public GameEventBus_Test() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		eventBus = new GameEventBus();
	}

	@After
	public void tearDown() {
	}

	interface DummySubscriber {
		public boolean wasNotified();
	}
	
	@Test
	public void testRegisteringAndPublishing() {
		DummySubscriber subscriber = new DummySubscriber() {
			boolean wasNotified = false;
			@Override
			public boolean wasNotified() {
				return wasNotified;
			}
			
			@Subscribe
			public void test(Object o) {
				wasNotified = true;
			}
		};
		eventBus.subscribe(subscriber);
		eventBus.publish(new Object());
		assertTrue(subscriber.wasNotified());
	}
	
	class CustomEvent {
		
	}
	
	@Test
	public void testSubscriberToParticularEvent() {
		DummySubscriber subscriber = new DummySubscriber() {
			boolean wasNotified = false;
			@Override
			public boolean wasNotified() {
				return wasNotified;
			}
			
			@Subscribe
			public void test(CustomEvent event) {
				wasNotified = true;
			}
		};
		eventBus.subscribe(subscriber);
		eventBus.publish(new String());
		assertFalse(subscriber.wasNotified());
		eventBus.publish(new CustomEvent());
		assertTrue(subscriber.wasNotified());
	}


}
