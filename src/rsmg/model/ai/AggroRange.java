package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
/**
 * help class with a function that is used in several AIs.
 * @author Johan Grönvall
 *
 */
public class AggroRange {
	
	/**
	 * Calculate if the PCharacter are in aggro range to an Enemy
	 * @param playerX x coordinate of the PCharacter
	 * @param playerY y coordinate of the PCharacter
	 * @param enemy The enemy to check aggro for 
	 * @param aggroRange The range before the enemy should get aggro to player
	 * @return true if in aggro range, otherwise false
	 */
	public static boolean charInAggroRange(double playerX, double playerY, Enemy enemy, double aggroRange) {
		double xDifference = playerX - enemy.getX();
		double yDifference = playerY - enemy.getY();
		
		return (Math.abs(xDifference) < aggroRange) && (Math.abs(yDifference) < aggroRange);
	}
}
