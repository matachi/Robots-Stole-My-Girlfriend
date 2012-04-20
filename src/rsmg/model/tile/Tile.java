package rsmg.model.tile;

import rsmg.model.Constants;

public abstract class Tile {

	private final int size = Constants.TILESIZE;
	
	private String name;

	/**
	 * Constructor of Tile
	 */
	public Tile(String name) {
		this.name = name;
	}

	public abstract boolean isSolid();

	/**
	 * @return the size of the Tile
	 */
	public int getSize() {
		return size;
	}
	
	public String getName() {
		return name;
	}
}
