package rsmg.model.object.unit;

import java.util.List;

import rsmg.model.ObjectName;
import rsmg.model.object.bullet.BasicBullet;
import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;

/**
 * Class representing a stationary non-flying enemy
 * @author Johan Gronvall
 */
public class Tankbot extends Enemy implements AttackingEnemy{
	
	private static int HEALTH = 20;
	private static int XBULLETSPEED = 200;
	private List<Bullet> bulletList;
	
	/**
	 * Creates a 'Tankbot' type enemy
	 * @param x the horiztonal coordinate for the position of this TankBot
	 * @param y the vertical coordinate for the position of this Tankbot
	 * @param bulletList the list where this Tankbot will place his bullets
	 */
	public Tankbot(double x, double y, List<Bullet> bulletList) {
		super(x, y, 26, 13, HEALTH, ObjectName.TANKBOT);
		this.bulletList = bulletList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTouchDamage(){
		return 10;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFlyingUnit(){
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot() {
		int bulletSpeed;
		
		if (isFacingRight()){
			bulletSpeed = XBULLETSPEED;
		} else {
			bulletSpeed = -XBULLETSPEED;
		}
		
		bulletList.add(new BasicBullet(getX(), getY(), 8, 2, ObjectName.PISTOL_BULLET, 25, new Vector2d(bulletSpeed, 0)));

	}
}
