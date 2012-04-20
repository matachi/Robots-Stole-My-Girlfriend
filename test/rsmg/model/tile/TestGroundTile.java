package rsmg.model.tile;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestGroundTile {

	@Test
	public void testSolid() {
		GroundTile tile = new GroundTile();
		assertTrue(tile.isSolid() == true);
	}
}
