package rsmg.model.object.bullet;

import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.variables.Variables;
import rsmg.util.Vector2d;

public class BigExplosion extends Bullet {
	private double age;
	
	public BigExplosion(double x, double y) {
		super(x, y, Variables.EXPLOSION_AOE_UPG, Variables.EXPLOSION_AOE_UPG, ObjectName.BIG_EXPLOSION, Variables.EXPLOSIONDMG, new Vector2d(0,0));
		age = 0;
	}
	/**
	 * create an explosion at the approriate location
	 * @param detonator object which creates the explosion
	 */
	public BigExplosion(InteractiveObject detonator){
		super(detonator.getX()-Variables.EXPLOSION_AOE_UPG/2+detonator.getWidth()/2, detonator.getY()-Variables.EXPLOSION_AOE_UPG/2+detonator.getHeight()/2,
				Variables.EXPLOSION_AOE_UPG, Variables.EXPLOSION_AOE_UPG, ObjectName.EXPLOSION, Variables.EXPLOSIONDMG, new Vector2d(0,0));
	}

	public double getAge() {
		return age;
	}

	@Override
	public void update(double delta) {
		age += delta;
	}
}
