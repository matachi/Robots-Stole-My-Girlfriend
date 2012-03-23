package rsgm.model;


public class HazardousTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of HazardousTile. Sets up the size and solid values
	 * 
	 * @param size
	 *            is the size of the HazardousTile
	 */
	public HazardousTile(int size) {
		super(size);
		solid = true;
	}

	/**
	 * @return if an HazardousTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
