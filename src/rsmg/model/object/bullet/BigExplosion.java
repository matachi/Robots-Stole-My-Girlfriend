package rsmg.model.object.bullet;

import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.variables.Variables;
import rsmg.util.Vector2d;
/**
 * Class representing an explosion
 * @author Johan Gronvall
 * @author Daniel Jonsson
 *
 */
class BigExplosion extends Bullet implements Explosion, Projectile{
	private double age;
	/**
	 * creates an explosion at the assigned position
	 * @param x Horizontal coordinate for where the explosion spawns.
	 * @param y Vertical coordinate for where the explosion spawns.
	 */
	public BigExplosion(double x, double y) {
		super(x, y, Variables.EXPLOSION_AOE_UPG, Variables.EXPLOSION_AOE_UPG, ObjectName.BIG_EXPLOSION, Variables.EXPLOSIONDMG, new Vector2d(0,0));
		age = 0;
	}
	
	/**
	 * Create an explosion at the assigned objects location.
	 * @param detonator object which creates the explosion
	 */
	public BigExplosion(InteractiveObject detonator){
		super(detonator.getX()-Variables.EXPLOSION_AOE_UPG/2+detonator.getWidth()/2, detonator.getY()-Variables.EXPLOSION_AOE_UPG/2+detonator.getHeight()/2,
				Variables.EXPLOSION_AOE_UPG, Variables.EXPLOSION_AOE_UPG, ObjectName.EXPLOSION, Variables.EXPLOSIONDMG, new Vector2d(0,0));
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
