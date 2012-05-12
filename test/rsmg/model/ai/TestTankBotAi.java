package rsmg.model.ai;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.unit.PCharacter;
import rsmg.model.object.unit.TankBot;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class TestTankBotAi {

	private Ai ai;
	private TankBot enemy;
	private List<Bullet> bullets;
	private PCharacter character;
	
	@Before
	public void before() {
		character = new PCharacter(null);
		bullets = new ArrayList<Bullet>();
		enemy = new TankBot(10, 10, bullets);
		ai = new TankBotAi(enemy, character);
	}
	
	@Test
	public void testUpdate() {
		// Facing left
		character.setPosition(0, 0);
		ai.update(0);
		assertTrue(!enemy.isFacingRight());

		// Facing right
		character.setX(20);
		ai.update(0);
		assertTrue(enemy.isFacingRight());

		// Shoots
		ai.update(1);
		assertTrue(bullets.size() == 1);

		// Doesn't shoot
		enemy.setX(300); // Out of range
		ai.update(1);
		assertTrue(bullets.size() == 1);
	}
		
	@Test
	public void testGetEnemy() {
		assertTrue(ai.getEnemy() == enemy);
	}
}
