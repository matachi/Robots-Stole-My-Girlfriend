package rsmg.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
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
import rsmg.model.object.unit.PCharacter;
import rsmg.model.object.unit.TankBot;
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

		PCharacter character = new PCharacter(new LinkedList<Bullet>());
		
		List<Item> items = new LinkedList<Item>();
		items.add(new HealthPack(10,10));
		
		List<Ai> enemies = new LinkedList<Ai>();
		enemies.add(new TankBotAi(new TankBot(10,10, new LinkedList<Bullet>()), character));
		
		
		level = new Level(tileGrid, character, items, enemies, new LinkedList<Bullet>(), new LinkedList<Bullet>());
	}

	@After
	public void after() {}
	
	@Test
	public void testSpawnChar(){
		Point spawnPoint;
		try {
			spawnPoint = tileGrid.getSpawnPoint();
			PCharacter character = level.getCharacter();
			assertTrue(character.getX() == spawnPoint.getX());
			assertTrue(character.getY() == spawnPoint.getY());
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	// TODO if possible
	@Test
	public void testUpdate(){}
	
	@Test
	public void testHasWon() {
		assertFalse(level.hasWon());
	}
	
	@Test
	public void testHasLost() {
		assertFalse(level.hasLost());
	}
	
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
