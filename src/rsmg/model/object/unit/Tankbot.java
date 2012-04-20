package rsmg.model.object.unit;

import rsmg.model.ObjectName;

public class Tankbot extends Enemy{
	
	public Tankbot(double x, double y) {
		super(x, y, 26, 13, 1, ObjectName.TANKBOT);
	}
	public int getTouchDamage(){
		return 10;
	}
	//TODO Ai here?
}
