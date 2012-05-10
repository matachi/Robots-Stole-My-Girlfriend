package rsmg.model.ai;

import rsmg.model.object.unit.BucketBot;
import rsmg.model.object.unit.Enemy;

public class BucketBotAi implements Ai{
	
	private BucketBot enemy;
	private double cooldown;
	
	/**
	 * Create a BucketBot AI.
	 * @param enemy Reference to the enemy it should control.
	 */
	public BucketBotAi(BucketBot enemy) {
		this.enemy = enemy;
	}

	/**
	 * Update the state and actions for the BucketBot
	 */
	@Override
	public void update(double delta, double playerX, double pLayerY) {
		if (playerX < enemy.getX()){
			enemy.setFacing(false);
		} else { // if playerX >= enemy.getX()
			enemy.setFacing(true);
		}
		
		
		cooldown += delta;
		
		// Every 0.8 second it gets the chance to shoot.
		if (cooldown > 0.8) {
			cooldown = 0;
			// Only 30 % probability that it actually will shoot.
			enemy.shoot();
		}
	}

	/**
	 * Returns the BucketBot using this AI
	 */
	@Override
	public Enemy getEnemy() {
		return enemy;
	}
}
