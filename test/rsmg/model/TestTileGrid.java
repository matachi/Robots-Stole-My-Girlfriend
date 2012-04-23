package rsmg.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.unit.PCharacter;
import rsmg.model.tile.AirTile;
import rsmg.model.tile.EndTile;
import rsmg.model.tile.GroundTile;
import rsmg.model.tile.SpawnTile;
import rsmg.model.tile.Tile;

public class TestTileGrid {

	TileGrid grid;
	
	@Before
	public void before() {
		Tile[][] tile = {{new AirTile(),new AirTile()},
						 {new SpawnTile(), new  EndTile()},
						 {new GroundTile(), new GroundTile()}};
		grid = new TileGrid(tile);
	}
	
	@Test
	public void testSet() {
		assertFalse(grid.getFromCoord(1, 0).isSolid());
		grid.set(1, 0, new GroundTile());
		assertTrue(grid.getFromCoord(1, 0).isSolid());
	}
	
	@Test
	public void testGetWith() {
		assertTrue(grid.getWidth() == 2);
	}
	
	@Test
	public void testGetHeight() {
		assertTrue(grid.getHeight() == 3);
	}
	
	@Test
	public void testGetSpawnPoint() throws Exception {
		Point p = grid.getSpawnPoint();
		Point point = new Point((int)p.getX()/Constants.TILESIZE,
								(int)p.getY()/Constants.TILESIZE);
		assertTrue(point.getX() == 0);
		assertTrue(point.getY() == 1);
	}
	
	@Test
	public void testTilePosFromRealPos() {
		assertTrue(grid.getTilePosFromRealPos(50) == 1);
	}
	
	@Test
	public void testIntersectsWith() {
		PCharacter character = new PCharacter(50, 64, new LinkedList<Bullet>());
		
		assertTrue(grid.intersectsWith(character));
	}
	
	@Test
	public void testLeftSideIntersection() {
		PCharacter character = new PCharacter(50, 64, new LinkedList<Bullet>());
        
        assertTrue(grid.leftSideIntersection(character) == 14);
	}
	
	//@Test
	public void testRightSideIntersection() {
		PCharacter character = new PCharacter(24, 24, new LinkedList<Bullet>());
        assertTrue(grid.rightSideIntersection(character) == 14);
	}
	
	//@Test
	public void testBottomSideIntersection() {
	    PCharacter character = new PCharacter(50, 64, new LinkedList<Bullet>());
        //character.getHeight()
	    System.out.println(grid.rightSideIntersection(character));
        assertTrue(grid.bottomSideIntersection(character) == 22);
	}
	
	//@Test
	public void testTopSideIntersection() {
		PCharacter character = new PCharacter(50, 64, new LinkedList<Bullet>());
        
        assertTrue(grid.topSideIntersection(character) == -22);
	}	
	
}
