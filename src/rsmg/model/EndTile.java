package rsgm.model;


public class EndTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of EndTile. Sets up the size and solid values
	 * 
	 * @param size
	 *            is the size of the EndTile
	 */
	public EndTile(int size) {
		super(size);
		solid = false;
	}

	/**
	 * @return if an EndTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
