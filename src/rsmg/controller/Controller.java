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
public class Controller extends StateBasedGame {

	public static final int MAINMENU_STATE = 0;
	
	public static final int LEVEL1_STATE = 1;
	
	/**
	 * 
	 */
	public Controller() {
		super("Robots Stole My Girlfriend");
	}

	/**
	 * Initializes all states in the game.
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new MainMenuState(MAINMENU_STATE));
		this.addState(new LevelState(LEVEL1_STATE));
//		this.getState(MAINMENU_STATE).init(container, this);
//		this.getState(LEVEL1_STATE).init(container, this);
	}
}
