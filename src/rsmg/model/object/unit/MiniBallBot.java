package rsmg.model.object.unit;

import rsmg.model.ObjectName;

public class MiniBallBot extends Enemy{

	public MiniBallBot(double x, double y) {
		super(x, y, 20, 20, 10, ObjectName.MINIBALLBOT);
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
