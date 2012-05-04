package rsmg.model.object.item;

import rsmg.model.ObjectName;


public class Pistol extends Item {
	
	/**
	 * @param x horizontal coordinate for where the Item location
	 * @param y vertical coordinate for where the Item location
	 * @param width width of the item
	 * @param height height of the item
	 */
	public Pistol(double x, double y, double width, double height) {
		super(x+((32-width)/2), y+32-height, width, height, ObjectName.LASER_PISTOL);
	}
}
