package rsmg.model.object.unit;

import rsmg.model.ObjectName;

public class Tankbot extends Enemy{
	
	private static int HEALTH = 20;
	
	public Tankbot(double x, double y) {
		super(x, y, 26, 13, HEALTH, ObjectName.TANKBOT);
	}
	public int getTouchDamage(){
		return 10;
	}
}
