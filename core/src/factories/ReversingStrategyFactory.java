package factories;

import java.util.HashMap;
import java.util.Map;

import mycontroller.MyAIController;
import strategies.ReversingStrategy;
import strategies.reversingStrategies.*;

public class ReversingStrategyFactory {
	
	protected enum reversingStrategies{
		STRAIGHT, U_TURN, THREE_POINT;
	}
	
	/** A map for all the strategies */
	private Map<reversingStrategies, ReversingStrategy> strategies = 
			new HashMap<reversingStrategies, ReversingStrategy>();
	
	private ReversingStrategyFactory instance;
	private ReversingStrategy currentStrategy;
	
	/**
	 * Constructor factory for the reversing strategies
	 */
	public ReversingStrategyFactory(MyAIController c) {
		strategies.put(reversingStrategies.STRAIGHT, new DirectReverseStrategy(c));
		strategies.put(reversingStrategies.U_TURN, new UTurnStrategy());
		strategies.put(reversingStrategies.THREE_POINT, new ThreePointTurnStrategy());
		
		setReversingStrategy(reversingStrategies.STRAIGHT);
	}
	
	/**
	 * Gets the reversing strategy for the car at the moment
	 * @return reversal strategy for dealing with tight spaces
	 */
	public ReversingStrategyFactory getInstance() {
		return instance;
	}
	
	/**
	 * Switches the current employed strategy
	 * @param newStrategy changing strategy
	 */
	public void setReversingStrategy(reversingStrategies newStrategy) {
		currentStrategy = strategies.get(newStrategy);
	}
	
	/**
	 * Gets the reversing strategy for the car in use
	 * @return maze reversing strategy for dealing with dead ends
	 */
	public ReversingStrategy getReversingStrategy(){
		return currentStrategy;
	}
}
