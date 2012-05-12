package rsmg.model.object.unit;

import java.util.List;

import rsmg.model.ObjectName;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.bullet.CurveBullet;
import rsmg.util.Vector2d;
/**
 * Class representing a stationary non-flying enemy
 * @author Johan Gronvall
 *
 */
public class BucketBot extends Enemy implements AttackingEnemy{
	private List<Bullet> bulletList;
	private static int XBULLETSPEED = 50;
	private static int YBULLETSPEED = -100; 
	/**
	 * creates a 'BucketBot' type enemy
	 * @param x the horizontal coordinate for this BucketBots position
	 * @param y the vertical coordinate for this BucketsBots position
	 * @param bulletList
	 */
	public BucketBot(double x, double y, List<Bullet> bulletList) {
		super(x, y, 22, 30, 20, ObjectName.BUCKETBOT);
		this.bulletList = bulletList;

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTouchDamage() {
		return 20;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFlyingUnit() {
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
		bulletList.add(new CurveBullet(getX(), getY(), 10, 10, ObjectName.STONE, 20, new Vector2d(bulletSpeed, YBULLETSPEED)));
	}
}
