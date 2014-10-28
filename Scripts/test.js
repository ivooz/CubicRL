function processSimpleItem(GameEvent) {
	GameEvent.getField().getResident().modifyBaseStat("HP",-10);
}
registerItemAction("simpleItem",processSimpleItem);	