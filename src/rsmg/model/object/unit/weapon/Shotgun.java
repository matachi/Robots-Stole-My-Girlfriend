package rsmg.model.object.unit.weapon;

import java.util.Collection;
import java.util.Random;

import rsmg.io.CharacterProgress;
import rsmg.model.ObjectName;
import rsmg.model.object.bullet.BasicBullet;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.variables.Constants;
import rsmg.util.Vector2d;
/**
 * Class representing a 'shotgun' type weapon
 * @author Johan Gronvall
 *
 */
public class Shotgun implements Weapon {
	
	private Collection<Bullet> bulletList;
	private boolean shot;
	private int bulletWidth = 2;
	private int bulletHeight = 2;
	private int bulletDamage = 3;
	private int bulletSpeed = 600;
	private int offsetX;
	private int offsetY = 5;
	private static int amountOfBulletsPerShot = 9;
	private static int shotgunKnockback = 50;
	private final int spread;
	
	public Shotgun(Collection<Bullet> bulletList) {
		this.bulletList = bulletList;
		shot = false;
		if(CharacterProgress.isIncShotgunSpreadUnlocked())
			spread = Constants.SHOTGUN_SPREAD_UPG;
		else
			spread = Constants.SHOTGUN_SPREAD;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot(double x, double y, boolean isFacingRight) {

		int xBulletSpeed;
		if (isFacingRight){
			xBulletSpeed = bulletSpeed;
			offsetX = 25;
		}else{
			xBulletSpeed = -bulletSpeed;
			offsetX = -5;
		}
		
		Random randomGen = new Random();
		
		for(int i = 0; i < amountOfBulletsPerShot; i++){
			Vector2d bulletVelocity = new Vector2d();
			bulletVelocity.setX(xBulletSpeed);
			bulletVelocity.setY((randomGen.nextDouble()*2-1)*spread);
			bulletList.add(new BasicBullet(x+offsetX, y+offsetY, bulletWidth, bulletHeight, ObjectName.SHOTGUN_BULLET, bulletDamage, bulletVelocity));
		}
		
		shot = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getCooldown() {
		if(CharacterProgress.isRapidFireUnlocked()) {
			return 550;
		}
		return 600;
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
		int knockback = shotgunKnockback;
		if(isFacingRight) {
			knockback*=-1;
		}
		return new Vector2d(knockback, 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return ObjectName.SHOTGUN;
	}
}
