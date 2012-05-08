package rsmg.model.object.unit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import rsmg.io.CharacterProgress;
import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.item.Item;
import rsmg.model.object.unit.weapon.Pistol;
import rsmg.model.object.unit.weapon.RocketLauncher;
import rsmg.model.object.unit.weapon.Shotgun;
import rsmg.model.object.unit.weapon.Weapon;
import rsmg.model.variables.Variables;

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
	private Weapon currentWeapon;
	
	/**
	 * List of references to the character's weapons.
	 */
	private Map<String, Weapon> weapons;
	
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
	 * Keeps track of when the character last Dashed
	 */
	private long lastDashed = 0;
	
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
	 * The character's speed limit.
	 */
	private final int maxRunningSpeed;
	
	/**
	 *  upgradePoints the user can spend after each level
	 */
	private int upgradePoints = 0;
	
	/**
	 * how many times the character can jump while in the air
	 */
	private int doubleJumps = 1;
	
	/**
	 * boolean value describing if the character is trying to run East or not
	 */
	private boolean directionEast = false;
	
	/**
	 * boolean value describing if the character is trying to run west or not
	 */
	private boolean directionWest = false;
	
	private boolean runningRight = false;
	private boolean runningLeft = false;
	
	
	/**
	 * Create a character that the player controls.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 * @param bulletList Reference to the level's bullet list where the character's bullets should be stored.
	 */
	public PCharacter(double x, double y, Collection<Bullet> bulletList) {
		super(x, y, Variables.CHARACTERWIDTH, Variables.CHARACTERHEIGHT, Variables.CHARACTERHEALTH, ObjectName.CHARACTER);
		canDash = CharacterProgress.isDashUnlocked();
		weapons = new HashMap<String, Weapon>();
		weapons.put(ObjectName.PISTOL, new Pistol(bulletList));
		weapons.put(ObjectName.SHOTGUN, new Shotgun(bulletList));
		weapons.put(ObjectName.ROCKET_LAUNCHER, new RocketLauncher(bulletList));
		currentWeapon = weapons.get(ObjectName.PISTOL);
		
		if (CharacterProgress.isIncRunningSpeedUnlocked())
			maxRunningSpeed = Variables.CHAR_SPEED_UPG;
		else
			maxRunningSpeed = Variables.CHAR_SPEED;
	}
	
	/**
	 * Update the character. This should be called every frame.
	 * 
	 * @param delta Time since last frame.
	 * @param airborne If the character is in the air/airborne.
	 */
	public void update(double delta, boolean airborne) {
		
		accelerate(delta);
		setDirections(false);
		// Update whether the character is in the air or standing on the ground.
		setAirborne(airborne);
		// Apply gravity to the character so he will fall down if he is in the air.
		applyGravity(delta);
		
		// Move the character.
		move(delta);
		
		applyFriction(delta);
		updateImmortality();
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		if (obj instanceof Enemy) {
			if(!isDashing()){
				this.getHit(((Enemy) obj).getTouchDamage());
			}
		}
		if (obj instanceof Bullet) {
			if(isDashing){
				isDashing = false;
			}
			this.getHit(((Bullet)obj).getDamage());
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
	
	public void updateImmortality() {
		
		if(lastAttackedTime + Variables.CHARACTER_IMMORTALITY_TIME < System.currentTimeMillis()){
			immortal = false;
			lastAttackedTime = 0;
		}
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
	
	/**
	 * Method which accelerates up the character in his current direction
	 * @param delta
	 */
	public void accelerate(double delta) {
		if (directionEast) {
			this.addVelocity(Variables.CHARACTER_ACCELERATION*delta, 0);
		}else if(directionWest) {
			this.addVelocity(-Variables.CHARACTER_ACCELERATION*delta, 0);
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
			performDash(delta);
		
		super.move(delta);
	}

	/**
	 * Returns a reference to the weapon that the character is currently wielding.
	 * @return A reference to the weapon that the character is currently wielding.
	 */
	public Weapon getWeapon() {
		return currentWeapon;
	}
	
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
		if(!airborne) {
			doubleJumps = 1;
		}
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
		if (!airborne){
			this.addVelocity(0, -Variables.JUMPSTRENGTH);
			
		} else if (doubleJumpAvailable()){
			this.setVelocityY(-Variables.DOUBLEJUMPSTRENGTH);
			doubleJumps -= 1;
		}
	}

	/**
	 * Changes the character's velocity, moving him westwards in next loop.
	 */
	public void moveLeft() {
		if (this.getVelocityX()*(-1) < maxRunningSpeed || this.isFacingRight())
			directionWest = true;
		
		setFacing(false);
		runningLeft = true;
	}

	/**
	 * Changes the character's velocity, moving him eastwards in next loop.
	 */
	public void moveRight() {
		if (this.getVelocityX() < maxRunningSpeed || !this.isFacingRight())
			directionEast = true;
		
		runningRight = true;
		setFacing(true);
	}
	
	
	/**
	 * returns true if the character has double jumps to use
	 * @return
	 */
	public boolean doubleJumpAvailable() {
		return doubleJumps > 0 && CharacterProgress.isDoubleJumpUnlocked();
		
	}
	
	public void moveRightReleased() {
		runningLeft = false;
	}
	
	public void moveLeftReleased() {
		runningRight = false;
	}

	/**
	 * Called when the character no longer tries to go higher when he is jumping.
	 */
	public void jumpReleased() {
		if (getVelocityY() < Variables.RELEASED_JUMP_VELOCITY)
			setVelocityY(Variables.RELEASED_JUMP_VELOCITY);
	}

	/**
	 * Make the character attack (i.e. shoot with his gun.
	 */
	public void attack() {
		if (lastAttacktime + currentWeapon.getCooldown() < System.currentTimeMillis()) {
			currentWeapon.shoot(this.getX(), this.getY(), this.isFacingRight());
			this.addVelocity(currentWeapon.getKnockback(isFacingRight()));
			lastAttacktime = System.currentTimeMillis(); 
		}
	}

	/**
	 * Dash with the character.
	 */
	public void dash() {
		if (canDash && !(recentlyDashed()))
			isDashing = true;
	}

	/**
	 * Make the character perform the "dash" move.
	 */
	private void performDash(double delta) {
		distanceDashed += Variables.DASHSPEED * delta;
		if (this.isFacingRight())
			this.setVelocityX(Variables.DASHSPEED);
		else
			this.setVelocityX(-Variables.DASHSPEED);
	
		if (distanceDashed > Variables.DASHLENGTH) {
			isDashing = false;
			distanceDashed = 0;
		}
		lastDashed = System.currentTimeMillis();
	}
	
	private boolean recentlyDashed() {
		return lastDashed + Variables.DASHCOOLDOWN > System.currentTimeMillis();
	}

	/**
	 * method called when the character is hit by an enemy
	 */
	public void getHit(int dmg) {
		if(!immortal) {
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
	@Override
	public void updateFacing(){
		if (directionWest){
			setFacing(false);
		}else if (directionEast){
			setFacing(true);
		}
	}
	@Override
	public void setFacing(boolean isFacingRight){
		if(!isDashing) {
			super.setFacing(isFacingRight);
		}
	}
	/**
	 * applies friction to the character, consistently retarding his  
	 * @param delta
	 */
	public void applyFriction(double delta) {
		double friction;
		if(airborne){
			friction = Variables.AIR_FRICTION;
		} else {
			friction = Variables.GROUND_FRICTION;
		}
		
		if (isMovingRight()) {
			this.setVelocityX(this.getVelocityX()-delta*friction);
			if(getVelocityX() < 0) {
				setVelocityX(0);
			}
		}else { //if Moving Left
			this.setVelocityX(this.getVelocityX()+delta*friction);
			if(getVelocityX() > 0) {
				setVelocityX(0);
			}
		}
	}

	public void setDirections(boolean newDirection) {
		directionEast = newDirection;
		directionWest = newDirection;
	}
	
	public boolean isRunning() {
		return runningRight || runningLeft;
	}
	
	public double getDistanceDashed(){
		return distanceDashed;
	}
	
	/**
	 * Returns how much the weapon has loaded.
	 * @return 0.0 if it's just fired. Then it loads up to 1.0.
	 */
	public float getWeaponLoadedPercentage() {
		float loaded = (System.currentTimeMillis() - lastAttacktime) / (float)currentWeapon.getCooldown();
		if (loaded > 1)
			return 1;
		else
			return loaded;
	}
}