package rsgm.model;


public class GroundTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of GroundTile. Sets up the size and solid values
	 * 
	 * @param size
	 *            is the size of the GroundTile
	 */
	public GroundTile(int size) {
		super(size);
		solid = true;
	}

	/**
	 * @return if an GroundTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
