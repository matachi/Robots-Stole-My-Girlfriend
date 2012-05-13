package rsmg.model.tile;

import rsmg.model.variables.ObjectName;

/**
 * Class to creates a AirTile that is not solid
 * @author Johan Rignas
 */
public class AirTile extends Tile {

	private static boolean solid = false;

	/**
	 * Constructor of AirTile
	 */
	public AirTile() {
		super(ObjectName.AIR_TILE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSolid() {
		return solid;
	}
}
