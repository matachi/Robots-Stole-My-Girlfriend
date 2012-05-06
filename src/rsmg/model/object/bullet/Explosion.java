package rsmg.model.object.bullet;

import rsmg.io.CharacterProgress;
import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.variables.Variables;
import rsmg.util.Vector2d;

public class Explosion extends Bullet {
	private double age;
	
	public Explosion(double x, double y) {
		super(x, y, Variables.getExplosionAOE(), Variables.getExplosionAOE(), ObjectName.EXPLOSION, Variables.EXPLOSIONDMG, new Vector2d(0,0));
		age = 0;
	}
	/**
	 * create an explosion at the approriate location
	 * @param detonator object which creates the explosion
	 */
	public Explosion(InteractiveObject detonator){
		super(detonator.getX()-Variables.getExplosionAOE()/2+detonator.getWidth()/2, detonator.getY()-Variables.getExplosionAOE()/2+detonator.getHeight()/2,
				Variables.getExplosionAOE(), Variables.getExplosionAOE(), ObjectName.EXPLOSION, Variables.EXPLOSIONDMG, new Vector2d(0,0));
	}

	public double getAge(){
		return age;
	}

	@Override
	public void update(double delta){
		age+=delta;
		
	}
	
	@Override
	public int getDamage(){
		return dmg;
	}
	
	@Override
	public String getName() {
		if (CharacterProgress.isIncRPGAoEUnlocked()) {
			return ObjectName.BIG_EXPLOSION;
		}else {
			return super.getName();
		}
	}
}
