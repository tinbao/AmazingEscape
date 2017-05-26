package mycontroller;

import controller.CarController;
import world.Car;
import factories.*;

public class MyAIController extends CarController{
	
	protected TraversalStrategyFactory mtsFactory;
	protected TrapStrategyFactory tsFactory;
	protected ReversingStrategyFactory rsFactory;
	
	public MyAIController(Car car) {
		super(car);
		
		/* creates SINGLETON instances of the factories for strategies */
		mtsFactory = new TraversalStrategyFactory();
		tsFactory = new TrapStrategyFactory();
		rsFactory = new ReversingStrategyFactory();
		
		
	}

	@Override
	public void update(float delta) {
		
		
	}

}
