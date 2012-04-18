package rsmg.io;

import java.io.File;
import java.util.List;

import rsmg.model.item.Item;
import rsmg.model.Tile;

/**
 * Load and store data by reading files
 */
public class IO {

	// Is set when the map is loaded
	private List<Item> itemList;
	
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
		itemList = converter.getItemList();
		return grid;
	}
	
	public List<Item> getItemList(){
		return itemList;
	}
}
