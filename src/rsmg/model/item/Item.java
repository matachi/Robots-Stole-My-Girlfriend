package rsmg.model.item;

import rsmg.model.InteractiveObject;

public abstract class Item extends InteractiveObject {
	/**
	 * 
	 * @param x horizontal coordinate for where the Item location
	 * @param y vertical coordinate for where the Item location
	 * @param width width of the item
	 * @param height height of the item
	 */
	public Item(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public abstract String getType();
	
	@Override
	public void collide(InteractiveObject obj) {
	}
}
