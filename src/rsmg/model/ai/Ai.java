package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;

/**
 * The interface for the artificial Intelligence classes
 * 
 * @author Johan Gr�nvall
 * 
 */
public interface Ai {
	
	/**
	 * Update method for the Ai which is called every loop updating this Ai's
	 * state and performing its actions
	 * 
	 * @param delta Seconds since last update.
	 */
	public void update(double delta);
	
	/**
	 * Return the Enemy that implements this interface
	 * @return This Enemy
	 */
	public Enemy getEnemy();
}
