package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
/**
 * The interface for the artificial Intelligence classes
 * @author Johan Gronvall
 *
 */
public interface Ai {
	/**
	 * update method for the Ai which is called every loop
	 * updating this Ai's state and performing its actions
	 * @param delta
	 * @param PlayerX the PCharacters x position
	 * @param PlayerY the PCharacters y position
	 */
	public void update(double delta, double playerX, double playerY);
	
	/**
	 * Return the Enemy that implements this interface
	 * @return This Enemy
	 */
	public Enemy getEnemy();
}
