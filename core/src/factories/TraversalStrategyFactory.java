package factories;

import strategies.MazeTraversalStrategy;

public class TraversalStrategyFactory {
	
	private TraversalStrategyFactory instance;
	
	/**
	 * Constructor factory for the maze traversal strategies
	 */
	public TraversalStrategyFactory() {
		
	}
	
	/**
	 * Gets the traversal strategy for the car at the moment
	 * @return maze traversal strategy for dealing with tight spaces
	 */
	public MazeTraversalStrategy getTraversalStrategy(){
					
		return null;
	}
	
	/**
	 * Sets the traversal strategy for the car at the moment
	 * @return maze traversal strategy for dealing with tight spaces
	 */
	public void setTraversalStrategy(String strategy) {
		
	}
}
