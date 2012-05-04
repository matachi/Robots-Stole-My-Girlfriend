package rsmg.model.ai;

import org.lwjgl.Sys;

import rsmg.model.object.unit.Enemy;
import rsmg.model.object.unit.Tankbot;
/**
 * Class in charge of control an idling Enemy
 * @author Johan Grönvall
 * 
 */
public class TankBotAi implements Ai {
	private Tankbot enemy;
	private static int AGGRORANGE = 200;
	
	/**
	 * boolean whether the enemy should be attacking or not
	 */
	private boolean aggresive = false;
	
	/**
	 * creates a TankBotAi with default values
	 * @param enemy
	 */
	public TankBotAi(Tankbot enemy) {
		this.enemy = enemy;
	}

	@Override
	public void update(double delta, double playerX, double playerY) {
		
		double xDiff = playerX - enemy.getX();
		
		if(xDiff > 0 && xDiff < AGGRORANGE) {
			aggresive = true;
			enemy.setFacing(true);
			
		} else if(xDiff < 0 && xDiff*(-1) < AGGRORANGE) {
			enemy.setFacing(false);
			aggresive = true;
		}else {
			idle();
			aggresive = false;
		}
		
		if(shouldAttack()) {
			enemy.shoot();
		}
	}
	
	private void idle() {
		enemy.setVelocityX(0);
	}
	
	private boolean shouldAttack() {
		return Sys.getTime() % 1200 == 0 && aggresive;
	}
	

	@Override
	public Enemy getEnemy() {
		return enemy;
	}
}
