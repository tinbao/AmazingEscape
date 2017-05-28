package strategies.trapStrategies;

import mycontroller.CarState;
import mycontroller.MyAIController;
import strategies.TrapStrategy;

public class LavaMudStrategy implements TrapStrategy {
	
	MyAIController control;
	
	public LavaMudStrategy(MyAIController control) {
		this.control = control;
	}
	
	@Override
	/** 
	 * Update for the positioning of the car under the strategy
	 * @param delta time passed
	 */
	public void update(float delta) {
		if(seeRoad()) {
			control.applyForwardAcceleration();
		}
		else {
			/* Do left hand traverse normally */
			if(control.state == CarState.DRIVING) {
				control.mtsFactory.getTraversalStrategy().update(delta);
			} else if(control.state == CarState.REVERSING){
				control.rsFactory.getReversingStrategy().update(delta);
			}
		}
	}

	private boolean seeRoad() {
		return false;
	}

}
