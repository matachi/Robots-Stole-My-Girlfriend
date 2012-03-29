package rsmg.model;


/**
 * Class for representing the playable Character
 * @author Johan Grönvall
 * @author Daniel Jonsson
 */

public class Character extends LivingObject {
	private boolean airbourne;
	//Weapon currentWeapon;
	public Character(double x, double y) {
		super(x, y, (double)Constants.CHARACTERWIDTH, (double)Constants.CHARACTERHEIGHT, Constants.CHARACTERHEALTH);
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void applyGravity(double delta) {
		// Apply gravity to the character if he is in the air, in other words,
		// not standing on the ground.
		if (airbourne)
			super.applyGravity(delta);
	}
	
	/**
	 * Update whether the character is in the air or standing on the ground.
	 * @param airbourne If the character is in the air.
	 */
	public void setAirbourne(boolean airbourne) {
		this.airbourne = airbourne;
	}
	
	/**
	 * Adds a velocity upwards to the character, sending him up in the air.
	 */
	public void jump() {
		// Only jump if the character is standing on the ground.
		if (!airbourne)
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
		// character.attack();
	}
}