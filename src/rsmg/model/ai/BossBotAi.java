package rsmg.model.ai;

import rsmg.model.object.unit.BossBotHead;
import rsmg.model.object.unit.Enemy;
import rsmg.util.Vector2d;

public class BossBotAi implements Ai{
	private BossBotHead enemy;
	/**
	 * The cooldown variable used for the bosses primary attack
	 */
	double cooldown;
	
	/**
	 * The cooldown variable used to make the lasers sway every now and then.
	 */
	double SwayCooldown;
	
	/**
	 * The cooldown variable used for the bosses secondary attack
	 */
	double cooldown2;
	
	private double angle = Math.PI/2.7;
	private static final double angleIncr = Math.PI/14;
	private static final int bulletSpeed = 200;
	private static final int bulletSpeed2 = 230;
	private Vector2d bulletVector = new Vector2d();
	private double offset = 0.05;
	
	/**
	 * Create a BossBot AI.
	 * @param enemy Reference to the enemy it should control.
	 */
	public BossBotAi(BossBotHead enemy) {
		this.enemy = enemy;
	}

	/**
	 * Update the state and actions for the BossBot
	 */
	@Override
	public void update(double delta, double playerX, double playerY) {
		
		double tempAngle = Math.atan((playerY - enemy.getY())/(enemy.getX() - playerX));
		if (tempAngle > angle+offset) {
			angle+= angleIncr*delta;
		} else {
			angle-= angleIncr*delta;
		}
		
		if (SwayCooldown > 1.5){
			offset*=-1;
			SwayCooldown = 0;
		}
		
		if (characterIsBehindBoss(playerX)){
			angle = Math.PI/2.7;
			bulletVector = new Vector2d();
		} else if (shouldAttack()) {
			//do the primary attack
			bulletVector.setX(-bulletSpeed*Math.cos(angle));
			bulletVector.setY(bulletSpeed*Math.sin(angle));
			enemy.shoot(bulletVector, angle);
		}
		
		if(shouldAttack2()) {
			//do the second attack
			double angle2 = Math.random()*Math.PI-2;
			Vector2d bulletVector2 = new Vector2d();
			bulletVector2.setX(-bulletSpeed2*Math.cos(angle2));
			bulletVector2.setY(bulletSpeed2*Math.sin(angle2));
			enemy.shoot2(bulletVector2, angle2);
		}
		SwayCooldown += delta;
		cooldown += delta;
		cooldown2 += delta;
	}
	
	/**
	 * Describing if the boss should use his other attack
	 * @param delta
	 * @return true if he should use his other attack, otherwise false
	 */
	private boolean shouldAttack2() {
		if (cooldown2 > 0.2) {
			cooldown2 = 0;
			return true;
		}else
			return false;
	}

	/**
	 * Returns if the player is behind the boss
	 * @param playerX The x coordinate of the player
	 * @return true if the player is behind the boss, otherwise false
	 */
	private boolean characterIsBehindBoss(double playerX) {
		return playerX > enemy.getX();
	}
	
	/**
	 * Tell when the BossBot should attack
	 * @return true if it should attack, otherwise false
	 */
	private boolean shouldAttack() {
		// Every 0.19 second it gets the chance to shoot.
		if (cooldown > 0.12) {
			cooldown = 0;
			return true;
		}
		return false;
	}

	/**
	 * Returns the BossBot using this AI
	 */
	@Override
	public Enemy getEnemy() {
		return this.enemy;
	}
}
