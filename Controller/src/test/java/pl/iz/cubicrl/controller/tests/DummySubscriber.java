/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.iz.cubicrl.controller.tests;

import com.google.common.eventbus.Subscribe;
import java.io.Serializable;

/**
 *
 * @author Ivo
 */
public class DummySubscriber implements Serializable {
	
	public boolean wasNotified=false;
	
	@Subscribe
	public void notify(String string) {
		wasNotified=true;
	}
}
