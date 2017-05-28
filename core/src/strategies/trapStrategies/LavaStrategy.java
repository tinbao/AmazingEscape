package strategies.trapStrategies;

import java.util.HashMap;

import mycontroller.MyAIController;
import strategies.TrapStrategy;
import tiles.MapTile;
import utilities.Coordinate;

public class LavaStrategy implements TrapStrategy{
	
	/* Need controller for car's functions */
	private MyAIController control;
	
	// How many minimum units the wall is away from the player.
	private int wallSensitivity = 2;
	
	/** 
	 * Update for the positioning of the car under the strategy
	 * @param delta time passed
	 */
	@Override
	public void update(float delta) {
		
		// Gets what the car can see
		HashMap<Coordinate, MapTile> currentView = control.getView();
		
		if (checkLavaEast(currentView) && checkLavaWest(currentView) && checkLavaNorth(currentView)) {
			//new DirectReverseStrategy();
		}
		
	}
	
	public LavaStrategy(MyAIController control) {
		this.control = control;
	}

	public boolean checkLavaEast(HashMap<Coordinate, MapTile> currentView){
		// Check tiles to my right
		Coordinate currentPosition = new Coordinate(control.getPosition());
		for(int i = 0; i <= wallSensitivity; i++){
			MapTile tile = currentView.get(new Coordinate(currentPosition.x+i, currentPosition.y));
			if(tile.toString().startsWith("tiles.LavaTrap") || tile.toString().startsWith("tiles.GrassTrap")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkLavaWest(HashMap<Coordinate,MapTile> currentView){
		// Check tiles to my left
		Coordinate currentPosition = new Coordinate(control.getPosition());
		for(int i = 0; i <= wallSensitivity; i++){
			MapTile tile = currentView.get(new Coordinate(currentPosition.x-i, currentPosition.y));
			if(tile.toString().startsWith("tiles.LavaTrap")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkLavaNorth(HashMap<Coordinate,MapTile> currentView){
		// Check tiles to towards the top
		Coordinate currentPosition = new Coordinate(control.getPosition());
		for(int i = 0; i <= wallSensitivity; i++){
			MapTile tile = currentView.get(new Coordinate(currentPosition.x, currentPosition.y+i));
			if(tile.toString().startsWith("tiles.LavaTrap")) {
				return true;
			}
		}
		return false;
	}

}
