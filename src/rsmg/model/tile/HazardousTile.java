package rsmg.model.tile;

import rsmg.model.ObjectName;

public class HazardousTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of HazardousTile. Sets the solid value
	 */
	public HazardousTile() {
		super(ObjectName.HAZARDOUS_TILE);
		solid = true;
	}

	/**
	 * @return if an HazardousTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
