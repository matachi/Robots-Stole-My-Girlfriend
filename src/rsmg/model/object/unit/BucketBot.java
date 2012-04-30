package rsmg.model.object.unit;

import rsmg.model.ObjectName;

public class BucketBot extends Enemy{

	public BucketBot(double x, double y) {
		super(x, y, 22, 30, 20, ObjectName.BUCKETBOT);
	}

	@Override
	public int getTouchDamage() {
		return 20;
	}

	@Override
	public boolean isFlyingUnit() {
		return false;
	}

}
