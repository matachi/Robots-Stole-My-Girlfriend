package rsmg.model.object.unit;

public interface IWeapon {
	/**
	 * method in which this weapon creates a Bullet
	 */
	public void shoot();
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
///**
// * likely not going to be implemented
// * @return
// */
//public int getClipSize();
///**
// * 
// * @return
// */
//public long getReloadTime();
	/**
	 * retrns the name of this gun
	 * @return name of the gun as a String
	 */
	public String getName();
}
