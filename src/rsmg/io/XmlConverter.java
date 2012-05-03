package rsmg.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import rsmg.model.Constants;
import rsmg.model.ObjectName;
import rsmg.model.ai.Ai;
import rsmg.model.ai.EmptyAi;
import rsmg.model.ai.PatrollingAi;
import rsmg.model.object.item.HealthPack;
import rsmg.model.object.item.Item;
import rsmg.model.object.item.UpgradePoints;
import rsmg.model.object.item.Weapon;
import rsmg.model.object.unit.BallBot;
import rsmg.model.object.unit.BucketBot;
import rsmg.model.object.unit.Enemy;
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
public class XmlConverter {

	private List<Item> itemList = new ArrayList<Item>();
	private List<Ai> aiList = new ArrayList<Ai>();
	/**
	 * Converts the XML file to Tile matrix by setting appropriate Tiles in the
	 * matrix according to the XML file
	 * 
	 * @param file
	 *            The XML file that are going to be converted to Tiles
	 * @return A Tile matrix. If error: null
	 */
	public Tile[][] xmlToTiles(File xmlFile) {
		SAXBuilder builder = new SAXBuilder();
		Tile[][] grid;
		try {

			Document document = (Document) builder.build(xmlFile);
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
							ai = new PatrollingAi(new Tankbot(x*scale, y*scale));
						else if(enemyValue.equals(ObjectName.ROCKETBOT))
							ai = new PatrollingAi(new RocketBot(x*scale, y*scale));
						else if(enemyValue.equals(ObjectName.BALLBOT))
							ai = new PatrollingAi(new BallBot(x*scale, y*scale));
						else if(enemyValue.equals(ObjectName.BUCKETBOT))
							ai = new PatrollingAi(new BucketBot(x*scale, y*scale));
						else
							ai = new EmptyAi(new Spikes(x*scale, y*scale));
						aiList.add(ai);
					}
				}
			}
			return grid;
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		System.out.println("error!!");
		return null;
	}
			
	public List<Item> getItemList(){
		return itemList;
	}
	
	public List<Ai> getAiList(){
		return aiList;
	}
}
