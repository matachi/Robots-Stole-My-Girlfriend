package rsgm.model;


public class AirTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of AirTile. Sets up the size and solid values
	 * 
	 * @param size
	 *            is the size of the AirTile
	 */
	public AirTile(int size) {
		super(size);
		solid = false;
	}

	/**
	 * @return if an AirTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
