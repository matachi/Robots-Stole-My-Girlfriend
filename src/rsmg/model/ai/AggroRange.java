package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
/**
 * help class with a function that is used in several AIs.
 * @author Johan Grönvall
 *
 */
public class AggroRange {
	
	public static boolean charInAggroRange(double playerX, double playerY, Enemy enemy, double aggroRange) {
		double xDifference = playerX - enemy.getX();
		double yDifference = playerY - enemy.getY();
		
		return (((xDifference > 0 && xDifference < aggroRange) || (xDifference < 0 && xDifference*(-1) < aggroRange)) &&
				((yDifference > 0 && yDifference < aggroRange) || (yDifference < 0 && yDifference*(-1) < aggroRange)));
	}
}
