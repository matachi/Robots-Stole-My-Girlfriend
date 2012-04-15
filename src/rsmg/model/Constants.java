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
}
