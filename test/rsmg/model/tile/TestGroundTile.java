package rsmg.model.tile;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import rsmg.model.variables.ObjectName;

public class TestGroundTile {

	@Test
	public void testSolid() {
		GroundTile tile = new GroundTile(ObjectName.BOX_TILE1);
		assertTrue(tile.isSolid() == true);
	}
}
