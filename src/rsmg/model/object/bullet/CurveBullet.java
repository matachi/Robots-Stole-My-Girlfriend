package rsmg.model.object.bullet;

import rsmg.model.variables.ObjectName;
import rsmg.util.Vector2d;

/**
 * A bullet that is affected by gravity
 * @author Johan Gronvall
 *
 */
public class CurveBullet extends Bullet implements Projectile {
	
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
	public CurveBullet(double x, double y, double width, double height,
			ObjectName name, int dmg, Vector2d velocity) {
		super(x, y, width, height, name, dmg, velocity);
		
		this.setVelocity(velocity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(double delta) {
		this.applyGravity(delta);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExplosion() {
		return false;
	}
	
}
