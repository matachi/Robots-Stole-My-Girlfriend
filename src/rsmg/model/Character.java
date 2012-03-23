package rsmg.model;

public class Character extends InteractiveObject {
	
	public Character(double x, double y) {
		super(x, y, Constants.CHARACTERWIDTH, Constants.CHARACTERHEIGHT);
	}
	
	@Override
	public void collide(InteractiveObject obj) {
		// TODO Auto-generated method stub
		
	}

}