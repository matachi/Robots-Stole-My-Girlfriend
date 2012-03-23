package rsmg.model;

public class GroundTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of GroundTile. Sets the solid value
	 */
	public GroundTile() {
		solid = true;
	}

	/**
	 * @return if an GroundTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
