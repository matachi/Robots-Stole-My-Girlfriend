package rsmg.model.object;

import rsmg.model.variables.Constants;
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
	
	/**
	 * Constructor of InteractiveObject
	 * @param x The x position of the InteractiveObject
	 * @param y The y position of the InteractiveObject
	 * @param name The name of the InteractiveObject
	 */
	protected InteractiveObject(double x, double y, String name) {
		this(x, y, 0, 0, name);
	}
	
	/**
	 * Constructor of InteractiveObject
	 * @param x The x position of the InteractiveObject
	 * @param y The y position of the InteractiveObject
	 * @param width The width of the InteractiveObject
	 * @param height The height of the InteractiveObject
	 * @param name The name of the InteractiveObject
	 */
	protected InteractiveObject(double x, double y, double width, double height, String name) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.velocity = new Vector2d();
		this.name = name;
	}
	
	/**
	 * Get the name for the InteractiveObject
	 * @return The name
	 */
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

	/**
	 * Get the objects velocity in x-axis
	 * @return The velocity
	 */
	public double getVelocityX() {
		return velocity.getX();
	}

	/**
	 * Get the objects velocity in y-axis
	 * @return The velocity
	 */
	public double getVelocityY() {
		return velocity.getY();
	}
	
	/**
	 * Set the velocity of the object
	 * @param velocity The velocity
	 */
	public void setVelocity(Vector2d velocity) {
		this.velocity=velocity;
	}
	
	/**
	 * Set the velocity in x-axis
	 * @param x The velocity
	 */
	public void setVelocityX(double x) {
		velocity.setX(x);
	}
	
	/**
	 * Set the velocity in y-axis
	 * @param y The velocity
	 */
	public void setVelocityY(double y) {
		velocity.setY(y);
	}
	
	/**
	 * Add velocity to the object
	 * @param vector velocity to add
	 */
	public void addVelocity(Vector2d vector) {
		velocity.add(vector);
	}
	
	/**
	 * Add velocity to the object
	 * @param x The velocity to add in x-axis
	 * @param y The velocity to add in y-axis
	 */
	public void addVelocity(double x, double y) {
		velocity.add(x, y);
	}

	/**
	 * Gets the height of the object
	 * @return The height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Get the width of the object
	 * @return The width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Get the X position of the object
	 * @return The x position
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the y position of the object
	 * @return The y position
	 */
	public double getY() {
		return y;
	}

	/**
	 * Set the x position of the object
	 * @param x The x position to be set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Set the y position of the object
	 * @param y The y position to be set
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Set the postion of the object
	 * @param x The x postion to be set
	 * @param y The y postion to be set
	 */
	public void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * Get the previous x position of the object
	 * @return The previous x position
	 */
	public double getPX(){
		return previousX;
	}
	
	/**
	 * Get the previous y position of the object
	 * @return The previous y position
	 */
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
	 * Applies a gravity vector to this InteractiveObject 
	 * @param delta
	 */
	public void applyGravity(double delta) {
		addVelocity(0, Constants.GRAVITYSTRENGTH*delta);
	}
	
	/**
	 * Checks if the given Interactive object have collided with this. Dose not check for collision in same plane(to optimize performance)
	 * @param obj is the object that intersect this one
	 * @return true if the two InteractiveObjects have collided, otherwise it returns false
	 */
	public boolean hasCollidedWith(InteractiveObject obj){
		if (obj == null) {
			return false;
		} else {
			return (leftCollision(obj) || rightCollision(obj));
		}
	}
	
	/**
	 * Check if the object have collided from the left side
	 * @param obj is the object that intersect this one
	 * @return true if the two InteractiveObjects have collided from left side, otherwise it returns false
	 */
	private boolean leftCollision(InteractiveObject obj){
		return leftUpCollision(obj) || leftDownCollision(obj);
	}
	
	/**
	 * Check if the object have collided from the right side
	 * @param obj is the object that intersect this one
	 * @return true if the two InteractiveObjects have collided from right side, otherwise it returns false
	 */
	private boolean rightCollision(InteractiveObject obj){
		return rightUpCollision(obj) || rightDownCollision(obj);
	}
	
	
	/**
	 * Checks if the object have collided from left-upper side
	 * @param obj is the object that intersect this one
	 * @return true if the two InteractiveObjects have collided from left-upper side, otherwise it returns false
	 */
	private boolean leftUpCollision(InteractiveObject obj){
		return (((this.getX() - obj.getX()) < obj.getWidth()) && (this.getX() > obj.getX()) &&
				((this.getY() - obj.getY()) < obj.getHeight()) && (this.getY() > obj.getY()));
	}
	
	/**
	 * Checks if the object have collided from left-bottom side
	 * @param obj is the object that intersect this one
	 * @return true if the two InteractiveObjects have collided from left-bottom side, otherwise it returns false
	 */
	private boolean leftDownCollision(InteractiveObject obj){
		return (((this.getX() - obj.getX()) < obj.getWidth()) && (this.getX() > obj.getX()) &&
				((obj.getY() - this.getY()) < this.getHeight()) && (obj.getY() > this.getY()));
	}
	
	/**
	 * Checks if the object have collided from right-upper side
	 * @param obj is the object that intersect this one
	 * @return true if the two InteractiveObjects have collided from right-upper side, otherwise it returns false
	 */
	private boolean rightUpCollision(InteractiveObject obj){
		return (((obj.getX() - this.getX()) < this.getWidth()) && (obj.getX() > this.getX()) &&
				((this.getY() - obj.getY()) < obj.getHeight()) && (this.getY() > obj.getY()));
	}
	
	/**
	 * Checks if the object have collided from right-bottom side
	 * @param obj is the object that intersect this one
	 * @return true if the two InteractiveObjects have collided from right-bottom side, otherwise it returns false
	 */
	private boolean rightDownCollision(InteractiveObject obj){
		return ((obj.getX() - this.getX() < this.getWidth()) && (obj.getX() > this.getX()) &&
				(obj.getY() - this.getY() < this.getHeight()) && (obj.getY() > this.getY()));
	}
	
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
	 * Method for specifying what happens to this object when it collides with another InteractiveObject
	 */
	public abstract void collide(InteractiveObject obj);
}
