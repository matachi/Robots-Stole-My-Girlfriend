package rsmg.model;

import java.util.ArrayList;

import rsmg.util.Vector2d;

public class LaserPistol implements IWeapon{
	private InteractiveObject wielder;
	private ArrayList<Bullet> bulletList;
	
	public LaserPistol(InteractiveObject wielder, ArrayList<Bullet> bulletList) {
		this.wielder = wielder;
		this.bulletList = bulletList;
		
	}
	
	@Override
	public void shoot() {
		int bulletWidth = 5;
		int bulletHeight = 3;
		int bulletDamage = 10;
		int bulletSpeed = 500;
		Vector2d bulletVelocity = new Vector2d();
		Vector2d knockback = new Vector2d(10, -3);
		int offsetX;
		int offsetY;

		if (wielder.isMovingLeft()){
			bulletVelocity.setX(-bulletSpeed);
			offsetX = -5;
			offsetY = 5;
		}else{
			bulletVelocity.setX(bulletSpeed);
			offsetX = 25;
			offsetY = 5;
		}
		
		bulletList.add(new Bullet(wielder.getX()+offsetX, wielder.getY()+offsetY, bulletWidth, bulletHeight, 1, bulletVelocity, knockback, bulletDamage));
	}

	@Override
	public long getCooldown() {
		return 300;
	}
}
