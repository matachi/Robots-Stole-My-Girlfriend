package rsmg.controller;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class Controller {

	/**
	 * If you want to show the FPS in the console.
	 */
	private boolean showFPSInConsole = true;
	
	/**
	 * The model that is visualized on the screen and should be updated.
	 */
	private State state;

	/**
	 *  Necessary to calculate the time between two loops.
	 */
	long curLoopTime;
	
	/**
	 *  Necessary if FPS is going to be calculated and shown.
	 */
	long nextTimeToPrintFPS;
	long fpsCounter;
	
	
	public Controller() {
		// Let the initial state be level 1
		state = StateFactory.getState(StateFactory.LEVEL1);
		
		startGameLoop();
	}
	
	private void startGameLoop() {
		if (showFPSInConsole)
			runGameLoopWithFPSCounter();
		else
			runGameLoopWithoutFPSCounter();
	}
	
	private void runGameLoopWithoutFPSCounter() {
		initCurLoopTime();
		
		while (true) {
			calculateDelta();
			
			state.update(delta);
		}
	}
	
	private void runGameLoopWithFPSCounter() {
		initFPSCounterVariables();
		initCurLoopTime();
		
		while (true) {
			calculateDelta();
			
			calculateAndPrintFPS();
			
			state.update(delta);
		}
	}
	
	private void initCurLoopTime() {
		curLoopTime = System.currentTimeMillis();
	}
	
	private long calculateDelta() {
		long prevLoopTime = curLoopTime;
		curLoopTime = System.currentTimeMillis();
		return curLoopTime - prevLoopTime;
	}
	
	private void initFPSCounterVariables() {
		nextTimeToPrintFPS = System.currentTimeMillis() + 5000;
		fpsCounter = 0;
	}
	
	private void calculateAndPrintFPS() {
		if (curLoopTime < nextTimeToPrintFPS) {
			fpsCounter++;
		} else {
			long timeUsed = curLoopTime - nextTimeToPrintFPS + 5000;
			nextTimeToPrintFPS = System.currentTimeMillis() + 5000;
			System.out.println(fpsCounter + " frames in " + timeUsed / 1000f
					+ " seconds = " + (fpsCounter / (timeUsed / 1000f)));
			fpsCounter = 0;
		}
	}
}
