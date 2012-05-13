package rsmg.model.object.bullet;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rsmg.model.variables.ObjectName;
import rsmg.util.Vector2d;

public class TestRotatableBullet {
	private RotatableBullet bullet;
	
	@BeforeClass
	public static void beforeClass() {
	}

	@AfterClass
	public static void afterClass() {
	}

	@Before
	public void before() {
		bullet = new RotatableBullet(10, 10, 10, 10, ObjectName.LASER_BULLET, 99, new Vector2d(0,0), 10);
	}

	@After
	public void after() {
	}

	@Test
	public void testUpdate() {
		// TODO: Not implemented. Remove or implement
		//bullet.update(20);
		//assertTrue(bullet.getVelocityY() > 0);
	}
	
	@Test
	public void testIsExplosion() {
		assertTrue(!bullet.isExplosion());
	}
	
	@Test
	public void testAngle() {
		assertTrue(bullet.getRotation() == 10);
	}
}

