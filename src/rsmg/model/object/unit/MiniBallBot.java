package rsmg.model.object.unit;

import rsmg.model.ObjectName;
/**
 * Class representing a small flying enemy
 * @author zapray
 *
 */
public class MiniBallBot extends Enemy{

	public MiniBallBot(double x, double y) {
		super(x, y, 20, 20, 10, ObjectName.MINIBALLBOT);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTouchDamage() {
		return 10;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFlyingUnit() {
		return true;
	}

}
