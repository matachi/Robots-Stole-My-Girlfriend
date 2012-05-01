package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;
/**
 * An Ai that doesn't do anything
 * @author Johan Grönvall
 *
 */
public class EmptyAi implements Ai{
	private Enemy enemy;
	
	public EmptyAi(Enemy enemy){
		this.enemy = enemy;
	}
	
	@Override
	public void update(double delta) {
		//nothing
	}

	@Override
	public Enemy getEnemy() {
		return enemy;
	}
	
}
