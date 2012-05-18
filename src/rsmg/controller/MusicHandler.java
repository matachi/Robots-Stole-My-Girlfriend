package rsmg.controller;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import rsmg.io.Config;

/**
 * This class handles starting music tracks.
 * 
 * @author Daniel Jonsson
 *
 */
public final class MusicHandler {

	/**
	 * The available music tracks.
	 */
	public enum Track { MENU_MUSIC, LEVEL_MUSIC, BOSS_MUSIC };
	
	/**
	 * Keeps track of which track is currently being played.
	 */
	private static Track currentTrack;
	
	/**
	 * Start a music track.
	 * 
	 * @param track Which track you want to play.
	 * @throws SlickException
	 */
	public static void startTrack(Track track) throws SlickException {
		System.out.println(track);
		System.out.println(currentTrack);
		System.out.println("");
		if (Config.musicOn() && track != currentTrack) {
			switch (track) {
			case MENU_MUSIC :
				new Music("res/music/WolfRock-WelcomeToTheTemple.ogg", true).loop(1, 0.1f);
				break;
			case LEVEL_MUSIC :
				new Music("res/music/WolfRock-NightOfTheMutants.ogg", true).loop(1, 0.2f);
				break;
			case BOSS_MUSIC :
				new Music("res/music/TwoStepsFromHell-DragonRider.ogg", true).loop(1, 0.1f);
				break;
			default :
				throw new IllegalArgumentException();
			}
			currentTrack = track;
		}
	}
}
