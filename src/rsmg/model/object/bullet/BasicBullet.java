package rsmg.model.object.bullet;

import rsmg.util.Vector2d;

/**
 * This class holds information about a bullet.
 * 
 * @author Johan Grönvall
 * @author Daniel Jonsson
 *
 */
public class BasicBullet extends Bullet implements Projectile {
	
	public BasicBullet(double x, double y, double width, double height,
			String name, int dmg, Vector2d velocity) {
		super(x, y, width, height, name, dmg, velocity);
		// TODO Auto-generated constructor stub
	}
	
	public void update(double delta) {
		//nothing at this point
	}

	@Override
	public boolean isExplosion() {
		return false;
	}
}

