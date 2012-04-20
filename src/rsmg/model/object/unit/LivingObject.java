package rsmg.model.object.unit;

import rsmg.model.object.InteractiveObject;

public abstract class LivingObject extends InteractiveObject{
	
	private boolean facingRight;
	private int health;
	private int maxHealth;
	
	public LivingObject(double x, double y, double width, double height, int health, String name) {
		super(x, y, width, height, name);
		this.health = maxHealth = health;
		facingRight = true;
	}

	/**
	 * @return health for the LivingObject
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * 
	 * @return maxHealth for the LivingObject
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * @param dmg the LivingObject will take
	 */
	public void damage(int dmg) {
		health = health-dmg;
	}
	
	/**
	 * Give the LivingObject more health
	 */
	public void addHealth(){
//		health += 10;
//		if(health > maxHealth)
			health = maxHealth;
	}
	
	/**
	 * Update facingRight variable to proper value
	 */
	public void updateFacing(){
		if (isMovingLeft()){
			facingRight = false;
		}else if (isMovingRight()){
			facingRight = true;
		}
	}
	
	/**
	 * @return if the LivingObject is facing right
	 */
	public boolean isFacingRight(){
		return facingRight;
	}
	
	/**
	 * @return if the LivingObject is dead
	 */
	public boolean isDead(){
		return health < 1;
	}
}
