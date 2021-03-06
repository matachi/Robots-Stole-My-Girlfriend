package rsmg.model.object.unit;

import java.util.List;

import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.bullet.RotatableBullet;
import rsmg.model.variables.ObjectName;
import rsmg.util.Vector2d;

/**
 * class representing the head of the 'bossbot' type Enemy
 * @author zapray
 *
 */
public class BossBotHead extends Enemy {
	private List<Bullet> bulletList;
	private static final int OFFSETX1 = 3;
	private static final int OFFSETY1 = 13;
	
	//offset for where the second attack is fired
	private static final int CRYSTAL_OFFSETY = 130;
	private static final int CRYSTAL_OFFSETX = -16;
	
	//for the other eye
	private static int OFFSETX2 = -18;

	private static int DMG = 60;
	
	/**
	 * Constructor of BossBotHead
	 * @param x The x position of the boss
	 * @param y The y position of the boss
	 * @param bulletList A bulletList for the boss
	 */
	public BossBotHead(double x, double y, List<Bullet> bulletList) {
		super(x, y, 48, 42, 400, ObjectName.BOSSBOT);
		this.bulletList = bulletList;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTouchDamage() {
		return 30;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFlyingUnit() {
		return true;
	}
	
	/**
	 * Fires the bosses primary attack
	 */
	public void shoot(Vector2d bulletVelocity, double angle) {
		bulletList.add(new RotatableBullet(this.getX()+OFFSETX1, this.getY()+OFFSETY1, 10, 8, ObjectName.LASERBOLT, DMG, bulletVelocity, (float)angle));
		bulletList.add(new RotatableBullet(this.getX()+OFFSETX2, this.getY()+OFFSETY1, 10, 8, ObjectName.LASERBOLT, DMG, bulletVelocity, (float)angle));
	}
	
	/**
	 * Fires the bosses secondary attack
	 */
	public void shoot2(Vector2d bulletVelocity, double angle) {
		bulletList.add(new RotatableBullet(this.getX()+CRYSTAL_OFFSETX, this.getY()+CRYSTAL_OFFSETY, 33, 11, ObjectName.LASERBLAST, 10, bulletVelocity, (float)angle));
	}
}
