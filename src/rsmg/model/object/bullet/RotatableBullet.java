package rsmg.model.object.bullet;

import rsmg.util.Vector2d;
/**
 * bullet class which stores an angle
 * making it possible for the graphics to draw it in other ways
 * @author Johan Gronvall
 *
 */
public class RotatableBullet extends Bullet {
	private float angle;
	/**
	 * creats a RotatableBullet
	 * @param x Horizontal coordinate for where the bullet spawns.
	 * @param y Vertical coordinate for where the bullet spawns.
	 * @param width Width of the bullet.
	 * @param height Height of the bullet.
	 * @param name Name of the bullet.
	 * @param dmg The bullet's damage.
	 * @param velocity The bullet's velocity.
	 * @param angle the angle this bullet is rotated to
	 */
	public RotatableBullet(double x, double y, double width, double height,
			String name, int dmg, Vector2d velocity, float angle) {
		super(x, y, width, height, name, dmg, velocity);
		this.angle = angle;
		// TODO Auto-generated constructor stub
	}
	/**
	 * returns what angle the RotatableBullet currently is at
	 * @return the RotatableBullets angle
	 */
	public float getRotation() {
		return angle;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExplosion() {
		return false;
	}

}
