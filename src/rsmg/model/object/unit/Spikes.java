package rsmg.model.object.unit;

import rsmg.model.ObjectName;
/**
 * Class representing a stationary non-flying AI-less enemy
 * @author Johan Rignas
 *
 */
public class Spikes extends Enemy {
	
	/**
	 * Creates a "spikes" type enemy at the given location
	 */
	public Spikes(double x, double y) {
		super(x, y, 32, 4, 999, ObjectName.SPIKES);
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
		return false;
	}
}
