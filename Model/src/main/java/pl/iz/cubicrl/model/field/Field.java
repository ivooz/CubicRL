/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.field;

import java.io.Serializable;
import pl.iz.cubicrl.model.occurence.Occurence;
import java.util.Stack;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.api.Visitor;
import pl.iz.cubicrl.model.core.Coords2D;
import pl.iz.cubicrl.model.core.GameEventBus;

/**
 * Represents a tiled space on map.
 *
 * @author Ivo
 */
public class Field implements TurnObserver, Serializable {

	protected final Coords2D roomCoordinates;
	private final Coords2D spriteSheetCoordinates;
	protected final Stack<Occurence> occurences;
	transient protected GameEventBus eventBus;
	private final String name;
	private boolean visible;
	private boolean visited;

	/**
	 *
	 * @param name identifies a type
	 * @param roomCoordinates location in the room
	 * @param spriteSheetCoordinates location of graphics on spritesheet
	 * @param eventBus used for publishing in game events
	 */
	public Field(String name, Coords2D roomCoordinates, Coords2D spriteSheetCoordinates,
		GameEventBus eventBus) {
		this.name = name;
		this.eventBus = eventBus;
		this.roomCoordinates = roomCoordinates;
		this.spriteSheetCoordinates = spriteSheetCoordinates;
		occurences = new Stack<>();
		visited = false;
		visible = false;
	}

	/**
	 * Used for allowing interaction with Creatures, Occurences, (Items?).
	 * These classes manipulate Field's state via this method.
	 *
	 * @param visitor
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public Coords2D getSpriteSheetCoords() {
		return spriteSheetCoordinates;
	}

	public Coords2D getRoomCoords() {
		return roomCoordinates;
	}

	public GameEventBus getEventBus() {
		return eventBus;
	}

	/**
	 *
	 * @return true if visible by the player
	 */
	public boolean isVisible() {
		return visible;
	}

	public void makeVisible() {
		visible = true;
		visited = true;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 *
	 * @return true if visited by the player
	 */
	public boolean isVisited() {
		return visited;
	}

	public String getName() {
		return name;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Returns Occurences currently occuring on the field
	 *
	 * @return Occurences occuring
	 */
	public Stack<Occurence> getOccurences() {
		return occurences;
	}

	/**
	 * Adds new occurence. Does not allow two occurences with the same name
	 *
	 * @param occurence
	 */
	public void addOccurence(Occurence occurence) {
		if (!occurences.parallelStream().anyMatch(o -> o.getName().equals(occurence.getName()))) {
			occurences.push(occurence);
		}
	}

	/**
	 * Returns uppermost Occurence(the latest), to be used for drawing
	 *
	 * @return uppermost Occurence
	 */
	public Occurence getTopOccurence() {
		return occurences.peek();
	}

	public void removeOccurence(Occurence occurence) {
		occurences.remove(occurence);
	}

	public boolean hasOccurence() {
		return !occurences.isEmpty();
	}

	@Override
	public void nextTurnNotify() {
		occurences.forEach(o -> o.visit(this));
		occurences.removeIf(o -> o.isExpired());
	}

	/**
	 * For use only during deserialization
	 * @param eventBus 
	 */
	public void setEventBus(GameEventBus eventBus) {
		if (this.eventBus == null) {
			this.eventBus = eventBus;
		}
	}
	
	
}
