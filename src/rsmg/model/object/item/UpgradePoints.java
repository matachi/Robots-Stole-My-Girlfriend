package rsmg.model.object.item;

import rsmg.model.ObjectName;

public class UpgradePoints extends Item {
	
	/**
	 * @param x horizontal coordinate for where the Item location
	 * @param y vertical coordinate for where the Item location
	 * @param width width of the item
	 * @param height height of the item
	 */
	public UpgradePoints(double x, double y) {
		// TODO: Set size
		super(x+((32-15)/2), y+32-15, 10, 10, ObjectName.UPGRADE_POINT);
	}
}
