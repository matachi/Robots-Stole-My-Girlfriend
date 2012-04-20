package rsmg.model.object.unit;

public interface IWeapon {
	public void shoot();

	public long getCooldown();
	
	/**
	 * Returns if the weapon has been fired and then resets it back to false.
	 * @return If the weapon has been fired.
	 */
	public boolean shot();
	public int getClipSize();
	public long getReloadTime();
}
