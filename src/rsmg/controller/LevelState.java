package rsmg.controller;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

import rsmg.model.Bullet;
import rsmg.model.Character;
import rsmg.model.Level;

/**
 * The state where the levels are played out.
 * @author Daniel Jonsson
 *
 */
public class LevelState extends State {

	
	/**
	 * The background image behind the tile grid.
	 */
	private Image background;
	
	/**
	 * The tiles building up the environment.
	 */
	private Image airTile;
	private Image boxTile;
	private Image laserBullet;
	
	/**
	 * The character that the player controls.
	 */
	private Animation character;
	private Animation characterRight;
	private Animation characterLeft;
	
	/**
	 * Reference to the level model.
	 */
	private Level level;
	
	/**
	 * Track if the up key is down or not.
	 */
	private boolean upKeyIsDown;
	
	/**
	 * Construct the level.
	 * @param stateID The ID to the state.
	 */
	public LevelState(int stateID) {
		super(stateID);
	}
	
	/**
	 * Initialize level images and the level model.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		background = new Image("res/sprites/level/bg.jpg", false, Image.FILTER_NEAREST);
		
		airTile = new Image("res/sprites/level/airTile.png", false, Image.FILTER_NEAREST);
		airTile = airTile.getScaledCopy(2f);
		boxTile = new Image("res/sprites/level/boxTile.png", false, Image.FILTER_NEAREST);
		boxTile = boxTile.getScaledCopy(2f);
		laserBullet = new Image("res/sprites/level/laserBullet.png", false, Image.FILTER_NEAREST);
		laserBullet = laserBullet.getScaledCopy(2f);

		/**
		 * Make an animation for when the character is running to the right;
		 */
		Image characterImage = new Image("res/sprites/level/charLaserPistolRunningSheet.png", false, Image.FILTER_NEAREST);
		SpriteSheet characterSheet = new SpriteSheet(characterImage.getScaledCopy(2f), 64, 46);
		character = characterRight = new Animation(characterSheet, 140);
		
		/**
		 * Make an animation for when the character is running to the left;
		 */
		characterImage = characterImage.getFlippedCopy(true, false);
		characterSheet = new SpriteSheet(characterImage.getScaledCopy(2f), 64, 46);
		characterLeft = new Animation(characterSheet, 140);
		
		level = new Level();
	}

	/**
	 * Draw everything from the game model on the screen.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		drawBackground();
		drawEnvironment();
		drawCharacter();
		drawBullets(level.getABulletList());
	}


	/**
	 * Draw a background image behind the tile grid.
	 */
	private void drawBackground() {
		background.draw(0, 0);
	}
	
	private void drawBullets(ArrayList<Bullet> bulletList) {
		for(Bullet bullet : bulletList){
			laserBullet.draw((float)bullet.getX()*2, (float)bullet.getY()*2);
		}
	}
	
	/**
	 * Draw the environment which consists of the tiles.
	 */
	private void drawEnvironment() {
		for (int y = 0; y < level.getTileGrid().getHeight(); y++) {
			for (int x = 0; x < level.getTileGrid().getWidth(); x++) {
				if (level.getTileGrid().getFromCoord(x, y).isSolid())
					boxTile.draw(x * 64, y * 64);
			}
		}
	}

	/**
	 * Draw the character/protagonist on the screen.
	 */
	private void drawCharacter() {
		character.draw((float)level.getCharacter().getX()*2-12, (float)level.getCharacter().getY()*2);
	}

	/**
	 * Handle inputs from the user and update the model.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		handleKeyboardEvents(gc.getInput(), sbg);
		
		// Update the model and give it the time that has passed since last
		// update as seconds.
		level.update((double)delta / 1000);
		
		if (level.getCharacter().isFacingRight())
			character = characterRight;

		else
			character = characterLeft;
	}
	
	/**
	 * Handle keyboard events.
	 * @param input
	 */
	public void handleKeyboardEvents(Input input, StateBasedGame sbg) {
		
		Character modelCharacter = level.getCharacter();
		
		if (input.isKeyDown(Input.KEY_LEFT))
			modelCharacter.moveLeft();
		else if (input.isKeyDown(Input.KEY_RIGHT))
			modelCharacter.moveRight();

		if (input.isKeyDown(Input.KEY_UP)) {
			if (!upKeyIsDown)
				modelCharacter.jump();
			upKeyIsDown = true;
		} else if (upKeyIsReleased())
			modelCharacter.jumpReleased();

		if (input.isKeyPressed(Input.KEY_SPACE))
			modelCharacter.attack();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Controller.LEVEL_SELECTION_STATE, null, new BlobbyTransition());
		}
	}

	/**
	 * Returns if the up key has been released since last loop.
	 * @return If the up key has been released.
	 */
	private boolean upKeyIsReleased() {
		if (upKeyIsDown) {
			upKeyIsDown = false;
			return true;
		}
		return false;
	}
}
