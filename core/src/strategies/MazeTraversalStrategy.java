package strategies;

public interface MazeTraversalStrategy {
	
	/** 
	 * Update for the positioning of the car under the strategy
	 * @param delta time passed
	 */
	public abstract void update(float delta);
	
	
}
