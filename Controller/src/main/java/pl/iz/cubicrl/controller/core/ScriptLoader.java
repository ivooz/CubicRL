/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.controller.core;

import com.google.common.io.Files;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import pl.iz.cubicrl.model.core.GameEventBus;

/**
 *
 * @author Ivo
 */
@Singleton
public class ScriptLoader {

	GameEventBus eventBus;
	ItemActionExecutor actionExecutor;

	@Inject
	public ScriptLoader(GameEventBus eventBus, ItemActionExecutor actionExecutor) {
		this.actionExecutor = actionExecutor;
		this.eventBus = eventBus;
	}

	public void initializeScripts() throws IOException, ScriptException {
		ScriptEngineManager m = new ScriptEngineManager();
		ScriptEngine engine = m.getEngineByName("nashorn");
		SimpleBindings bindings = new SimpleBindings();
		bindings.put("eventBus", eventBus);
		bindings.put("itemActionExecutor", actionExecutor);
		engine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
		StringBuilder sb = new StringBuilder();
		sb.append(Files.toString(new File("../Scripts/utils.js"), Charset.forName("UTF-8")));
		java.nio.file.Files.walk(Paths.get("../Scripts"))
			//.filter(file -> file.endsWith("js") && !file.endsWith("utils.js"))
			.forEach(f -> {
				try {
					System.out.println("########################################################################################");
					System.out.println(f);
					sb.append(Files.toString(
							new File(f.getFileName().toUri()), Charset.forName("UTF-8")));
				} catch (IOException ex) {
					System.out.println(ex);
				}
			});
		System.out.println(sb.toString());
		engine.eval(sb.toString());
	}

}
