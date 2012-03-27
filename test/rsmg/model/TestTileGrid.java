package rsmg.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

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
	public void testGet() {
		assertFalse(grid.get(1, 0).isSolid());
	}
	
	@Test
	public void testSet() {
		assertFalse(grid.get(1, 0).isSolid());
		grid.set(1, 0, new GroundTile());
		assertTrue(grid.get(1, 0).isSolid());
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
		// TODO
	}
	
	@Test
	public void testIntersectsWith() {
		// TODO
	}
	
	@Test
	public void testLeftSideIntersection() {
		// TODO
	}
	@Test
	public void testRightSideIntersection() {
		// TODO
	}
	
	@Test
	public void testBottomSideIntersection() {
		// TODO
	}
	
	@Test
	public void testTopSideIntersection() {
		// TODO
	}	
	
}
