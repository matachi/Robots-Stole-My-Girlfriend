package rsmg.model.object.livingobject;

import java.util.Collection;

import rsmg.model.Bullet;
import rsmg.model.Constants;
import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.object.item.Item;
import rsmg.model.weapon.IWeapon;
import rsmg.model.weapon.LaserPistol;
import rsmg.util.Vector2d;

/**
 * Class for representing the playable Character
 * @author Johan Gronvall
 * @author Daniel Jonsson
 */

public class PCharacter extends LivingObject {
	private boolean airborne;
	private IWeapon currentWeapon;
	private Collection<Bullet> bulletList;
	private long lastAttacktime = 0;
	private int ammo;
	private double distanceDashed = 0;
	private boolean isDashing = false;
	private boolean canDash = true;
	
	public PCharacter(double x, double y, Collection<Bullet> bulletList) {
		this(x, y);
		this.bulletList = bulletList;	
		currentWeapon = new LaserPistol(this, bulletList);
		
	}
	public PCharacter(double x, double y) {
		super(x, y, (double)Constants.CHARACTERWIDTH, (double)Constants.CHARACTERHEIGHT, Constants.CHARACTERHEALTH, ObjectName.CHARACTER);
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		if (obj instanceof Enemy){
			this.damage(((Enemy) obj).getTouchDamage());
		}
		if (obj instanceof Item){
			if(obj.getName().equals("laserPistol")){
				currentWeapon = new LaserPistol(this, bulletList);
				System.out.println("laserPistol picked up");
			}
			else if(obj.getName().equals(ObjectName.HEALTH_PACK)){
				System.out.println("healthPack picked up");
			}
		}
	}
	
	public void reload(){
		currentWeapon.getReloadTime();
	}
	
	@Override
	public void applyGravity(double delta) {
		// Apply gravity to the character if he is in the air, in other words,
		// not standing on the ground.
		if (airborne)
			super.applyGravity(delta);
	}
	
	public void setDashing(boolean isDashing){
		this.isDashing = isDashing;
	}
	
	/**
	 * Returns a reference to the weapon that the character is currently wielding.
	 * @return A reference to the weapon that the character is currently wielding.
	 */
	public IWeapon getWeapon() {
		return currentWeapon;
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
	/**
	 * Make the character perform the "dash" move
	 */
	public void dash(double delta) {
		distanceDashed+=Constants.DASHSPEED*delta;
		
		if (this.isFacingRight()){
			this.addVelocity(new Vector2d(Constants.DASHSPEED, 0));
		}else{
			this.addVelocity(new Vector2d(-Constants.DASHSPEED, 0));
		}
	}
	public boolean isDashing() {
		return isDashing;
	}

	public boolean canDash(){
		return canDash;
	}
	
	public void updateDashing(double delta) {
		//tell the character to perform the dash move if the boolean "isDashing" is set to true
		if(distanceDashed > Constants.DASHLENGTH){
			setDashing(false);
			distanceDashed = 0;
		}
		if (isDashing){
			dash(delta);
		}
	}
}