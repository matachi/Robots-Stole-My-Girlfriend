package rsmg.model.object.unit;

import java.util.List;

import rsmg.model.ObjectName;
import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;

public class BucketBot extends Enemy implements AttackingEnemy{
	private List<Bullet> bulletList;

	public BucketBot(double x, double y, List<Bullet> bulletList) {
		super(x, y, 22, 30, 20, ObjectName.BUCKETBOT);
		this.bulletList = bulletList;

	}

	@Override
	public int getTouchDamage() {
		return 20;
	}

	@Override
	public boolean isFlyingUnit() {
		return false;
	}

	@Override
	public void shoot(List<Bullet> bulletList) {
		//TODO change these numbers
		bulletList.add(new Bullet(getX(), getY(), 10, 10, ObjectName.PISTOL_BULLET, 20, new Vector2d(0, 0)));
	}
	

}
