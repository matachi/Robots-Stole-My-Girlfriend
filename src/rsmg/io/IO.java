package rsmg.io;

import java.io.File;
import java.util.List;

import rsmg.model.ai.Ai;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.item.Item;
import rsmg.model.tile.Tile;

/**
 * Load and store data by reading files
 */
public class IO {

	// Is set when the map is loaded
	private List<Item> itemList;
	private List<Ai> aiList;
	private List<Bullet> enemyBulletList;
	
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
		aiList = converter.getAiList();
		enemyBulletList = converter.getEnemyBulletList();
		return grid;
	}
	
	public List<Item> getItemList(){
		return itemList;
	}
	
	public List<Ai> getAiList(){
		return aiList;
	}

	public List<Bullet> getEnemyBulletList() {
		return enemyBulletList;
	}
}
