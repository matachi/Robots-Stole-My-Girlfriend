package rsmg.model;

import java.awt.Point;

import rsmg.util.Vector2d;


public class Level {
	
	private Character character;
	/**
	 * Keeps track of which level the user have reached
	 */
	private int levelReached = 1;

	/**
	 * The grid layout of the level
	 */
	private TileGrid tGrid;
	/**
	 * Constructor of Level
	 */
	public Level() {
		tGrid = new TileGrid(levelReached); // Should check which level the user
											// have selected(not reached)
		spawnChar();
		//TODO spawn enemies and such
	}
	/**
	 * method for spawning the character
	 * spawns the character at spawn location, if no spawn location is found
	 * the character is spawned at 0,0
	 */
	private void spawnChar(){
		try{
			Point spawnPoint= tGrid.getSpawnPoint();
			character = new Character(spawnPoint.getX(), spawnPoint.getY());
		}catch(Exception a){ //TODO change to specific exception 
			character = new Character(0,0);
		}
	}
	
	/**
	 * method which handles necessary updates to the levels state
	 * @param delta, time since last update
	 */
	public void update(int delta){
		if (isAirbourne(character)){
			character.applyGravity(delta);
		}
	}
	private boolean isAirbourne(InteractiveObject obj){
		//TODO algorithm for checking if a tile is underneath an object
		return true;
	}

	/**
	 * Pause the game by stopping tick() in Controller.
	 * Pause should be from GUI to Controller. Not in Model:Level
	 */
	public void pause() {
		// controll.pause();
	}

	/**
	 * Are called when a level is completed. Increase level the user have
	 * completed and navigate to LevelMenu
	 */
	public void completedLevel() {
		levelReached++;
		// Change state to LevelMenu where Level will be reloaded again
		// controll.goToLevelMenu();
	}

	/**
	 * public int getLevelReached() { return levelReached; }
	 * 
	 * public void setLevelReached(int level) { levelReached = level; }
	 */
}
