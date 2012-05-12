package rsmg.model.object.unit;

import rsmg.model.ObjectName;
/**
 * Class representing a weak flying enemy
 * @author Johan Gronvall
 *
 */
public class RocketBot extends Enemy{

	public RocketBot(double x, double y) {
		super(x, y, 19, 20, 20, ObjectName.ROCKETBOT);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTouchDamage() {
		return 20;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFlyingUnit() {
		return true;
	}
	
}
