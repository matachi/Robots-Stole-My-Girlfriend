package rsmg.model.ai;

import rsmg.model.object.unit.Enemy;

public interface Ai {
	public void update(double delta);
	public Enemy getEnemy();
}
