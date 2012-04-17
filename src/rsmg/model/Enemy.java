package rsmg.model;

public abstract class Enemy extends LivingObject{

	public Enemy(double x, double y, double width, double height, int health) {
		super(x, y, width, height, health);
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		if (obj instanceof Bullet){
			this.damage(((Bullet)obj).getDamage());
		}
	}
	public abstract int getTouchDamage();
}
