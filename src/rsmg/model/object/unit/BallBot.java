package rsmg.model.object.unit;

import rsmg.model.ObjectName;

public class BallBot extends Enemy{

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
