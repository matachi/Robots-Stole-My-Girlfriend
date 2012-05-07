package rsmg.model.ai;

import rsmg.model.object.unit.BossBotHead;
import rsmg.model.object.unit.Enemy;
import rsmg.util.Vector2d;

public class BossBotAi implements Ai{
	private BossBotHead enemy;
	double cooldown;
	private double angle = 0;
	private static double angleIncr = Math.PI;
	private static int bulletSpeed = 200;
	private Vector2d bulletVector = new Vector2d();
	
	public BossBotAi(BossBotHead enemy) {
		this.enemy = enemy;
	}

	@Override
	public void update(double delta, double playerX, double playerY) {
		if (characterIsBehindBoss(playerX)){
			angle = Math.PI/2.7;
			bulletVector = new Vector2d();
		} else {
			if (shouldAttack(delta)) {
				double tempAngle = Math.tan((playerY - enemy.getY())/(enemy.getX() - playerX));
					if (tempAngle > angle) {
						angle+=angleIncr*delta;
					} else {
						angle-=angleIncr*delta;
					}
				bulletVector.setX(-bulletSpeed*Math.cos(angle));
				bulletVector.setY(bulletSpeed*Math.sin(angle));
				enemy.shoot(bulletVector, angle);
			}
		}
	}
	
	private boolean characterIsBehindBoss(double playerX) {
		return playerX > enemy.getX();
	}
	
	private boolean shouldAttack(double delta) {
		cooldown += delta;
		// Every 0.2 second it gets the chance to shoot.
		if (cooldown > 0.15) {
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
