package factories;

import java.util.HashMap;
import java.util.Map;

import mycontroller.MyAIController;
import strategies.TrapStrategy;
import strategies.trapStrategies.*;

public class TrapStrategyFactory {
	
	private TrapStrategyFactory instance;
	private TrapStrategy currentStrategy;
	
	/** A map for all the strategies */
	private Map<TrapStrategies, TrapStrategy> strategies = 
			new HashMap<TrapStrategies, TrapStrategy>();
	
	/**
	 * Constructor factory for the trap strategies
	 */
	public TrapStrategyFactory(MyAIController c) {
		strategies.put(TrapStrategies.LAVA, new LavaStrategy(c));
		strategies.put(TrapStrategies.MUD, new MudStrategy());
		strategies.put(TrapStrategies.GRASS, new GrassStrategy(c));
		
		setTrapStrategy(TrapStrategies.LAVA);
	}
	
	/**
	 * Getter for the instance (factory)
	 * @return trap strategy factory
	 */
	public TrapStrategyFactory getInstance() {
		return instance;
	}
	
	/**
	 * Gets the trap strategy for the car at a deadend
	 * @return trap strategy for deadends 
	 */
	public TrapStrategy getTrapStrategy() {
		return currentStrategy;
	}
	
	/**
	 * Switches the current employed strategy
	 * @param newStrategy changing strategy
	 */
	public void setTrapStrategy(TrapStrategies newStrategy) {
		currentStrategy = strategies.get(newStrategy);
	}
}
