package rsmg.controller;

import org.newdawn.slick.state.BasicGameState;

/**
 * Simplifies the BasicGameState class. A state is a view in the game, like the
 * main menu, level selection screen or an actual level. Only one state can be
 * showed at a single time. This abstract class help with handling these states
 * ID's.
 * 
 * @author Daniel Jonsson
 * 
 */
abstract class State extends BasicGameState  {

	/**
	 * The state's unique ID.
	 */
	private int stateID;
	
	/**
	 * Create a new State an assign it with an ID.
	 * @param stateID This state's ID.
	 */
	State(int stateID) {
		this.stateID = stateID;
	}

	/**
	 * The state's ID.
	 */
	@Override
	public int getID() {
		return stateID;
	}

}
