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

public class TestPistol {
	
	private Pistol pistol;
	private Collection<Bullet> bulletList;
	
	@Before
	public void before() {
		bulletList = new LinkedList<Bullet>();
		pistol = new Pistol(bulletList);
	}

	@Test
	public void testShoot() {
		assertTrue(bulletList.size() == 0);
		pistol.shoot(0, 0, true);
		assertTrue(bulletList.size() == 1);
	}

	@Test
	public void testGetCooldown() {
		// TODO change CharacterProgress to non rapid fire in start
		//assertTrue(pistol.getCooldown() == 300);
	}
	
	@Test
	public void testShot() {
		assertFalse(pistol.shot());
		pistol.shoot(0, 0, true);
		assertTrue(pistol.shot());
	}

	@Test
	public void testGetKnockback() {
		Vector2d vec = pistol.getKnockback(true);
		assertTrue(vec.getX() == new Vector2d(0,0).getX());
		assertTrue(vec.getY() == new Vector2d(0,0).getY());
		assertTrue(vec.getlength() == 0);
	}
	
	@Test
	public void testGetName() {
		assertTrue(pistol.getName() == ObjectName.PISTOL);
	}
}
