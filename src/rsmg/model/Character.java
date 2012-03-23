package rsmg.model;

import rsmg.util.Vector2d;

/**
 * Class for representing the playable Character
 * @author Johan Grönvall
 *
 */
public class Character extends InteractiveObject {
	int health;
	//Weapon currentWeapon;
	public Character(double x, double y) {
		super(x, y, (double)Constants.CHARACTERWIDTH, (double)Constants.CHARACTERHEIGHT);
		health =  Constants.CHARACTERHEALTH;
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Adds a vertical vector to the character sending him up in the air
	 */
	public void jump(){
		this.getVector().add(new Vector2d(0, Constants.JUMPSTRENGTH));
	}
	
	/**
	 * Adds a horizontal vector to the character sending him westwards
	 */
	public void moveLeft(){
		this.getVector().add(new Vector2d(-Constants.CHARACTERSPEED, 0));
	}
	
	/**
	 * Adds a horizontal vector to the character sending him westwards
	 */
	public void moveRight(){
		this.getVector().add(new Vector2d(Constants.CHARACTERSPEED, 0));
	}

}