package mhcs.danielle;

public class CoordinateCalculator {
	
	private int xcoorMap; 
	private int ycoorMap;
	private int xcoorGrid;
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
		xcoorGrid = xcoorMap-1;
		
		// y coordinate calculation 
		ycoorGrid = 50-ycoor;
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
