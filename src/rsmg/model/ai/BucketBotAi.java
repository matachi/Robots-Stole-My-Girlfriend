package rsmg.model.ai;

import org.lwjgl.Sys;

import rsmg.model.object.unit.BucketBot;
import rsmg.model.object.unit.Enemy;

public class BucketBotAi implements Ai{
	
	private BucketBot enemy;
	
	public BucketBotAi(BucketBot enemy) {
		this.enemy = enemy;
	}

	@Override
	public void update(double delta, double playerX, double pLayerY) {
		if (playerX < enemy.getX()){
			enemy.setFacing(false);
		} else { // if playerX >= enemy.getX()
			enemy.setFacing(true);
		}
		
		if (Sys.getTime() % 800 == 0){
			enemy.shoot();
		}
		
	}

	@Override
	public Enemy getEnemy() {
		return enemy;
	}

}
