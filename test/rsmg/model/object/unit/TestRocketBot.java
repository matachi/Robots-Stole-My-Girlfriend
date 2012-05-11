package rsmg.model.object.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestRocketBot {
	
	private RocketBot rocketbot;

	@Before
	public void before() {
		rocketbot = new RocketBot(0, 0);
	}

	@Test
	public void testGetTouchDamage() {
		assertTrue(rocketbot.getTouchDamage() == 20);
	}

	@Test
	public void testIsFlyingUnit() {
		assertTrue(rocketbot.isFlyingUnit());
	}
}