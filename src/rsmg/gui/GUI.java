package rsmg.gui;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class GUI extends BasicGame {

	
	Image bg;
	Image ch;
	SpriteSheet ss;
	Animation a;
	
	float x = 0;
	float y = 0;

	public GUI() {
		super("Test");
	}

	@Override
	public void init(GameContainer g) throws SlickException {
		ch = new Image("res/sprites/charPistolRunningSheet.png", false, Image.FILTER_NEAREST);
		ss = new SpriteSheet(ch.getScaledCopy(2f), 64, 46);
		a = new Animation(ss, 140);
		bg = new Image("res/art/conceptLevel.jpg", false, Image.FILTER_NEAREST);
	}

	@Override
	public void update(GameContainer g, int delta) throws SlickException {
		Input input = g.getInput();
		if (input.isKeyDown(Input.KEY_LEFT)) {
			x -= 400f * delta / 1000;
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			x += 400f * delta / 1000;
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			y -= 400f * delta / 1000;
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			y += 400f * delta / 1000;
		}
	}

	@Override
	public void render(GameContainer g, Graphics gfx) throws SlickException {
		bg.draw(-400, -400, 2f);
		a.draw(x, y);
	}

	public static void main(String[] args) throws SlickException,
			LWJGLException {

		AppGameContainer app = new AppGameContainer(new GUI());

		app.setDisplayMode(800, 600, false);
		app.start();
	}
}