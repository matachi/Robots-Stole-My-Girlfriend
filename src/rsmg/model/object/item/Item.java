package rsmg.model.object.item;

import rsmg.model.object.InteractiveObject;

public abstract class Item extends InteractiveObject {
	/**
	 * 
	 * @param x horizontal coordinate for where the Item location
	 * @param y vertical coordinate for where the Item location
	 * @param width width of the item
	 * @param height height of the item
	 * @param name name of the item
	 */
	public Item(double x, double y, double width, double height, String name) {
		super(x, y, width, height, name);
	}
	
	@Override
	public void collide(InteractiveObject obj) {
	}
}
