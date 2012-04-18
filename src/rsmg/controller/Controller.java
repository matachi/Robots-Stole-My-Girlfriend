package rsmg.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Contains more or less the whole game, and manages the different states of the
 * game.
 * @author Daniel Jonsson
 *
 */
class Controller extends StateBasedGame {

	static final int MAINMENU_STATE = 0;
	
	static final int LEVEL1_STATE = 1;
	
	static final int LEVEL_SELECTION_STATE = 2;
	
	static final int OPTIONS_STATE = 3;
	
	static final int PAUSE_MENU_STATE = 4;
	
	/**
	 * 
	 */
	Controller() {
		super("Robots Stole My Girlfriend");
	}

	/**
	 * Initializes all states in the game.
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MainMenuState(MAINMENU_STATE));
		this.addState(new LevelState(LEVEL1_STATE));
		this.addState(new LevelSelectionState(LEVEL_SELECTION_STATE));
		this.addState(new OptionsState(OPTIONS_STATE));
		this.addState(new PauseMenuState(PAUSE_MENU_STATE));
//		this.getState(MAINMENU_STATE).init(container, this);
//		this.getState(LEVEL1_STATE).init(container, this);
	}
}
