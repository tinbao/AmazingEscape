package strategies.mazeTraversal;

import java.util.HashMap;

import mycontroller.MyAIController;
import strategies.MazeTraversalStrategy;
import tiles.MapTile;
import utilities.Coordinate;
import world.WorldSpatial;

/**
 * Left hand algorithm as used in the souce code
 * @author TB RS VP
 *
 */
public class LeftHandTraversal implements MazeTraversalStrategy{

	/* Need controller for car's functions */
	private MyAIController control;
	
	// How many minimum units the wall is away from the player.
	private int wallSensitivity = 2;
		
	/** Car's top speed */
	private final float CAR_SPEED = 2.5f;
	
	/** Offset used to differentiate between 0 and 360 degrees */
	private int EAST_THRESHOLD = 3;
	
	private boolean isFollowingWall; // This is initialized when the car sticks to a wall.
	private WorldSpatial.RelativeDirection lastTurnDirection; // Shows the last turn direction the car takes.
	private boolean isTurningLeft;
	private boolean isTurningRight; 
	private WorldSpatial.Direction previousState; // Keeps track of the previous state
	
	/**
	 * Constructor for the traversal algorithm
	 * @param control controller
	 */
	public LeftHandTraversal(MyAIController control) {
		this.control = control;
		
		this.isFollowingWall = false;
		
		this.lastTurnDirection = null;
		this.isTurningLeft = false;
		this.isTurningRight = false; 
		this.previousState = null;
	}
	
	@Override
	/** 
	 * Update for the positioning of the car under the strategy
	 * @param delta time passed
	 */
	public void update(float delta){
		
		// Gets what the car can see
		HashMap<Coordinate, MapTile> currentView = control.getView();
		
		checkStateChange();
		
		// If you are not following a wall initially, find a wall to stick to!
		if(!isFollowingWall){
			if(control.getVelocity() < CAR_SPEED){
				control.applyForwardAcceleration();
			}
			// Turn towards the north
			if(!control.getOrientation().equals(WorldSpatial.Direction.NORTH)){
				lastTurnDirection = WorldSpatial.RelativeDirection.LEFT;
				applyLeftTurn(control.getOrientation(),delta);
			}
			if(checkNorth(currentView)){
				// Turn right until we go back to east!
				if(!control.getOrientation().equals(WorldSpatial.Direction.EAST)){
					lastTurnDirection = WorldSpatial.RelativeDirection.RIGHT;
					applyRightTurn(control.getOrientation(),delta);
				}
				else{
					isFollowingWall = true;
				}
			}
		}
		// Once the car is already stuck to a wall, apply the following logic
		else{
			if(control.getVelocity() < CAR_SPEED){
				control.applyForwardAcceleration();
			}
			// Readjust the car if it is misaligned.
			readjust(lastTurnDirection,delta);
			
			if(isTurningRight){
				applyRightTurn(control.getOrientation(),delta);
			}
			else if(isTurningLeft){
				// Apply the left turn if you are not currently near a wall.
				if(!checkFollowingWall(control.getOrientation(),currentView)){
					applyLeftTurn(control.getOrientation(),delta);
				}
				else{
					isTurningLeft = false;
				}
			}
			// Try to determine whether or not the car is next to a wall.
			else if(checkFollowingWall(control.getOrientation(),currentView)){
				// Maintain some velocity
				if(control.getVelocity() < CAR_SPEED){
					control.applyForwardAcceleration();
				}
				// If there is wall ahead, turn right!
				if(checkWallAhead(control.getOrientation(),currentView)){
					lastTurnDirection = WorldSpatial.RelativeDirection.RIGHT;
					isTurningRight = true;				
					
				}

			}
			// This indicates that I can do a left turn if I am not turning right
			else{
				lastTurnDirection = WorldSpatial.RelativeDirection.LEFT;
				isTurningLeft = true;
			}
		}
	}
	/**
	 * Readjust the car to the orientation we are in.
	 * @param lastTurnDirection 
	 * @param delta time passed
	 */
	public void readjust(WorldSpatial.RelativeDirection lastTurnDirection, float delta) {
		if(lastTurnDirection != null){
			if(!isTurningRight && lastTurnDirection.equals(WorldSpatial.RelativeDirection.RIGHT)){
				adjustRight(control.getOrientation(),delta);
			}
			else if(!isTurningLeft && lastTurnDirection.equals(WorldSpatial.RelativeDirection.LEFT)){
				adjustLeft(control.getOrientation(),delta);
			}
		}
		
	}
	
	/**
	 * Try to orient myself to a degree that I was supposed to be at if I am
	 * misaligned.
	 */
	private void adjustLeft(WorldSpatial.Direction orientation, float delta) {
		
		switch(orientation){
		case EAST:
			if(control.getAngle() > WorldSpatial.EAST_DEGREE_MIN+EAST_THRESHOLD){
				control.turnRight(delta);
			}
			break;
		case NORTH:
			if(control.getAngle() > WorldSpatial.NORTH_DEGREE){
				control.turnRight(delta);
			}
			break;
		case SOUTH:
			if(control.getAngle() > WorldSpatial.SOUTH_DEGREE){
				control.turnRight(delta);
			}
			break;
		case WEST:
			if(control.getAngle() > WorldSpatial.WEST_DEGREE){
				control.turnRight(delta);
			}
			break;
			
		default:
			break;
		}
		
	}

	private void adjustRight(WorldSpatial.Direction orientation, float delta) {
		switch(orientation){
		case EAST:
			if(control.getAngle() > WorldSpatial.SOUTH_DEGREE && control.getAngle() < WorldSpatial.EAST_DEGREE_MAX){
				control.turnLeft(delta);
			}
			break;
		case NORTH:
			if(control.getAngle() < WorldSpatial.NORTH_DEGREE){
				control.turnLeft(delta);
			}
			break;
		case SOUTH:
			if(control.getAngle() < WorldSpatial.SOUTH_DEGREE){
				control.turnLeft(delta);
			}
			break;
		case WEST:
			if(control.getAngle() < WorldSpatial.WEST_DEGREE){
				control.turnLeft(delta);
			}
			break;
			
		default:
			break;
		}
		
	}
	
	/**
	 * Checks whether the car's state has changed or not, stops turning if it
	 *  already has.
	 */
	private void checkStateChange() {
		if(previousState == null){
			previousState = control.getOrientation();
		}
		else{
			if(previousState != control.getOrientation()){
				if(isTurningLeft){
					isTurningLeft = false;
				}
				if(isTurningRight){
					isTurningRight = false;
				}
				previousState = control.getOrientation();
			}
		}
	}
	
	/**
	 * Check if you have a wall in front of you!
	 * @param orientation the orientation we are in based on WorldSpatial
	 * @param currentView what the car can currently see
	 * @return
	 */
	private boolean checkWallAhead(WorldSpatial.Direction orientation, HashMap<Coordinate, MapTile> currentView){
		switch(orientation){
		case EAST:
			return checkEast(currentView);
		case NORTH:
			return checkNorth(currentView);
		case SOUTH:
			return checkSouth(currentView);
		case WEST:
			return checkWest(currentView);
		default:
			return false;
		
		}
	}
	
	/**
	 * Check if the wall is on your left hand side given your orientation
	 * @param orientation
	 * @param currentView
	 * @return
	 */
	private boolean checkFollowingWall(WorldSpatial.Direction orientation, HashMap<Coordinate, MapTile> currentView) {
		
		switch(orientation){
		case EAST:
			return checkNorth(currentView);
		case NORTH:
			return checkWest(currentView);
		case SOUTH:
			return checkEast(currentView);
		case WEST:
			return checkSouth(currentView);
		default:
			return false;
		}
		
	}
	

	/**
	 * Method below just iterates through the list and check in the correct coordinates.
	 * i.e. Given your current position is 10,10
	 * checkEast will check up to wallSensitivity amount of tiles to the right.
	 * checkWest will check up to wallSensitivity amount of tiles to the left.
	 * checkNorth will check up to wallSensitivity amount of tiles to the top.
	 * checkSouth will check up to wallSensitivity amount of tiles below.
	 */
	public boolean checkEast(HashMap<Coordinate, MapTile> currentView){
		// Check tiles to my right
		Coordinate currentPosition = new Coordinate(control.getPosition());
		for(int i = 0; i <= wallSensitivity; i++){
			MapTile tile = currentView.get(new Coordinate(currentPosition.x+i, currentPosition.y));
			if(tile.getName().equals("Wall")){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkWest(HashMap<Coordinate,MapTile> currentView){
		// Check tiles to my left
		Coordinate currentPosition = new Coordinate(control.getPosition());
		for(int i = 0; i <= wallSensitivity; i++){
			MapTile tile = currentView.get(new Coordinate(currentPosition.x-i, currentPosition.y));
			if(tile.getName().equals("Wall")){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkNorth(HashMap<Coordinate,MapTile> currentView){
		// Check tiles to towards the top
		Coordinate currentPosition = new Coordinate(control.getPosition());
		for(int i = 0; i <= wallSensitivity; i++){
			MapTile tile = currentView.get(new Coordinate(currentPosition.x, currentPosition.y+i));
			if(tile.getName().equals("Wall")){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkSouth(HashMap<Coordinate,MapTile> currentView){
		// Check tiles towards the bottom
		Coordinate currentPosition = new Coordinate(control.getPosition());
		for(int i = 0; i <= wallSensitivity; i++){
			MapTile tile = currentView.get(new Coordinate(currentPosition.x, currentPosition.y-i));
			if(tile.getName().equals("Wall")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Turn the car counter clock wise (think of a compass going counter clock-wise)
	 */
	private void applyLeftTurn(WorldSpatial.Direction orientation, float delta) {
		switch(orientation){
		case EAST:
			if(!control.getOrientation().equals(WorldSpatial.Direction.NORTH)){
				control.turnLeft(delta);
			}
			break;
		case NORTH:
			if(!control.getOrientation().equals(WorldSpatial.Direction.WEST)){
				control.turnLeft(delta);
			}
			break;
		case SOUTH:
			if(!control.getOrientation().equals(WorldSpatial.Direction.EAST)){
				control.turnLeft(delta);
			}
			break;
		case WEST:
			if(!control.getOrientation().equals(WorldSpatial.Direction.SOUTH)){
				control.turnLeft(delta);
			}
			break;
		default:
			break;
		
		}
		
	}
	
	/**
	 * Turn the car clock wise (think of a compass going clock-wise)
	 */
	private void applyRightTurn(WorldSpatial.Direction orientation, float delta) {
		switch(orientation){
		case EAST:
			if(!control.getOrientation().equals(WorldSpatial.Direction.SOUTH)){
				control.turnRight(delta);
			}
			break;
		case NORTH:
			if(!control.getOrientation().equals(WorldSpatial.Direction.EAST)){
				control.turnRight(delta);
			}
			break;
		case SOUTH:
			if(!control.getOrientation().equals(WorldSpatial.Direction.WEST)){
				control.turnRight(delta);
			}
			break;
		case WEST:
			if(!control.getOrientation().equals(WorldSpatial.Direction.NORTH)){
				control.turnRight(delta);
			}
			break;
		default:
			break;
		
		}
		
	}
}
