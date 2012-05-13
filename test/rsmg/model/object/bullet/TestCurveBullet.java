package rsmg.model.object.bullet;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rsmg.util.Vector2d;
import rsmg.model.variables.Constants;
import rsmg.model.variables.ObjectName;

public class TestCurveBullet {
	private CurveBullet bullet;
	
	@BeforeClass
	public static void beforeClass() {
	}

	@AfterClass
	public static void afterClass() {
	}

	@Before
	public void before() {
		bullet = new CurveBullet(10, 10, 10, 10, ObjectName.LASER_BULLET, 99, new Vector2d(0,0));
	}

	@After
	public void after() {
	}

	@Test
	public void testUpdate() {
		bullet.update(20);
		assertTrue(bullet.getVelocityY() == Constants.GRAVITYSTRENGTH*20);
	}
	
	@Test
	public void testIsExplosion() {
		assertTrue(!bullet.isExplosion());
	}
}
