package rsmg.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rsmg.model.ai.Ai;
import rsmg.model.ai.TankBotAi;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.item.HealthPack;
import rsmg.model.object.item.Item;
import rsmg.model.object.unit.Enemy;
import rsmg.model.object.unit.PCharacter;
import rsmg.model.object.unit.Tankbot;
import rsmg.model.tile.AirTile;
import rsmg.model.tile.EndTile;
import rsmg.model.tile.GroundTile;
import rsmg.model.tile.SpawnTile;
import rsmg.model.tile.Tile;

/**
 * Tests the Level class
 *
 */
public class TestLevel {
	
	private Level level;
	private TileGrid tileGrid;
	
	@BeforeClass
	public static void beforeClass() {
	}

	@AfterClass
	public static void afterClass() {
	}

	@Before
	public void before() {
		Tile[][] tiles = {{new AirTile(),new AirTile()},
				 {new SpawnTile(), new  EndTile()},
				 {new GroundTile("boxTile1"), new GroundTile("boxTile1")}};
		tileGrid = new TileGrid(tiles);
		
		List<Item> items = new LinkedList<Item>();
		items.add(new HealthPack(10,10));
		
		List<Ai> enemies = new LinkedList<Ai>();
		enemies.add(new TankBotAi(new Tankbot(10,10, new LinkedList<Bullet>())));
		
		level = new Level(tileGrid, items, enemies, new LinkedList<Bullet>());
	}

	@After
	public void after() {}
	
	
	
	// SKIP? (private + hard to test)
	@Test
	public void testLoadUpgrades(){	}
	
	@Test
	public void testSpawnChar(){
		Point spawnPoint;
		try {
			spawnPoint = tileGrid.getSpawnPoint();
			PCharacter character = new PCharacter(spawnPoint.getX(), spawnPoint.getY(), new LinkedList<Bullet>());
			assertTrue(character.getX() == spawnPoint.getX());
			assertTrue(character.getY() == spawnPoint.getY());
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	// SKIP? (hard to test)
	@Test
	public void testUpdate(){}
	
	// PRIVATE...
	@Test
	public void testOutsideLevelCheck(){
		Collection<Enemy> enemies = level.getEnemies();
		
		assertTrue(enemies.size() == 1);
		Enemy e = (Enemy)enemies.toArray()[0];
		enemies.clear();
		e.setX(-1);
		enemies.add(e);
		
		//level.outsideLevelCheck(); // private...
		//assertTrue(level.getEnemies().size() == 0);
	}
	
	// PRIVATE
	@Test
	public void testUpdateCharacter(){}
	
	// PRIVATE
	@Test
	public void testEnemyBulletCollision(){	}
	
	//PRIVATE
	@Test
	public void testUpdateEnemies(){}
	
	//PRIVATE
	@Test
	public void testupdateBullets(){}
	
	// Should be private...
	@Test
	public void testUpdateItem(){}
	
	// Private
	@Test
	public void testIsAirbourne(){}
	
	// Private
	@Test
	public void testCheckVictory(){}
	
	// Private
	@Test
	public void testCheckDeath(){}
	
	@Test
	public void testHasWon() {
		assertFalse(level.hasWon());
	}
	
	@Test
	public void testHasLost() {
		assertFalse(level.hasLost());
	}
	
	// Private
	@Test
	public void testApplyNormalForce(){	}
	
	// Private
	@Test
	public void testCameFromAbove(){}
		
	// Private
	@Test
	public void testCameFromBelow(){}
		
	// Private
	@Test
	public void testCameFromLeft(){}
		
	// Private
	@Test
	public void testCameFromRight(){}
	
	// Private
	@Test
	public void testMoveUp(){}

	// Private
	@Test
	public void testMoveDown(){}
	
	// Private
	@Test
	public void testMoveLeft(){}
	
	// Private
	@Test
	public void testMoveRight(){}
	
	@Test
	public void testGetTileGrid(){
		assertTrue(level.getTileGrid() == tileGrid);
	}
	
	@Test
	public void testGetCharacter() throws Exception {
		Point spawnPoint = tileGrid.getSpawnPoint();
		
		double x = level.getCharacter().getX();
		double y = level.getCharacter().getY();
		
		assertTrue(spawnPoint.getX() == x);
		assertTrue(spawnPoint.getY() == y);
	}
	
	@Test
	public void testGetAlliedBulletList(){
		assertTrue(level.getAlliedBulletList().size() == 0);
	}
	
	@Test
	public void testGetEnemyBulletList(){
		assertTrue(level.getEnemyBulletList().size() == 0);
	}
	
	@Test
	public void testGetEnemies(){
		assertTrue(level.getEnemies().size() == 1);
	}
	
	@Test
	public void testGetItemList(){
		assertTrue(level.getItemList().size() == 1);
	}
}
