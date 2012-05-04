package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;

public class RocketBotAi implements Ai{
	private Enemy enemy;
	private static int TRAVELSPEED = 50;
	private static int AGGRORANGE = 200;
	
	public RocketBotAi(Enemy enemy) {
		this.enemy = enemy;
	}
	
	@Override
	public void update(double delta, double playerX, double playerY) {
		/**
		 * makes the enemy chase the character
		 */
		if(AggroRange.charInAggroRange(playerX, playerY,  enemy, AGGRORANGE)){
			double angle = Math.atan((enemy.getY()-playerY)/(enemy.getX()-playerX));
			int modifier = 1;
			if(enemy.getX() > playerX){
				modifier = -1;
			}
			enemy.setVelocityX((Math.cos(angle)*TRAVELSPEED)*modifier);
			enemy.setVelocityY((Math.sin(angle)*TRAVELSPEED)*modifier);
		} else {
			enemy.setVelocityX(0);
			enemy.setVelocityY(0);
		}
	}

	@Override
	public Enemy getEnemy() {
		return enemy;
	}
	
}
