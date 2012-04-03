package rsmg.model;

import java.util.ArrayList;


/**
 * Class for representing the playable Character
 * @author Johan Grönvall
 * @author Daniel Jonsson
 */

public class Character extends LivingObject {
	private boolean airborne;
	IWeapon currentWeapon;
	ArrayList<Bullet> bulletList;
	long lastAttacktime = 0;
	
	public Character(double x, double y, ArrayList<Bullet> bulletList) {
		super(x, y, (double)Constants.CHARACTERWIDTH, (double)Constants.CHARACTERHEIGHT, Constants.CHARACTERHEALTH);
		this.bulletList = bulletList;	
		currentWeapon = new LaserPistol(this, bulletList);
		
	}
	public Character(double x, double y) {
		super(x, y, (double)Constants.CHARACTERWIDTH, (double)Constants.CHARACTERHEIGHT, Constants.CHARACTERHEALTH);
		this.bulletList = bulletList;
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void applyGravity(double delta) {
		// Apply gravity to the character if he is in the air, in other words,
		// not standing on the ground.
		if (airborne)
			super.applyGravity(delta);
	}
	
	/**
	 * Update whether the character is in the air or standing on the ground.
	 * @param airborne If the character is in the air.
	 */
	public void setAirborne(boolean airborne) {
		this.airborne = airborne;
	}
	
	/**
	 * Is the character airborne, i.e. in the air.
	 * @return If the character is in the air.
	 */
	public boolean isAirborne() {
		return airborne;
	}
	
	/**
	 * Adds a velocity upwards to the character, sending him up in the air.
	 */
	public void jump() {
		// Only jump if the character is standing on the ground.
		if (!airborne)
			this.addVelocity(0, -Constants.JUMPSTRENGTH);
	}
	
	/**
	 * Changes the character's velocity, moving him westwards in next loop.
	 */
	public void moveLeft() {
		this.setVelocityX(-Constants.CHARACTERSPEED);
	}
	
	/**
	 * Changes the character's velocity, moving him eastwards in next loop.
	 */
	public void moveRight() {
		this.setVelocityX(Constants.CHARACTERSPEED);
	}

	/**
	 * Called when the user releases the jump key.
	 */
	public void jumpReleased() {
		if (getVelocityY() < Constants.RELEASED_JUMP_VELOCITY)
			setVelocityY(Constants.RELEASED_JUMP_VELOCITY);
	}

	/**
	 * Make the character attack (i.e. hit with his melee weapon or shoot with
	 * his gun.
	 */
	public void attack() {
		if (lastAttacktime + currentWeapon.getCooldown() < System.currentTimeMillis()){
			currentWeapon.shoot();
			lastAttacktime = System.currentTimeMillis(); 
		}
	}
}