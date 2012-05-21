package rsmg.model.object.unit.weapon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;
import rsmg.model.variables.ObjectName;
import rsmg.util.Vector2d;

public class TestLaserPistol {
	
	private LaserPistol laserPistol;
	private Collection<Bullet> bulletList;
	
	@Before
	public void before() {
		bulletList = new LinkedList<Bullet>();
		laserPistol = new LaserPistol(bulletList);
	}

	@Test
	public void testShoot() {
		assertTrue(bulletList.size() == 0);
		laserPistol.shoot(0, 0, true);
		assertTrue(bulletList.size() == 1);
	}

	@Test
	public void testGetCooldown() {
		// TODO change CharacterProgress to non rapid fire in start
		//assertTrue(laserPistol.getCooldown() == 500);
	}
	
	@Test
	public void testShot() {
		assertFalse(laserPistol.shot());
		laserPistol.shoot(0, 0, true);
		assertTrue(laserPistol.shot());
	}

	@Test
	public void testGetKnockback() {
		Vector2d vec = laserPistol.getKnockback(true);
		assertTrue(vec.getX() == new Vector2d(0,0).getX());
		assertTrue(vec.getY() == new Vector2d(0,0).getY());
		assertTrue(vec.getlength() == 0);
	}
	
	@Test
	public void testGetName() {
		assertTrue(laserPistol.getName() == ObjectName.LASER_PISTOL);
	}
}
