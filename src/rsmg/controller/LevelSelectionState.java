package rsmg.controller;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

/**
 * The level selection screen.
 * @author Daniel Jonsson.
 *
 */
public class LevelSelectionState extends State {

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
	 * How much everything should be multiplied with. Scale = 1 is Full HD.
	 */
	private float scale = 0.5f;
	
	/**
	 * Number of unlocked levels.
	 */
	private int unlockedLevels;
	
	/**
	 * Keeps track of which of the buttons that is currently selected.
	 */
	private int selectedButton;
	
	public LevelSelectionState(int stateID) {
		super(stateID);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

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

		// Create the menu buttons
		LevelButton level1 = new LevelButton(folderPath+"level1unlocked.png", folderPath+"level1locked.png", (gc.getWidth()-155*scale)/2-150, 450, true);
		LevelButton level2 = new LevelButton(folderPath+"level2unlocked.png", folderPath+"level2locked.png", (gc.getWidth()-155*scale)/2-50, 450, true);
		LevelButton level3 = new LevelButton(folderPath+"level3unlocked.png", folderPath+"level3locked.png", (gc.getWidth()-155*scale)/2+50, 450, true);
		LevelButton level4 = new LevelButton(folderPath+"level4unlocked.png", folderPath+"level4locked.png", (gc.getWidth()-155*scale)/2+150, 450, false);
		
		// Store the menu buttons in an ArrayList for convenience
		levelButtons = new ArrayList<LevelButton>();
		levelButtons.add(level1);
		levelButtons.add(level2);
		levelButtons.add(level3);
		levelButtons.add(level4);
		
		// Set the number of unlocked levels.
		unlockedLevels = 3;
		// Set which button is initially selected
		selectedButton = 2;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		// Draw the scrolling background image.
		background.draw(backgroundXPosition, 0);
		
		// Draw the title text.
		title.draw((gc.getWidth()-title.getWidth())/2, 50*scale);
		
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
	
	private void handleInputs(Input input, GameContainer gc, StateBasedGame sbg) {
		if (input.isKeyPressed(Input.KEY_LEFT))
			navigateLeftInMenu();
		else if (input.isKeyPressed(Input.KEY_RIGHT))
			navigateRightInMenu();
		else if (input.isKeyPressed(Input.KEY_ESCAPE))
			changeState(gc, sbg, 1);
		else if (input.isKeyDown(Input.KEY_ENTER))
			changeState(gc, sbg, 2);
	}
	
	private void navigateLeftInMenu() {
		if (selectedButton > 0)
			selectedButton--;
	}
	
	private void navigateRightInMenu() {
		if (selectedButton < unlockedLevels-1)
			selectedButton++;
	}
	
	private void changeState(GameContainer gc, StateBasedGame sbg, int select) {
		if (select == 1)
			sbg.enterState(Controller.MAINMENU_STATE, null, new BlobbyTransition());
		else
			sbg.enterState(Controller.LEVEL1_STATE, null, new FadeInTransition());
	}
	
	/**
	 * A class containing data about a level button.
	 * @author Daniel Jonsson
	 *
	 */
	private class LevelButton {
		
		private Image unlockedButton;
		private Image lockedButton;
		private float x;
		private float y;
		private boolean unlocked;

		/**
		 * Creates a LevelButton.
		 * @param pathToUnlockedButton File path to the unlocked button image.
		 * @param pathToLockedButton File path to the locked button image.
		 * @param x X coordinate.
		 * @param y Y coordinate what will be scaled.
		 * @param unlocked If the button is unlocked.
		 * @throws SlickException
		 */
		public LevelButton(String pathToUnlockedButton, String pathToLockedButton,
				float x, float y, boolean unlocked) throws SlickException {

			this.unlockedButton = new Image(pathToUnlockedButton);
			this.unlockedButton = this.unlockedButton.getScaledCopy(scale);
			
			this.lockedButton = new Image(pathToLockedButton);
			this.lockedButton = this.lockedButton.getScaledCopy(scale);
			
			this.x = x;
			this.y = y * scale;
			
			this.unlocked = unlocked;
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
