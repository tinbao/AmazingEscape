package factories;

import java.util.HashMap;
import java.util.Map;

import mycontroller.MyAIController;
import strategies.MazeTraversalStrategy;
import strategies.mazeTraversal.LeftHandTraversal;

public class TraversalStrategyFactory {
	
	protected enum traversalStrategies {
		LEFT_HAND;
	}
	
	private static TraversalStrategyFactory instance;
	private MazeTraversalStrategy currentStrategy;
	
	/** A map for all the strategies */
	private Map<traversalStrategies, MazeTraversalStrategy> strategies = 
			new HashMap<traversalStrategies, MazeTraversalStrategy>();
	
	/**
	 * Constructor factory for the maze traversal strategies
	 */
	public TraversalStrategyFactory(MyAIController c) {
		strategies.put(traversalStrategies.LEFT_HAND, new LeftHandTraversal(c));
		setTraversalStrategy(traversalStrategies.LEFT_HAND);
	}
	
	/**
	 * Getter for the instance (factory)
	 * @return traversal strategy factory
	 */
	public TraversalStrategyFactory getInstance() {
		return instance;
	}
	
	/**
	 * Switches the current employed strategy
	 * @param newStrategy changing strategy
	 */
	public void setTraversalStrategy(traversalStrategies newStrategy) {
		currentStrategy = strategies.get(newStrategy);
	}
	
	/**
	 * Gets the traversal strategy for the car at the moment
	 * @return maze traversal strategy for dealing with tight spaces
	 */
	public MazeTraversalStrategy getTraversalStrategy(){
		return currentStrategy;
	}
}
