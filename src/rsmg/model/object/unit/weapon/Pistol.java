package rsmg.model.object.unit.weapon;

import java.util.Collection;

import rsmg.io.CharacterProgress;
import rsmg.model.ObjectName;
import rsmg.model.object.bullet.BasicBullet;
import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;

public class Pistol implements Weapon {
	private Collection<Bullet> bulletList;
	private boolean shot;

	private static int bulletWidth = 5;
	private static int bulletHeight = 3;
	private static int bulletDamage = 5;
	private static int bulletSpeed = 500;
	private int offsetX;
	private int offsetY;
	
	public Pistol(Collection<Bullet> bulletList) {
		this.bulletList = bulletList;
		this.shot = false;
	}
	
	@Override
	public void shoot(double x, double y, boolean isFacingRight) {

		Vector2d bulletVelocity = new Vector2d();
		
		if (isFacingRight){
			bulletVelocity.setX(bulletSpeed);
			offsetX = 25;
			offsetY = 5;
		}else{
			bulletVelocity.setX(-bulletSpeed);
			offsetX = -5;
			offsetY = 5;
		}
		
		bulletList.add(new BasicBullet(x+offsetX, y+offsetY, bulletWidth, bulletHeight, ObjectName.PISTOL_BULLET, bulletDamage, bulletVelocity));
		
		shot = true;
	}

	@Override
	public long getCooldown() {
		if(CharacterProgress.isRapidFireUnlocked()) {
			return 250;
		}
		return 300;
	}
	
	@Override
	public boolean shot() {
		if (shot) {
			shot = !shot;
			return true;
		}
		return false;
	}
	
	@Override
	public Vector2d getKnockback(boolean isFacingRight) {
		return new Vector2d(0,0);
	}
	
	@Override
	public String getName() {
		return ObjectName.PISTOL;
	}
}
