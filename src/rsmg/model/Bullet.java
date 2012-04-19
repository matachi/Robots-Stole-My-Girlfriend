package rsmg.model;

import rsmg.util.Vector2d;

public class Bullet extends InteractiveObject {
	int bulletType;
	int dmg;
	/**
	 * 
	 * @param x horizontal coordinate for where the bullet spawns
	 * @param y vertical coordinate for where the bullet spawns
	 * @param width width of the bullet
	 * @param height height of the bullet
	 * @param bulletType integer value representing what kind of bullet this is (used for graphics)
	 * @param name Name of the bullet.
	 */
	public Bullet(double x, double y, double width, double height, int bulletType, Vector2d velocity, int dmg, String name) {
		super(x, y, width, height, name);
		this.setVelocity(velocity);
		this.bulletType=bulletType;
		this.dmg=dmg;
	}

	@Override
	public void collide(InteractiveObject obj) {
		//nothing really
	}
	public int getDamage(){
		return dmg;
	}
}
