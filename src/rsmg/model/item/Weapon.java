package rsmg.model.item;

public class Weapon extends Item {
	
	private boolean pickup = false;
	private String type;
	
	/**
	 * @param x horizontal coordinate for where the Item location
	 * @param y vertical coordinate for where the Item location
	 * @param width width of the item
	 * @param height height of the item
	 */
	public Weapon(double x, double y, double width, double height) {
		super(x, y, width, height);
		type = "weapon";
	}

	public void pickup(){
		pickup = true;
	}
	
	public boolean isPickedup(){
		return pickup;
	}
	
	public String getType(){
		return type;
	}
}
