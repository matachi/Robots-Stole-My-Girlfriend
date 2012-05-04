package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
/**
 * The interface for the Ai
 * @author zapray
 *
 */
public interface Ai {
	/**
	 * update method for the ai which is called every loop
	 * @param delta
	 * @param PlayerX the PCharacters x position
	 * @param PlayerY the PCharacters y position
	 */
	public void update(double delta, double playerX, double playerY);
	public Enemy getEnemy();
}
