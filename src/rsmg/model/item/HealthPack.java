package rsmg.model.item;

import rsmg.model.ObjectName;

public class HealthPack extends Item {
	
	/**
	 * @param x horizontal coordinate for where the Item location
	 * @param y vertical coordinate for where the Item location
	 * @param width width of the item
	 * @param height height of the item
	 */
	public HealthPack(double x, double y) {
		// Make the item start at the bottom(32-15)
		super(x, y+32-15, 15, 15, ObjectName.HEALTH_PACK);
	}
}
