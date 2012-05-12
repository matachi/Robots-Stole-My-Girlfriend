package rsmg.model.object.bullet;

import rsmg.util.Vector2d;

/**
 * bullet that moves in a curve
 * @author zapray
 *
 */
public class CurveBullet extends Bullet implements Projectile{

	public CurveBullet(double x, double y, double width, double height,
			String name, int dmg, Vector2d velocity) {
		super(x, y, width, height, name, dmg, velocity);
		
		this.setVelocity(velocity);
	}

	@Override
	public void update(double delta) {
		this.applyGravity(delta);
	}

	@Override
	public boolean isExplosion() {
		return false;
	}
	
}
