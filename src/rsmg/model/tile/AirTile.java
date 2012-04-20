package rsmg.model.tile;

import rsmg.model.ObjectName;

public class AirTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of AirTile. Sets the solid value
	 */
	public AirTile() {
		super(ObjectName.AIR_TILE);
		solid = false;
	}

	/**
	 * @return if an AirTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
