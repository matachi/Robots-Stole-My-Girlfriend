package rsmg.model.ai;

import java.util.List;

import rsmg.model.object.unit.BallBot;
import rsmg.model.object.unit.Enemy;
import rsmg.model.object.unit.MiniBallBot;
import rsmg.model.object.unit.PCharacter;
import rsmg.util.Vector2d;


/**
 * Creates an Ai used for BallBots. This Ai will make the BallBot circle around
 * in the air. It will also occasionally spawn miniBallBots.
 * 
 * @author Johan Gr�nvall
 * 
 */
public class BallBotAi implements Ai {
	
	private BallBot enemy;
	private List<Ai> enemyAiList;
	private PCharacter character;
	private static int TRAVELSPEED = 20;
	private static double ANGLES_PER_SECOND = 1;
	private static int AGGRORANGE = 300;
	private double angle;
	private double cooldown;
	
	/**
	 * Create a BallBot AI.
	 * 
	 * @param enemy Reference to the enemy it should control.
	 * @param enemyAiList The list of Ai for the enemies that are harmful to the character.
	 * @param character Reference to the player character.
	 */
	public BallBotAi(BallBot enemy, List<Ai> enemyAiList, PCharacter character) {
		this.enemy = enemy;
		this.enemyAiList = enemyAiList;
		this.character = character;
		enemy.setVelocity(new Vector2d(TRAVELSPEED, TRAVELSPEED));
		angle = (Math.PI/4);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(double delta) {
		angle += ANGLES_PER_SECOND*delta;

		double playerX = character.getX();
		double playerY = character.getY();
		
		enemy.setVelocityX(Math.cos(angle)*TRAVELSPEED);
		enemy.setVelocityY(Math.sin(angle)*TRAVELSPEED);
		
		if (shouldShoot(delta) && AggroRange.charInAggroRange(playerX, playerY, enemy, AGGRORANGE)) {
			spawnEnemy();
		}
	}

	/**
	 * Tell when the BallBot should shoot.
	 * 
	 * @param delta
	 * @return true If it should shoot, otherwise false.
	 */
	private boolean shouldShoot(double delta) {
		cooldown += delta;
		// Shoot every 2 seconds.
		if (cooldown > 2) {
			cooldown = 0;
			return true;
		}
		return false;
	}
	
	/**
	 * Spawns a MiniBallBot.
	 */
	private void spawnEnemy() {
		enemyAiList.add(new RocketBotAi(new MiniBallBot(enemy.getX(), enemy.getY()), character));
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Enemy getEnemy() {
		return enemy;
	}
}
