package rsmg.model.ai;

import rsmg.model.object.unit.BucketBot;
import rsmg.model.object.unit.Enemy;
import rsmg.model.object.unit.PCharacter;

/**
 * An Ai used for controlling a 'BucketBot' type enemy. The Ai will make the
 * bucketBot face towards the PCharacter and fire at regular intervals.
 * 
 * @author Johan Grönvall
 * 
 */
public class BucketBotAi implements Ai{
	
	private BucketBot enemy;
	private PCharacter character;
	private double cooldown;
	
	/**
	 * Create a BucketBot AI.
	 * 
	 * @param enemy Reference to the enemy it should control.
	 * @param character Reference to the player character.
	 */
	public BucketBotAi(BucketBot enemy, PCharacter character) {
		this.enemy = enemy;
		this.character = character;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(double delta) {
		
		// Set facing to the right if player's X pos. is larger than the enemy's.
		enemy.setFacing(character.getX() > enemy.getX());
		
		cooldown += delta;
		
		// Make it shoot every 0.8 seconds
		if (cooldown > 0.8) {
			cooldown = 0;
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
