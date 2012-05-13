package rsmg.model.tile;

import rsmg.model.variables.ObjectName;

/**
 * Class to creates a SpawnTile that is not solid
 * @author Johan Rignas
 *
 */
public class SpawnTile extends Tile {

	private static boolean solid = false;
	
	/**
	 * Constructor of SpawnTile
	 */
	public SpawnTile() {
		super(ObjectName.SPAWN_TILE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSolid() {
		return solid;
	}
}
