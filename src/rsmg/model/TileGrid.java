package rsmg.model;

import java.awt.Point;

import rsmg.io.IO;

/**
 * Contains information about the Tile[][]
 */
public class TileGrid {

	private Tile[][] grid;

	/**
	 * Constructor. Gets the level specified from IO
	 * 
	 * @param levelReached
	 *            Level that are going to run
	 */
	public TileGrid(int levelReached) {
		IO io = new IO();
		grid = io.getLevel(levelReached);

		showGrid();
	}

	/**
	 * Set specified Tile to specified place
	 * 
	 * @param x
	 *            X coordinate in matrix
	 * @param y
	 *            y coordinate in matrix
	 * @param tile
	 *            The tile to be set
	 */
	public void set(int x, int y, Tile tile) {
		grid[x][y] = tile;
	}

	/**
	 * Get specified Tile in matrix
	 * 
	 * @param x
	 *            x coordinate in matrix
	 * @param y
	 *            y coordinate in matrix
	 * @return The Tile in the matrix
	 */
	public Tile get(int x, int y) {
		return grid[x][y];
	}
	
	public Point getSpawnPoint() throws Exception{
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if (grid[i][j] instanceof SpawnTile){
					return new Point(i*Constants.TILESIZE, j*Constants.TILESIZE);
				}
			}
		}
		throw new Exception();
		//TODO change to a specifed exception
	}
	
	/**
	 * Get tile number from a coordinate in the model.
	 * For an example, if you give the method the argument 50 and a tile is
	 * 32 units large, the method will return 1 since the real position is in
	 * tile number 1.
	 * @param realPos The position in the game.
	 * @return The position in the matrix.
	 */
	public int getTilePosFromRealPos(double realPos) {
		return (int)(realPos / Constants.TILESIZE);
	}
	
	/**
	 * Check if an interactive object intersects with any solid tiles.
	 * @param object The interactive object.
	 * @return If the object intersects with any solid tiles.
	 */
	public boolean intersectsWith(InteractiveObject object) {
		
		int leftX = getTilePosFromRealPos(object.getX());
		int rightX = getTilePosFromRealPos(object.getX()+object.getWidth());
		int topY = getTilePosFromRealPos(object.getY());
		int bottomY = getTilePosFromRealPos(object.getY()+object.getHeight());
		
		for (int x = leftX; x <= rightX; x++) {
			for (int y = topY; y <= bottomY; y++) {
				if (get(x,y).isSolid() == true)
					return true;
			}
		}
		return false;
	}

	/**
	 * Test to display grid in console
	 */
	public void showGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j].isSolid() + " | ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Returns the number of tiles horizontally in the tile grid.
	 * @return Number of tiles horizontally.
	 */
	public int getWidth() {
		return grid.length;
	}
	
	/**
	 * Returns the number of tiles vertically in the tile grid.
	 * @return Number of tiles vertically.
	 */
	public int getHeight() {
		return grid[0].length;
	}
}
