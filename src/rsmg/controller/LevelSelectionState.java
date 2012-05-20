package rsmg.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

import rsmg.io.CharacterProgress;
import rsmg.io.Levels;

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
	private Image unlockedButton;
	private Image lockedButton;
	private List<LevelButton> levelButtons;
	
	/**
	 * Font to draw text on the screen.
	 */
	private UnicodeFont font;
	
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
	 * How far the view should be drawn from the top of the screen. If the
	 * screen hasn't the aspect radio 16:9, there will be black borders over and
	 * under the view.
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
	 * Maximum number of Tiles on a each row
	 */
	private static final int maxTilesOnRow = 6;
	
	/**
	 * Number of level rows. Depending on number of levels
	 */
	private int numberOfRows;
	
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

	/**
	 * Initialise the level selection menu(LevelSelectionState)
	 */
	@SuppressWarnings("unchecked")
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
		
		// Load the font which is used to write text to the screen
		font = new UnicodeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 15));
		font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		font.addAsciiGlyphs();
		font.loadGlyphs();
		
		// Create the selection glow image.
		selectionGlow = new Image(folderPath+"selectionGlow.png").getScaledCopy(scale);
		
		// Create the completed level mark image.
		completed = new Image(folderPath+"completed.png").getScaledCopy(scale/2);
		
		// Create the unlocked button image.
		unlockedButton = new Image(folderPath+"levelUnlocked.png").getScaledCopy(scale);
		
		// Create the locked button image.
		lockedButton = new Image(folderPath+"levelLocked.png").getScaledCopy(scale);

		// Set the number of unlocked levels.
		unlockedLevels = CharacterProgress.getUnlockedLevels();

		
		// Store the menu buttons in an ArrayList for convenience.
		levelButtons = new ArrayList<LevelButton>();
		
		// Fill the list levelButtons with the level buttons.
		fillLevelButtonsList();
		
		// Set which button is initially selected
		selectedButton = 0;
	}
	
	/**
	 * Fills the buttons to enter a level on the menu. Called at initialisation
	 * @throws SlickException
	 */
	private void fillLevelButtonsList() throws SlickException {
		// Get all available level numbers and sort them.
		List<Integer> levelNumbers = (ArrayList<Integer>)Levels.getLevelNumbers();
		Collections.sort(levelNumbers);
		
		numberOfRows = levelNumbers.size() / maxTilesOnRow + 1; // Total number of rows.
		
		// Go through all rows and construct the tiles.
		for (int y = 0; y < numberOfRows; y++) {

			// Number of tiles on this row.
			int tilesOnRow = (y < numberOfRows-1) ? maxTilesOnRow : levelNumbers.size() % maxTilesOnRow;
			
			// Go through all tiles on this row.
			for (int x = 0; x < tilesOnRow; x++) {

				// The button's number in the list.
				int buttonNr = y * maxTilesOnRow + x;
				
				// Get the button's level number from the levelNumbers list.
				// This is the number that should be shown on the button.
				int levelNumber = levelNumbers.get(buttonNr);
				
				// Make a LevelButton with the level number.
				levelButtons.add(new LevelButton(levelNumber, 460+x*200-77.5f, 450 + 300 * y, unlockedLevels > buttonNr));
			}
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
		gc.getInput().clearKeyPressedRecord();
		
		// Update number of unlocked levels.
		unlockedLevels = CharacterProgress.getUnlockedLevels();
		
		int numberOfLevels = Levels.getLevelNumbers().size();
		for (int i = 0; i < numberOfLevels; i++)
			levelButtons.get(i).setUnlocked(i < unlockedLevels);
	}

	/**
	 * This method is called multiply times every second to draw the graphics on
	 * the screen. Here is all images being drawn.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		// Draw the scrolling background image.
		background.draw(backgroundXPosition, topOffset);
		
		// Draw the title text.
		title.draw((gc.getWidth()-title.getWidth())/2, 50*scale + topOffset);
		
		// Draw information text about the upgrades.
		font.drawString(80*scale, 120*scale, "Press the U key to\nshow your upgrades.");
		
		// Draw the level selection buttons.
		for (int i = 0; i < levelButtons.size(); i++) {
			LevelButton button = levelButtons.get(i);

			if (button.isUnlocked())
				unlockedButton.draw(button.getX(), button.getY());
			else
				lockedButton.draw(button.getX(), button.getY());
			
			font.drawString(button.getX()+60*scale, button.getY()+50*scale, "" + button.getLevelNumber());
			
			// Mark the levels that have been completed.
			if (i < unlockedLevels-1)
				completed.draw(button.getX()+40*scale, button.getY()+170*scale);
		}

		// Draw the glow on the selected level.
		LevelButton sButton = levelButtons.get(selectedButton);
		selectionGlow.draw(sButton.getX()-(47*scale), sButton.getY()-(46*scale));
	}

	/**
	 * Update the level selection screen
	 */
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
	
	/**
	 * Handle input from the user to navigate in the level selection menu
	 * @param input key pressed
	 * @param gc
	 * @param sbg
	 * @throws SlickException
	 */
	private void handleInputs(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		if (input.isKeyPressed(Input.KEY_LEFT))
			navigateLeftInMenu();

		else if (input.isKeyPressed(Input.KEY_RIGHT))
			navigateRightInMenu();
		
		else if (input.isKeyPressed(Input.KEY_UP))
			navigateUpInMenu();
		
		else if (input.isKeyPressed(Input.KEY_DOWN))
			navigateDownInMenu();
		
		else if (input.isKeyPressed(Input.KEY_ESCAPE))
			sbg.enterState(Controller.MAINMENU_STATE, null, new BlobbyTransition());
		
		else if (input.isKeyPressed(Input.KEY_U))
			sbg.enterState(Controller.UPGRADES_STATE, null, new FadeInTransition());
		
		else if (input.isKeyDown(Input.KEY_ENTER)) {
			
			int selectedLevel = levelButtons.get(selectedButton).getLevelNumber();
			Controller.initLevel(selectedLevel);
			
			if (Levels.isBossLevel(selectedLevel)) { // Is a boss level.
				MusicHandler.startTrack(MusicHandler.Track.BOSS_MUSIC);
			} else { // Is a regular level.
				MusicHandler.startTrack(MusicHandler.Track.LEVEL_MUSIC);
			}
			
			sbg.enterState(Controller.LEVEL_STATE, null, new FadeInTransition());
		}
	}
	
	/**
	 * Navigating left in the level selection menu
	 */
	private void navigateLeftInMenu() {
		if (selectedButton % maxTilesOnRow > 0)
			selectedButton--;
	}
	
	/**
	 * Navigate right in the level selection menu
	 */
	private void navigateRightInMenu() {
		if (selectedButton % maxTilesOnRow < maxTilesOnRow-1 && selectedButton < levelButtons.size()-1 && levelButtons.get(selectedButton+1).isUnlocked())
			selectedButton++;
	}
	
	/**
	 * Navigate up in the level selection menu
	 */
	private void navigateUpInMenu() {
		if (selectedButton / maxTilesOnRow > 0)
			selectedButton -= maxTilesOnRow;
	}
	
	/**
	 * Navigate down in the level selection menu
	 */
	private void navigateDownInMenu() {
		if (selectedButton + maxTilesOnRow < levelButtons.size() && levelButtons.get(selectedButton + maxTilesOnRow).isUnlocked())
			selectedButton += maxTilesOnRow;
	}
	
	/**
	 * A class containing data about a level button.
	 * @author Daniel Jonsson
	 *
	 */
	private class LevelButton {
		
		private int levelNumber;
		private float x;
		private float y;
		private boolean unlocked;

		/**
		 * Creates a LevelButton.
		 * 
		 * @param levelNumber
		 *            The level's number.
		 * @param x
		 *            X coordinate.
		 * @param y
		 *            Y coordinate what will be scaled.
		 * @param unlocked
		 *            If the button is unlocked.
		 * @throws SlickException
		 */
		public LevelButton(int levelNumber, float x, float y, boolean unlocked) throws SlickException {

			this.levelNumber = levelNumber;
			
			this.x = x * scale;
			this.y = y * scale + topOffset;
			
			this.unlocked = unlocked;
		}
		
		/**
		 * @return The level number for this button
		 */
		private int getLevelNumber() {
			return levelNumber;
		}
		
		/**
		 * @return If this button is locked or not
		 */
		private boolean isUnlocked() {
			return unlocked;
		}
		
		/**
		 * Change the lock/unlock state of a button
		 * @param unlocked if it should be unlocked or not
		 */
		private void setUnlocked(boolean unlocked) {
			this.unlocked = unlocked;
		}
		
		/**
		 * Retrieve the x position for this button
		 * @return x position as float
		 */
		public float getX() {
			return x;
		}
		
		/**
		 * Retrieve the y position for this button
		 * @return y position as float
		 */
		public float getY() {
			return y;
		}
	}
}
