package rsmg.model.object.bullet;

import rsmg.model.object.InteractiveObject;
import rsmg.util.Vector2d;

/**
 * This class holds information about a bullet.
 * 
 * @author Johan Grönvall
 * @author Daniel Jonsson
 *
 */
public abstract class Bullet extends InteractiveObject implements Projectile {
	
	private int dmg;
	
	/**
	 * Create a bullet.
	 * 
	 * @param x Horizontal coordinate for where the bullet spawns.
	 * @param y Vertical coordinate for where the bullet spawns.
	 * @param width Width of the bullet.
	 * @param height Height of the bullet.
	 * @param name Name of the bullet.
	 * @param dmg The bullet's damage.
	 * @param velocity The bullet's velocity.
	 */
	public Bullet(double x, double y, double width, double height,
			String name, int dmg, Vector2d velocity) {
		
		super(x, y, width, height, name);
		this.dmg = dmg;
		this.setVelocity(velocity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDamage() {
		return dmg;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void collide(InteractiveObject obj) {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(double delta) {
		//nothing at this point
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract boolean isExplosion();

}
