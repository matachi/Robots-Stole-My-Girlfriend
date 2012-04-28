package rsmg.model;
/**
 * Singleton class for holding all global variables used throughout the application
 * @author Johan Grönvall
 *
 */
public enum Constants{
	;
	public static final int CHARACTERWIDTH = 20;
	public static final int CHARACTERHEIGHT = 23;
	public static final int CHARACTERHEALTH = 100;
	public static final int JUMPSTRENGTH = 150;
	public static final int CHARACTERSPEED = 100;
	
	/**
	 * The velocity that the character gets after a jump when the player
	 * releases the jump key. The velocity won't be changed if the character
	 * alreadt has a lower speed upwards or is falling downwards.
	 */
	public static final int RELEASED_JUMP_VELOCITY = -60;
	public static final int TILESIZE = 32;
	public static final int GRAVITYSTRENGTH = 300;
	public static final int DASHSPEED = 300;
	public static final int DASHLENGTH = 60;
	public static final int DASHCOOLDOWN = 3;
	public static final int EXPLOSIONDMG = 1;
	public static final int EXPLOSIONAOE = 30;
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
}
