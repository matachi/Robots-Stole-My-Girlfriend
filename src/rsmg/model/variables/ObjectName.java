package rsmg.model.variables;

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
	
	/**
	 * The tiles' names.
	 */
	BOX_TILE1, BOX_TILE2, BOX_TILE3, BOX_TILE4, AIR_TILE, SPAWN_TILE, END_TILE;
	

	/**
	 * The character's name.
	 */
	public static final String CHARACTER = "character";

	/**
	 * The enemies' names.
	 */
	public static final String TANKBOT = "tankbot";
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
	public static final String EXPLOSION = "explosion";
	public static final String BIG_EXPLOSION = "bigExplosion";
	public static final String PISTOL_BULLET = "bullet";
	public static final String LASER_BULLET = "laserBullet";
	public static final String ROCKETR = "rocketR";
	public static final String ROCKETL = "rocketL";
	public static final String SHOTGUN_BULLET = "shotgunBullet";
	public static final String STONE = "stone";
	public static final String LASERBOLT = "laserbolt";
	public static final String LASERFIRE = "laserFire";
	public static final String LASERBLAST = "laserBlast";
}
