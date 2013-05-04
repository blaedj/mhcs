package mhcs.danielle;

import mhcs.dan.Module.ModuleType;

import com.google.gwt.touch.client.Point;

/**
 * This private class Minimum is a module type (enum Code)
 * and a Point with x/y coordinate corresponding to Java.
 * coordinate system for the display grid.
 */
public class Minimum {
    /**
	 * String is code describing module.
	 */
	private final ModuleType code;

	/**
	 * Point is point on Grid.
	 */
	private final Point point;

	/**
	 * Constructor.
	 * @param point point
	 * @param code string
	 */
	public Minimum(final ModuleType code, final Point point) {
		this.code = code;
		this.point = point;
	}

	/**
	 * getCode returns the enum Code of this Minimum.
	 * @return code (enum Code)
	 */
	public final ModuleType getCode() {
		return code;
	}

	/**
	 * getPoint returns the (x,y) coordinate of this Minimum.
	 * @return (x,y) coordinate (Point)
	 */
	public final Point getPoint() {
		return point;
	}

}
