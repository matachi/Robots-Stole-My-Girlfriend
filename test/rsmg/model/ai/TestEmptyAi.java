package rsmg.model.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.unit.BallBot;
import rsmg.model.object.unit.Enemy;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class TestEmptyAi {

	private EmptyAi emptyAi;
	private Enemy enemy;
	
	@Before
	public void before() {
		enemy = new BallBot(10,10);
		emptyAi = new EmptyAi(enemy);
	}
		
	@Test
	public void testGetEnemy() {
		assertTrue(emptyAi.getEnemy() == enemy);
	}
}
