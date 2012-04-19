	package rsmg.model;

	import rsmg.util.Vector2d;
	
	/**
	 * 
	 * @param x horizontal coordinate for where the bullet spawns
	 * @param y vertical coordinate for where the bullet spawns
	 * @param width width of the bullet
	 * @param height height of the bullet
	 * @param bulletType integer value representing what kind of bullet this is (used for graphics)
	 * @param name Name of the bullet.
	 */
	public class Bullet extends InteractiveObject {
		int dmg;
		
		public Bullet(double x, double y, double width, double height,
				String name, int dmg, Vector2d velocity) {
			
			super(x, y, width, height, name);
			this.dmg = dmg;
			this.setVelocity(velocity);
			
			// TODO Auto-generated constructor stub
		}



		public int getDamage(){
			return dmg;
		}


		@Override
		public void collide(InteractiveObject obj) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * method consistently making it possible for bullets to accelerate / update
		 */
		public void update(double delta){
			//nothing at this point
		}
	}
