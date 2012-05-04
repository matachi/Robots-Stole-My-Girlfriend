package rsmg.model.ai;

import org.lwjgl.Sys;

import rsmg.model.object.unit.BallBot;
import rsmg.model.object.unit.Enemy;
import rsmg.util.Vector2d;

public class BallBotAi implements Ai{
	private BallBot enemy;
	private static int TRAVELSPEED = 20;
	private static double ANGLES_PER_SECOND = 1;
	private static int AGGRORANGE = 300;
	private double angle;
	
	public BallBotAi(BallBot enemy) {
		this.enemy = enemy;
		enemy.setVelocity(new Vector2d(TRAVELSPEED, TRAVELSPEED));
		angle = (Math.PI/4);
	}
	
	@Override
	public void update(double delta, double playerX, double playerY) {
		angle += ANGLES_PER_SECOND*delta;
		
		enemy.setVelocityX(Math.cos(angle)*TRAVELSPEED);
		enemy.setVelocityY(Math.sin(angle)*TRAVELSPEED);
		
		if (shouldShoot() && AggroRange.charInAggroRange(playerX, playerY, enemy, AGGRORANGE)) {
			enemy.spawnEnemy();
		}
	}

	private boolean shouldShoot() {
		return Sys.getTime() % 2000 == 0;
	}

	@Override
	public Enemy getEnemy() {
		return enemy;
	}
	
}
