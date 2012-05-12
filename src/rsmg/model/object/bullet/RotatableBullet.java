package rsmg.model.object.bullet;

import rsmg.util.Vector2d;
/**
 * bullet class which stores an angle
 * making it possible for the graphics to draw it in other ways
 * @author zapray
 *
 */
public class RotatableBullet extends Bullet {
	private float angle;
	
	public RotatableBullet(double x, double y, double width, double height,
			String name, int dmg, Vector2d velocity, float angle) {
		super(x, y, width, height, name, dmg, velocity);
		this.angle = angle;
		// TODO Auto-generated constructor stub
	}
	
	public float getRotation() {
		return angle;
	}

	@Override
	public boolean isExplosion() {
		return false;
	}

}
