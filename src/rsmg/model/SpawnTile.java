package rsmg.model;

public class SpawnTile extends Tile {

	private boolean solid;
	/**
	 * Constructor of SpawnTile. Sets solid value
	 */
	public SpawnTile() {
		super(ObjectName.SPAWN_TILE);
		solid = false;
	}

	/**
	 * @return if an SpawnTile is solid or not
	 */
	public boolean isSolid() {
		return solid;
	}
}
