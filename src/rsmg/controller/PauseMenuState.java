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

import rsmg.io.Config;

/**
 * The pause menu state.
 * @author Daniel Jonsson
 *
 */
class PauseMenuState extends State {

	/**
	 * The main menu's images.
	 */
	private Image background;
	private Image title;
	private ArrayList<MenuButton> menuButtons;
	
	/**
	 * Keeps track of which of the buttons that is currently selected.
	 */
	private int selectedButton;
	
	/**
	 * How much everything should be multiplied with.
	 */
	private float scale;
	
	/**
	 * How far the view should be drawn from the top of the screen. If the screen hasn't
	 * the aspect radio 16:9, there will be black borders over and under the view.
	 */
	private float topOffset;
	
	PauseMenuState(int stateID) {
		super(stateID);
	}

	/**
	 * Initialize the main menu.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		scale = (float)gc.getWidth() / 1920;
		
		topOffset = (gc.getHeight() - 1080 * scale) / 2;
		
		// Folder path to the sprites.
		String folderPath = "res/sprites/pauseMenu/";
		
		// Create the bg image and scale it to fit the window's width
		background = new Image(folderPath+"bg.jpg").getScaledCopy(scale);
		
		// Create the title image with the same scale as the background image
		title = new Image(folderPath+"title.png").getScaledCopy(scale);

		// Create the menu buttons
		MenuButton continueButton = new MenuButton(folderPath+"continue.png", folderPath+"continueSelected.png", gc.getWidth(), 400, scale);
		MenuButton quitButton = new MenuButton(folderPath+"quitLevel.png", folderPath+"quitLevelSelected.png", gc.getWidth(), 500, scale);
		
		// Store the menu buttons in an ArrayList for convenience
		menuButtons = new ArrayList<MenuButton>();
		menuButtons.add(continueButton);
		menuButtons.add(quitButton);
		
		// Set which button is initially selected
		selectedButton = 0;
		menuButtons.get(selectedButton).toggleSelected();
	}

	/**
	 * Handle inputs from the keyboard.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		handleInputs(gc.getInput(), gc, sbg);
	}

	/**
	 * Draw main menu's images.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		background.draw(0, topOffset);
		title.draw((gc.getWidth() - title.getWidth()) / 2, 40+topOffset);
		
		for (MenuButton m : menuButtons) {
			m.getImage().draw(m.getX(), m.getY()+topOffset);
		}
	}
	
	/**
	 * Reset the selected button to the first button before state is changed.
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame game)
			throws SlickException {
		menuButtons.get(selectedButton).toggleSelected();
		selectedButton = 0;
		menuButtons.get(selectedButton).toggleSelected();
	}

	/**
	 * Handle inputs from the user to navigate in the pause menu
	 * @param input
	 * @param gc
	 * @param sbg
	 * @throws SlickException
	 */
	private void handleInputs(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		if (input.isKeyPressed(Input.KEY_UP)) {
			navigateUpInMenu();
		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			navigateDownInMenu();
		} else if (input.isKeyDown(Input.KEY_ENTER) || input.isKeyDown(Input.KEY_RIGHT)) {
			switch (selectedButton) {
			case 0:
				sbg.enterState(Controller.LEVEL_STATE, null, new FadeInTransition());
				break;
			case 1:
				if (Config.musicOn()) {
					Music backgroundMusic = new Music("res/music/WolfRock-WelcomeToTheTemple.ogg", true);
					backgroundMusic.loop(1, 0.1f);
				}
				sbg.enterState(Controller.LEVEL_SELECTION_STATE, null, new BlobbyTransition());
				break;
			}
		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Controller.LEVEL_STATE, null, new BlobbyTransition());
		}
	}
	
	/**
	 * Navigate up in the menu.
	 */
	private void navigateUpInMenu() {
		if (selectedButton > 0) {
			menuButtons.get(selectedButton).toggleSelected();
			selectedButton--;
			menuButtons.get(selectedButton).toggleSelected();
		}
	}
	
	/**
	 * Navigate down in the menu.
	 */
	private void navigateDownInMenu() {
		if (selectedButton < menuButtons.size()-1) {
			menuButtons.get(selectedButton).toggleSelected();
			selectedButton++;
			menuButtons.get(selectedButton).toggleSelected();
		}
	}
	
	/**
	 * A class containing data about a menu button.
	 * @author Daniel Jonsson
	 *
	 */
	private class MenuButton {
		
		private Image button;
		private Image selectedButton;
		private float x;
		private float y;
		private boolean selected;

		/**
		 * Creates a menu button and places it in the center horizontally on
		 * the screen.
		 * @param pathToButton The path to where the image file is stored.
		 * @param pathToSelectedButton Path to the selected image.
		 * @param screenWidth The width of the screen.
		 * @param y The y coordinate on the original image.
		 * @param scale How much the menu button should be scaled.
		 * @throws SlickException
		 */
		public MenuButton(String pathToButton, String pathToSelectedButton,
				int screenWidth, int y, float scale) throws SlickException {

			this.button = new Image(pathToButton);
			this.button = this.button.getScaledCopy(scale);
			
			this.selectedButton = new Image(pathToSelectedButton);
			this.selectedButton = this.selectedButton.getScaledCopy(scale);
			
			this.x = (screenWidth - this.button.getWidth()) / 2;
			this.y = y * scale;
			
			selected = false;
		}
		
		/**
		 * Get this button
		 * @return this button
		 */
		public Image getImage() {
			if (selected)
				return selectedButton;
			else
				return button;
		}
		
		/**
		 * Toggle this button
		 */
		public void toggleSelected() {
			selected = !selected;
		}
		
		/**
		 * Retrieve the x position for this button
		 * @return X position as float
		 */
		public float getX() {
			return x;
		}
		
		/**
		 * Retrieve the y position for this button
		 * @return Y position as float
		 */
		public float getY() {
			return y;
		}
	}
}
