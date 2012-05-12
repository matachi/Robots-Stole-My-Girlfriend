package rsmg.model.object.unit;

import rsmg.model.ObjectName;
/**
 * An flying enemy of the type "Ballbot".
 * @author zapray
 *
 */
public class BallBot extends Enemy{
	/**
	 * @param x the horizontal coordinate of this BallBot
	 * @param y the vertical coordinate of this BallBot
	 */
	public BallBot(double x, double y) {
		super(x, y, 40, 40, 60, ObjectName.BALLBOT);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTouchDamage() {
		return 30;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFlyingUnit() {
		return true;
	}
}
