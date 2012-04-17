package rsmg.model;

import java.awt.Point;
import java.util.ArrayList;

import rsmg.io.IO;

/**
 * Class representing a level. This Class is in charge of storing and updating
 * information about a level
 * 
 * @author Johan Grönvall, Johan Rignäs, Daniel Jonsson
 * 
 */
public class Level {

	/**
	 * Reference to the character that the user controls.
	 */
	private Character character;
	
	/**
	 * List where bullets from guns are stored.
	 */
	private ArrayList<Bullet> aBullets;
	
	/**
	 * List where references to all living enemies in the level are stored.
	 */
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	/**
	 * The grid layout of the level. (I.e. the environment.)
	 */
	private TileGrid tGrid;

	/**
	 * Constructor of Level
	 */
	public Level() {

		IO io = new IO();
		aBullets = new ArrayList<Bullet>();  
		tGrid = new TileGrid(io.getLevel(1));
		spawnChar();
	}

	/**
	 * method for spawning the character spawns the character at spawn location,
	 * if no spawn location is found the character is spawned at 0,0
	 */
	private void spawnChar() {
		try {
			Point spawnPoint = tGrid.getSpawnPoint();
			character = new Character(spawnPoint.getX(), spawnPoint.getY(), aBullets);
		} catch (Exception NullPointerException) {
			character = new Character(0, 0, aBullets);
		}
	}

	/**
	 * This method handles all updating that should occur between every game frame.
	 * @param delta Time since last update in seconds.
	 */
	public void update(double delta) {
		for(Bullet bullet : aBullets){
			bullet.move(delta);
		
		}
		updateCharacter(delta);

		
		if(enemies.isEmpty()){
			//temporary enemy
			Enemy tempEnemy = new Tankbot(90, 40); 
			enemies.add(tempEnemy);
		}

 		updateEnemies(delta);
		
	}
	private void updateCharacter(double delta) {
		
		// Update whether the character is in the air or standing on the ground.
		character.setAirborne(isAirbourne(character));
		// Apply gravity to the character so he will fall down if he is in the air.
		character.applyGravity(delta);
		
		// Move the character.
		character.move(delta);
		
		// update what direction the character is facing towards
		character.updateFacing();
		
		// Check so the character isn't inside a solid tile, and if so, move
		// him outside it.
		applyNormalForce(character);
		character.setVelocityX(0);
		character.updateDashing(delta);
	}
	
	//perform a "isDeadCheck" and handle collision detection
	private void updateEnemies(double delta) {
		for (int i = 0; i < enemies.size(); i++) {

			Enemy enemy = enemies.get(i);
			
			if (enemy.isDead()) {
				enemies.remove(i);
				continue;
			}
			
			//see if enemy has collided with the character and act approrietly
			if (enemy.hasCollidedWith(character)) {
				character.collide(enemy);
				enemy.collide(character);
			}
			
			//see if enemy has collided with any bullets and act approrietly
			for (Bullet bullet : aBullets) {
				
				if(enemy.hasCollidedWith(bullet)) {
					enemy.collide(bullet);
					bullet.collide(enemy);
				}
			}
			enemy.applyGravity(delta);
			enemy.move(delta);
			applyNormalForce(enemy);
		}
	}

	/**
	 * Check if the object is flying (i.e. not standing on a solid tile).
	 * @param obj The InteractiveObject.
	 * @return true If there is no solid tile underneath specified object.
	 */
	private boolean isAirbourne(InteractiveObject obj) {
		double y = obj.getY() + obj.getHeight() + 0.00001;
		return !(tileIntersect(obj.getX(), y) || tileIntersect(
				obj.getX() + obj.getWidth() - 0.00001, y));
	}

//	/**
//	 * Check so the character isn't outside the level's boundaries.
//	 */
//	private boolean isOutSideMap(InteractiveObject obj){
//		return obj.getX() < 0 || obj.getY() < 0 ||
//				obj.getX() > tGrid.getWidth()*Constants.TILESIZE
//				||  obj.getY() > tGrid.getHeight()*Constants.TILESIZE;
//	}

	/**
	 * Check if the object collides with any solid tiles. And if that is the
	 * case, move the object back outside the tile.
	 * @param obj An InteractiveObject
	 */
	private void applyNormalForce(InteractiveObject obj) {
		/**
		 * Check if the object intersects with the grid.
		 */
		if (tGrid.intersectsWith(obj)) {
			// Check if the the object came from the left
			if (cameFromLeft(obj)) {
				// Move the object back to the left
				moveLeft(obj);
				// Set the object's x velocity to zero
				obj.setVelocityX(0);
			}

			// Check if the object came from the right
			if (cameFromRight(obj)) {
				moveRight(obj);
				obj.setVelocityX(0);
			}

			/**
			 * Check if the object is still colliding with any solid tiles. Then
			 * he must have collided with the tile from above or below.
			 */
			if (tGrid.intersectsWith(obj)) {
				if (cameFromAbove(obj)) {
					moveUp(obj);
					obj.setVelocityY(0);
				}
				if (cameFromBelow(obj)) {
					moveDown(obj);
					obj.setVelocityY(0);
				}
			}
		}
	}

	private boolean cameFromAbove(InteractiveObject obj) {
		return obj.getPY() + obj.getHeight() - 0.00001 <= tGrid
				.getTilePosFromRealPos(obj.getY() + obj.getHeight())
				* Constants.TILESIZE;
	}

	private boolean cameFromBelow(InteractiveObject obj) {
		return obj.getPY() >= (tGrid.getTilePosFromRealPos(obj.getY()) + 1)
				* Constants.TILESIZE;
	}

	private boolean cameFromLeft(InteractiveObject obj) {
		return obj.getPX() + obj.getWidth() - 0.00001 <= tGrid
				.getTilePosFromRealPos(obj.getX() + obj.getWidth())
				* Constants.TILESIZE;
	}

	private boolean cameFromRight(InteractiveObject obj) {
		return obj.getPX() >= (tGrid.getTilePosFromRealPos(obj.getX()) + 1)
				* Constants.TILESIZE;
	}

	private void moveUp(InteractiveObject obj) {
		double i = tGrid.bottomSideIntersection(obj);
		obj.setY(Math.floor(obj.getY() - i));
	}

	private void moveDown(InteractiveObject obj) {
		double i = tGrid.topSideIntersection(obj);
		obj.setY(Math.floor(obj.getY() + i));
	}

	private void moveLeft(InteractiveObject obj) {
		double i = tGrid.rightSideIntersection(obj);
		obj.setX(Math.round(obj.getX() - i));
	}

	private void moveRight(InteractiveObject obj) {
		double i = tGrid.leftSideIntersection(obj);
		obj.setX(Math.round(obj.getX() + i));
	}

	/**
	 * 
	 * @param obj
	 * @param tile
	 * @return true coordinates are inside one of the
	 */
	private boolean tileIntersect(double x, double y) {
		return tGrid.getTile(x, y).isSolid();
	}

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
	 * Returns the list of bullets.
	 * @return The list of bullets.
	 */
	public ArrayList<Bullet> getABulletList(){
		return aBullets;
	}
	/**
	 * Returns the list of alive enemies.
	 * @return The list of alive enemies.
	 */
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
}