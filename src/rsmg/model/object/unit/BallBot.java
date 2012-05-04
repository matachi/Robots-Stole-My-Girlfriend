package rsmg.model.object.unit;

import java.util.List;

import rsmg.model.ObjectName;
import rsmg.model.ai.Ai;
import rsmg.model.ai.RocketBotAi;

public class BallBot extends Enemy{
	private List<Ai> enemyAiList;
	/**
	 * @param x the horizontal coordinate of this BallBot
	 * @param y the vertical coordinate of this BallBot
	 * @param enemyAiList the list of enemies that are harmful to the character
	 */
	public BallBot(double x, double y, List<Ai> enemyAiList) {
		super(x, y, 40, 40, 60, ObjectName.BALLBOT);
		this.enemyAiList = enemyAiList;
	}

	@Override
	public int getTouchDamage() {
		return 30;
	}

	@Override
	public boolean isFlyingUnit() {
		return true;
	}

	public void spawnEnemy() {
		enemyAiList.add(new RocketBotAi(new MiniBallBot(getX(), getY())));
	}
}
