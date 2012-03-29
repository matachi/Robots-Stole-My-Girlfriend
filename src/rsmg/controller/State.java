package rsmg.controller;

import org.newdawn.slick.state.BasicGameState;

/**
 * Simplifies the BasicGameState class.
 * A state is a view in the game, like the main menu, level selection screen or
 * an actual level. Only one state can be showed at a single time.
 * @author Daniel Jonsson
 *
 */
public abstract class State extends BasicGameState  {

	private int stateID;
	
	public State(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public int getID() {
		return stateID;
	}

}
