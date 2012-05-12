package rsmg.model.ai;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.unit.BallBot;
import rsmg.model.object.unit.PCharacter;

/**
 * 
 * @author Johan Rignäs
 *
 */
public class TestBallBotAi {

	private BallBotAi ballBotAi;
	private List<Ai> enemyAiList;
	
	@Before
	public void before() {
		BallBot ballbot = new BallBot(10,10);
		enemyAiList = new LinkedList<Ai>();
		ballBotAi = new BallBotAi(ballbot, enemyAiList, new PCharacter(null));
	}
		
	// TODO
	// HARD TO TEST WHEN ENEMY IS MOVING!...
	@Test
	public void testUpdate() {
		double delta = 2;
		
		ballBotAi.update(delta);
		ballBotAi.update(delta);
		ballBotAi.update(delta);
		ballBotAi.update(delta);
		ballBotAi.update(delta);
		ballBotAi.update(delta);
		assertTrue(enemyAiList.size() == 3);
	}
		
	@Test
	public void testGetEnemy() {
		assertTrue(ballBotAi.getEnemy().getX() == 10);
		assertTrue(ballBotAi.getEnemy().getY() == 10);
	}
}
