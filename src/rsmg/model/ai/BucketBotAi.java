package rsmg.model.ai;

import java.util.List;

import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.unit.Enemy;

public class BucketBotAi implements Ai{
	
	private Enemy enemy;
	
	public BucketBotAi(Enemy enemy, List<Bullet> bulletList) {
		this.enemy = enemy;
	}

	@Override
	public void update(double delta, double playerX, double pLayerY) {
		if (playerX < enemy.getX()){
			enemy.setFacing(false);
		} else { // if playerX >= enemy.getX()
			enemy.setFacing(true);
		}
	}

	@Override
	public Enemy getEnemy() {
		return enemy;
	}

}
