package rsmg.controller;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

import rsmg.io.CharacterProgress;
import rsmg.io.Config;
import rsmg.io.LevelNumbers;

/**
 * The level selection screen.
 * @author Daniel Jonsson.
 *
 */
class LevelSelectionState extends State {

	/**
	 * Folder path to the sprites.
	 */
	private static final String folderPath = "res/sprites/levelSelection/";
	
	/**
	 * The view's images.
	 */
	private Image background;
	private Image title;
	private Image selectionGlow;
	private Image completed;
	private ArrayList<LevelButton> levelButtons;
	
	/**
	 * A couple of variables for the scrolling background image.
	 */
	private float backgroundXPosition;
	private boolean backgroundScrollingRight;
	
	/**
	 * How much everything should be multiplied with.
	 */
	private float scale;
	
	/**
	 * How far the view should be drawn from the top of the screen. If the screen hasn't
	 * the aspect radio 16:9, there will be black borders over and under the view.
	 */
	private float topOffset;
	
	/**
	 * Number of unlocked levels.
	 */
	private int unlockedLevels;
	
	/**
	 * Keeps track of which of the buttons that is currently selected.
	 */
	private int selectedButton;
	
	/**
	 * Create the level selection state.
	 * 
	 * @param stateID
	 *            The state's ID.
	 * @param controller
	 *            Reference to the controller. Is needed to be able to tell
	 *            LevelState what level data that should be initialized.
	 */
	LevelSelectionState(int stateID) {
		super(stateID);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		scale = (float)gc.getWidth() / 1920;
		
		topOffset = (gc.getHeight() - 1080 * scale) / 2;
		
		// Create the bg image and scale it to fit the window's width
		background = new Image(folderPath+"bg.jpg");
		background = background.getScaledCopy(scale);
		backgroundXPosition = 0;
		backgroundScrollingRight = true;
		
		// Create the title image with the same scale as the background image
		title = new Image(folderPath+"title.png");
		title = title.getScaledCopy(scale);
		
		// Create the selection glow image.
		selectionGlow = new Image(folderPath+"selectionGlow.png");
		selectionGlow = selectionGlow.getScaledCopy(scale);
		
		// Create the completed level mark image.
		completed = new Image(folderPath+"completed.png");
		completed = completed.getScaledCopy(scale);

		// Set the number of unlocked levels.
		unlockedLevels = CharacterProgress.unlockedLevels();

		// Store the menu buttons in an ArrayList for convenience
		levelButtons = new ArrayList<LevelButton>();
		
		// Create the menu buttons.
		// 77.5 is the half width of a button, and is needed to place them
		// centered horizontally on the screen. (The buttons are only
		// horizontally aligned if there are 4 buttons.)
		for (int i : LevelNumbers.getLevelNumbers())
			if (i > 0 && i < 5)
				levelButtons.add(new LevelButton(i, folderPath+"level"+i+"unlocked.png", folderPath+"level"+i+"locked.png", 460+i*200-77.5f, 450, unlockedLevels >= i));
		
		// Set which button is initially selected
		selectedButton = 0;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
		gc.getInput().clearKeyPressedRecord();
		unlockedLevels = CharacterProgress.unlockedLevels();
		for (int i = 0; i < CharacterProgress.unlockedLevels(); i++)
			if (!levelButtons.get(i).isUnlocked())
				levelButtons.get(i).toggleUnlocked();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		// Draw the scrolling background image.
		background.draw(backgroundXPosition, topOffset);
		
		// Draw the title text.
		title.draw((gc.getWidth()-title.getWidth())/2, 50*scale + topOffset);
		
		// Draw the level selection buttons.
		for (int i = 0; i < levelButtons.size(); i++) {
			LevelButton levelButton = levelButtons.get(i);
			levelButton.getImage().draw(levelButton.getX(), levelButton.getY());
			// Mark the levels that has been completed with.
			if (i < unlockedLevels-1)
				completed.draw(levelButton.getX(), levelButton.getY()+200*scale);
		}

		// Draw the glow on the selected level.
		LevelButton sButton = levelButtons.get(selectedButton);
		selectionGlow.draw(sButton.getX()-(47*scale), sButton.getY()-(46*scale));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		handleInputs(gc.getInput(), gc, sbg);
		
		// Scroll the background.
		if (backgroundScrollingRight)
			backgroundXPosition -= delta/70f;
		else
			backgroundXPosition += delta/70f;
		
		// Check if the scrolling direction should be changed.
		if (gc.getWidth() - backgroundXPosition >= background.getWidth() || backgroundXPosition >= 0)
			backgroundScrollingRight = !backgroundScrollingRight;
	}
	
	private void handleInputs(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		if (input.isKeyPressed(Input.KEY_LEFT))
			navigateLeftInMenu();
		else if (input.isKeyPressed(Input.KEY_RIGHT))
			navigateRightInMenu();
		else if (input.isKeyPressed(Input.KEY_ESCAPE))
			sbg.enterState(Controller.MAINMENU_STATE, null, new BlobbyTransition());
		else if (input.isKeyDown(Input.KEY_ENTER)) {
			Controller.initLevel(levelButtons.get(selectedButton).getLevelNumber());
			sbg.enterState(Controller.LEVEL_STATE, null, new FadeInTransition());
			if (Config.musicOn()) {
				Music backgroundMusic = new Music("res/music/WolfRock-NightOfTheMutants.ogg", true);
				backgroundMusic.loop(1, 0.2f);
			}
		}
	}
	
	private void navigateLeftInMenu() {
		if (selectedButton > 0)
			selectedButton--;
	}
	
	private void navigateRightInMenu() {
		if (selectedButton < unlockedLevels-1)
			selectedButton++;
	}
	
//	private void changeState(GameContainer gc, StateBasedGame sbg, int select) {
//		if (select == 1)
//			sbg.enterState(Controller.MAINMENU_STATE, null, new BlobbyTransition());
//		else
//			sbg.enterState(Controller.LEVEL_STATE, null, new FadeInTransition());
//	}
	
	/**
	 * A class containing data about a level button.
	 * @author Daniel Jonsson
	 *
	 */
	private class LevelButton {
		
		private int levelNumber;
		private Image unlockedButton;
		private Image lockedButton;
		private float x;
		private float y;
		private boolean unlocked;

		/**
		 * Creates a LevelButton.
		 * 
		 * @param levelNumber
		 *            The level's number.
		 * @param pathToUnlockedButton
		 *            File path to the unlocked button image.
		 * @param pathToLockedButton
		 *            File path to the locked button image.
		 * @param x
		 *            X coordinate.
		 * @param y
		 *            Y coordinate what will be scaled.
		 * @param unlocked
		 *            If the button is unlocked.
		 * @throws SlickException
		 */
		public LevelButton(int levelNumber, String pathToUnlockedButton,
				String pathToLockedButton,	float x, float y, boolean unlocked) throws SlickException {

			this.levelNumber = levelNumber;
			
			this.unlockedButton = new Image(pathToUnlockedButton);
			this.unlockedButton = this.unlockedButton.getScaledCopy(scale);
			
			this.lockedButton = new Image(pathToLockedButton);
			this.lockedButton = this.lockedButton.getScaledCopy(scale);
			
			this.x = x * scale;
			this.y = y * scale + topOffset;
			
			this.unlocked = unlocked;
		}
		
		private int getLevelNumber() {
			return levelNumber;
		}
		
		private boolean isUnlocked() {
			return unlocked;
		}
		
		private void toggleUnlocked() {
			unlocked = !unlocked;
		}
		
		public Image getImage() {
			if (unlocked)
				return unlockedButton;
			else
				return lockedButton;
		}
		
		public float getX() {
			return x;
		}
		
		public float getY() {
			return y;
		}
	}
}
