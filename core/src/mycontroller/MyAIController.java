package mycontroller;

import controller.CarController;
import world.Car;
import factories.*;

public class MyAIController extends CarController{
	
	/* STRATEGY FACTORIES */
	protected TraversalStrategyFactory mtsFactory;
	protected TrapStrategyFactory tsFactory;
	protected ReversingStrategyFactory rsFactory;
	/** Moving state of the car */
	protected CarState state;
	
	/** Cars move at */
	private final float CAR_SPEED = 3;
	
	public MyAIController(Car car) {
		super(car);
		
		/* creates SINGLETON instances of the factories for strategies */
		mtsFactory = new TraversalStrategyFactory();
		tsFactory = new TrapStrategyFactory();
		rsFactory = new ReversingStrategyFactory();
		
		/* start off parked and ready to go */
		state = CarState.STOPPED;
	}

	@Override
	public void update(float delta) {
		
		
	}

}
