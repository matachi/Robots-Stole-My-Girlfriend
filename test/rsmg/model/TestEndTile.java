package rsmg.model;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestEndTile {

	@Test
	public void testSolid() {
		EndTile tile = new EndTile();
		assertTrue(tile.isSolid() == false);
	}
}
