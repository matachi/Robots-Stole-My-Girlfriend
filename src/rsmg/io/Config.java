package rsmg.io;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * A class which reads and writes to the configuration file. The config file
 * says if the game shall be in full screen mode, if the music should be on and
 * so on.
 * @author Daniel Jonsson
 *
 */
public class Config {
	
	/**
	 * Path to the configuration file.
	 */
	private static final String configFilePath = "res/config/config.txt";
	
	/**
	 * Make this class to a singleton.
	 */
	private static final Config config = new Config();
	
	/**
	 * If full screen should be turned on.
	 */
	private boolean fullScreenOn;

	/**
	 * If music should be turned on.
	 */
	private boolean musicOn;

	/**
	 * If sound effects should be turned on.
	 */
	private boolean soundEffectsOn;
	
	/**
	 * Make this class to a singleton. Reads the settings from the config file
	 * and stores the values in this class.
	 */
	private Config() {
		SAXBuilder builder = new SAXBuilder();
		try {
			// The config document
			Document document = builder.build(configFilePath);
			
			// The root node in the config document
			Element rootNode = document.getRootElement();
			
			// Read and store the settings to the class
			fullScreenOn = rootNode.getChild("fullScreen").getText().equals("true");
			musicOn = rootNode.getChild("music").getText().equals("true");
			soundEffectsOn = rootNode.getChild("soundEffects").getText().equals("true");
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns if the game should be played in full screen.
	 * @return If the game should be played in full screen.
	 */
	public static boolean fullScreenOn() {
		return config.fullScreenOn;
	}
	
	/**
	 * Returns if music should be played in the game.
	 * @return If music should be played in the game.
	 */
	public static boolean musicOn() {
		return config.musicOn;
	}

	/**
	 * Returns if sound effects should be played in the game.
	 * @return If sound effects should be played in the game.
	 */
	public static boolean soundEffectsOn() {
		return config.soundEffectsOn;
	}
	
	/**
	 * Store that the game should be played in full screen mode.
	 * @param on If full screen should be turned on.
	 */
	public static void setFullScreenOn(boolean on) {
		config.fullScreenOn = on;
	}

	/**
	 * Store that music should be turned on.
	 * @param on If music should be turned on.
	 */
	public static void setMusicOn(boolean on) {
		config.musicOn = on;
	}

	/**
	 * Store that sound effects should be turned on.
	 * @param on If sound effects should be turned on.
	 */
	public static void setSoundEffectsOn(boolean on) {
		config.soundEffectsOn = on;
	}
	
	/**
	 * Write the configurations to the hard drive.
	 * Until this is run, changed settings are only available in the current
	 * session, and lost when closing the game.
	 */
	public static void saveConfigFile() {
		SAXBuilder builder = new SAXBuilder();
        try {
        	// Instance of the config document
        	Document document = builder.build(configFilePath);

			// The root node in the config document
			Element rootNode = document.getRootElement();
			
			// Store the settings in the instance of the config document
			rootNode.getChild("fullScreen").setText(Boolean.toString(fullScreenOn()));
			rootNode.getChild("music").setText(Boolean.toString(musicOn()));
			rootNode.getChild("soundEffects").setText(Boolean.toString(soundEffectsOn()));
        	
			// Write the document to the config file on the HDD
    		XMLOutputter outputter = new XMLOutputter();
            FileWriter writer = new FileWriter(configFilePath);
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
}
