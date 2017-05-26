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
	 * Gets the reversing strategy for the car at a deadend
	 * @return reversing strategy for deadends 
	 */
	public ReversingStrategy getReversingStrategy() {
		return null;
	}
	
	/**
	 * Sets the new reversing strategy for the car at a deadend
	 * @param reversing strategy for deadends 
	 */
	public void setReversingStrategy(String strategy) {
		
	}
}
