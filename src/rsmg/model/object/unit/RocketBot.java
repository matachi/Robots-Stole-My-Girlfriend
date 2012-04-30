package rsmg.model.object.unit;

import rsmg.model.ObjectName;

public class RocketBot extends Enemy{

	public RocketBot(double x, double y) {
		super(x, y, 19, 20, 20, ObjectName.ROCKETBOT);
	}

	@Override
	public int getTouchDamage() {
		return 20;
	}

	@Override
	public boolean isFlyingUnit() {
		return true;
	}
	
}
