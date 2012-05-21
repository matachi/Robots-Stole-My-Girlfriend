package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
import rsmg.model.object.unit.PCharacter;
import rsmg.model.object.unit.TankBot;

/**
 * An Ai used for TankBots. The Ai will make the tankbot shoot at regular
 * intervals and always face towards the PCharacter if he is in 'aggrorange'.
 * 
 * @author Johan Grönvall
 * 
 */
public class TankBotAi implements Ai {
	
	private TankBot enemy;
	private PCharacter character;
	private final static int AGGRO_RANGE = 200;
	private double cooldown;
	
	/**
	 * Whether the enemy should be attacking or not.
	 */
	private boolean aggresive = false;
	
	/**
	 * Creates a TankBotAi with default values.
	 * 
	 * @param enemy Reference to the enemy it should control.
	 * @param character Reference to the player character.
	 */
	public TankBotAi(TankBot enemy, PCharacter character) {
		this.enemy = enemy;
		this.character = character;
	}

	/**
	 * Update the state and actions for the TankBot.
	 */
	@Override
	public void update(double delta) {

		double playerX = character.getX();
		
		double xDiff = playerX - enemy.getX();
		
		if (Math.abs(xDiff) < AGGRO_RANGE) {
			enemy.setFacing(xDiff > 0);
			aggresive = true;
		} else {
			idle();
			aggresive = false;
		}
		
		if (aggresive && shouldAttack(delta)) {
			enemy.shoot();
		}
	}
	
	/**
	 * Telling the TankBot to be still.
	 */
	private void idle() {
		enemy.setVelocityX(0);
	}
	
	/**
	 * Tell when the TankBot should attack.
	 * 
	 * @return True if it should attack, otherwise false.
	 */
	private boolean shouldAttack(double delta) {
		cooldown += delta;
		// Attack every 0.8 seconds.
		if (cooldown > 0.8) {
			cooldown = 0;
			return true;
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enemy getEnemy() {
		return enemy;
	}
}
