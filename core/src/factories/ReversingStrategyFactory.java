package factories;

import strategies.ReversingStrategy;

public class ReversingStrategyFactory {
	
	private ReversingStrategy instance;
	
	/**
	 * Constructor factory for the reversing strategies
	 */
	public ReversingStrategyFactory() {
		
	}
	
	/**
	 * Gets the reversing strategy for the car at the moment
	 * @return reversal strategy for dealing with tight spaces
	 */
	public ReversingStrategy getReversingStrategy() {
		
		return null;
	}
	
	/**
	 * Sets the reversing strategy for the car at the moment
	 * @param strategy comparitor string to check which strategy is employed
	 */
	public void setReversingStrategy(String strategy) {
		
	}
}
