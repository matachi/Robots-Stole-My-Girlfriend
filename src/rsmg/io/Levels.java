package rsmg.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * This class provides methods for reading information about the available levels.
 * 
 * @author Daniel Jonsson
 * 
 */
public final class Levels {
	
	/**
	 * Path to the level folder.
	 */
	private static final String levelFolder = "res/data/level/";
	
	/**
	 * Each level has a unique number. This method returns a collection
	 * containing these numbers.
	 * 
	 * @return A collection containing the available levels' numbers.
	 */
	public static Collection<Integer> getLevelNumbers() {
		File[] files = new File(levelFolder).listFiles();
		Collection<Integer> numberCollection = new ArrayList<Integer>();
		for (File f : files) {
			String fileName = f.getName();
			String regex = "^Level[0-9]+\\.xml$";
			// Check if the file name matches the regular expression
			if (fileName.matches(regex)) {
				// Get the number from the file name
				int levelNumber = Integer.parseInt(f.getName().split("Level|\\.")[1]);
				numberCollection.add(levelNumber);
			}
		}
		return numberCollection;
	}
	
	/**
	 * Get a level as an jDom XML document.
	 * 
	 * @param levelNumber
	 *            The level's number.
	 * @return The level as an jDom XML document.
	 */
	public static Document getLevel(int levelNumber) {
		try {
			return new SAXBuilder().build("res/data/level/Level" + levelNumber + ".xml");
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
