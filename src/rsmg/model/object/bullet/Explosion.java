package rsmg.model.object.bullet;

import rsmg.model.Constants;
import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.util.Vector2d;

public class Explosion extends Bullet {
	private double age;
	
	public Explosion(double x, double y) {
		super(x, y, Constants.EXPLOSIONAOE, Constants.EXPLOSIONAOE, ObjectName.EXPLOSION, Constants.EXPLOSIONDMG, new Vector2d(0,0));
		age = 0;
	}
	/**
	 * create an explosion at the approriate location
	 * @param detonator object which creates the explosion
	 */
	public Explosion(InteractiveObject detonator){
		super(detonator.getX()-Constants.EXPLOSIONAOE/2+detonator.getWidth()/2, detonator.getY()-Constants.EXPLOSIONAOE/2+detonator.getHeight()/2,
				Constants.EXPLOSIONAOE, Constants.EXPLOSIONAOE, ObjectName.EXPLOSION, Constants.EXPLOSIONDMG, new Vector2d(0,0));
	}

	public double getAge(){
		return age;
	}

	@Override
	public void update(double delta){
		age+=delta;
	}
}
