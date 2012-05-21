package rsmg.model.object.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;

public class TestTankBot {
	
	private TankBot tank;
	private List<Bullet> bulletList;
	
	@Before
	public void before() {
		bulletList = new LinkedList<Bullet>();
		tank = new TankBot(0, 0, bulletList);
	}

	@Test
	public void testGetTouchDamage() {
		assertTrue(tank.getTouchDamage() == 10);
	}

	@Test
	public void testIsFlyingUnit() {
		assertFalse(tank.isFlyingUnit());
	}
	
	@Test
	public void testShoot() {
		assertTrue(bulletList.size() == 0);
		tank.shoot();
		tank.shoot();
		assertTrue(bulletList.size() == 2);
	}
}