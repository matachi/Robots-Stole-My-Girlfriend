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
	
	public Vector2d getVelocity(){
		return velocity;
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
	
}
