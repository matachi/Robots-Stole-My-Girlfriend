package rsmg.model.object.bullet;

import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.util.Vector2d;

/**
 * A class representing an explosion created when a LaserBolt collides with anything 
 * @author Johan Gronvall
 *
 */
public class LaserFire extends Bullet implements Explosion{
	private double age;
	
	/**
	 * Creates LaserFire at the given position
	 * @param x Horizontal coordinate for where the LaserFire spawns.
	 * @param y Vertical coordinate for where the LaserFire spawns.
	 */
	public LaserFire(double x, double y) {
		super(x, y, 20, 28, ObjectName.LASERFIRE, 70, new Vector2d(0,0));
	}
	
	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * Ages 6 times faster than explosions so that it will disappear faster
	 */
	@Override
	public void update(double delta) {
		age += delta*6;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExplosion() {
		return true;
	}

}
