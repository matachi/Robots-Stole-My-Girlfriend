package rsmg.model.object.bullet;

import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.variables.Constants;
import rsmg.util.Vector2d;
/**
 * Class representing an explosion
 * @author Johan Gronvall
 * @author Daniel Jonsson
 *
 */
class SmallExplosion extends Bullet implements Explosion, Projectile {
	private double age;
	
	public SmallExplosion(double x, double y) {
		super(x, y, Constants.EXPLOSION_AOE, Constants.EXPLOSION_AOE, ObjectName.EXPLOSION, Constants.EXPLOSIONDMG, new Vector2d(0,0));
		age = 0;
	}
	
	/**
	 * Create an explosion at the appropriate location.
	 * @param detonator object which creates the explosion
	 */
	public SmallExplosion(InteractiveObject detonator){
		super(detonator.getX()-Constants.EXPLOSION_AOE/2+detonator.getWidth()/2, detonator.getY()-Constants.EXPLOSION_AOE/2+detonator.getHeight()/2,
				Constants.EXPLOSION_AOE, Constants.EXPLOSION_AOE, ObjectName.EXPLOSION, Constants.EXPLOSIONDMG, new Vector2d(0,0));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getAge() {
		return age;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(double delta) {
		age += delta;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExplosion() {
		return true;
	}
}
