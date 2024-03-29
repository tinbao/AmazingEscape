package mycontroller;

import java.util.HashMap;

import controller.CarController;
import world.Car;
import factories.*;
import utilities.Coordinate;
import tiles.MapTile;

public class MyAIController extends CarController {
	
	private static final double REVOLUTION = 360.;
	private static final double QUADRANTS = 4.;
	private static final double ANGLE_TOLERANCE = 0.05;
	
	/* STRATEGY FACTORIES */
	public TraversalStrategyFactory mtsFactory;
	public TrapStrategyFactory tsFactory;
	public ReversingStrategyFactory rsFactory;
	/** Moving state of the car */
	public CarState state;
	
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
		if(!straight()) {
			System.out.println("Aligning");
			mtsFactory.getTraversalStrategy().update(delta);
			return;
		}
		else {
			/* Detect what the next tile is from getView */
			TileType tt = Detector.tileAhead(getView(), getPosition(), getOrientation());
			switch(tt) {
			case LAVAMUD:
				tsFactory.setTrapStrategy(TrapStrategies.LAVAMUD);
				break;
			default:
				break;
			}
			if(tt == TileType.MAPTILE) {
				/* Do left hand traverse normally */
				if(state == CarState.DRIVING) {
					mtsFactory.getTraversalStrategy().update(delta);
				} else if(state == CarState.REVERSING){
					rsFactory.getReversingStrategy().update(delta);
				}
			}
			else {
				tsFactory.getTrapStrategy().update(delta);
			}
		}
		
	}
	
	private boolean straight() {
		double epsilon = Math.abs((((this.getAngle()/REVOLUTION)*QUADRANTS)%1.));
		System.out.println(epsilon);
		return epsilon < ANGLE_TOLERANCE || epsilon > 1-ANGLE_TOLERANCE;
	}

	/**
	 * Chooses a suitable algorithm for the specific environment the car is in
	 * @param currentView car's current viewable range
	 */
	public void chooseAlgorithm(HashMap<Coordinate, MapTile> currentView) {
		
	}
}
