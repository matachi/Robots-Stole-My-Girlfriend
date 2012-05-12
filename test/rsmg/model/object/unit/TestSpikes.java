package rsmg.model.object.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestSpikes {
	
	private Spikes spikes;

	@Before
	public void before() {
		spikes = new Spikes(0, 0);
	}

	@Test
	public void testGetTouchDamage() {
		assertTrue(spikes.getTouchDamage() == 30);
	}

	@Test
	public void testIsFlyingUnit() {
		assertFalse(spikes.isFlyingUnit());
	}
}