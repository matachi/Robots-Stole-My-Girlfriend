package rsmg.model.tile;

import rsmg.model.Variables;

public abstract class Tile {

	private final int size = Variables.TILESIZE;
	
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
