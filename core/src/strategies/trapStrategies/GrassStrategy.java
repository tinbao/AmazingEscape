package strategies.trapStrategies;

import mycontroller.MyAIController;
import strategies.TrapStrategy;
import world.WorldSpatial;

public class GrassStrategy implements TrapStrategy{

	/* Need controller for car's functions */
	private MyAIController control;
	
	/** Offset used to differentiate between 0 and 360 degrees */
	private int EAST_THRESHOLD = 3;
	
	public GrassStrategy(MyAIController control) {
		this.control = control;
	}
	
	/** 
	 * Update for the positioning of the car under the strategy
	 * @param delta time passed
	 */
	@Override
	public void update(float delta) {
		
		/* adjusts the car so that it moves straight through the grass */
		adjustRight(control.getOrientation(), delta);
		adjustLeft(control.getOrientation(), delta);
	}
	
	/**
	 * Try to orient myself to a degree that I was supposed to be at if I am
	 * misaligned.
	 */
	private void adjustLeft(WorldSpatial.Direction orientation, float delta) {
		
		switch(orientation){
		case EAST:
			if(control.getAngle() > WorldSpatial.EAST_DEGREE_MIN+EAST_THRESHOLD){
				control.turnRight(delta);
			}
			break;
		case NORTH:
			if(control.getAngle() > WorldSpatial.NORTH_DEGREE){
				control.turnRight(delta);
			}
			break;
		case SOUTH:
			if(control.getAngle() > WorldSpatial.SOUTH_DEGREE){
				control.turnRight(delta);
			}
			break;
		case WEST:
			if(control.getAngle() > WorldSpatial.WEST_DEGREE){
				control.turnRight(delta);
			}
			break;
			
		default:
			break;
		}
		
	}

	private void adjustRight(WorldSpatial.Direction orientation, float delta) {
		switch(orientation){
		case EAST:
			if(control.getAngle() > WorldSpatial.SOUTH_DEGREE && control.getAngle() < WorldSpatial.EAST_DEGREE_MAX){
				control.turnLeft(delta);
			}
			break;
		case NORTH:
			if(control.getAngle() < WorldSpatial.NORTH_DEGREE){
				control.turnLeft(delta);
			}
			break;
		case SOUTH:
			if(control.getAngle() < WorldSpatial.SOUTH_DEGREE){
				control.turnLeft(delta);
			}
			break;
		case WEST:
			if(control.getAngle() < WorldSpatial.WEST_DEGREE){
				control.turnLeft(delta);
			}
			break;
			
		default:
			break;
		}
		
	}
	

}
