package rsmg.controller;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

import rsmg.io.Config;

/**
 * The options view state. In this state is the user able to change some settings
 * in the game. Like if the music should be on or off, or if he wants to play
 * in full screen.
 * @author Daniel Jonsson
 *
 */
class OptionsState extends State {

	/**
	 * Set the default option settings
	 */
	private static final int FULLSCREEN_SETTING = 0;
	private static final int MUSIC_SETTING = 1;
	private static final int SOUNDEFFECTS_SETTING = 2;
	
	/**
	 * The options view's various images.
	 */
	private Image background;
	private Image title;
	private Image on;
	private Image off;
	private ArrayList<OptionEntry> options;
	private Image selected;
	
	/**
	 * The position that the title image will have.
	 */
	private float titleXPos;
	private float titleYPos;
	
	/**
	 * How far the on/off controller will be placed from the option entry on the x-axis.
	 */
	private float onOffOffset;
	
	/**
	 * How far the selected marker will be placed from the selected option entry on the x-axis.
	 */
	private float selectedOffset;
	
	/**
	 * Keeps track of which of the buttons that is currently selected.
	 */
	private int selectedEntry;
	
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
	 * Create the options view state.
	 * @param stateID
	 */
	OptionsState(int stateID) {
		super(stateID);
	}

	/**
	 * Will be initialized when the state is created.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		scale = (float)gc.getWidth() / 1920;
		
		topOffset = (gc.getHeight() - 1080 * scale) / 2;
		
		// Folder path to the sprites.
		String folderPath = "res/sprites/options/";
		
		// Create the bg image and scale it to fit the window's width
		background = new Image(folderPath+"bg.jpg");
		background = background.getScaledCopy(scale);
		
		// Create the title image with the same scale as the background image
		title = new Image(folderPath+"title.png");
		title = title.getScaledCopy(scale);

		// Set the title's position
		titleXPos = 651 * scale;
		titleYPos = 100 * scale;
		
		// Create an image to show which entry is selected
		selected = new Image(folderPath+"selected.png");
		selected = selected.getScaledCopy(scale);
		
		// How far from the entry on the x-axis the selected image will be placed
		selectedOffset = -100 * scale;
		
		// Create an image that is shown when an option is turned ON
		on = new Image(folderPath+"on.png");
		on = on.getScaledCopy(scale);
		
		// Create an image that is shown when an option is turned OFF
		off = new Image(folderPath+"off.png");
		off = off.getScaledCopy(scale);

		// How far from the entry on the x-axis the on/off controller image will be placed
		onOffOffset = 400 * scale;

		// Create the options entries and initialize them with the settings from the config file
		OptionEntry fullScreen = new OptionEntry(folderPath+"fullScreen.png", 650, 400, Config.fullScreenOn(), scale);
		OptionEntry music = new OptionEntry(folderPath+"music.png", 650, 500, Config.musicOn(), scale);
		OptionEntry soundEffects = new OptionEntry(folderPath+"soundEffects.png", 650, 600, Config.soundEffectsOn(), scale);
		
		// Store the options buttons in an ArrayList for convenience
		options = new ArrayList<OptionEntry>();
		options.add(fullScreen);
		options.add(music);
		options.add(soundEffects);
		
		// Set which button is initially selected
		selectedEntry = 0;
		options.get(selectedEntry).toggleSelected();
	}
	
	/**
	 * This method is run when the controller enter this state.
	 */
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
		
		// To prevent the selected option in the menu to be toggled when entering the state
		gc.getInput().clearKeyPressedRecord();
	}

	/**
	 * This method in run when the controller leaves this state.
	 */
	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.leave(container, game);
		
		// Write the configuration settings to the hard drive
		Config.saveConfigFile();
	}

	/**
	 * This method is run between every frame. Here is key inputs handled.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		handleInputs(input, gc, sbg);
	}

	/**
	 * Handle and respond to various key inputs.
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
		} else if (input.isKeyPressed(Input.KEY_ENTER)) {
			options.get(selectedEntry).toggleOn();
			boolean settingOn = options.get(selectedEntry).getOn();
			switch (selectedEntry) {
			case FULLSCREEN_SETTING:
				Config.setFullScreenOn(settingOn);
				break;
			case MUSIC_SETTING:
				Config.setMusicOn(settingOn);
				break;
			case SOUNDEFFECTS_SETTING:
				Config.setSoundEffectsOn(settingOn);
				break;
			}
		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			MusicHandler.startTrack(MusicHandler.Track.MENU_MUSIC);
			sbg.enterState(Controller.MAINMENU_STATE, null, new BlobbyTransition());
		}
	}
	
	/**
	 * Navigate up in the menu.
	 */
	private void navigateUpInMenu() {
		if (selectedEntry > 0) {
			options.get(selectedEntry).toggleSelected();
			selectedEntry--;
			options.get(selectedEntry).toggleSelected();
		}
	}
	
	/**
	 * Navigate down in the menu.
	 */
	private void navigateDownInMenu() {
		if (selectedEntry < options.size()-1) {
			options.get(selectedEntry).toggleSelected();
			selectedEntry++;
			options.get(selectedEntry).toggleSelected();
		}
	}

	/**
	 * This method is called multiply times every second to draw the graphics on
	 * the screen. Here is all images being drawn.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0, topOffset);
		title.draw(titleXPos, titleYPos+topOffset);
		
		for (OptionEntry o : options) {
			o.getImage().draw(o.getX(), o.getY()+topOffset);
			
			if (o.getOn())
				on.draw(o.getX() + onOffOffset, o.getY()+topOffset);
			else
				off.draw(o.getX() + onOffOffset, o.getY()+topOffset);
			
			if (o.getSelected())
				selected.draw(o.getX() + selectedOffset, o.getY()+topOffset);
		}
	}
	
	/**
	 * A class containing data about a option menu entry.
	 * @author Daniel Jonsson
	 *
	 */
	private class OptionEntry {
		
		private Image entry;
		private float x;
		private float y;
		private boolean on;
		private boolean selected;

		/**
		 * Create an option entry in the menu.
		 * @param entry The path to the entry image.
		 * @param x The x position for the entry.
		 * @param y The y position for the entry.
		 * @param on If the option is turned on or off.
		 * @param scale How much the image and position should be scaled.
		 * @throws SlickException
		 */
		public OptionEntry(String entry, float x, float y, boolean on, float scale) throws SlickException {

			this.entry = new Image(entry).getScaledCopy(scale);
			
			this.x = x * scale;
			this.y = y * scale;
			
			this.on = on;
			
			this.selected = false;
		}
		
		/**
		 * Get this button
		 * @return this button
		 */
		public Image getImage() {
			return entry;
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
		
		/**
		 * Toggle this button
		 */
		public void toggleOn() {
			on = !on;
		}
		
		/**
		 * Tells if the button is selected or not
		 * @return If button is selected
		 */
		public boolean getOn() {
			return on;
		}
		
		/**
		 * Toggle this button
		 */
		public void toggleSelected() {
			selected = !selected;
		}
		
		/**
		 * Tells if the button is selected or not
		 * @return If button is selected
		 */
		public boolean getSelected() {
			return selected;
		}
	}
}
