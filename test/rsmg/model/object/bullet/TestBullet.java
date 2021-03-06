package rsmg.model.object.bullet;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;
import rsmg.model.variables.ObjectName;

/**
 * Tests the bullet class
 *
 */
public class TestBullet {
	
	private Bullet bullet;
	
	@BeforeClass
	public static void beforeClass() {
	}

	@AfterClass
	public static void afterClass() {
	}

	@Before
	public void before() {
		bullet = new BasicBullet(10, 10, 10, 10, ObjectName.LASER_BULLET, 99, null);
	}

	@After
	public void after() {
	}

	@Test
	public void testGetDamage() {
		assertTrue(bullet.getDamage() == 99);
	}
	
	public void testIsExplosion() {
		assertTrue(!bullet.isExplosion());
	}
	
}
