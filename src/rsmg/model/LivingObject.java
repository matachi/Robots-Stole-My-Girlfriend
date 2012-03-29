package rsmg.model;


public abstract class LivingObject extends InteractiveObject{
	public LivingObject(double x, double y, double width, double height, int health) {
		super(x, y, width, height);
		this.health=health;
	}

	int health;
	
	public int getHealth() {
		return health;
	}
	
	public void damage(int dmg) {
		health = health-dmg;
	}
	
}
