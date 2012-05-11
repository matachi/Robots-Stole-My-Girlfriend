package rsmg.model.object.unit.weapon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;

public class TestShotgun {
	
	private Shotgun shotgun;
	private Collection<Bullet> bulletList;
	
	@Before
	public void before() {
		bulletList = new LinkedList<Bullet>();
		shotgun = new Shotgun(bulletList);
	}

	@Test
	public void testShoot() {
		assertTrue(bulletList.size() == 0);
		shotgun.shoot(0, 0, true);
		assertTrue(bulletList.size() == 9);
	}

	@Test
	public void testGetCooldown() {
		// TODO change CharacterProgress to non rapid fire in start
		//assertTrue(shotgun.getCooldown() == 600);
	}
	
	@Test
	public void testShot() {
		assertFalse(shotgun.shot());
		shotgun.shoot(0, 0, true);
		assertTrue(shotgun.shot());
	}

	@Test
	public void testGetKnockback() {
		Vector2d vec = shotgun.getKnockback(false);
		assertTrue(vec.getX() == new Vector2d(50,0).getX());
		assertTrue(vec.getY() == new Vector2d(50,0).getY());
		assertTrue(vec.getlength() >= vec.getY());
		assertTrue(vec.getlength() >= vec.getX());
		assertTrue(vec.getlength() <= (vec.getX() + vec.getY()));
	}
	
	@Test
	public void testGetName() {
		assertTrue(shotgun.getName() == "shotgun");
	}
}
