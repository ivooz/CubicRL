/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.field;

import java.io.Serializable;
import pl.iz.cubicrl.model.occurrence.Occurrence;
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

	private final Coords2D spriteSheetCoordinates;
	protected final Stack<Occurrence> Occurrences;
	protected GameEventBus eventBus;
	protected Coords2D roomCoordinates;
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
		Occurrences = new Stack<>();
		visited = false;
		visible = false;
	}

	/**
	 *
	 * @param name identifies a type
	 * @param spriteSheetCoordinates location of graphics on spritesheet
	 * @param eventBus used for publishing in game events
	 */
	public Field(String name, Coords2D spriteSheetCoordinates,
		GameEventBus eventBus) {
		this.name = name;
		this.eventBus = eventBus;
		this.spriteSheetCoordinates = spriteSheetCoordinates;
		Occurrences = new Stack<>();
		visited = false;
		visible = false;
	}

	/**
	 * Used for allowing interaction with Creatures, Occurrences, (Items?).
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
	 * Returns Occurrences currently occuring on the field
	 *
	 * @return Occurrences occuring
	 */
	public Stack<Occurrence> getOccurrences() {
		return Occurrences;
	}

	/**
	 * Adds new Occurrence. Does not allow two Occurrences with the same
	 * name
	 *
	 * @param Occurrence
	 */
	public void addOccurrence(Occurrence Occurrence) {
		if (!Occurrences.parallelStream().anyMatch(o -> o.getName().equals(Occurrence.getName()))) {
			Occurrences.push(Occurrence);
		}
	}

	/**
	 * Returns uppermost Occurrence(the latest), to be used for drawing
	 *
	 * @return uppermost Occurrence
	 */
	public Occurrence getTopOccurrence() {
		return Occurrences.peek();
	}

	public void removeOccurrence(Occurrence Occurrence) {
		Occurrences.remove(Occurrence);
	}

	public boolean hasOccurrence() {
		return !Occurrences.isEmpty();
	}

	@Override
	public void nextTurnNotify() {
		Occurrences.forEach(o -> o.visit(this));
		Occurrences.removeIf(o -> o.isExpired());
	}

	/**
	 * For use only during deserialization
	 *
	 * @param eventBus
	 */
	public void setEventBus(GameEventBus eventBus) {
		if (this.eventBus == null) {
			this.eventBus = eventBus;
		}
	}

	public void setRoomCoordinates(Coords2D roomCoordinates) {
		this.roomCoordinates = roomCoordinates;
	}

}
