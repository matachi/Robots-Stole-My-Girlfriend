package rsgm.io;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import java.io.File;
import org.w3c.dom.*;

//import org.xml.sax.SAXException;
//import org.xml.sax.SAXParseException;

import org.w3c.dom.Document;

import rsgm.model.AirTile;
import rsgm.model.EndTile;
import rsgm.model.GroundTile;
import rsgm.model.SpawnTile;
import rsgm.model.Tile;

/**
 * Converts to/from XML to/from Tile[][]
 */
public class XmlConverter {

	/**
	 * Converts the XML file to Tile matrix by setting appropriate Tiles in the
	 * matrix according to the XML file
	 * 
	 * @param file
	 *            The XML file that are going to be converted to Tiles
	 * @return A Tile matrix. If error: null
	 */
	public Tile[][] xmlToTiles(File file) {
		Tile[][] grid;
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);

			int height = getElemByDoc(doc, "height");
			int width = getElemByDoc(doc, "width");

			// Knows the size of Tile[][]: Set it up
			grid = new Tile[height][width];
			grid = resetGrid(grid);
			grid = addGround(grid);
			grid = addSpawnAndEnd(grid);

			grid = addToGrid(doc, grid, "cliff");
			return grid;

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * TODO
	 * 
	 * @param doc
	 * @param grid
	 * @param elem
	 * @return
	 */
	public Tile[][] addToGrid(Document doc, Tile[][] grid, String elem) {

		NodeList cliffs = doc.getElementsByTagName(elem);
		for (int i = 0; i < cliffs.getLength(); i++) {

			Node node = cliffs.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;

				// To specific. TODO!!!
				int cliffStart = getElemByElem(e, "start");
				int cliffEnd = getElemByElem(e, "end");
				grid = addCliff(grid, cliffStart, cliffEnd);
			}
		}
		return grid;
	}

	/**
	 * Get the given child values(int) of an XML Document
	 * 
	 * @param doc
	 *            Document to retrieve the Node from
	 * @param target
	 *            The Node you would like to retrieve
	 * @return Node value as Integer
	 */
	public int getElemByDoc(Document doc, String target) {
		NodeList nodeList = doc.getElementsByTagName(target);
		Element elem = (Element) nodeList.item(0);

		NodeList list = elem.getChildNodes();
		String sRes = ((Node) list.item(0)).getNodeValue();
		int iRes = Integer.parseInt(sRes);
		return iRes;
	}

	/**
	 * Get the given child values of a parent Element
	 * 
	 * @param e
	 *            Element to get the child nodes from
	 * @param target
	 *            The child node to get value from
	 * @return Node value as Integer
	 */
	public int getElemByElem(Element e, String target) {
		NodeList nodeList = e.getElementsByTagName(target);
		Element elem = (Element) nodeList.item(0);

		NodeList list = elem.getChildNodes();
		String sRes = ((Node) list.item(0)).getNodeValue();
		int iRes = Integer.parseInt(sRes);
		return iRes;
	}

	/**
	 * Resets the grid by filling the Tile matrix with AirTiles
	 * 
	 * @param grid
	 *            The Tile matrix that are to be modified
	 * @return Updated Tile matrix
	 */
	public Tile[][] resetGrid(Tile[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new AirTile(1);
			}
		}
		return grid;
	}

	/**
	 * Adds ground the player can stand on by setting bottom row in matrix to
	 * GroundTiles
	 * 
	 * @param grid
	 *            The Tile matrix that are to be modified
	 * @return Updated Tile matrix
	 */
	public Tile[][] addGround(Tile[][] grid) {
		for (int j = 0; j < grid[0].length; j++) {
			grid[grid.length - 1][j] = new GroundTile(1);
		}
		return grid;
	}

	/**
	 * Adds a space in ground where player can fall down
	 * 
	 * @param grid
	 *            The Tile matrix that are to be modified
	 * @param start
	 *            Where the cliff should start in x
	 * @param end
	 *            Where the cliff should end in x
	 * @return Updated Tile matrix
	 */
	public Tile[][] addCliff(Tile[][] grid, int start, int end) {
		if (start < end && grid[0].length > end && start > 0) {
			for (int i = start; i < end; i++) {
				grid[grid.length - 1][i] = new AirTile(1);
			}
		}
		return grid;
	}
	
	public Tile[][] addSpawnAndEnd(Tile[][] grid){
		grid[grid.length - 1][0] = new SpawnTile(1);
		grid[grid.length - 1][grid[0].length - 1] = new EndTile(1);
		return grid;
	}
}
