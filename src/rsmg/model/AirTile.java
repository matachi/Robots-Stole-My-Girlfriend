package rsmg.model;

public class AirTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of AirTile. Sets the solid value
	 */
	public AirTile() {
		super(ObjectName.AIR_TILE);
		solid = false;
	}

	/**
	 * @return if an AirTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
