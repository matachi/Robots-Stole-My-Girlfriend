package rsgm.model;

public abstract class Tile {

	private int size;

	/**
	 * Constructor of Tile. Sets up the tile size
	 * 
	 * @param size
	 *            The size of the Tile
	 */
	public Tile(int size) {
		this.size = size;
	}

	abstract boolean isSolid();

	/**
	 * @return the size of the Tile
	 */
	public int getSize() {
		return size;
	}
}
