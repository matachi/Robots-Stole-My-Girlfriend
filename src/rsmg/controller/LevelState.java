package rsmg.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

/**
 * The state where the levels are played out.
 * @author Daniel Jonsson
 *
 */
public class LevelState extends State {

	Image bg;
	
	public LevelState(int stateID) {
		super(stateID);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		bg = new Image("res/art/conceptLevel.jpg", false, Image.FILTER_NEAREST);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		bg.draw(-400, -400, 2f);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Controller.MAINMENU_STATE, null, new BlobbyTransition());
		}
	}
}
