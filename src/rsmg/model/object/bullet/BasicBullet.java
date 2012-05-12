package rsmg.model.object.bullet;

import rsmg.util.Vector2d;

/**
 * Class representing a basic bullet that simply moves in a straight 
 * non-accelerating manner
 * 
 * @author Johan Gronvall
 * @author Daniel Jonsson
 *
 */
public class BasicBullet extends Bullet implements Projectile {
	
	/**
	 * Creates a basicBullet
	 * @param x Horizontal coordinate for where the bullet spawns.
	 * @param y Vertical coordinate for where the bullet spawns.
	 * @param width Width of the bullet.
	 * @param height Height of the bullet.
	 * @param name Name of the bullet.
	 * @param dmg The bullet's damage.
	 * @param velocity The bullet's velocity.
	 */
	public BasicBullet(double x, double y, double width, double height,
			String name, int dmg, Vector2d velocity) {
		super(x, y, width, height, name, dmg, velocity);
		// TODO Auto-generated constructor stub
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
	public boolean isExplosion() {
		return false;
	}
}

