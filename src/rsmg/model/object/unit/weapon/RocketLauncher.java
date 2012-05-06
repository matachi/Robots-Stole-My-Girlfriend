package rsmg.model.object.unit.weapon;

import java.util.Collection;

import rsmg.model.ObjectName;
import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;
/**
 * Class used to create the "rocket" type bullet
 * @author zapray
 *
 */
public class RocketLauncher implements IWeapon{
	private Collection<Bullet> bulletList;
	private boolean shot;
	private static int bulletWidth = 14;
	private static int bulletHeight = 14;
	private static int bulletDamage = 3;
	private static int bulletSpeed = 400;

	public RocketLauncher(Collection<Bullet> bulletList) {
		this.bulletList = bulletList;
		shot = false;
	}
	
	@Override
	public void shoot(double x, double y, boolean isFacingRight) {

		Vector2d bulletVelocity = new Vector2d();
		int offsetX;
		int offsetY;
		
		
		if (isFacingRight){
			bulletVelocity.setX(bulletSpeed);
			offsetX = 25;
			offsetY = 0;
			bulletList.add(new Bullet(x+offsetX, y+offsetY, bulletWidth, bulletHeight, ObjectName.ROCKETR, bulletDamage, bulletVelocity));

		}else{
			bulletVelocity.setX(-bulletSpeed);
			offsetX = -5;
			offsetY = 0;
			bulletList.add(new Bullet(x+offsetX, y+offsetY, bulletWidth, bulletHeight, ObjectName.ROCKETL, bulletDamage, bulletVelocity));

		}
		
		
		shot = true;
	}

	@Override
	public long getCooldown(boolean rapidFire) {
		if(rapidFire) {
			return 700;
		}
		return 800;
	}
	
	@Override
	public boolean shot() {
		if (shot) {
			shot = !shot;
			return true;
		}
		return false;
	}
	
//	@Override
//	public long getReloadTime() {
//		
//		return 1000;
//	}
//	
	@Override
	public String getName() {
		return ObjectName.ROCKET_LAUNCHER;
	}
}
