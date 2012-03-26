package rsmg.model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Class representing a level.
 * This Class is in charge of storing and updating information about a level
 * @author Johan Grönvall, Johan Rignäs
 *
 */
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
		
//		IO io = new IO();
//		tGrid = new TileGrid(io.getLevel(levelReached));
		
		AirTile a = new AirTile();
		GroundTile g = new GroundTile();
		SpawnTile s = new SpawnTile();
		Tile[][] grid = {
			{g, g, g, g, g, g, g, g, g, g, g, g, g},
			{g, a, a, a, a, a, a, a, a, a, a, a, g},
			{g, g, a, s, a, a, a, a, a, a, g, g, g},
			{g, g, a, a, a, g, g, a, a, g, g, a, g},
			{g, a, a, g, a, a, g, a, g, g, g, g, g},
			{g, g, g, g, g, g, g, g, g, g, g, g, g}};
		tGrid = new TileGrid(grid);
		
		spawnChar();
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
	 * @param delta Time since last update in seconds.
	 */
	public void update(double delta) {
		outsideMapCheck();
		
		//Apply gravity to the character.
		
		character.applyGravity(delta);
		
		// Move the character.
		character.move(delta);
		
		// Check so the character isn't inside a solid tile.
		//applyNormalForce2(character);
//		applyNormalForce(character, delta);
		applyNormalForce3(character);

		if (!isAirbourne(character))
			character.getVelocity().setX(0);
	}
	/**
	 * 
	 * @param obj
	 * @return true if there is no tile underneath specified object
	 */
	private boolean isAirbourne(InteractiveObject obj){
		double y = obj.getY()+obj.getHeight()+1;
		return !(tileIntersect(obj.getX(), y) || tileIntersect(obj.getX()+obj.getWidth(), y));
	}
	private void outsideMapCheck(){
		if (character.getX() < 0){
			character.setX(0);
		}
		if (character.getY() < 0){
			character.setY(0);
		}
	}
	private void applyNormalForce4(InteractiveObject obj){
		if (tGrid.intersectsWith(obj)) {
		//determine what tiles the char is colliding with
		ArrayList<Double> tileOffset= new ArrayList<Double>();
			tileOffset.add(tGrid.bottomSideIntersection(obj));
			tileOffset.add(tGrid.topSideIntersection(obj));
			tileOffset.add(tGrid.leftSideIntersection(obj));
			tileOffset.add(tGrid.rightSideIntersection(obj));
			
			double offset = Math.min(tileOffset.get(0), Math.min(tileOffset.get(1), Math.min(tileOffset.get(2), tileOffset.get(3))));
			int index = tileOffset.indexOf(offset);
			System.out.println(offset);
			System.out.println(index);
			switch(index){
			case 0:
				obj.setY(obj.getY()+Math.abs(offset));
				break;
			case 1:
				obj.setY(obj.getY()-Math.abs(offset));
				break;
			case 2:
				obj.setX(obj.getX()-Math.abs(offset));
				break;
			case 3:
				obj.setX(obj.getX()+Math.abs(offset));
			}
			
		}
	}
	
	private void applyNormalForce3(InteractiveObject obj) {
		/**
		 * Check if the object intersects with the grid.
		 */
		if (tGrid.intersectsWith(obj)) {
			if (cameFromAbove(obj)) {
				moveUp(obj);
				obj.getVelocity().setY(0);
			}
			if (cameFromBelow(obj)) {
				moveDown(obj);
				obj.getVelocity().setY(0);
			}
			if (tGrid.intersectsWith(obj)){
				if (cameFromLeft(obj)) {
					moveLeft(obj);
					obj.getVelocity().setX(0);
				}
				if (cameFromRight(obj)) {
					moveRight(obj);
					obj.getVelocity().setX(0);
				}
			}
			System.out.println(character.getVelocity().getY());
		}
	}
	
	private boolean cameFromAbove(InteractiveObject obj) {
		return obj.getPY()+obj.getHeight() <= tGrid.getTilePosFromRealPos(obj.getY()+obj.getHeight())*Constants.TILESIZE; 
	}
	
	private void moveUp(InteractiveObject obj) {
		double i = tGrid.bottomSideIntersection(obj);
		obj.setY(Math.round(obj.getY() - i));
	}
	
	private boolean cameFromBelow(InteractiveObject obj) {
		return obj.getPY() >= (tGrid.getTilePosFromRealPos(obj.getY())+1)*Constants.TILESIZE;
	}
	
	private void moveDown(InteractiveObject obj) {
		double i = tGrid.topSideIntersection(obj);
		obj.setY(Math.round(obj.getY() + i));
	}
	
	private boolean cameFromLeft(InteractiveObject obj) {
		return obj.getPX()+obj.getWidth() <= tGrid.getTilePosFromRealPos(obj.getX()+obj.getWidth())*Constants.TILESIZE; 
	}
	
	private void moveLeft(InteractiveObject obj) {
		double i = tGrid.rightSideIntersection(obj);
		obj.setX(Math.round(obj.getX() - i));
	}
	
	private boolean cameFromRight(InteractiveObject obj) {
		return obj.getPX() >= (tGrid.getTilePosFromRealPos(obj.getX())+1)*Constants.TILESIZE; 
	}
	
	private void moveRight(InteractiveObject obj) {
		double i = tGrid.leftSideIntersection(obj);
		obj.setX(Math.round(obj.getX() + i));
	}
	
	private void applyNormalForce2(InteractiveObject obj) {
		
		/**
		 * Check if the object intersects with any tiles.
		 */
		if (tGrid.intersectsWith(obj)) {

			double i = tGrid.bottomSideIntersection(obj);
			// If the object fell into a tile under himself
			if (i > 0) {
				obj.setY((int)(obj.getY() - i));
			}
			
			i = tGrid.topSideIntersection(obj);
			// If the object jumped into a tile over himself
			if (i > 0) {
				obj.setY(obj.getY() + i);
			}
			
			i = tGrid.leftSideIntersection(obj);
			// If the object walked into a tile to his left
			if (i > 0) {
				obj.setX(obj.getX() + i);
			}
			
			i = tGrid.rightSideIntersection(obj);
			// If the object walked into a tile to his right
			if (i > 0) {
				obj.setX(obj.getX() - i);
			}
		}
	}
	/**
	 * Method representing the normal force
	 * The method moves an object that inside a tile outside of said tile and slows it down
	 * TODO requires tweaks
	 */
	private void applyNormalForce(InteractiveObject obj, double delta){
		double right = obj.getX()+obj.getWidth();
		double bottom = obj.getY()+obj.getHeight();
		double left = obj.getX();
		normalForce(right, bottom, obj, delta);
		normalForce(left, bottom, obj, delta);

	}
	private void normalForce(double x, double y, InteractiveObject obj, double delta){
		int tileSize = Constants.TILESIZE;
		if (tileIntersect(x, y-1)){
			System.out.println("Solid!");
			//approximates where the previous coordinate was
			double oldX = obj.getX()-obj.getVelocity().getX()*delta;
			double oldY = obj.getY()-obj.getVelocity().getY()*delta;
			
			int oldXTileIndex = (int)(oldX/tileSize);
			int yTileIndex = (int)(y/tileSize);
			
			//if object descended or ascended into tile
			if(oldXTileIndex != (int)(x/tileSize) && !tGrid.get((oldXTileIndex),(int)(yTileIndex)).isSolid()){
				if(x > oldX){
					//moveLeft()
					obj.setX((int)(x/tileSize)*tileSize-obj.getWidth());
				}else{ //if (x < oldX)
					//moveRight()
					obj.setX((int)((x/tileSize))*tileSize);
				}
				obj.getVelocity().setX(0);
			}
			
			//if object horizontally entered the tile
			if((int)(oldY/tileSize) != (int)(y/tileSize) && tGrid.get((oldXTileIndex),(int)(yTileIndex)).isSolid()){
				if(y > oldY){
					//moveUp()
					obj.setY((int)(y/tileSize)*tileSize-obj.getHeight());
				}else{ //if ( y < oldY)
					//moveDown()
					obj.setY((int)((y/tileSize))*tileSize);
				}
				obj.getVelocity().setY(0);
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param tile
	 * @return true coordinates are inside one of the 
	 */
	private boolean tileIntersect(double x, double y){
		int xTilePos = (int)(x/Constants.TILESIZE);
		int yTilePos = (int)(y/Constants.TILESIZE);
		return tGrid.get(xTilePos, yTilePos).isSolid();
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
	
	/**
	 * Returns the tile grid.
	 * @return The tile grid.
	 */
	public TileGrid getTileGrid() {
		return tGrid;
	}
	
	/**
	 * Returns the character.
	 * @return The character.
	 */
	public Character getCharacter() {
		return character;
	}
	
	/**
	 * Move the character to the left.
	 */
	public void moveLeft() {
		character.moveLeft();
	}
	
	/**
	 * Move the character to the right.
	 */
	public void moveRight() {
		character.moveRight();
	}
	
	/**
	 * Jump with the character.
	 */
	public void jump() {
		if(!isAirbourne(character)){
			character.jump();
		}
	}
	
	/**
	 * Attack with the character.
	 */
	public void attack() {
		//character.attack();
	}
}
