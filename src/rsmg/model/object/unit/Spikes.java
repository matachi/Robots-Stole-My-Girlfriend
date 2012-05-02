package rsmg.model.object.unit;

import rsmg.model.ObjectName;

public class Spikes extends Enemy{

	public Spikes(double x, double y) {
		super(x, y, 32, 4, 999, ObjectName.SPIKES);
	}

	@Override
	public int getTouchDamage() {
		return 30;
	}

	@Override
	public boolean isFlyingUnit() {
		return false;
	}
}
