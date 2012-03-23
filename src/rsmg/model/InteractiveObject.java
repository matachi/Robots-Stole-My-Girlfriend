package rsmg.model;
import rsmg.util.Vector2d;

/**
 * Class which represents an object that can interact with the main character
 * 	and has the possibility of being moved around  
 * @author Johan Grönvall
 *
 */
public abstract class InteractiveObject {
	private double x;
	private double y;
	private double height;
	private double width;
	private Vector2d velocity;
	
	public InteractiveObject(double x, double y){
		this.x=x;
		this.y=y;
	}
	public InteractiveObject(double x, double y, double width, double height ){
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
	}
	
	public Vector2d getVector(){
		return velocity;
	}
	
	public double getVelocity(){
		return velocity.getlength();
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getHeight(){
		return height;
	}
	
	public double getWidth(){
		return width;
	}
	
	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}
	
	
	/**
	 * 
	 * @param obj
	 * @return true if the two InteractiveObjects have collided, otherwise it returns false
	 */
	public boolean hasCollidedWith(InteractiveObject obj){
		if (obj == null){
			return false;
		}
		else{
			return (((this.getX() - obj.getX() < (obj.getWidth()) && (this.getX() > obj.getX()) &&
					((this.getY() - obj.getY()) < obj.getHeight()) && (this.getY() > obj.getY()) ) ||
					((obj.getX() - this.getX() < this.getWidth()) && (obj.getX() > this.getX()) &&
						(obj.getY() - this.getY() < this.getHeight()) && (obj.getY() > this.getY()))));
		}
	}
	/**
	 * class for specifying what happens when this objects collides with another InteractiveObject
	 */
	public abstract void collide(InteractiveObject obj);
	
}
