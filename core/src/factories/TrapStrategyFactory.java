package factories;

import strategies.ReversingStrategy;
import strategies.TrapStrategy;

public class TrapStrategyFactory {
	
	private TrapStrategy instance;
	
	/**
	 * Constructor factory for the trap strategies
	 */
	public TrapStrategyFactory() {
		
	}
	
	/**
	 * Gets the trap avoidance strategy for the car at the moment
	 * @return trap strategy for dealing with special cells.
	 */
	public ReversingStrategy getReversingStrategy() {
		return null;
	}
	
	/**
	 * 
	 * @param strategy
	 * @return
	 */
	public ReversingStrategy setReversingStrategy(String strategy) {
		return null;
	}
	
}
