package rsmg.model.object.bullet;

import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.variables.Variables;
import rsmg.util.Vector2d;

public class LaserFire extends Bullet implements Explosion{
	private double age;
	
	public LaserFire(double x, double y) {
		super(x, y, 20, 28, ObjectName.LASERFIRE, 70, new Vector2d(0,0));
	}

	@Override
	public double getAge() {
		return age;
	}
	
	/**
	 * Create an explosion at the appropriate location.
	 * @param detonator object which creates the explosion
	 */
	public LaserFire(InteractiveObject detonator){
		super(detonator.getX()-10, detonator.getY()-14, 20, 28, ObjectName.LASERFIRE, 70, new Vector2d(0,0));
	}

	@Override
	public void update(double delta) {
		age += delta*6;
	}

}
