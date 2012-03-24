package rsmg.model;

public abstract class Tile {

	private int size = Constants.TILESIZE;

	/**
	 * Constructor of Tile
	 */
	public Tile() {}

	public abstract boolean isSolid();

	/**
	 * @return the size of the Tile
	 */
	public int getSize() {
		return size;
	}
}
