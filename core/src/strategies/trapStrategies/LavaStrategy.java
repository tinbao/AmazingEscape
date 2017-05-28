package strategies.trapStrategies;

import mycontroller.MyAIController;
import strategies.TrapStrategy;

public class LavaStrategy implements TrapStrategy{
	
	private MyAIController control;
	
	/** 
	 * Update for the positioning of the car under the strategy
	 * @param delta time passed
	 */
	@Override
	public void update(float delta) {
		
	}

	public LavaStrategy(MyAIController control) {
		this.control = control;
	}
	
	
	
}

