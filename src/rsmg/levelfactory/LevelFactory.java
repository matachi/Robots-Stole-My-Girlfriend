package rsmg.levelfactory;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import rsmg.io.Levels;
import rsmg.model.Constants;
import rsmg.model.Level;
import rsmg.model.ObjectName;
import rsmg.model.TileGrid;
import rsmg.model.ai.Ai;
import rsmg.model.ai.BallBotAi;
import rsmg.model.ai.BucketBotAi;
import rsmg.model.ai.EmptyAi;
import rsmg.model.ai.RocketBotAi;
import rsmg.model.ai.TankBotAi;
import rsmg.model.object.bullet.Bullet;
import rsmg.model.object.item.HealthPack;
import rsmg.model.object.item.Item;
import rsmg.model.object.item.UpgradePoints;
import rsmg.model.object.item.Weapon;
import rsmg.model.object.unit.BallBot;
import rsmg.model.object.unit.BucketBot;
import rsmg.model.object.unit.RocketBot;
import rsmg.model.object.unit.Spikes;
import rsmg.model.object.unit.Tankbot;
import rsmg.model.tile.AirTile;
import rsmg.model.tile.EndTile;
import rsmg.model.tile.GroundTile;
import rsmg.model.tile.SpawnTile;
import rsmg.model.tile.Tile;

/**
 * This class is responsible for creating the levels. It asks the class Levels i
 * the io package for a level XML file, and then it converts it into a working
 * Level.
 * 
 * @author Johan Rignäs, Daniel Jonsson
 * 
 */
public final class LevelFactory {

	/**
	 * Get a working, playable Level.
	 * 
	 * @param levelNumber
	 *            The level's number.
	 * @return A Level.
	 */
	public static Level getLevel(int levelNumber) {

		// Prepare some variables that Level's constructor will need.
		Tile[][] grid;
		List<Item> itemList = new ArrayList<Item>();
		List<Ai> aiList = new ArrayList<Ai>();
		List<Bullet> enemyBulletList = new ArrayList<Bullet>();
		
		// Get the document from the io package.
		Document document = Levels.getLevel(levelNumber);
		
		// Get the root node.
		Element rootNode = document.getRootElement();

		// Set the size of tile tile grid.
		Element sizeElem = rootNode.getChild("size");
		int height = Integer.parseInt(sizeElem.getChildText("height"));
		int width = Integer.parseInt(sizeElem.getChildText("width"));
		grid = new Tile[height][width];

		// Walk through all row nodes in the document. These row nodes defines
		// how the tile grid should be constructed.
		@SuppressWarnings("unchecked")
		List<Element> rows = rootNode.getChildren("row");
		for (int y = 0; y < rows.size(); y++) {
			
			// Walk through all cells on the current row.
			Element row = rows.get(y);
			@SuppressWarnings("unchecked")
			List<Element> cells = row.getChildren("cell");
			for (int x = 0; x < cells.size(); x++) {

				// Retrieve tile
				Element cell = (Element) cells.get(x);
				String cellValue = cell.getText();
				Tile tile;
				switch (cellValue) {
					case "AirTile" :
						tile = new AirTile();
						break;
					case "GroundTile" :
						tile = new GroundTile();
						break;
					case "SpawnTile" :
						tile = new SpawnTile();
						break;
					case "EndTile" :
						tile = new EndTile();
						break;
					default :
						tile = new AirTile();
						break;
				}
				grid[y][x] = tile;
				
				int scale = Constants.TILESIZE; // Used to place items and enemies on appropriate positions.
				// Retrieve eventual item
				String itemValue = cell.getAttributeValue("item");
				if (itemValue != null) {
					Item item;
					switch (itemValue) {
						case ObjectName.HEALTH_PACK :
							item = new HealthPack(x*scale,y*scale);
							break;
						case ObjectName.UPGRADE_POINT :
							item = new UpgradePoints(x*scale,y*scale);
							break;
						case ObjectName.LASER_PISTOL :
							item = new Weapon(x*scale,y*scale, 15, 15, ObjectName.LASER_PISTOL);
							break;
						case ObjectName.SHOTGUN :
							item = new Weapon(x*scale,y*scale, 15, 15, ObjectName.SHOTGUN);
							break;
						default :
							item = new Weapon(x*scale,y*scale, 33, 15, ObjectName.ROCKET_LAUNCHER);
							break;
					}
					itemList.add(item);
				}
				
				// Retrieve Enemies and assign AI to them
				String enemyValue = cell.getAttributeValue("enemy");
				if (enemyValue != null) {
					Ai ai;
					switch (enemyValue) {
						case ObjectName.TANKBOT :
							ai = new TankBotAi(new Tankbot(x*scale, y*scale, enemyBulletList));
							break;
						case ObjectName.ROCKETBOT :
							ai = new RocketBotAi(new RocketBot(x*scale, y*scale));
							break;
						case ObjectName.BALLBOT :
							ai = new BallBotAi(new BallBot(x*scale, y*scale), aiList);
							break;
						case ObjectName.BUCKETBOT :
							ai = new BucketBotAi(new BucketBot(x*scale, y*scale, enemyBulletList));
							break;
						default :
							ai = new EmptyAi(new Spikes(x*scale, y*scale));
							break;
					}
					aiList.add(ai);
				}
			}
		}
		
		return new Level(new TileGrid(grid), itemList, aiList, enemyBulletList);
	}
}
