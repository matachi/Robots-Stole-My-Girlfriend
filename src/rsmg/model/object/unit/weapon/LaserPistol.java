package rsmg.model.object.unit.weapon;

import java.util.Collection;

import rsmg.io.CharacterProgress;
import rsmg.model.object.bullet.BasicBullet;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.variables.ObjectName;
import rsmg.util.Vector2d;
/**
 * Class representing a weapon with bullets that
 * do not disappear when they touch and enemy
 * @author Johan Gronvall
 *
 */
public class LaserPistol implements Weapon { 
	
	private boolean shot;
	private static int bulletWidth = 9;
	private static int bulletHeight = 3;
	private static int bulletDamage = 2;
	private static int bulletSpeed = 600;
	private int offsetX;
	private int offsetY;
	private Collection<Bullet> bulletList;
	
	public LaserPistol(Collection<Bullet> bulletList) {
		this.bulletList = bulletList;
		this.shot = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot(double x, double y, boolean isFacingRight) {
	
		Vector2d bulletVelocity = new Vector2d();
		
		if (isFacingRight) {
			bulletVelocity.setX(bulletSpeed);
			offsetX = 25;
			offsetY = 5;
		} else { //isFacingLeft
			bulletVelocity.setX(-bulletSpeed);
			offsetX = -5;
			offsetY = 5;
		}
		
		bulletList.add(new BasicBullet(x+offsetX, y+offsetY, bulletWidth, bulletHeight, ObjectName.LASER_BULLET, bulletDamage, bulletVelocity));
		
		shot = true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getCooldown() {
		if(CharacterProgress.isRapidFireUnlocked()) {
			return 450;
		}
		return 500;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean shot() {
		if (shot) {
			shot = !shot;
			return true;
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector2d getKnockback(boolean isFacingRight) {
		return new Vector2d(0,0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectName getName() {
		return ObjectName.LASER_PISTOL;
	}
}
