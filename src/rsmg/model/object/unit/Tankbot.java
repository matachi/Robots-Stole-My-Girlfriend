package rsmg.model.object.unit;

import java.util.List;

import rsmg.model.ObjectName;
import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;

public class Tankbot extends Enemy implements AttackingEnemy{
	
	private static int HEALTH = 20;
	private static int XBULLETSPEED = 200;
	private List<Bullet> bulletList;
	
	public Tankbot(double x, double y, List<Bullet> bulletList) {
		super(x, y, 26, 13, HEALTH, ObjectName.TANKBOT);
		this.bulletList = bulletList;
	}
	
	public int getTouchDamage(){
		return 10;
	}
	
	public boolean isFlyingUnit(){
		return false;
	}
	
	@Override
	public void shoot() {
		int bulletSpeed;
		
		if (isFacingRight()){
			bulletSpeed = XBULLETSPEED;
		} else {
			bulletSpeed = -XBULLETSPEED;
		}
		
		bulletList.add(new Bullet(getX(), getY(), 8, 2, ObjectName.PISTOL_BULLET, 25, new Vector2d(bulletSpeed, 0)));

	}
}
