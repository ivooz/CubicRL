function registerListener(classname,fun) {
	var ListenerClass = Java.type(classname);
	var listener = new ListenerClass(fun);
	eventBus.subscribe(listener);
};

function registerItemAction(itemName,fun) {
	var ListenerClass = Java.type(getListenerClass("ItemUsedEvent"));
	var listener = new ListenerClass(fun);
	itemActionExecutor.registerItemAction(itemName,fun);
}

function getEnumClass(enumName) {
	return Java.type("pl.iz.cubicrl.model.enums."+enumName);
};

function getListenerClass(classname) {
	return "pl.iz.cubicrl.model.api.events."+classname+"Listener";
};
