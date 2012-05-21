package rsmg.model.object.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.unit.BallBot;

public class TestBallBot {
	
	private BallBot ballBot;
	
	@Before
	public void before() {
		ballBot = new BallBot(0,0);
	}

	@Test
	public void testGetTouchDamage() {
		assertTrue(ballBot.getTouchDamage() == 30);
	}

	@Test
	public void testIsFlyingUnit() {
		assertTrue(ballBot.isFlyingUnit());
	}
}