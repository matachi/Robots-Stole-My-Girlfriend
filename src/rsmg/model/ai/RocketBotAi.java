package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
import rsmg.model.object.unit.PCharacter;

/**
 * An Ai used for controlling certain flying enemies. The Ai will make the enemy
 * try to fly towards the PCharacter.
 * 
 * @author Johan Grönvall
 * 
 */
public class RocketBotAi implements Ai {
	
	private Enemy enemy;
	private PCharacter character;
	private static final int TRAVEL_SPEED = 50;
	private static final int AGGRO_RANGE = 200;
	
	/**
	 * Create a RocketBot AI.
	 * 
	 * @param enemy Reference to the enemy it should control.
	 * @param character Reference to the player character.
	 */
	public RocketBotAi(Enemy enemy, PCharacter character) {
		this.enemy = enemy;
		this.character = character;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(double delta) {

		double playerX = character.getX();
		double playerY = character.getY();
		
		/**
		 * Makes the enemy chase the character.
		 */
		if (AggroRange.charInAggroRange(playerX, playerY, enemy, AGGRO_RANGE)) {
			double angle = Math.atan((enemy.getY()-playerY)/(enemy.getX()-playerX));
			int modifier = 1;
			if (enemy.getX() > playerX) {
				modifier = -1;
			}
			enemy.setVelocityX((Math.cos(angle) * TRAVEL_SPEED) * modifier);
			enemy.setVelocityY((Math.sin(angle) * TRAVEL_SPEED) * modifier);
		} else {
			enemy.setVelocityX(0);
			enemy.setVelocityY(0);
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
