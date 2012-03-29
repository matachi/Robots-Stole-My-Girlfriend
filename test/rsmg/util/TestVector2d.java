package rsmg.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test the vector class.
 * @author Daniel Jonsson
 *
 */
public class TestVector2d {
	
	@Test
	public void testVector2d() {
		Vector2d v = new Vector2d();
		assertTrue(v.getX() == 0 && v.getY() == 0);
	}

	@Test
	public void testVector2dIntInt() {
		Vector2d v = new Vector2d(0, 0);
		boolean test1 = v.getX() == 0 && v.getY() == 0;
		
		v = new Vector2d(5, 6);
		boolean test2 = v.getX() == 5 && v.getY() == 6;
		
		v = new Vector2d(-5, -6);
		boolean test3 = v.getX() == -5 && v.getY() == -6;
		
		assertTrue(test1 && test2 && test3);
	}

	@Test
	public void testAdd() {
		Vector2d v1 = new Vector2d();
		Vector2d v2 = new Vector2d(5, 10);
		Vector2d v3 = new Vector2d(-4, -5);
		
		v1.add(v2);
		boolean test1 = v1.getX() == 5 && v1.getY() == 10;
		
		v1.add(v2);
		boolean test2 = v1.getX() == 10 && v1.getY() == 20;
		
		v1.add(v3);
		boolean test3 = v1.getX() == 6 && v1.getY() == 15;

		assertTrue(test1 && test2 && test3);
	}

	@Test
	public void testSubtract() {
		Vector2d v1 = new Vector2d();
		Vector2d v2 = new Vector2d(5, 10);
		Vector2d v3 = new Vector2d(-4, -5);
		
		v1.subtract(v2);
		boolean test1 = v1.getX() == -5 && v1.getY() == -10;
		
		v1.subtract(v2);
		boolean test2 = v1.getX() == -10 && v1.getY() == -20;
		
		v1.subtract(v3);
		boolean test3 = v1.getX() == -6 && v1.getY() == -15;

		assertTrue(test1 && test2 && test3);
	}

	@Test
	public void testGetlength() {
		Vector2d v1 = new Vector2d();
		Vector2d v2 = new Vector2d(3, 4);
		Vector2d v3 = new Vector2d(-3, -4);
		
		boolean test1 = v1.getlength() == 0;
		
		boolean test2 = v2.getlength() > 4.95 && v2.getlength() < 5.05;
		
		boolean test3 = v3.getlength() > 4.95 && v3.getlength() < 5.05;

		assertTrue(test1 && test2 && test3);
	}

}
