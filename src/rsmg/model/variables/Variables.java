package rsmg.model.variables;
/**
 * Singleton class for holding all global variables used throughout the application
 * @author Johan Grönvall
 *
 */
public enum Variables{
	
	;
	public static final int CHARACTERWIDTH = 20;
	public static final int CHARACTERHEIGHT = 23;
	public static final int CHARACTERHEALTH = 100;
	public static final int JUMPSTRENGTH = 230;
	public static final int DOUBLEJUMPSTRENGTH = 200;
	public static final int AIR_FRICTION = 200;
	public static final int GROUND_FRICTION = 500;

	/**
	 * how fast the character accelerates
	 */
	public static final int CHARACTER_ACCELERATION = 1100;
	/**
	 * The Characters runningSpeed, this variable is private to make it mutable within this class
	 */
	private static int charSpeed = 100;
	private static final int UPGRADEDCHARSPEED = 120;
	
	/**
	 * The velocity that the character gets after a jump when the player
	 * releases the jump key. The velocity won't be changed if the character
	 * already has a lower speed upwards or is falling downwards.
	 */
	public static final int RELEASED_JUMP_VELOCITY = -60;
	public static final int TILESIZE = 32;
	public static final int GRAVITYSTRENGTH = 400;
	public static final int DASHSPEED = 300;
	public static final int DASHLENGTH = 60;
	public static final int DASHCOOLDOWN = 600;
	public static final int DASHDAMAGE = 1;
	public static final int EXPLOSIONDMG = 1;
	
	/**
	 * the hitbox for explosions fired from the RPG, this variable is private to make it mutable within the class
	 */
	private static int explosionAOE = 30;
	private static final int UPGRADEDEXPLOSIONAOE = 45;
	
	private static int shotgunSpread = 200;
	private static final int UPGRADEDSHOTGUNSPREAD = 300;
	
	/**
	 * the amount of time an enemy should be flashing after he is hit in milliseconds
	 */
	public static final int ENEMY_FLASHDURATION = 100;
	/**
	 * the amount of time in between each damage tick of an explosion
	 */
	public static final int EXPLOSION_TICK = 20;
	/**
	 * the amount of time an explosion persists in seconds
	 */
	public static final int EXPLOSIONDURATION = 1;
	/**
	 * the amount of time the character turns immortal after getting hit by an enemy
	 * in milliseconds
	 */
	public static final int CHARACTER_IMMORTALITY_TIME = 800;
	

	
	public static void upgradeSpeed() {
		charSpeed = UPGRADEDCHARSPEED;
	}
	
	public static void upgradeExplosionAOE() {
		explosionAOE = UPGRADEDEXPLOSIONAOE;
	}
	public static void upgradeShotgunSpead() {
		shotgunSpread = UPGRADEDSHOTGUNSPREAD; 
	}
	
	public static int getCharSpeed() {
		return charSpeed;
	}
	
	public static int getExplosionAOE() {
		return explosionAOE;
	}
	
	public static int getShotgunSpread() {
		return shotgunSpread;
	}
	
}
