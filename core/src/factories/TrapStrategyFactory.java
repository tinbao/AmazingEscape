package factories;

import java.util.HashMap;
import java.util.Map;

import strategies.TrapStrategy;
import strategies.trapStrategies.*;

public class TrapStrategyFactory {
	
	protected enum trapStrategies {
		GRASS, MUD, LAVA;
	}
	
	private TrapStrategyFactory instance;
	private TrapStrategy currentStrategy;
	
	/** A map for all the strategies */
	private Map<trapStrategies, TrapStrategy> strategies = 
			new HashMap<trapStrategies, TrapStrategy>();
	
	/**
	 * Constructor factory for the trap strategies
	 */
	public TrapStrategyFactory() {
		strategies.put(trapStrategies.LAVA, new LavaStrategy());
		strategies.put(trapStrategies.MUD, new MudStrategy());
		strategies.put(trapStrategies.GRASS, new GrassStrategy());
		setTrapStrategy(trapStrategies.LAVA);
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
	public void setTrapStrategy(trapStrategies newStrategy) {
		currentStrategy = strategies.get(newStrategy);
	}
}
