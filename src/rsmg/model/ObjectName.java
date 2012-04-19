package rsmg.model;

/**
 * Names of all object in the game.
 * 
 * They're stored in a separate class so they are accessible even if a Level and
 * the objects don't exist.
 * 
 * @author Daniel Jonsson
 * 
 */
public enum ObjectName {
	;

	/**
	 * The character's name.
	 */
	public static final String CHARACTER = "character";

	/**
	 * The enemies' names.
	 */
	public static final String TANKBOT = "tankbot";

	/**
	 * The items' names.
	 */
	public static final String HEALTH_PACK = "healthPack";
	public static final String UPGRADE_POINT = "upgradePoint";
	public static final String WEAPON = "weapon";

	/**
	 * The bullets' names.
	 */
	public static final String PISTOL_BULLET = "bullet";
	public static final String LASER_BULLET = "laserBullet";

	/**
	 * The tiles' names.
	 */
	public static final String BOX_TILE = "boxTile";
	public static final String AIR_TILE = "airTile";
	public static final String HAZARDOUS_TILE = "airTile";
	public static final String SPAWN_TILE = "airTile";
	public static final String END_TILE = "airTile";
}
