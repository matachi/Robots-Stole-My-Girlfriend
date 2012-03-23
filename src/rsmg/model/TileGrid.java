package rsmg.model;

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
}
