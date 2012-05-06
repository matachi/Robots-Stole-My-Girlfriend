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
	public static final String EXPLOSION = "explosion";
	public static final String ROCKETBOT = "rocketbot";
	public static final String BALLBOT = "ballbot";
	public static final String BUCKETBOT = "bucketbot";
	public static final String SPIKES = "spikes";
	public static final String MINIBALLBOT = "miniBallBot";
	public static final String BOSSBOT = "bossBot";
	

	/**
	 * The items' names.
	 */
	public static final String HEALTH_PACK = "healthPack";
	public static final String UPGRADE_POINT = "upgradePoint";
	public static final String LASER_PISTOL = "laserPistol";
	public static final String ROCKET_LAUNCHER = "rpg";
	public static final String SHOTGUN = "shotgun";
	public static final String PISTOL = "pistol";
	
	/**
	 * The bullets' names.
	 */
	public static final String PISTOL_BULLET = "bullet";
	public static final String LASER_BULLET = "laserBullet";
	public static final String ROCKETR = "rocketR";
	public static final String ROCKETL = "rocketL";
	public static final String SHOTGUN_BULLET = "shotgunBullet";
	public static final String STONE = "stone";
	public static final String LASERBOLT = "laserbolt";

	/**
	 * The tiles' names.
	 */
	public static final String BOX_TILE1 = "boxTile1";
	public static final String BOX_TILE2 = "boxTile2";
	public static final String BOX_TILE3 = "boxTile3";
	public static final String BOX_TILE4 = "boxTile4";
	public static final String AIR_TILE = "airTile";
	public static final String HAZARDOUS_TILE = "airTile";
	public static final String SPAWN_TILE = "airTile";
	public static final String END_TILE = "endTile";
}
