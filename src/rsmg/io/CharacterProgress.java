package rsmg.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * This class reads and writes to the character progress file. The character
 * progress file stores which upgrade points that has been found, which upgrades
 * and levels that have been unlocked and so on.
 * 
 * @author Daniel Jonsson
 * 
 */
public class CharacterProgress {

	/**
	 * Path to the configuration file.
	 */
	private static final String progressFilePath = "res/data/progress/progress.xml";
	
	/**
	 * Make this class to a singleton.
	 */
	private static final CharacterProgress config = new CharacterProgress();

	/**
	 * Store all progress variables in one map.
	 */
	private Map<String, Integer> progress;
	
	private static final int TRUE = 1;
	private static final int FALSE = 0;
	
	/**
	 * Make this class to a singleton. Reads the settings from the character
	 * progress file and stores the values in this class.
	 */
	private CharacterProgress() {
		SAXBuilder builder = new SAXBuilder();
		try {
			// The character progress document
			Document document = builder.build(progressFilePath);
			
			// The root node in the document
			Element rootNode = document.getRootElement();
			
			// Read and store the settings to the class
			progress = new HashMap<String, Integer>();
			progress.put("unlockedLevels", numberToInt(rootNode.getChildText("unlockedLevels")));
			progress.put("upgradePoints", numberToInt(rootNode.getChildText("upgradePoints")));

			Element weapon = rootNode.getChild("unlockedWeapons");
			progress.put("rpg", booleanToInt(weapon.getChildText("rpg")));
			progress.put("shotgun", booleanToInt(weapon.getChildText("shotgun")));
			progress.put("assaultRifle", booleanToInt(weapon.getChildText("assaultRifle")));

			Element upgrade = rootNode.getChild("unlockedUpgrades");
			progress.put("dash", booleanToInt(upgrade.getChildText("dash")));
			progress.put("doubleJump", booleanToInt(upgrade.getChildText("doubleJump")));
			progress.put("rapidFire", booleanToInt(upgrade.getChildText("rapidFire")));
			progress.put("incRunningSpeed", booleanToInt(upgrade.getChildText("incRunningSpeed")));
			progress.put("incShotgunSpread", booleanToInt(upgrade.getChildText("incShotgunSpread")));
			progress.put("incRPGKnockback", booleanToInt(upgrade.getChildText("incRPGKnockback")));
			progress.put("incRPGAoE", booleanToInt(upgrade.getChildText("incRPGAoE")));
			progress.put("decAssaultRifleKnockback", booleanToInt(upgrade.getChildText("decAssaultRifleKnockback")));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Get methods.
	 */
	public static int getUnlockedLevels() {
		return variableNumber("unlockedLevels");
	}
	
	public static int getUpgradePoints() {
		return variableNumber("upgradePoints");
	}

	// weapons
	public static boolean isRPGUnlocked() {
		return variableIsTrue("rpg");
	}

	public static boolean isShotgunUnlocked() {
		return variableIsTrue("shotgun");
	}

	public static boolean isAssaultRifleUnlocked() {
		return variableIsTrue("assaultRifle");
	}
	
	// upgrades
	public static boolean isDashUnlocked() {
		return variableIsTrue("dash");
	}
	
	public static boolean isDoubleJumpUnlocked() {
		return variableIsTrue("doubleJump");
	}
	
	public static boolean isRapidFireUnlocked() {
		return variableIsTrue("rapidFire");
	}
	
	public static boolean isIncRunningSpeedUnlocked() {
		return variableIsTrue("incRunningSpeed");
	}
	
	public static boolean isIncShotgunSpreadUnlocked() {
		return variableIsTrue("incShotgunSpread");
	}
	
	public static boolean isIncRPGKnockbackUnlocked() {
		return variableIsTrue("incRPGKnockback");
	}
	
	public static boolean isIncRPGAoeUnlocked() {
		return variableIsTrue("incRPGAoE");
	}
	
	public static boolean isDecAssaultRifleKnockbackUnlocked() {
		return variableIsTrue("decAssaultRifleKnockback");
	}
	
	/*
	 * Set methods.
	 */
	public static void setUnlockedLevels(int i) {
		setVariable("unlockedLevels", i);
	}
	
	public static void setUpgradePoints(int i) {
		setVariable("upgradePoints", i);
	}
	
	// weapons
	public static void setRpgUnlocked(boolean unlocked) {
		setVariable("rpg", unlocked);
	}
	
	public static void setShotgunUnlocked(boolean unlocked) {
		setVariable("shotgun", unlocked);
	}
	
	public static void setAssaultRifleUnlocked(boolean unlocked) {
		setVariable("assaultRifle", unlocked);
	}
	
	// upgrades
	public static void setDashUnlocked(boolean unlocked) {
		setVariable("dash", unlocked);
	}
	
	public static void setDoubleJumpUnlocked(boolean unlocked) {
		setVariable("doubleJump", unlocked);
	}
	
	public static void setRapidFireUnlocked(boolean unlocked) {
		setVariable("rapidFire", unlocked);
	}
	
	public static void setIncRunningSpeedUnlocked(boolean unlocked) {
		setVariable("incRunningSpeed", unlocked);
	}
	
	public static void setIncShotgunSpreadUnlocked(boolean unlocked) {
		setVariable("incShotgunSpread", unlocked);
	}
	
	public static void setIncRPGKnockbackUnlocked(boolean unlocked) {
		setVariable("incRPGKnockback", unlocked);
	}
	
	public static void setIncRPGAoEUnlocked(boolean unlocked) {
		setVariable("incRPGAoE", unlocked);
	}
	
	public static void setDecAssaultRifleKnockbackUnlocked(boolean unlocked) {
		setVariable("decAssaultRifleKnockback", unlocked);
	}
	
	/**
	 * Write the configurations to the hard drive.
	 * Until this is run, changed settings are only available in the current
	 * session, and lost when closing the game.
	 */
	public static void saveFile() {
		SAXBuilder builder = new SAXBuilder();
        try {
        	// Instance of the config document
        	Document document = builder.build(progressFilePath);

			// The root node in the config document
			Element rootNode = document.getRootElement();
			
			// Store the settings in the instance of the config document
			rootNode.getChild("unlockedLevels").setText(String.valueOf(getUnlockedLevels()));
			rootNode.getChild("upgradePoints").setText(String.valueOf(getUpgradePoints()));
			
			// weapons
			Element childNode = rootNode.getChild("unlockedWeapons");
			childNode.getChild("rpg").setText(Boolean.toString(isRPGUnlocked()));
			childNode.getChild("shotgun").setText(Boolean.toString(isShotgunUnlocked()));
			childNode.getChild("assaultRifle").setText(Boolean.toString(isAssaultRifleUnlocked()));

			// upgrades
			childNode = rootNode.getChild("unlockedUpgrades");
			childNode.getChild("dash").setText(Boolean.toString(isDashUnlocked()));
			childNode.getChild("doubleJump").setText(Boolean.toString(isDoubleJumpUnlocked()));
			childNode.getChild("rapidFire").setText(Boolean.toString(isRapidFireUnlocked()));
			childNode.getChild("incRunningSpeed").setText(Boolean.toString(isIncRunningSpeedUnlocked()));
			childNode.getChild("incShotgunSpread").setText(Boolean.toString(isIncShotgunSpreadUnlocked()));
			childNode.getChild("incRPGKnockback").setText(Boolean.toString(isIncRPGKnockbackUnlocked()));
			childNode.getChild("incRPGAoE").setText(Boolean.toString(isIncRPGAoeUnlocked()));
			childNode.getChild("decAssaultRifleKnockback").setText(Boolean.toString(isDecAssaultRifleKnockbackUnlocked()));
        	
			// Write the document to the progress file on the HDD
    		XMLOutputter outputter = new XMLOutputter();
            FileWriter writer = new FileWriter(progressFilePath);
			outputter.output(document, writer);
	        writer.close();
	        
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	
	/**
	 * Convert a boolean string to an int.
	 * @param bol Boolean value as a string.
	 * @return 1 if true. 0 if false.
	 */
	private static int booleanToInt(String bol) {
		return (Boolean.parseBoolean(bol)) ? TRUE : FALSE;
	}

	/**
	 * Convert a boolean value to an int.
	 * @param bol Boolean variable.
	 * @return 1 if true. 0 if false.
	 */
	private static int booleanToInt(boolean bol) {
		return (bol) ? TRUE : FALSE;
	}
	
	/**
	 * Convert a string number to an int.
	 * @param num Number as a string.
	 * @return The number as an int.
	 */
	private static int numberToInt(String num) {
		return Integer.parseInt(num);
	}
	
	/**
	 * Returns if the variable stored in the progression map is true.
	 * @param variable The variable's name.
	 * @return If the variable is true.
	 */
	private static boolean variableIsTrue(String variable) {
		return config.progress.get(variable).equals(TRUE);
	}

	/**
	 * Returns the variable's number.
	 * @param variable The key's name.
	 * @return The key's value.
	 */
	private static int variableNumber(String variable) {
		return config.progress.get(variable);
	}
	
	/**
	 * Store a number in the progression map.
	 * @param variable The variable name in the map.
	 * @param value The value.
	 */
	private static void setVariable(String variable, int value) {
		config.progress.put(variable, value);
	}
	
	/**
	 * Store a boolean value in the progression map.
	 * @param variable The key in the map.
	 * @param bol A boolean value.
	 */
	private static void setVariable(String variable, boolean bol) {
		config.progress.put(variable, booleanToInt(bol));
	}
}
