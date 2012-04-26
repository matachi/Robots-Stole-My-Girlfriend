package rsmg.model.object.unit;

import java.util.Collection;

import rsmg.model.ObjectName;
import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;

public class Pistol implements IWeapon{
	private LivingObject wielder;
	private Collection<Bullet> bulletList;
	private boolean shot;

	private static int bulletWidth = 5;
	private static int bulletHeight = 3;
	private static int bulletDamage = 10;
	private static int bulletSpeed = 500;
	private Vector2d bulletVelocity = new Vector2d();
	private int offsetX;
	private int offsetY;
	
	public Pistol(LivingObject wielder, Collection<Bullet> bulletList) {
		this.wielder = wielder;
		this.bulletList = bulletList;
		this.shot = false;
	}
	
	@Override
	public void shoot() {


		if (wielder.isFacingRight()){
			bulletVelocity.setX(bulletSpeed);
			offsetX = 25;
			offsetY = 5;
		}else{
			bulletVelocity.setX(-bulletSpeed);
			offsetX = -5;
			offsetY = 5;
		}
		
		bulletList.add(new Bullet(wielder.getX()+offsetX, wielder.getY()+offsetY, bulletWidth, bulletHeight, ObjectName.LASER_BULLET, bulletDamage, bulletVelocity));
		
		shot = true;
	}

	@Override
	public long getCooldown() {
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
	public String getName() {
		return "pistol";
	}
}
