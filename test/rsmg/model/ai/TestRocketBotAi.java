package rsmg.model.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.unit.PCharacter;
import rsmg.model.object.unit.RocketBot;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class TestRocketBotAi {

	private Ai ai;
	private RocketBot enemy;
	private PCharacter character;
	
	@Before
	public void before() {
		character = new PCharacter(null);
		enemy = new RocketBot(10, 10);
		ai = new RocketBotAi(enemy, character);
	}
	
	@Test
	public void testUpdate() {
		// Moving left
		character.setPosition(0, 0);
		ai.update(0);
		assertTrue(enemy.getVelocityX() < 0);

		// Facing right
		character.setX(20);
		ai.update(0);
		assertTrue(enemy.getVelocityX() > 0);

		// Out of range
		enemy.setX(300);
		ai.update(1);
		assertTrue(enemy.getVelocity() == 0);
	}
		
	@Test
	public void testGetEnemy() {
		assertTrue(ai.getEnemy() == enemy);
	}
}
