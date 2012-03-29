package rsmg.model;

public class HazardousTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of HazardousTile. Sets the solid value
	 */
	public HazardousTile() {
		solid = true;
	}

	/**
	 * @return if an HazardousTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
