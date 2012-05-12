package rsmg.model.ai;

import rsmg.model.object.unit.BucketBot;
import rsmg.model.object.unit.Enemy;
/**
 * An Ai used for controlling a 'BucketBot' type enemy
 * The Ai will make the bucketBot face towards the PCharacter
 * and fire at regular intervals
 * @author Johan Gronvall
 *
 */
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public Enemy getEnemy() {
		return enemy;
	}
}
