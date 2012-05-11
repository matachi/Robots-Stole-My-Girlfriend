package rsmg.model.object.unit.weapon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;
import rsmg.util.Vector2d;

public class TestRocketLauncher {
	
	private RocketLauncher launcher;
	private Collection<Bullet> bulletList;
	
	@Before
	public void before() {
		bulletList = new LinkedList<Bullet>();
		launcher = new RocketLauncher(bulletList);
	}

	@Test
	public void testShoot() {
		assertTrue(bulletList.size() == 0);
		launcher.shoot(0, 0, true);
		assertTrue(bulletList.size() == 1);
	}

	@Test
	public void testGetCooldown() {
		// TODO change CharacterProgress to non rapid fire in start
		//assertTrue(launcher.getCooldown() == 1800);
	}
	
	@Test
	public void testShot() {
		assertFalse(launcher.shot());
		launcher.shoot(0, 0, true);
		assertTrue(launcher.shot());
	}

	@Test
	public void testGetKnockback() {
		Vector2d vec = launcher.getKnockback(false);
		assertTrue(vec.getX() == new Vector2d(200,0).getX());
		assertTrue(vec.getY() == new Vector2d(200,0).getY());
		assertTrue(vec.getlength() >= vec.getY());
		assertTrue(vec.getlength() >= vec.getX());
		assertTrue(vec.getlength() <= (vec.getX() + vec.getY()));
	}
	
	@Test
	public void testGetName() {
		assertTrue(launcher.getName() == "rpg");
	}
}
