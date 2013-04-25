package mhcs.danielle;

import com.google.gwt.user.client.Window;

public class CoordinateCalculator {
	
	/**
	 * xcoorMap is the coordinate of the module on landing area map.
	 */
	private int xcoorMap; 
	
	/**
	 * ycoorMap is the coordinate of the module on landing area map. 
	 */
	private int ycoorMap;
	
	/**
	 * xcoorGrid is the new x coordinate for the grid (column number).
	 */
	private int xcoorGrid;
	
	/**
	 * ycoorGrid is the new y coordinate for the grid (row number).
	 */
	private int ycoorGrid;
	
	/**
	 * 	Constructs Coordinate Calculator object 
	 *  that will return a grid position for x/y coor.
	 * 
	 * @param xcoor string of module x coord.
	 * @param ycoor string of module y coor.
	 */
	public CoordinateCalculator(int xcoor, int ycoor){
		
		this.xcoorMap = xcoor;
		this.ycoorMap = ycoor;
		
		// Create the appropriate Point for this object
		calculate(this.xcoorMap, this.ycoorMap);
	}
	
	/**
	 * Calculates the grid position for (x,y) map point
	 * @param xcoor x coordinate
	 * @param ycoor y coordinate
	 */
	private void calculate(int xcoor, int ycoor){
		// x coordinate calculation 
		// for grid position
		if(xcoorMap == 0){
			xcoorGrid = 0;
		
		}else if(xcoorMap < 0){
			Window.alert("Map coordinate x should never be negative!");
			xcoorGrid = 0;
		}else{
			xcoorGrid = xcoorMap-1;
		}
		// y coordinate calculation 
		if(ycoorMap > 50){
			ycoorGrid = 0;
		}else if(ycoorMap < 0){
			Window.alert("Map coordinate y should never be negative!");
			ycoorGrid = 0;
		} else if (ycoorMap == 0) {
			ycoorGrid = 0;
		}else{
			ycoorGrid = 50 - ycoorMap;
		}
		
	}
	
	/**
	 * Returns the x position on the display grid
	 * @return x coordinate of grid
	 */
	public int xCoorGrid(){
		return xcoorGrid;
	}
	
	/**
	 * Returns the y position on the display grid
	 * @return y coordinate of grid
	 */
	public int yCoorGrid(){
		return ycoorGrid;
	}
}
