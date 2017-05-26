package mycontroller;

import java.util.HashMap;

import controller.CarController;
import world.Car;
import world.WorldSpatial;
import factories.*;
import utilities.Coordinate;
import tiles.MapTile;

public class MyAIController extends CarController{
	
	/* STRATEGY FACTORIES */
	protected TraversalStrategyFactory mtsFactory;
	protected TrapStrategyFactory tsFactory;
	protected ReversingStrategyFactory rsFactory;
	/** Moving state of the car */
	protected CarState state;
	
	private boolean isTurningLeft = false;
	private boolean isTurningRight = false; 
	
	/** Car's top speed */
	private final float CAR_SPEED = 3;
	
	/** Offset used to differentiate between 0 and 360 degrees */
	private int EAST_THRESHOLD = 3;
		
	/**
	 * Constructor for the controller (only going to be instiantised once)
	 * @param car object representing the car
	 */
	public MyAIController(Car car) {
		super(car);
		
		/* creates SINGLETON instances of the factories for strategies */
		mtsFactory = new TraversalStrategyFactory();
		tsFactory = new TrapStrategyFactory();
		rsFactory = new ReversingStrategyFactory();
		
		/* start off parked and ready to go */
		state = CarState.STOPPED;
	}

	/**
	 * Update the car
	 * @param delta time passing
	 */
	@Override
	public void update(float delta) {
		
		
	}
	
	/**
	 * Chooses a suitable algorithm for the specific environment the car is in
	 * @param currentView car's current viewable range
	 */
	public void chooseAlgorithm(HashMap<Coordinate, MapTile> currentView) {
		
	}
	
	
	/**
	 * Readjust the car to the orientation we are in.
	 * @param lastTurnDirection 
	 * @param delta time passed
	 */
	@SuppressWarnings("unused")
	private void readjust(WorldSpatial.RelativeDirection lastTurnDirection, float delta) {
		if(lastTurnDirection != null){
			if(!isTurningRight && lastTurnDirection.equals(WorldSpatial.RelativeDirection.RIGHT)){
				adjustRight(getOrientation(),delta);
			}
			else if(!isTurningLeft && lastTurnDirection.equals(WorldSpatial.RelativeDirection.LEFT)){
				adjustLeft(getOrientation(),delta);
			}
		}
		
	}
	
	/**
	 * Try to orient myself to a degree that I was supposed to be at if I am
	 * misaligned.
	 */
	private void adjustLeft(WorldSpatial.Direction orientation, float delta) {
		
		switch(orientation){
		case EAST:
			if(getAngle() > WorldSpatial.EAST_DEGREE_MIN+EAST_THRESHOLD){
				turnRight(delta);
			}
			break;
		case NORTH:
			if(getAngle() > WorldSpatial.NORTH_DEGREE){
				turnRight(delta);
			}
			break;
		case SOUTH:
			if(getAngle() > WorldSpatial.SOUTH_DEGREE){
				turnRight(delta);
			}
			break;
		case WEST:
			if(getAngle() > WorldSpatial.WEST_DEGREE){
				turnRight(delta);
			}
			break;
			
		default:
			break;
		}
		
	}

	private void adjustRight(WorldSpatial.Direction orientation, float delta) {
		switch(orientation){
		case EAST:
			if(getAngle() > WorldSpatial.SOUTH_DEGREE && getAngle() < WorldSpatial.EAST_DEGREE_MAX){
				turnLeft(delta);
			}
			break;
		case NORTH:
			if(getAngle() < WorldSpatial.NORTH_DEGREE){
				turnLeft(delta);
			}
			break;
		case SOUTH:
			if(getAngle() < WorldSpatial.SOUTH_DEGREE){
				turnLeft(delta);
			}
			break;
		case WEST:
			if(getAngle() < WorldSpatial.WEST_DEGREE){
				turnLeft(delta);
			}
			break;
			
		default:
			break;
		}
		
	}
}
