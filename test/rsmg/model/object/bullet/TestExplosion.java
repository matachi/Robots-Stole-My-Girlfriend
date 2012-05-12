package rsmg.model.object.bullet;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the Explosion class
 *
 */
public class TestExplosion {
	
	private Explosion explosion;
	
	@BeforeClass
	public static void beforeClass() {
	}

	@AfterClass
	public static void afterClass() {
	}

	@Before
	public void before() {
		explosion = new SmallExplosion(10, 10);
	}

	@After
	public void after() {
	}

	@Test
	public void testAge() {
		assertTrue(explosion.getAge() == 0);
	}
}
