package mycontroller;

import java.util.HashMap;

import controller.CarController;
import world.Car;
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
	
	/**
	 * Constructor for the controller (only going to be instiantised once)
	 * @param car object representing the car
	 */
	public MyAIController(Car car) {
		super(car);
		
		/* creates SINGLETON instances of the factories for strategies */
		mtsFactory = new TraversalStrategyFactory(this);
		tsFactory = new TrapStrategyFactory(this);
		rsFactory = new ReversingStrategyFactory(this);
		
		/* start off ready to go */
		state = CarState.DRIVING;
	}

	/**
	 * Update the car's actions based on strategies
	 * @param delta time passing
	 */
	@Override
	public void update(float delta) {
		/* Detect what the next tile is from getView */
		TileType tt = Detector.tileAhead(getView(), getPosition(), getOrientation());
		switch(tt) {
		case GRASS:
			tsFactory.setTrapStrategy(TrapStrategies.GRASS);
			break;
		case LAVA:
			tsFactory.setTrapStrategy(TrapStrategies.LAVA);
			break;
		case MUD:
			tsFactory.setTrapStrategy(TrapStrategies.MUD);
			break;
		default:
			break;
		}

		if(tt != TileType.MAPTILE) {
			tsFactory.getTrapStrategy().update(delta);
		}
		else {
			/* Do left hand traverse normally */
			if(state == CarState.DRIVING){
				mtsFactory.getTraversalStrategy().update(delta);
			}
		}
	}
	
	/**
	 * Chooses a suitable algorithm for the specific environment the car is in
	 * @param currentView car's current viewable range
	 */
	public void chooseAlgorithm(HashMap<Coordinate, MapTile> currentView) {
		
	}
}
