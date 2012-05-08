package rsmg.model.ai;

import rsmg.model.object.unit.BossBotHead;
import rsmg.model.object.unit.Enemy;
import rsmg.util.Vector2d;

public class BossBotAi implements Ai{
	private BossBotHead enemy;
	/**
	 * the cooldown variable used for the bosses primary attack
	 */
	double cooldown;
	
	/**
	 * the cooldown variable used to make the lasers sway every now and then.
	 */
	double SwayCooldown;
	
	/**
	 * the cooldown variable used for the bosses secondary attack
	 */
	double cooldown2;
	
	private double angle = Math.PI/2.7;
	private static final double angleIncr = Math.PI/14;
	private static final int bulletSpeed = 200;
	private static final int bulletSpeed2 = 230;
	private Vector2d bulletVector = new Vector2d();
	private double offset = 0.05;
	
	public BossBotAi(BossBotHead enemy) {
		this.enemy = enemy;
	}

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
	 * describing if the boss should use his other attack
	 * @param delta
	 * @return
	 */
	private boolean shouldAttack2() {
		if (cooldown2 > 0.2) {
			cooldown2 = 0;
			return true;
		}else
			return false;
	}

	private boolean characterIsBehindBoss(double playerX) {
		return playerX > enemy.getX();
	}
	
	private boolean shouldAttack() {
		// Every 0.19 second it gets the chance to shoot.
		if (cooldown > 0.12) {
			cooldown = 0;
			return true;
		}
		return false;
	}

	@Override
	public Enemy getEnemy() {
		return this.enemy;
	}

}
