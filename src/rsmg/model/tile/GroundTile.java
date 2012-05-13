package rsmg.model.tile;

import rsmg.model.variables.ObjectName;

/**
 * Class to creates a GroundTile that is solid
 * @author Johan Rignas
 */
public class GroundTile extends Tile {

	private static boolean solid = true;

	/**
	 * Constructor of GroundTile
	 */
	public GroundTile(Enum<ObjectName> groundType) {
		super(groundType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSolid() {
		return solid;
	}
}
