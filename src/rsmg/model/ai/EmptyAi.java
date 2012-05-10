package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
/**
 * An Ai that doesn't do anything
 * @author Johan Grönvall
 *
 */
public class EmptyAi implements Ai{
	private Enemy enemy;
	
	/**
	 * Create a Empty AI.
	 * @param enemy Reference to the enemy it should control.
	 */
	public EmptyAi(Enemy enemy){
		this.enemy = enemy;
	}
	
	/**
	 * Update the state and actions for the EmptyAi Enemies
	 */
	@Override
	public void update(double delta, double playerX, double playerY) {
		//nothing
	}

	/**
	 * Returns the Enemy using this AI
	 */
	@Override
	public Enemy getEnemy() {
		return enemy;
	}
}
