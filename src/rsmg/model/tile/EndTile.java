package rsmg.model.tile;

import rsmg.model.ObjectName;

/**
 * Class to creates a EndTile that is not solid
 * @author Johan Rignas
 *
 */
public class EndTile extends Tile {

	private static boolean solid = false;

	/**
	 * Constructor of EndTile
	 */
	public EndTile() {
		super(ObjectName.END_TILE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSolid() {
		return solid;
	}
}
