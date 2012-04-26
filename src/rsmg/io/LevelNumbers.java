package rsmg.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Counts the number of Levels available in the Level data folder and returns a
 * collection with their numbers.
 * 
 * @author Daniel Jonsson
 * 
 */
public final class LevelNumbers {
	;
	
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
}
