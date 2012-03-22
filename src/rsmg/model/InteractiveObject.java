package rsmg.model;

import java.util.Vector;
/**
 * Class which represents an object that can interact with the main character
 * 	and has the possibility of being moved around  
 * @author Zap
 *
 */
public abstract class InteractiveObject {
	double x;
	double y;
	double height;
	double width;
	Vector velocity;
	public InteractiveObject(double x, double y){
		
	}
}
