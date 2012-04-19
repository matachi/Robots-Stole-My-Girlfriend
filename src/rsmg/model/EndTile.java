package rsmg.model;

public class EndTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of EndTile. Sets the solid value
	 */
	public EndTile() {
		super(ObjectName.END_TILE);
		solid = false;
	}

	/**
	 * @return if an EndTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
