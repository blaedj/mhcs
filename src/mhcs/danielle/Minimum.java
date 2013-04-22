package mhcs.danielle;

import com.google.gwt.touch.client.Point;

/**
 * This private class Minimum is a module type (enum Code)
 * and a Point with x/y coordinate corresponding to Java 
 * coordinate system for the display grid.
 */
public class Minimum {
	
	private String code;
	private Point pt;
	
	public Minimum(String code, Point pt){
		this.code = code;
		this.pt = pt;
	}
	
	/**
	 * getCode returns the enum Code of this Minimum
	 * @return code (enum Code)
	 */
	public String getCode(){
		return code;
	}
	
	/**
	 * getPoint returns the (x,y) coordinate of this Minimum
	 * @return (x,y) coordinate (Point)
	 */
	public Point getPoint(){
		return pt;
	}
	
}
