package rsmg.model;

public class GroundTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of GroundTile. Sets the solid value
	 */
	public GroundTile() {
		super(ObjectName.BOX_TILE);
		solid = true;
	}

	/**
	 * @return if an GroundTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
