package rsmg.controller;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Constructs the game's window and starts the game loop.
 * @author Daniel Jonsson
 *
 */
public class Window {

	public Window() {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new Controller());
			app.setDisplayMode(960, 540, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
