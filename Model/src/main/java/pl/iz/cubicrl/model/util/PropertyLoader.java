/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.util;

import com.google.inject.Singleton;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * TODO:avoid loading same value multiple times
 *
 * @author Ivo
 */
@Singleton
public class PropertyLoader {

	private static PropertyLoader instance;
	Properties properties;

	private PropertyLoader() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("../config/game.properties"));
		} catch (IOException ex) {
			Logger.getLogger(PropertyLoader.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static PropertyLoader getInstance() {
		if (instance == null) {
			instance = new PropertyLoader();
		}
		return instance;
	}

	public String loadProperty(String property) {
		return properties.getProperty(property);
	}
}
