package rsmg.model.object.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import rsmg.model.object.bullet.Bullet;

public class TestBucketBot {
	
	private BucketBot bucketbot;
	private List<Bullet> bulletList;
	
	@Before
	public void before() {
		bulletList = new LinkedList<Bullet>();
		bucketbot = new BucketBot(0, 0, bulletList);
	}

	@Test
	public void testGetTouchDamage() {
		assertTrue(bucketbot.getTouchDamage() == 20);
	}

	@Test
	public void testIsFlyingUnit() {
		assertFalse(bucketbot.isFlyingUnit());
	}
	
	@Test
	public void testShoot() {
		assertTrue(bulletList.size() == 0);
		bucketbot.shoot();
		bucketbot.shoot();
		assertTrue(bulletList.size() == 2);
	}
}