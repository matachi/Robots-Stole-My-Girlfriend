package rsmg.model.object.unit;

import java.util.List;

import rsmg.model.ObjectName;
import rsmg.model.ai.IBoss;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.bullet.RotatableBullet;
import rsmg.util.Vector2d;
/**
 * class representing the head of the bossbot
 * @author zapray
 *
 */
public class BossBotHead extends Enemy implements IBoss {
	private List<Bullet> bulletList;
	private static int OFFSETX1 = 6;
	private static int OFFSETY1 = 8;
	
	//for the other eye
	private static int OFFSETX2 = 14;
	private static int OFFSETY2 = 8;
	
	private static int DMG = 2;
	
	public BossBotHead(double x, double y, List<Bullet> bulletList) {
		super(x, y, 24, 21, 1000, ObjectName.BOSSBOT);
		this.bulletList = bulletList;
	}

	@Override
	public int getTouchDamage() {
		return 30;
	}

	@Override
	public boolean isFlyingUnit() {
		return true;
	}

	@Override
	public void shoot(Vector2d bulletVelocity, double angle) {
		bulletList.add(new RotatableBullet(this.getX()+OFFSETX1, this.getY()+OFFSETY1, 10, 10, ObjectName.LASERBOLT, DMG, bulletVelocity, (float)angle));
	}
}