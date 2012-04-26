package rsmg.model.object.unit;

import java.util.Collection;
import java.util.Random;

import rsmg.model.ObjectName;
import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;


	
public class Shotgun implements IWeapon{
	private LivingObject wielder;
	private Collection<Bullet> bulletList;
	private boolean shot;
	private int bulletWidth = 2;
	private int bulletHeight = 2;
	private int bulletDamage = 2;
	private int bulletSpeed = 600;
	private int offsetX;
	private int offsetY = 5;
	private static int amountOfBulletsPerShot = 6;
	private static int spread = 200;
	
	public Shotgun(LivingObject wielder, Collection<Bullet> bulletList) {
		this.wielder = wielder;
		this.bulletList = bulletList;
		shot = false;
	}
	
	@Override
	public void shoot() {

		int xBulletSpeed;
		if (wielder.isFacingRight()){
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
			bulletList.add(new Bullet(wielder.getX()+offsetX, wielder.getY()+offsetY, bulletWidth, bulletHeight, ObjectName.SHOTGUN_BULLET, bulletDamage, bulletVelocity));
		}
		
		shot = true;
	}

	@Override
	public long getCooldown() {
		return 500;
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
		return "shotgun";
	}
}
