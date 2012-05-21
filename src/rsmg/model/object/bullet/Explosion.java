package rsmg.model.object.bullet;

/**
 * Interface for the explosions.
 * @author Daniel Jonsson
 *
 */
public interface Explosion {

	/**
	 * Returns the age of the explosion. I.e. the time it has existed in
	 * seconds.
	 * 
	 * @return The explosion's age in seconds.
	 */
	public double getAge();
}
