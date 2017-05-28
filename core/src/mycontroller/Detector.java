package mycontroller;

import java.util.HashMap;

import tiles.MapTile;
import utilities.Coordinate;
import world.Car;
import world.WorldSpatial.Direction;

public class Detector {
	
	public static TileType tileAhead(HashMap<Coordinate, MapTile> currentView, String position, Direction orientation) {
		Coordinate currentPosition = new Coordinate(position);
		// horizontal and vertical multipliers for correct direction iteration
		int h=0;
		int v=0;
		System.out.println(orientation);
		switch(orientation) {
			case NORTH:
				v=1;
				break;
			case EAST:
				h=1;
				break;
			case SOUTH:
				v=-1;
				break;
			case WEST:
				h=-1;
				break;
		}
		String sTile;
		for(int i=0; i<= Car.VIEW_SQUARE; i++) {
			sTile = currentView.get(new Coordinate(currentPosition.x+i*h, currentPosition.y+i*v)).toString();
			sTile = sTile.split("@")[0];
			System.out.println(sTile);
			switch(sTile) {
				case "tiles.LavaTrap":
					return TileType.LAVAMUD;
				case "tiles.MudTrap":
					return TileType.LAVAMUD;
			}
		}
		return TileType.MAPTILE;
	}
}
