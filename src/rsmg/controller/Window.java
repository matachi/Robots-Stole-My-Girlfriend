package rsmg.controller;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import rsmg.io.Config;

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
			
			if (Config.fullScreenOn())
				app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
			else
				app.setDisplayMode(960, 540, false);
			
			app.setShowFPS(false);
			app.setTargetFrameRate(120);
			
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
