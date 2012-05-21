package rsmg.model.object.bullet;

import rsmg.io.CharacterProgress;
import rsmg.model.object.InteractiveObject;

/**
 * This class is responsible for creating the explosions.
 * 
 * @author Daniel Jonsson
 *
 */
public final class ExplosionFactory {

	/**
	 * Creates an explosion on top of an object.
	 * 
	 * @param object On which object the explosion should be created.
	 * @return An explosion.
	 */
	public static Bullet getExplosion(InteractiveObject object) {
		if (CharacterProgress.isIncRPGAoEUnlocked())
			return new BigExplosion(object);
		else
			return new SmallExplosion(object);
	}
}
