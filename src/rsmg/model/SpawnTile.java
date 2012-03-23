package rsgm.model;

public class SpawnTile extends Tile {

	private boolean solid;

	/**
	 * Constructor of SpawnTile. Sets up the size and solid values
	 * 
	 * @param size
	 *            is the size of the SpawnTile
	 */
	public SpawnTile(int size) {
		super(size);
		solid = false;
	}

	/**
	 * @return if an SpawnTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
