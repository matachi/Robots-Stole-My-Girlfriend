package rsmg.io;

import java.io.File;
import java.util.List;

import rsmg.model.Enemy;
import rsmg.model.object.item.Item;
import rsmg.model.tile.Tile;

/**
 * Load and store data by reading files
 */
public class IO {

	// Is set when the map is loaded
	private List<Item> itemList;
	private List<Enemy> enemyList;
	
	/**
	 * Get the level as a Tile matrix
	 * 
	 * @param iLevel
	 *            The level that are going to be retrieved, 1..3
	 * @return A Tile matrix representing a level
	 */
	public Tile[][] getLevel(int iLevel) {
		File file = new File("res/data/level/Level" + iLevel + ".xml");
		XmlConverter converter = new XmlConverter();
		Tile[][] grid = converter.xmlToTiles(file);
		itemList = converter.getItemList();
		enemyList = converter.getEnemyList();
		return grid;
	}
	
	public List<Item> getItemList(){
		return itemList;
	}
	
	public List<Enemy> getEnemyList(){
		return enemyList;
	}
}
