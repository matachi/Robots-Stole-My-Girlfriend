package rsmg.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

import rsmg.io.CharacterProgress;

/**
 * The upgrades menu state. This is the view where the player can spend his
 * upgrade points on upgrades.
 * 
 * @author Daniel Jonsson
 * 
 */
class UpgradesState extends State {

	/**
	 * The upgrade screen's images.
	 */
	private Image background;
	private Image title;
	private Image upgradeLocked;
	private Image upgradeLockedSelected;
	private Image upgradeUnlocked;
	private Image upgradeUnlockedSelected;
	private Image upgradeUnavailable;

	/**
	 * Font to draw text on the screen.
	 */
	private UnicodeFont font;
	
	/**
	 * Information about the upgrade buttons.
	 */
	private UpgradeButton upgradeButtons[][];
	
	/**
	 * Keeps track of which of the buttons that is currently selected.
	 */
	private int selectedX;
	private int selectedY;
	
	/**
	 * How much everything should be multiplied with.
	 */
	private float scale;
	
	/**
	 * How far the view should be drawn from the top of the screen. If the screen hasn't
	 * the aspect radio 16:9, there will be black borders over and under the view.
	 */
	private float topOffset;
	
	UpgradesState(int stateID) {
		super(stateID);
	}

	/**
	 * Initialize the upgrade menu.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		scale = (float)gc.getWidth() / 1920;
		
		topOffset = (gc.getHeight() - 1080 * scale) / 2;
		
		// Folder path to the sprites.
		String folderPath = "res/sprites/upgradesScreen/";
		
		// Create the bg image and scale it to fit the window's width
		background = newImage(folderPath+"bg.jpg", scale);
		
		// Create the title image with the same scale as the background image
		title = newImage(folderPath+"title.png", scale);
		
		// Load the font which is used to write text to the screen
		font = new UnicodeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 15));
		font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		font.addAsciiGlyphs();
		font.loadGlyphs();
		
		// Init upgrade button images
		upgradeLocked = newImage(folderPath+"upgradeLocked.png", scale);
		upgradeLockedSelected = newImage(folderPath+"upgradeLockedSelected.png", scale);
		upgradeUnlocked = newImage(folderPath+"upgradeUnlocked.png", scale);
		upgradeUnlockedSelected = newImage(folderPath+"upgradeUnlockedSelected.png", scale);
		upgradeUnavailable = newImage(folderPath+"upgradeUnavailable.png", scale);
		
		// Init upgrade button information
		upgradeButtons = new UpgradeButton[2][4];
		upgradeButtons[0][0] = new UpgradeButton(CharacterProgress.INC_RUNNING_SPEED, "Run Faster", 100, 400, scale);
		upgradeButtons[0][1] = new UpgradeButton(CharacterProgress.INC_RPG_KNOCKBACK, "More RPG\nKnockback", 500, 400, scale);
		upgradeButtons[0][2] = new UpgradeButton(CharacterProgress.INC_SHOTGUN_SPREAD, "Shotgun Spread", 1107, 400, scale);
		upgradeButtons[0][3] = new UpgradeButton(CharacterProgress.DEC_ASSAULT_RIFLE_KNOCKBACK, "Less Assault Rifle\nKnockback", 1507, 400, scale);
		upgradeButtons[1][0] = new UpgradeButton(CharacterProgress.DASH, "Dash", 100, 700, scale);
		upgradeButtons[1][1] = new UpgradeButton(CharacterProgress.DOUBLE_JUMP, "Double Jump", 500, 700, scale);
		upgradeButtons[1][2] = new UpgradeButton(CharacterProgress.RAPID_FIRE, "Rapid Fire", 1107, 700, scale);
		upgradeButtons[1][3] = new UpgradeButton(CharacterProgress.INC_RPG_AOE, "Bigger RPG\nAoE", 1507, 700, scale);
		
		// Check which upgrades are already unlocked
		if (CharacterProgress.isIncRunningSpeedUnlocked())
			upgradeButtons[0][0].toggleUnlocked();
		if (CharacterProgress.isIncRPGKnockbackUnlocked())
			upgradeButtons[0][1].toggleUnlocked();
		if (CharacterProgress.isIncShotgunSpreadUnlocked())
			upgradeButtons[0][2].toggleUnlocked();
		if (CharacterProgress.isDecAssaultRifleKnockbackUnlocked())
			upgradeButtons[0][3].toggleUnlocked();
		if (CharacterProgress.isDashUnlocked())
			upgradeButtons[1][0].toggleUnlocked();
		if (CharacterProgress.isDoubleJumpUnlocked())
			upgradeButtons[1][1].toggleUnlocked();
		if (CharacterProgress.isRapidFireUnlocked())
			upgradeButtons[1][2].toggleUnlocked();
		if (CharacterProgress.isIncRPGAoEUnlocked())
			upgradeButtons[1][3].toggleUnlocked();
		
		// Set which button is initially selected
		selectedX = 0;
		selectedY = 0;
		upgradeButtons[selectedY][selectedX].toggleSelected();
	}
	
	private static Image newImage(final String path, final float scale) throws SlickException {
		return new Image(path).getScaledCopy(scale);
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
		
		drawBackground();
		drawTitle(gc.getWidth());
		drawTexts();
		drawUpgradeButtons();
	}
	
	private void drawBackground() {
		background.draw(0, topOffset);
	}
	
	private void drawTitle(int windowWidth) {
		title.draw((windowWidth - title.getWidth()) / 2, 80*scale+topOffset);
	}
	
	private void drawTexts() {
		// titles for the upgrade categories
		font.drawString(300*scale, 300*scale, "Movement Upgrades");
		font.drawString(1300*scale, 300*scale, "Combat Upgrades");
		
		// number of available upgrade points
		font.drawString(100*scale, 100*scale, "Upgrade Points: " + CharacterProgress.getUpgradePoints());
	}
	
	private void drawUpgradeButtons() {
		for (UpgradeButton[] rows : upgradeButtons) {
			for (UpgradeButton upgrade : rows) {
				if (upgrade.isAvailable()) {
					
					if (upgrade.isSelected() && upgrade.isUnlocked())
						upgradeUnlockedSelected.draw(upgrade.getX()-46f*scale, upgrade.getY()-46f*scale);
					
					else if (upgrade.isSelected() && !upgrade.isUnlocked())
						upgradeLockedSelected.draw(upgrade.getX()-46f*scale, upgrade.getY()-46f*scale);
					
					else if (!upgrade.isSelected() && upgrade.isUnlocked())
						upgradeUnlocked.draw(upgrade.getX(), upgrade.getY());
					
					else if (!upgrade.isSelected() && !upgrade.isUnlocked())
						upgradeLocked.draw(upgrade.getX(), upgrade.getY());
					
				} else {
					upgradeUnavailable.draw(upgrade.getX(), upgrade.getY());
				}
				font.drawString(upgrade.getX()+20*scale, upgrade.getY()+20*scale, upgrade.getText());
			}
		}
	}
	
	/**
	 * Reset the selected button to the first button before state is changed.
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame game)
			throws SlickException {
//		menuButtons.get(selectedButton).toggleSelected();
//		selectedButton = 0;
//		menuButtons.get(selectedButton).toggleSelected();
	}

	private void handleInputs(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		if (input.isKeyPressed(Input.KEY_UP))
			navigateUpInGrid();
		else if (input.isKeyPressed(Input.KEY_DOWN))
			navigateDownInGrid();
		else if (input.isKeyPressed(Input.KEY_LEFT))
			navigateLeftInGrid();
		else if (input.isKeyPressed(Input.KEY_RIGHT))
			navigateRightInGrid();
		else if (input.isKeyPressed(Input.KEY_ENTER))
			selectUpgrade();
		else if (input.isKeyPressed(Input.KEY_ESCAPE))
			sbg.enterState(Controller.LEVEL_SELECTION_STATE, null, new BlobbyTransition());
	}
	
	private void navigateUpInGrid() {
		if (selectedY > 0) {
			upgradeButtons[selectedY][selectedX].toggleSelected();
			selectedY--;
			upgradeButtons[selectedY][selectedX].toggleSelected();
		}
	}
	
	private void navigateDownInGrid() {
		if (selectedY < upgradeButtons.length-1) {
			upgradeButtons[selectedY][selectedX].toggleSelected();
			selectedY++;
			upgradeButtons[selectedY][selectedX].toggleSelected();
		}
	}
	
	private void navigateLeftInGrid() {
		if (selectedX > 0) {
			upgradeButtons[selectedY][selectedX].toggleSelected();
			selectedX--;
			upgradeButtons[selectedY][selectedX].toggleSelected();
		}
	}
	
	private void navigateRightInGrid() {
		if (selectedX < upgradeButtons[0].length-1) {
			upgradeButtons[selectedY][selectedX].toggleSelected();
			selectedX++;
			upgradeButtons[selectedY][selectedX].toggleSelected();
		}
	}
	
	private void selectUpgrade() {
		if (!upgradeButtons[selectedY][selectedX].isUnlocked()) {
			CharacterProgress.setUpgrade(upgradeButtons[selectedY][selectedX].getUpgrade(), true);
			CharacterProgress.saveFile();
			upgradeButtons[selectedY][selectedX].toggleUnlocked();
		}
	}
	
	/**
	 * A class containing data about a upgrade button.
	 * 
	 * @author Daniel Jonsson
	 * 
	 */
	private class UpgradeButton {
		
		private int upgrade;
		private String buttonText;
		private float x;
		private float y;
		private boolean selected;
		private boolean unlocked;
		private boolean available;

		/**
		 * Creates an upgrade button.
		 * 
		 * @param upgrade
		 *            What upgrade it should be linked to.
		 * @param buttonText
		 *            Text on the button.
		 * @param x
		 *            Button's X coordinate.
		 * @param y
		 *            Button's Y coordinate.
		 * @param scale
		 *            How much the button should be scaled.
		 * @throws SlickException
		 */
		public UpgradeButton(int upgrade, String buttonText, int x, int y,
				float scale) throws SlickException {
			
			this.upgrade = upgrade;
			
			this.buttonText = buttonText;
			
			this.x = x * scale;
			this.y = y * scale;
			
			selected = false;
			unlocked = false;
			available = true;
		}
		
		public void toggleSelected() {
			selected = !selected;
		}
		
		public void toggleUnlocked() {
			unlocked = !unlocked;
		}
		
//		public void toggleAvailable() {
//			available = !available;
//		}
		
		public boolean isSelected() {
			return selected;
		}
		
		public boolean isUnlocked() {
			return unlocked;
		}
		
		public boolean isAvailable() {
			return available;
		}
		
		public int getUpgrade() {
			return upgrade;
		}
		
		public float getX() {
			return x;
		}
		
		public float getY() {
			return y;
		}
		
		public String getText() {
			return buttonText;
		}
	}
}
