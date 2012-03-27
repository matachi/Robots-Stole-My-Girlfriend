package rsmg.model;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestSpawnTile {

	@Test
	public void testSolid() {
		SpawnTile tile = new SpawnTile();
		assertTrue(tile.isSolid() == false);
	}
}
