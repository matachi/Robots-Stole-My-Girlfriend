package rsmg.model.object.livingobject;

import rsmg.model.object.InteractiveObject;

public abstract class LivingObject extends InteractiveObject{
	
	boolean facingRight;
	int health;
	int maxHealth;
	
	public LivingObject(double x, double y, double width, double height, int health, String name) {
		super(x, y, width, height, name);
		this.health = maxHealth = health;
		facingRight = true;
		
	}

	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void damage(int dmg) {
		health = health-dmg;
	}
	public void updateFacing(){
		if (isMovingLeft()){
			facingRight = false;
		}else if (isMovingRight()){
			facingRight = true;
			
		}
	}
	
	public boolean isFacingRight(){
		return facingRight;
	}
	
	public boolean isDead(){
		return health < 1;
	}
	
}
