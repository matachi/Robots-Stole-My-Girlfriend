package rsmg.model.object.unit;

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
	private LivingObject wielder;
	private Collection<Bullet> bulletList;
	private boolean shot;
	private static int bulletWidth = 14;
	private static int bulletHeight = 14;
	private static int bulletDamage = 10;
	private static int bulletSpeed = 400;

	public RocketLauncher(LivingObject wielder, Collection<Bullet> bulletList) {
		this.wielder = wielder;
		this.bulletList = bulletList;
		shot = false;
	}
	
	@Override
	public void shoot() {

		Vector2d bulletVelocity = new Vector2d();
		int offsetX;
		int offsetY;
		
		if (wielder.isFacingRight()){
			bulletVelocity.setX(bulletSpeed);
			offsetX = 25;
			offsetY = 0;
		}else{
			bulletVelocity.setX(-bulletSpeed);
			offsetX = -5;
			offsetY = 0;
		}
		
		bulletList.add(new Bullet(wielder.getX()+offsetX, wielder.getY()+offsetY, bulletWidth, bulletHeight, ObjectName.ROCKET, bulletDamage, bulletVelocity));
		
		shot = true;
	}

	@Override
	public long getCooldown() {
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
		return "rocketLauncher";
	}
}
