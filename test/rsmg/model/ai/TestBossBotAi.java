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
		double delta = 2;
		character.setX(50);
		
		// TODO: Why zero first time? If it´s as it should, remove this line!!
		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 0);

		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 1);

		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 2);
		
		character.setX(0);
		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 5);
		
		bossBotAi.update(delta);
		assertTrue(bulletList.size() == 8);
	}
		
	@Test
	public void testGetEnemy() {
		assertTrue(bossBotAi.getEnemy().getX() == 20);
		assertTrue(bossBotAi.getEnemy().getY() == 20);
	}
}