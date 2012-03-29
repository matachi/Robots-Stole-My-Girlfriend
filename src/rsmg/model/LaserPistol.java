package rsmg.model;

public class LaserPistol implements IWeapon{
	private InteractiveObject wielder;
	
	public LaserPistol(InteractiveObject wielder){
		this.wielder = wielder;
	}
	
	@Override
	public void shoot() {
		int bulletWidth = 5;
		int bulletHeight = 5;
		int bulletDamage = 10;
		//Vector2d bulletSpeed = new Vector2d()
		//Vector2d knockback = new Vector2d()
		//new Bullet(wielder.getX(), wielder.getY(), )
	}
}
