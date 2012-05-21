package rsmg.model.ai;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.unit.BossBotHead;
import rsmg.model.object.unit.PCharacter;

/**
 * 
 * @author Johan Rignas
 * @author Daniel Jonsson
 *
 */
public class TestBossBotAi {
	
	private BossBotAi bossBotAi;
	private List<Bullet> bulletList;
	private PCharacter character;
	
	@Before
	public void before() {
		bulletList = new LinkedList<Bullet>();
		BossBotHead bossBot = new BossBotHead(20,20, bulletList);
		character = new PCharacter(null);
		bossBotAi = new BossBotAi(bossBot, character);
	}

	@Test
	public void testUpdate() {
		double delta = 0.3;
		
		// Check if it shoots correctly
		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 3);

		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 6);

		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 9);
		
		// Behind the boss should only the secondary attack work
		character.setX(150);
		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 10);
		
		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 11);
	}
		
	@Test
	public void testGetEnemy() {
		assertTrue(bossBotAi.getEnemy().getX() == 20);
		assertTrue(bossBotAi.getEnemy().getY() == 20);
	}
}