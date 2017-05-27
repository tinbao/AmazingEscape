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
		tsFactory = new TrapStrategyFactory();
		rsFactory = new ReversingStrategyFactory();
		
		/* start off ready to go */
		state = CarState.DRIVING;
	}

	/**
	 * Update the car's actions based on strategies
	 * @param delta time passing
	 */
	@Override
	public void update(float delta) {
		/* Do left hand traverse normally */
		if(state == CarState.DRIVING){
			mtsFactory.getTraversalStrategy().update(delta);
		} 
		/* Do reversing strategy when in reverse mode */
		else if (state == CarState.REVERSING) {
			rsFactory.getReversingStrategy().update(delta);
		}
		
		/* Always be on the lookout for traps */
		tsFactory.getTrapStrategy().update(delta);
		
		/* Update the car's state after the strategy's decisions */
		state.event(delta);
	}
	
	/**
	 * Chooses a suitable algorithm for the specific environment the car is in
	 * @param currentView car's current viewable range
	 */
	public void chooseAlgorithm(HashMap<Coordinate, MapTile> currentView) {
		
	}
}
