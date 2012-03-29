package rsmg.model;

import rsmg.util.Vector2d;

public class Bullet extends InteractiveObject {
	int bulletType;
	int dmg;
	Vector2d knockback;
	/**
	 * 
	 * @param x horizontal coordinate for where the bullet spawns
	 * @param y vertical coordinate for where the bullet spawns
	 * @param width width of the bullet
	 * @param height height of the bullet
	 * @param bulletType integer value representing what kind of bullet this is (used for graphics)
	 */
	public Bullet(double x, double y, double width, double height, int bulletType, Vector2d velocity, Vector2d knockback, int dmg) {
		super(x, y, width, height);
		this.setVelocity(velocity);
		this.bulletType=bulletType;
		this.knockback=knockback;
		
	}

	@Override
	public void collide(InteractiveObject obj) {
		((LivingObject)obj).damage(dmg);
		obj.addVelocity(knockback);
	}
}
