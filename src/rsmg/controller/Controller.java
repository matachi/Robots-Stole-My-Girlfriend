package rsmg.controller;

public class Controller {

	private Model model;
	
	public Controller() {
	}
	
	private void gameLoop() {
		long startTime = System.currentTimeMillis() + 5000;
		long fps = 0;
		
		while (true) {
			double delta;
			model.update(delta);
			
			if (startTime > System.currentTimeMillis()) {
				fps++;
			} else {
				long timeUsed = 5000 + (startTime - System.currentTimeMillis());
				startTime = System.currentTimeMillis() + 5000;
				System.out.println(fps + " frames in " + timeUsed / 1000f
						+ " seconds = " + (fps / (timeUsed / 1000f)));
				fps = 0;
			}
		}
	}
}
