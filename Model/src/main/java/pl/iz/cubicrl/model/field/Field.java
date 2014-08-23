/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.field;

import pl.iz.cubicrl.model.occurence.Occurence;
import java.util.Stack;
import pl.iz.cubicrl.model.api.TurnObserver;
import pl.iz.cubicrl.model.api.Visitor;
import pl.iz.cubicrl.model.core.Coords2D;

/**
 * Represents a tiled space on map.
 *
 * @author Ivo
 */
public class Field implements TurnObserver {

	protected final Coords2D roomCoordinates;
	private final Coords2D spriteSheetCoordinates;
	protected final Stack<Occurence> occurences;
	private final String name;
	private boolean visible;
	private boolean visited;

	/**
	 *
	 * @param name identifies a type
	 * @param roomCoordinates location in the room
	 * @param spriteSheetCoordinates location of graphics on spritesheet
	 */
	public Field(String name, Coords2D roomCoordinates, Coords2D spriteSheetCoordinates) {
		this.name = name;
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
}
