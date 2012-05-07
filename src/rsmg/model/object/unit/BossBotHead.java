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
	private static int OFFSETX1 = 24;
	private static int OFFSETY1 = 31;
	
	//for the other eye
//	private static int OFFSETX2 = 24;
//	private static int OFFSETY2 = 40;
	
	private static int DMG = 70;
	
	public BossBotHead(double x, double y, List<Bullet> bulletList) {
		super(x, y, 48, 42, 1000, ObjectName.BOSSBOT);
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
		bulletList.add(new RotatableBullet(this.getX()+OFFSETX1, this.getY()+OFFSETY1, 40, 8, ObjectName.LASERBOLT, DMG, bulletVelocity, (float)angle));
	}
}