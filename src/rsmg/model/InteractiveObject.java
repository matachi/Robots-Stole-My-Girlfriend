package rsmg.model;
import rsmg.util.Vector2d;

/**
 * Class which represents an object that can interact with the main character
 * and has the possibility of being moved around.  
 * @author Johan Grönvall
 *
 */
public abstract class InteractiveObject {
	private double previousY = 0;
	private double previousX = 0;
	private double x;
	private double y;
	private double height;
	private double width;
	private String name;
	
	/**
	 * Store the velocity as a 2d vector.
	 */
	private Vector2d velocity;
	
	protected InteractiveObject(double x, double y, String name) {
		this(x, y, 0, 0, name);
	}
	protected InteractiveObject(double x, double y, double width, double height, String name) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.velocity = new Vector2d();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Get the Object's velocity.
	 * @return The velocity.
	 */
	public double getVelocity() {
		return velocity.getlength();
	}

	public double getVelocityX() {
		return velocity.getX();
	}

	public double getVelocityY() {
		return velocity.getY();
	}
	
	public void setVelocity(Vector2d velocity) {
		this.velocity=velocity;
	}
	
	public void setVelocityX(double x) {
		velocity.setX(x);
	}
	
	public void setVelocityY(double y) {
		velocity.setY(y);
	}
	
	public void addVelocity(Vector2d vector) {
		velocity.add(vector);
	}
	
	public void addVelocity(double x, double y) {
		velocity.add(x, y);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	public double getPX(){
		return previousX;
	}
	public double getPY(){
		return previousY;
	}
	
	/**
	 * Changes the position of the InteractiveObject based on its current Vector.
	 * a vector with the strength of 1 is equivalent to a speed of 1pixel/s
	 * @param delta, time since last update
	 */
	public void move(double delta) {
		previousX = x;
		previousY = y;
		x += getVelocityX() * delta;
		y += getVelocityY() * delta;
	}

	/**
	 * applies a gravity vector to this InteractiveObject 
	 * @param delta
	 */
	public void applyGravity(double delta) {
		addVelocity(0, Constants.GRAVITYSTRENGTH*delta);
	}
	
	/**
	 * Checks if the two objects have collided with each other.
	 * @param obj
	 * @return true if the two InteractiveObjects have collided, otherwise it returns false
	 */
	public boolean hasCollidedWith(InteractiveObject obj){
		if (obj == null) {
			return false;
		} else {
//			if (verticalCollision(obj)) {
//				return horizontalCollision(obj);
//			}
			return (((this.getX() - obj.getX() < (obj.getWidth()) && (this.getX() > obj.getX()) &&
					((this.getY() - obj.getY()) < obj.getHeight()) && (this.getY() > obj.getY()) ) ||
					((obj.getX() - this.getX() < this.getWidth()) && (obj.getX() > this.getX()) &&
						(obj.getY() - this.getY() < this.getHeight()) && (obj.getY() > this.getY()))));
		}
	}
	
//	private boolean verticalCollision(InteractiveObject obj) {
//		return (getX() - obj.getX() < obj.getWidth() && getX() > obj.getX()) ||
//			   (obj.getX() - getX() < getWidth() && obj.getX() > getX());
//	}
//	
//	private boolean horizontalCollision(InteractiveObject obj) {
//		return (getY() - obj.getY() < obj.getHeight() && getY() < obj.getY()) ||
//			   (obj.getY() - getY() < getHeight()) && (obj.getY() < getY());
//	}
	/**
	 * 
	 * @return true if object did not move horizontally last time
	 *  the move() function was called
	 */
	public boolean isStandingStill() {
		return getX() == getPX();
	}
	/**
	 * 
	 * @return true if the objects X coordinate is lower now than
	 *  it was last time the move() function was called
	 */
	public boolean isMovingLeft() {
		return getX() < getPX();
	}
	/**
	 * 
	 * @return true if the objects X coordinate is higher now than
	 *  it was last time the move() function was called
	 */
	public boolean isMovingRight() {
		return getX() > getPX();
	}
	
	
	/**
	 * method for specifying what happens to this object when it collides with another InteractiveObject
	 */
	public abstract void collide(InteractiveObject obj);
}
