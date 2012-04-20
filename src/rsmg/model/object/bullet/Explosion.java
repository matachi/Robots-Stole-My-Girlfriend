package rsmg.model.object.bullet;

import rsmg.model.Constants;
import rsmg.model.ObjectName;
import rsmg.util.Vector2d;

public class Explosion extends Bullet {
	private double age;
	
	public Explosion(double x, double y) {
		super(x, y, 30, 30, ObjectName.EXPLOSION, Constants.EXPLOSIONDMG, new Vector2d(0,0));
		age = 0;
	}
	public double getAge(){
		return age;
	}
	
	@Override
	public void update(double delta){
		age+=delta;
	}

}
