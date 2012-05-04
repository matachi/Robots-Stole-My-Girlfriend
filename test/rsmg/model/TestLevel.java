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

import rsmg.model.object.item.HealthPack;
import rsmg.model.object.item.Item;
import rsmg.model.object.unit.Enemy;
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
				 {new GroundTile(), new GroundTile()}};
		tileGrid = new TileGrid(tiles);
		
		List<Item> items = new LinkedList<Item>();
		items.add(new HealthPack(10,10));
		
		List<Enemy> enemies = new LinkedList<Enemy>();
		enemies.add(new Tankbot(10,10, null));
		
		//level = new Level(tileGrid, items, enemies);
	}

	@After
	public void after() {
	}

	@Test
	public void testHasWon() {
		assertFalse(level.hasWon());
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
	public void testGetBulletList(){
		assertTrue(level.getAlliedBulletList().size() == 0);
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
