package rsmg.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAirTile {

	@Test
	public void testSolid() {
		AirTile tile = new AirTile();
		assertTrue(tile.isSolid() == false);
	}
}
