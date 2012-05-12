package rsmg.model.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import rsmg.model.object.unit.Enemy;
import rsmg.model.object.unit.PCharacter;
import rsmg.model.object.unit.Tankbot;

/**
 * 
 * @author Johan Rignäs
 *
 */
public class TestAggroRange {
	
	@Test
	public void testCharInAggroRange(){
		PCharacter character = new PCharacter(null);
		character.setPosition(4, 4);
		Enemy enemy = new Tankbot(5,5, null);
		
		assertTrue(AggroRange.charInAggroRange(character.getX(), character.getY(), enemy, 2));
	}
}

