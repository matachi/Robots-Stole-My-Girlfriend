package rsmg.model.object.unit.weapon;

import java.util.Collection;

import rsmg.io.CharacterProgress;
import rsmg.model.object.bullet.BasicBullet;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.variables.ObjectName;
import rsmg.util.Vector2d;
/**
 * Class representing a 'RocketLauncher'
 * the rocketLauncher creates bullets of the type "rocket"
 * @author Johan Gronvall
 *
 */
public class RocketLauncher implements Weapon {
	private Collection<Bullet> bulletList;
	private boolean shot;
	private static final int bulletWidth = 14;
	private static final int bulletHeight = 14;
	private static final int bulletDamage = 3;
	private static final int bulletSpeed = 400;
	private static final int rpgKnockback = 200;

	public RocketLauncher(Collection<Bullet> bulletList) {
		this.bulletList = bulletList;
		shot = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot(double x, double y, boolean isFacingRight) {

		Vector2d bulletVelocity = new Vector2d();
		int offsetX;
		int offsetY;
		
		
		if (isFacingRight){
			bulletVelocity.setX(bulletSpeed);
			offsetX = 25;
			offsetY = 0;
			bulletList.add(new BasicBullet(x+offsetX, y+offsetY, bulletWidth, bulletHeight, ObjectName.ROCKETR, bulletDamage, bulletVelocity));

		}else{
			bulletVelocity.setX(-bulletSpeed);
			offsetX = -5;
			offsetY = 0;
			bulletList.add(new BasicBullet(x+offsetX, y+offsetY, bulletWidth, bulletHeight, ObjectName.ROCKETL, bulletDamage, bulletVelocity));

		}
		
		
		shot = true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getCooldown() {
		if(CharacterProgress.isRapidFireUnlocked()) {
			return 1800;
		}
		return 2000;
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
		int knockback = rpgKnockback;
		if(isFacingRight) {
			knockback*=-1;
		}
		return new Vector2d(knockback, 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectName getName() {
		return ObjectName.ROCKET_LAUNCHER;
	}
}
