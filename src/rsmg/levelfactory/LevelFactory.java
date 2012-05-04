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
 * Converts to/from XML to/from Tile[][]
 */
public final class LevelFactory {

	/**
	 * Converts the XML file to Tile matrix by setting appropriate Tiles in the
	 * matrix according to the XML file
	 * 
	 * @param file
	 *            The XML file that are going to be converted to Tiles
	 * @return A Tile matrix. If error: null
	 */
	public static Level getLevel(int levelNumber) {

		Tile[][] grid;
		List<Item> itemList = new ArrayList<Item>();
		List<Ai> aiList = new ArrayList<Ai>();
		List<Bullet> enemyBulletList = new ArrayList<Bullet>();
		

			Document document = Levels.getLevel(levelNumber);
			Element rootNode = document.getRootElement();

			Element sizeElem = rootNode.getChild("size");
			int height = Integer.parseInt(sizeElem.getChildText("height"));
			int width = Integer.parseInt(sizeElem.getChildText("width"));
			grid = new Tile[height][width];

			@SuppressWarnings("unchecked")
			List<Element> rows = rootNode.getChildren("row");
			for (int y = 0; y < rows.size(); y++) {
				Element cellsElem = (Element) rows.get(y);
				@SuppressWarnings("unchecked")
				List<Element> cells = cellsElem.getChildren("cell");

				for (int x = 0; x < cells.size(); x++) {

					// Retrieve Tiles
					Element cell = (Element) cells.get(x);
					String cellValue = cell.getText();
					Tile tile;
					if (cellValue.equals("AirTile"))
						tile = new AirTile();
					else if (cellValue.equals("GroundTile"))
						tile = new GroundTile();
					else if (cellValue.equals("SpawnTile"))
						tile = new SpawnTile();
					else if (cellValue.equals("EndTile"))
						tile = new EndTile();
					else
						tile = new AirTile();
					grid[y][x] = tile;
					
					int scale = Constants.TILESIZE;
					// Retrieve Items
					String itemValue = cell.getAttributeValue("item");
					if(itemValue != null){
						Item item;
						if(itemValue.equals(ObjectName.HEALTH_PACK))
							item = new HealthPack(x*scale,y*scale);
						else if(itemValue.equals(ObjectName.UPGRADE_POINT))
							item = new UpgradePoints(x*scale,y*scale);
						else if(itemValue.equals(ObjectName.LASER_PISTOL))
							item = new Weapon(x*scale,y*scale, 15, 15, ObjectName.LASER_PISTOL);
						else if(itemValue.equals(ObjectName.SHOTGUN))
							item = new Weapon(x*scale,y*scale, 15, 15, ObjectName.SHOTGUN);
						else
							item = new Weapon(x*scale,y*scale, 33, 15, ObjectName.ROCKET_LAUNCHER);
						itemList.add(item);
					}
					
					// Retrieve Enemies and assign AI to them
					String enemyValue = cell.getAttributeValue("enemy");
					if(enemyValue != null){
						Ai ai;
						if(enemyValue.equals(ObjectName.TANKBOT))
							ai = new TankBotAi(new Tankbot(x*scale, y*scale, enemyBulletList));
						
						else if(enemyValue.equals(ObjectName.ROCKETBOT))
							ai = new RocketBotAi(new RocketBot(x*scale, y*scale));
						
						else if(enemyValue.equals(ObjectName.BALLBOT))
							ai = new BallBotAi(new BallBot(x*scale, y*scale, aiList));
						
						else if(enemyValue.equals(ObjectName.BUCKETBOT))
							ai = new BucketBotAi(new BucketBot(x*scale, y*scale, enemyBulletList));
						
						else
							ai = new EmptyAi(new Spikes(x*scale, y*scale));
						aiList.add(ai);
					}
				}
			}
		return new Level(new TileGrid(grid), itemList, aiList, enemyBulletList);
	}
}
