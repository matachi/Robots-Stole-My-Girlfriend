package rsmg.model.object.bullet;

public interface Projectile {
	/**
	 * method consistently making it possible for bullets to accelerate / update
	 */
	public void update(double delta);
	
	/**
	 * returns how much damage this bullet deals
	 * @return how much damage this bullet deals
	 */
	public int getDamage();
	/**
	 * Returns true if this bullet should be treated like an explosion
	 * in other words, the bullet deals damage in ticks, rather than only dealing damage once
	 * @return
	 */
	public boolean isExplosion();
}
