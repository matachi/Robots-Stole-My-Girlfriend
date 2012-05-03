package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
import rsmg.util.Vector2d;

/**
 * Class in charge of control an idling Enemy
 * @author Johan Gr�nvall
 * 
 */
public class PatrollingAi implements Ai {
	private Enemy enemy;
	private Vector2d velocityR;
	private Vector2d veloctiyL;
	
	/**
	 * how far the enemy will idle around
	 */
	private double walkingDistance;
	
	/**
	 * the position the enemy started out at
	 */
	private double originalXPos;
	
	/**
	 * creates an IdleAi
	 * @param enemy the enemy this ai is controlling
	 * @param idleSpeed the speed the enemy should be patrolling at
	 * @param walkingDistance how far the enemy will idle around
	 */
	public PatrollingAi(Enemy enemy, Vector2d idleSpeed, double walkingDistance) {
		this.enemy = enemy;
		this.velocityR = idleSpeed;
		this.veloctiyL = idleSpeed.getNegatedCopy();
		this.walkingDistance = walkingDistance;
		originalXPos = enemy.getX();
		enemy.setVelocity(velocityR);
	}
	
	/**
	 * creates an IdleAi with default values
	 * @param enemy
	 */
	public PatrollingAi(Enemy enemy) {
		this.enemy = enemy;
		this.velocityR = new Vector2d(100, 0);
		this.veloctiyL = velocityR.getNegatedCopy();
		this.walkingDistance = 50;
		originalXPos = enemy.getX();
		enemy.setVelocity(velocityR);
	}

	@Override
	public void update(double delta) {
		//TODO make this stuff work
		
		if (enemy.getX() > originalXPos + walkingDistance || isStuckR()) {
			//turn left
			enemy.setVelocity(veloctiyL);
		}else if(enemy.getX() < originalXPos - walkingDistance || isStuckL()) {
			//turn right
			enemy.setVelocity(velocityR);
		}
	}
	
	private boolean isStuckR() {
		return (enemy.isFacingRight() && enemy.getVelocityX() == 0);
	}
	
	private boolean isStuckL() {
		return ((!enemy.isFacingRight()) && enemy.getVelocityX() == 0);
	}

	@Override
	public Enemy getEnemy() {
		return enemy;
	}
}