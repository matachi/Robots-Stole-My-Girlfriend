package rsmg.io;

import java.io.File;

import rsmg.model.Tile;

/**
 * Load and store data by reading files
 */
public class IO {

	/**
	 * Get the level as a Tile matrix
	 * 
	 * @param iLevel
	 *            The level that are going to be retrieved, 1..3
	 * @return A Tile matrix representing a level
	 */
	public Tile[][] getLevel(int iLevel) {
		File file = new File("src/rsmg/io/Level" + iLevel + ".xml");
		XmlConverter converter = new XmlConverter();
		Tile[][] grid = converter.xmlToTiles(file);
		return grid;
	}
}
