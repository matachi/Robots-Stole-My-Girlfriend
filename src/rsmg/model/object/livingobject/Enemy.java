package rsmg.model.object.livingobject;

import rsmg.model.Bullet;
import rsmg.model.object.InteractiveObject;

public abstract class Enemy extends LivingObject{

	public Enemy(double x, double y, double width, double height, int health, String name) {
		super(x, y, width, height, health, name);
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		if (obj instanceof Bullet){
			this.damage(((Bullet)obj).getDamage());
		}
	}
	public abstract int getTouchDamage();
}
