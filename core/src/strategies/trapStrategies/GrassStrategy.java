package strategies.trapStrategies;

import mycontroller.MyAIController;
import strategies.TrapStrategy;

public class GrassStrategy implements TrapStrategy{

	/* Need controller for car's functions */
	private MyAIController control;
	
	/** Offset used to differentiate between 0 and 360 degrees */
	private int EAST_THRESHOLD = 3;
	
	public GrassStrategy(MyAIController control) {
		this.control = control;
	}
	
	@Override
	/** 
	 * Update for the positioning of the car under the strategy
	 * @param delta time passed
	 */
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
