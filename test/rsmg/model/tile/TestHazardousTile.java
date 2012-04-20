package rsmg.model.tile;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestHazardousTile {

	@Test
	public void testSolid() {
		HazardousTile tile = new HazardousTile();
		assertTrue(tile.isSolid() == true);
	}
}
