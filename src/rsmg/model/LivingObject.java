package rsmg.model;


public abstract class LivingObject extends InteractiveObject{
	
	boolean facingRight;
	int health;
	
	public LivingObject(double x, double y, double width, double height, int health, String name) {
		super(x, y, width, height, name);
		this.health=health;
		facingRight = true;
		
	}

	public int getHealth() {
		return health;
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
