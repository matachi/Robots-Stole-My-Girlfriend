package rsmg.model;

import java.util.ArrayList;

import rsmg.util.Vector2d;

public class RocketLauncher implements IWeapon{
	private LivingObject wielder;
	private ArrayList<Bullet> bulletList;
	private boolean shot;
	
	public RocketLauncher(LivingObject wielder, ArrayList<Bullet> bulletList) {
		this.wielder = wielder;
		this.bulletList = bulletList;
		shot = false;
	}
	@Override
	public void shoot() {
		int bulletWidth = 5;
		int bulletHeight = 3;
		int bulletDamage = 10;
		int bulletSpeed = 400;
		Vector2d bulletVelocity = new Vector2d();
		Vector2d knockback = new Vector2d(10, -3);
		int offsetX;
		int offsetY;

		if (wielder.isFacingRight()){
			bulletVelocity.setX(bulletSpeed);
			offsetX = 25;
			offsetY = 5;
		}else{
			bulletVelocity.setX(-bulletSpeed);
			offsetX = -5;
			offsetY = 5;
		}
		
		bulletList.add(new Bullet(wielder.getX()+offsetX, wielder.getY()+offsetY, bulletWidth, bulletHeight, 2, bulletVelocity, knockback, bulletDamage));
		
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
}
