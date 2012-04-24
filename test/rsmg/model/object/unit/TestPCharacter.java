package rsmg.model.object.unit;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;


/**
 * Tests the character class.
 * @author Daniel Jonsson
 *
 */
public class TestPCharacter {

	PCharacter character;
	
	@BeforeClass
	public static void beforeClass() {
	}

	@AfterClass
	public static void afterClass() {
	}

	@Before
	public void before() {
		character = new PCharacter(0, 0, new LinkedList<Bullet>());
	}

	@After
	public void after() {
	}

	@Test
	public void testJump() {
		character.jump();
		assertTrue(character.getVelocityY() < 0);
	}

	@Test
	public void testMoveLeft() {
		character.moveLeft();
		assertTrue(character.getVelocityX() < 0);
	}

	@Test
	public void testMoveRight() {
		character.moveRight();
		assertTrue(character.getVelocityX() > 0);
	}
	
//	@Test
//	public void testCollide() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCharacter() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInteractiveObjectDoubleDouble() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInteractiveObjectDoubleDoubleDoubleDouble() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetVector() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetVelocity() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddVector() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetX() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetY() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetHeight() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetWidth() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetX() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetY() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMove() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testApplyGravity() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testHasCollidedWith() {
		
		PCharacter character = new PCharacter(0, 0, null);
		PCharacter character2 = new PCharacter(0, 0, null);
		
		boolean test1 = character.hasCollidedWith(character2);
		
		character.setX(character.getWidth());
		character.setY(character.getHeight());
		
		boolean test2 = character.hasCollidedWith(character2);
		assertTrue(test1 && test2);
	}
}
