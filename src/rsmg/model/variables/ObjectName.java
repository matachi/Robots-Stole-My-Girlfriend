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
	BOX_TILE1, BOX_TILE2, BOX_TILE3, BOX_TILE4, AIR_TILE, SPAWN_TILE, END_TILE,

	/**
	 * The character's name.
	 */
	CHARACTER,

	/**
	 * The enemies' names.
	 */
	TANKBOT, ROCKETBOT, BALLBOT, BUCKETBOT, SPIKES, MINIBALLBOT, BOSSBOT,
	
	/**
	 * The items' names.
	 */
	HEALTH_PACK, UPGRADE_POINT, LASER_PISTOL, ROCKET_LAUNCHER, SHOTGUN, PISTOL,
	
	
	/**
	 * The bullets' names.
	 */
	EXPLOSION, BIG_EXPLOSION, PISTOL_BULLET, LASER_BULLET, ROCKETR, ROCKETL,
	SHOTGUN_BULLET, STONE, LASERBOLT, LASERFIRE, LASERBLAST;
}
