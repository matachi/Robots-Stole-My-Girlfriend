package rsmg.util;

public class Vector2d {
	private double x;
	private double y;
	
	public Vector2d(){
		x = 0;
		y = 0;
	}
	
	public Vector2d(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void add(Vector2d vector){
		x+=vector.getX();
		y+=vector.getY();
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
}
