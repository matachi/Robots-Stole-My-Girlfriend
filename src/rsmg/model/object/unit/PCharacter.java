package rsmg.model.object.unit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import rsmg.io.CharacterProgress;
import rsmg.model.Constants;
import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.item.Item;
import rsmg.model.weapon.IWeapon;
import rsmg.model.weapon.Pistol;
import rsmg.model.weapon.RocketLauncher;
import rsmg.model.weapon.Shotgun;

/**
 * Class for representing the playable Character
 * @author Johan Gronvall
 * @author Daniel Jonsson
 */

public class PCharacter extends LivingObject {
	
	private boolean immortal = false;
	/**
	 * If the character is airborne (i.e. in the air).
	 */
	private boolean airborne;
	
	/**
	 * The weapon that the character is currently equipped with.
	 */
	private IWeapon currentWeapon;
	
//	/**
//	 * Reference to the level's bullet list.
//	 */
//	private Collection<Bullet> bulletList;
	
	/**
	 * List of refrences to the characters weapons
	 */
	private Map<String, IWeapon> weapons;
	
	/**
	 * Keeps track of when the character last attacked. Used to have a cooldown
	 * on the attack.
	 */
	private long lastAttacktime = 0;
	
	/**
	 * Keeps track of when the character was last attacked.
	 */
	private long lastAttackedTime = 0;
	
	/**
	 * If the character can use the dash move.
	 */
	private boolean canDash;
	
	/**
	 * How far the character has traveled with the dash move.
	 */
	private double distanceDashed = 0;
	
	/**
	 * If the character is dashing.
	 */
	private boolean isDashing = false;
	
	/**
	 *  upgradePoints the user can spend after each level
	 */
	private int upgradePoints = 0;
	
	/**
	 * Create a character that the player controls.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 * @param bulletList Reference to the level's bullet list where the character's bullets should be stored.
	 */
	public PCharacter(double x, double y, Collection<Bullet> bulletList) {
		super(x, y, Constants.CHARACTERWIDTH, Constants.CHARACTERHEIGHT, Constants.CHARACTERHEALTH, ObjectName.CHARACTER);
//		this.bulletList = bulletList;
		canDash = CharacterProgress.isDashUnlocked();
		weapons = new HashMap<String, IWeapon>();
		weapons.put(ObjectName.PISTOL, new Pistol(bulletList));
		weapons.put(ObjectName.SHOTGUN, new Shotgun(bulletList));
		weapons.put(ObjectName.ROCKET_LAUNCHER, new RocketLauncher(bulletList));
		currentWeapon = weapons.get(ObjectName.PISTOL);
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		if (obj instanceof Enemy) {
			this.getHit(((Enemy) obj).getTouchDamage());
		}
		if (obj instanceof Item) {
			if(obj.getName().equals(ObjectName.LASER_PISTOL)){
				this.changeWeapon(obj.getName());
				CharacterProgress.setPistolUnlocked(true);
			}
			else if(obj.getName().equals(ObjectName.ROCKET_LAUNCHER)){
				this.changeWeapon(obj.getName());
				CharacterProgress.setRpgUnlocked(true);
			}
			else if(obj.getName().equals(ObjectName.SHOTGUN)){
				this.changeWeapon(obj.getName());
				CharacterProgress.setShotgunUnlocked(true);
			}
			
			else if(obj.getName().equals(ObjectName.HEALTH_PACK))
				addHealth();
			
			else if(obj.getName().equals(ObjectName.UPGRADE_POINT))
				upgradePoints++;
		}
	}
	public void updateImmortality(){
		
		if(lastAttackedTime + Constants.CHARACTER_IMMORTALITY_TIME < System.currentTimeMillis()){
			immortal = false;
			lastAttackedTime = 0;
		}
	}
	
	@Override
	public void applyGravity(double delta) {
		// Apply gravity to the character if he is in the air, in other words,
		// not standing on the ground.
		if (airborne)
			super.applyGravity(delta);
	}

	@Override
	public void move(double delta) {
		if (isDashing)
			dash(delta);
		
		super.move(delta);
	}

	/**
	 * Returns a reference to the weapon that the character is currently wielding.
	 * @return A reference to the weapon that the character is currently wielding.
	 */
	public IWeapon getWeapon() {
		return currentWeapon;
	}
	
//	public void changeWeapon(String weaponName) {
//		if (CharacterProgress.isUpgradeUnlocked(weaponName)){
//			currentWeapon = weapons.get(weaponName);
//		}
//	}
	
	public void changeWeapon(String weaponName) {
		if (ObjectName.ROCKET_LAUNCHER.equals(weaponName) &&
				CharacterProgress.isRPGUnlocked()) {
			currentWeapon = weapons.get(weaponName);
			
		} else if (ObjectName.PISTOL.equals(weaponName) &&
				CharacterProgress.isPistolUnlocked()) {
			currentWeapon = weapons.get(weaponName);
			
		} else if (ObjectName.SHOTGUN.equals(weaponName) &&
				CharacterProgress.isShotgunUnlocked()) {
			currentWeapon = weapons.get(weaponName);
		}
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
	 * Make the character attack (i.e. shoot with his gun.
	 */
	public void attack() {
		if (lastAttacktime + currentWeapon.getCooldown() < System.currentTimeMillis()) {
			currentWeapon.shoot(this.getX(), this.getY(), this.isFacingRight());
			lastAttacktime = System.currentTimeMillis(); 
		}
	}

	/**
	 * Dash with the character.
	 */
	public void dash() {
		if (canDash)
			isDashing = true;
	}

	/**
	 * Make the character perform the "dash" move.
	 */
	private void dash(double delta) {
		distanceDashed += Constants.DASHSPEED * delta;

		if (this.isFacingRight())
			this.setVelocityX(Constants.DASHSPEED);
		else
			this.setVelocityX(-Constants.DASHSPEED);
		
		if (distanceDashed > Constants.DASHLENGTH) {
			isDashing = false;
			distanceDashed = 0;
		}
	}
	/**
	 * method called when the character is hit by an enemy
	 */
	public void getHit(int dmg) {
		if(immortal){
			// TODO get knocked back?
		}else{
			this.damage(dmg);
			immortal = true;
			lastAttackedTime = System.currentTimeMillis();
		}
	}

	/**
	 * If the character is dashing.
	 * @return If the character is dashing.
	 */
	public boolean isDashing() {
		return isDashing;
	}

	public int getUpgradePoints() {
		return upgradePoints;
	}
	
	/**
	 * returns true if the character should no longer be taking damage
	 * @return true if the character should no longer be taking damage
	 */
	public boolean isImmortal() {
		return immortal;
	}
	
	public void setMortality(boolean isImmortal){
		this.immortal = isImmortal;
	}
	
}