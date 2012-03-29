package rsmg.model;

public class SpawnTile extends Tile {

	private boolean solid;
	/**
	 * Constructor of SpawnTile. Sets solid value
	 */
	public SpawnTile() {
		solid = false;
	}

	/**
	 * @return if an SpawnTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
