package rsmg.model.object.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestMiniBallBot {
	
	private MiniBallBot miniball;
	
	@Before
	public void before() {
		miniball = new MiniBallBot(0, 0);
	}

	@Test
	public void testGetTouchDamage() {
		assertTrue(miniball.getTouchDamage() == 10);
	}

	@Test
	public void testIsFlyingUnit() {
		assertTrue(miniball.isFlyingUnit());
	}
}