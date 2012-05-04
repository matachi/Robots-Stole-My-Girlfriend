package rsmg.model.object.unit;

import rsmg.model.ObjectName;

public class BallBot extends Enemy{
	
	/**
	 * @param x the horizontal coordinate of this BallBot
	 * @param y the vertical coordinate of this BallBot
	 */
	public BallBot(double x, double y) {
		super(x, y, 40, 40, 60, ObjectName.BALLBOT);
	}

	@Override
	public int getTouchDamage() {
		return 30;
	}

	@Override
	public boolean isFlyingUnit() {
		return true;
	}
}
