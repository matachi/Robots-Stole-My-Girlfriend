package rsmg.model.object.unit.weapon;

import rsmg.util.Vector2d;
/**
 * Interface for classes representing Weapons
 * weapons are wielded by LivingObjects and
 * creates bullets
 * @author Johan Gronvall
 *
 */
public interface Weapon {
	/**
	 * Method in which this weapon creates a bullet
	 * @param x horizontal coordinate for the LivingObject holding the gun
	 * @param y vertical coordinate for the LivingOBject holding the gun
	 * @param facingRight boolean informing where the LivingObject is facing
	 */
	public void shoot(double x, double y, boolean facingRight);
	/**
	 * returns the time interval of how often this weapon can be shot
	 * @return the time interval of how often this weapon can be shot
	 */
	public long getCooldown();
	
	/**
	 * Returns true if the weapon has been fired and then resets it back to false.
	 * @return If the weapon has been fired.
	 */
	public boolean shot();

	/**
	 * returns the vector of how much the wield should be pushed back when firing the weapon
	 * @param isFacingRight
	 * @return
	 */
	public Vector2d getKnockback(boolean isFacingRight);
	
	/**
	 * retrns the name of this gun
	 * @return name of the gun as a String
	 */
	public String getName();
}
