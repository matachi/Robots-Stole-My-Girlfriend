package rsmg.model.tile;

import rsmg.model.variables.Constants;
import rsmg.model.variables.ObjectName;

/**
 * A Tile are parts of the environment in the game
 * @author Johan Rignas
 */
public abstract class Tile {

	/**
	 * The size the tiles are supposed to have
	 */
	private final int size = Constants.TILESIZE;
	
	/**
	 * Name for the tile
	 */
	private Enum<ObjectName> name;

	/**
	 * Constructor of Tile
	 * @param name Name of the tile
	 */
	public Tile(Enum<ObjectName> name) {
		this.name = name;
	}

	/**
	 * Tells the tile are solid or not
	 * @return True for solid, otherwise false
	 */
	public abstract boolean isSolid();

	/**
	 * @return The size of the Tile
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * The name for this tile. Used for sub-Tiles
	 * @return Tile name
	 */
	public Enum<ObjectName> getName() {
		return name;
	}
}
