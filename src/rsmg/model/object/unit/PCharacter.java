package rsmg.model.object.unit;

import java.util.Collection;
import java.util.EnumMap;

import rsmg.io.CharacterProgress;
import rsmg.model.object.InteractiveObject;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.item.Item;
import rsmg.model.object.unit.weapon.LaserPistol;
import rsmg.model.object.unit.weapon.Pistol;
import rsmg.model.object.unit.weapon.RocketLauncher;
import rsmg.model.object.unit.weapon.Shotgun;
import rsmg.model.object.unit.weapon.Weapon;
import rsmg.model.variables.Constants;
import rsmg.model.variables.ObjectName;

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
	private EnumMap<ObjectName, Weapon> weapons;
	
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
	 * 
	 * @param bulletList Reference to the level's bullet list where the character's bullets should be stored.
	 */
	public PCharacter(Collection<Bullet> bulletList) {
		this(0, 0, bulletList);
	}
	
	/**
	 * Create a character that the player controls.
	 * 
	 * @param x The character's X position.
	 * @param y The character's Y position.
	 * @param bulletList Reference to the level's bullet list where the character's bullets should be stored.
	 */
	public PCharacter(double x, double y, Collection<Bullet> bulletList) {
		super(x, y, Constants.CHARACTERWIDTH, Constants.CHARACTERHEIGHT, Constants.CHARACTERHEALTH, ObjectName.CHARACTER);
		canDash = CharacterProgress.isDashUnlocked();
		weapons = new EnumMap<ObjectName, Weapon>(ObjectName.class);
		weapons.put(ObjectName.PISTOL, new Pistol(bulletList));
		weapons.put(ObjectName.SHOTGUN, new Shotgun(bulletList));
		weapons.put(ObjectName.ROCKET_LAUNCHER, new RocketLauncher(bulletList));
		weapons.put(ObjectName.LASER_PISTOL, new LaserPistol(bulletList));
		currentWeapon = weapons.get(ObjectName.PISTOL);
		
		if (CharacterProgress.isIncRunningSpeedUnlocked())
			maxRunningSpeed = Constants.CHAR_SPEED_UPG;
		else
			maxRunningSpeed = Constants.CHAR_SPEED;
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
	
	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * updates whether the character can take damage
	 */
	private void updateImmortality() {
		
		if(lastAttackedTime + Constants.CHARACTER_IMMORTALITY_TIME < System.currentTimeMillis()){
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
	/**
	 * sets whether the character can currently take damage 
	 * @param isImmortal
	 */
	public void setMortality(boolean isImmortal){
		this.immortal = isImmortal;
	}
	
	/**
	 * Method which accelerates up the character in his current direction
	 * @param delta
	 */
	public void accelerate(double delta) {
		if (directionEast) {
			this.addVelocity(Constants.CHARACTER_ACCELERATION*delta, 0);
		}else if(directionWest) {
			this.addVelocity(-Constants.CHARACTER_ACCELERATION*delta, 0);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */	
	@Override
	public void applyGravity(double delta) {
		// Apply gravity to the character if he is in the air, in other words,
		// not standing on the ground.
		if (airborne)
			super.applyGravity(delta);
	}
	
	/**
	 * {@inheritDoc}
	 */
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
	
	public void changeWeapon(ObjectName enum1) {
		if (ObjectName.ROCKET_LAUNCHER.equals(enum1) &&
				CharacterProgress.isRPGUnlocked()) {
			currentWeapon = weapons.get(enum1);
			
		} else if (ObjectName.PISTOL.equals(enum1)) {
			currentWeapon = weapons.get(enum1);
			
		} else if (ObjectName.SHOTGUN.equals(enum1) && 
				CharacterProgress.isShotgunUnlocked()) {
			currentWeapon = weapons.get(enum1);
				
		} else if (ObjectName.LASER_PISTOL.equals(enum1)) {
				CharacterProgress.isPistolUnlocked();
			currentWeapon = weapons.get(enum1);
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
		if (!isDead()) {
			// Only jump if the character is standing on the ground.
			if (!airborne){
				this.addVelocity(0, -Constants.JUMPSTRENGTH);
				
			} else if (doubleJumpAvailable()){
				this.setVelocityY(-Constants.DOUBLEJUMPSTRENGTH);
				doubleJumps -= 1;
			}
		}
	}

	/**
	 * Changes the character's velocity, moving him westwards in next loop.
	 */
	public void moveLeft() {
		if (!isDead()) {
			if (this.getVelocityX()*(-1) < maxRunningSpeed || this.isFacingRight()) {
				directionWest = true;
			}
			setFacing(false);
			runningLeft = true;
		}
	}

	/**
	 * Changes the character's velocity, moving him eastwards in next loop.
	 */
	public void moveRight() {
		if (!isDead()) {
			if (this.getVelocityX() < maxRunningSpeed || !this.isFacingRight()) {
				directionEast = true;
			}
			runningRight = true;
			setFacing(true);
		}
	}
	
	
	/**
	 * returns true if the character has double jumps to use
	 * @return
	 */
	public boolean doubleJumpAvailable() {
		return doubleJumps > 0 && CharacterProgress.isDoubleJumpUnlocked();
		
	}
	
	/**
	 * called whe nthe character no longer tries to move right
	 */
	public void moveRightReleased() {
		runningRight = false;
	}
	
	/**
	 * called when the character no longer tries to move left
	 */
	public void moveLeftReleased() {
		runningLeft = false;
	}

	/**
	 * Called when the character no longer tries to go higher when he is jumping.
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
			this.addVelocity(currentWeapon.getKnockback(isFacingRight()));
			lastAttacktime = System.currentTimeMillis(); 
		}
	}

	/**
	 * Dash with the character.
	 */
	public void dash() {
		if (canDash && !(recentlyDashed()) && !isDead())
			isDashing = true;
	}

	/**
	 * Make the character perform the "dash" move.
	 */
	private void performDash(double delta) {
		distanceDashed += Constants.DASHSPEED * delta;
		if (this.isFacingRight())
			this.setVelocityX(Constants.DASHSPEED);
		else
			this.setVelocityX(-Constants.DASHSPEED);
	
		if (distanceDashed > Constants.DASHLENGTH) {
			isDashing = false;
			distanceDashed = 0;
		}
		lastDashed = System.currentTimeMillis();
	}
	
	private boolean recentlyDashed() {
		return lastDashed + Constants.DASHCOOLDOWN > System.currentTimeMillis();
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

	/**
	 * returns how many upgrade points this character has in store
	 * @return
	 */
	public int getUpgradePoints() {
		return upgradePoints;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateFacing(){
		if (directionWest){
			setFacing(false);
		}else if (directionEast){
			setFacing(true);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
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
			friction = Constants.AIR_FRICTION;
		} else {
			friction = Constants.GROUND_FRICTION;
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

	private void setDirections(boolean newDirection) {
		directionEast = newDirection;
		directionWest = newDirection;
	}
	
	/**
	 * returns true of the character is running
	 * (running doesn't nessescarily mean moving
	 * but rather if the character is trying to move
	 * anywhere) 
	 * @return true if the character is running
	 */
	public boolean isRunning() {
		return runningRight || runningLeft;
	}
	
	/**
	 * returns how far the character has dashed since the last time
	 * the dash move was initiated
	 * @return
	 */
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