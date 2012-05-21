package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;

/**
 * An Ai that doesn't do anything.
 * 
 * @author Johan Grönvall
 * 
 */
public class EmptyAi implements Ai {
	
	private Enemy enemy;
	
	/**
	 * Create a Empty AI.
	 * 
	 * @param enemy Reference to the enemy it should control.
	 */
	public EmptyAi(Enemy enemy) {
		this.enemy = enemy;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(double delta) {
		//nothing
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enemy getEnemy() {
		return enemy;
	}
}
