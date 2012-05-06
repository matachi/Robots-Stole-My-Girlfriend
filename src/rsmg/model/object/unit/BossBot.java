package rsmg.model.object.unit;

import java.util.List;

import rsmg.model.ObjectName;
import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;

public class BossBot extends Enemy implements AttackingEnemy {
	private List<Bullet> bulletList;
	private static int OFFSETX = 70;
	private static int OFFSETY = 20;
	private static int DMG = 2;
	private Vector2d bulletVelocity = new Vector2d();
	
	public BossBot(double x, double y, List<Bullet> bulletList) {
		super(x, y, 107, 124, 1000, ObjectName.BOSSBOT);
		this.bulletList = bulletList;
	}

	@Override
	public int getTouchDamage() {
		return 30;
	}

	@Override
	public boolean isFlyingUnit() {
		return false;
	}
	
	public Vector2d getBulletVelocity() {
		return bulletVelocity;
	}

	@Override
	public void shoot() {
		bulletList.add(new Bullet(OFFSETX, OFFSETY, 10, 10, ObjectName.LASERBOLT, DMG, bulletVelocity));
	}
}