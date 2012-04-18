package rsmg.model;

import java.util.ArrayList;
import java.util.Iterator;

public class EnemyList implements Iterable<Enemy> {
	private ArrayList<Enemy> enemyArray;
	
	@Override
	public Iterator<Enemy> iterator() {
		return enemyArray.iterator();
	}
	
	public Enemy get(int index) {
		return enemyArray.get(index);
	}
	
	public int size(){
		return enemyArray.size();
	}
	
	public void add(Enemy enemy){
		enemyArray.add(enemy);
	}
	
	public void remove(int index){
		enemyArray.remove(index);
	}

}
