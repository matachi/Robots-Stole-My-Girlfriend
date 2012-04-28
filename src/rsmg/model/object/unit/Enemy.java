package rsmg.model.object.unit;

import rsmg.model.Constants;
import rsmg.model.ObjectName;
import rsmg.model.object.InteractiveObject;
import rsmg.model.object.bullet.Bullet;

public abstract class Enemy extends LivingObject{
	
	private long lastAttackedTime;
	private boolean vulnerableToExplosions = true;
	/**
	 * creates an enemy
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 * @param width width of the enemy hitbox
	 * @param height height of the enemy hitbox
	 * @param health health of the enemy
	 * @param name the enemy name
	 */
	public Enemy(double x, double y, double width, double height, int health, String name) {
		super(x, y, width, height, health, name);
	}
	@Override
	public void collide(InteractiveObject obj) {
		//the enemy should take damage from bullets as long as the bullet isn't an explosion and
		// the enemy isn't invulnerable to explosions
		
		if (obj instanceof Bullet) {
			if(obj.getName()==ObjectName.EXPLOSION && !vulnerableToExplosions) {
				//do nothing
			}else {
				lastAttackedTime = System.currentTimeMillis();
				vulnerableToExplosions = false;
				this.damage(((Bullet)obj).getDamage());
				
			}
		}
	}
	public abstract int getTouchDamage();
	
	public void updateVulnerability() {
		if(lastAttackedTime + Constants.EXPLOSION_TICK < System.currentTimeMillis()){
			vulnerableToExplosions = true;
		}
	}
	/**
	 * returns true if this Enemy can take damage from explosions
	 * @return true if this Enemy can take damage from explosions
	 */
	public boolean isVulnerabletoExplosions(){
		return vulnerableToExplosions;
	}
	/**
	 * returns true if enemy recently took damage
	 * @return
	 */
	public boolean recentlytookDamage() {
		return (lastAttackedTime + Constants.ENEMY_FLASHDURATION > System.currentTimeMillis());
	}
}
