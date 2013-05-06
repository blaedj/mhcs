package mhcs.danielle;

import java.util.ArrayList;

import com.google.gwt.touch.client.Point;

import mhcs.dan.Module;
import mhcs.dan.Module.ModuleType;
import mhcs.dan.ModuleList;


/**
 *  MinimumConfiguration objects returns the
 *  grid indices of the appropriate locations
 *  of each module in the configuration.
 *   @author daniellestewart
 */
public class MinimumConfiguration {
    /**
     * Module list to work with.
     */
    private ModuleList theList;
    /**
	 * minArray.
	 */
	private ArrayList<Minimum> minArray;
	/**
	 * allGood flag to show if min config is possible.
	 */
	private boolean allGood;
	/**
	 * adjustX is calculated by findCentroid and will
	 * adjust accordingly.
	 */
	private int adjustX;
	/**
	 * adjustY is calculated by findCentroid and will
	 * adjust accordingly.
	 */
	private int adjustY;
	/**
	 * MinimumConfiguration constructor creates
	 * an object that consists of two lists: one of
	 * module type and one of coordinate points.
	 * This coordinate point is returned as Java
	 * coordinates and *not* map coordinates.
	 * May return empty array list. If this happens,
	 * something went wrong in testing for a possible configuration.
	 */

	public MinimumConfiguration() {
		// Initialize data members
		adjustX = 0;
		adjustY = 0;
		theList = ModuleList.get();
		createMinArray();
	}
	/**
	 * Creates minimum array.
	 */
	private void createMinArray() {
	    this.minArray = new ArrayList<Minimum>();
	    // Go through modList and copy pertinent information.
	    for (int k = 0; k < theList.size(); k++) {
	        Module tMod = theList.get(k);
	        Point tPoint = new Point(
	                Integer.parseInt(tMod.getXCoor()),
	                Integer.parseInt(tMod.getYCoor()));
	        ModuleType tType = tMod.getType();
	        this.minArray.add(new Minimum(tType, tPoint));
	    }
	    // Now the array has been created.
	}
	/**
	 * Tests to see if minimum configuration is possible.
	 * @return boolean showing if minimum configuration is possible.
	 */
	public static boolean testMinConfig() {
		// Set up some local variables for static function
	    // If there is no module list, there cannot be
	    // configurations.

	    MinimumConfiguration minCon = new MinimumConfiguration();
	    ArrayList<Minimum> minArray = minCon.getMinArray(ModuleList.get());
	    if (minArray.size() == 0) {
	        return false;
	    }
	    ModuleList theList = ModuleList.get();
	    if (theList.size() < 2 + (2 * 2 * 2)) {
	        return false;
	    }
		// We have a list to test.
		Module temp;
		int i = 0;
		boolean allGood = true;

		// create array size 10 for type count.
		// Initialize all to zero.
		int[] codes = new int[2 + (2 * 2 * 2)]; // size = 10.
		for (i = 0; i < (2 + (2 * 2 * 2)); i++) {
			codes[i] = 0;
		}
		assert (codes[1 + (2 * 2 * 2)] == 0); // 9.

		// Traverse list of modules counting types
		for (i = 0; i < theList.size(); i++) {

			temp = theList.get(i);
			/**
			 * Counts each module type in our list
			 * Need 3 plain and 1 of each non-plain type
			 *
			 * index - code value
			 * 0....plain (need 3)
			 * 1....power
			 * 2....food and water
			 * 3....air
			 * 4....canteen
			 * 5....dorm
			 * 6....sanitation
			 * 7....control
			 */
			ModuleType codeTmp = temp.getType();
			if (codeTmp.equals(ModuleType.PLAIN)) {
				codes[0]++;
			} else if (codeTmp.equals(ModuleType.POWER)) {
				codes[1]++;
			} else if (codeTmp.equals(ModuleType.FOOD_AND_WATER)) {
				codes[2]++;
			} else if (codeTmp.equals(ModuleType.AIRLOCK)) {
				codes[2 + 1]++;
			} else if (codeTmp.equals(ModuleType.CANTEEN)) {
				codes[2 * 2]++;
			} else if (codeTmp.equals(ModuleType.DORMITORY)) {
				codes[2 * 2 + 1]++;
			} else if (codeTmp.equals(ModuleType.SANITATION)) {
				codes[2 * 2 + 2]++;
			} else if (codeTmp.equals(ModuleType.CONTROL)) {
				codes[2 * 2 * 2 - 1]++;
			}

		}

		// If we don't have 3 plain, we can't have a minimum
		if (codes[0] >= (1 + 2)) {
		// Test to make sure we aren't missing any modules
			for (i = 1; i < (2 * 2 * 2); i++) { // i < 8

				if (codes[i] == 0) {
					allGood = false;
					// There is a module missing
				}
			}
		} else {
		    // there isn't 3 plain
			allGood = false;
		}
		if (allGood) {
		    // We can make configuration.
		    setMinConfig(minArray);
		}
		return allGood;
	}
//*********************************************************************
	/**
	 * setMinConfig will set *grid* coord for map.
	 * @param minArray is the list of modules showing type/coordinate.
	 */
	public static final void setMinConfig(
	        final ArrayList<Minimum> minArray) {

		// Find centroid of the modules in landing area
		// Returns the MAP coordinate, NOT the grid coordinate.

		Point centroid = findCentroid();
		assert ((int) centroid.getX() >= 0);
		assert ((int) centroid.getY() >= 0);
		int total = 2 * 2 * 2 + 2; // 10.
		ModuleType type;
		ModuleType typeReal;
		int valuex = 0;
		int valuey = 0;
		int adjustX = 0;
		int adjustY = 0;
		Point point;

		// if x is between 40 and 50, we might be in sandy- check y
		if ((centroid.getX() >= 40)
		        && (centroid.getX() <= 50)) {

			// if y is between 40 and 50, we are in the sandy area
			if ((centroid.getY() >= 40)
			        && (centroid.getY() <= 50)) {

				// Case 1: Left side of sandy
				if (centroid.getX() < 45) {
					// Case 1.1: Left upper
					if (centroid.getY() >= 45) {
						adjustX = 35;
						adjustY = 1 + 2;
					} else {
						// Case 1.2: Left Lower
						adjustY = 30;
						adjustX = 35;
					}
				// End of Case 1
				// Case 2: Right side of sandy
				} else {
					// Case 2.1: Right upper
					if (centroid.getX() >= 45) {
						adjustX = 59;
						adjustY = 1 + 2;
					} else {
					// Case 2.2: Right lower
						adjustX = 59;
						adjustY = 45;
					}
				} // End of Case 2
			}
		}
		// Local counter to keep track of how many plain
		// modules we have collected.
		int countPlain = 0;
		/** Traverse modules and set up list of Minimum objects.
		 * The points are set as the adjustment needed
		 * for the configuration placement.
		 */
		for (int i = 0; i < total; i++) {
		    // Get the real module type.
		    typeReal = minArray.get(i).getCode();
		    // Start testing and setting up minimum list.
			if (typeReal.equals(ModuleType.PLAIN)) {
				type = ModuleType.PLAIN;
				if (countPlain == 0) {
					valuex = 1;
					valuey = 1;
				} else if (countPlain == 1) {
					valuex = 1 + 2;
					valuey = 1;
				} else if (countPlain == 2) {
					valuex = 2;
					valuey = 2;
				}
				// Increment plain so we know how many
				// we have collected.
				countPlain++;
			} else if (typeReal.equals(ModuleType.SANITATION)) {
				type = ModuleType.SANITATION;
				valuex = 1 + 2;
				valuey = 0;
			} else if (typeReal.equals(ModuleType.CONTROL)) {
				type = ModuleType.CONTROL;
				valuex = 2;
				valuey = 1 + 2;
			} else if (typeReal.equals(ModuleType.AIRLOCK)) {
				type = ModuleType.AIRLOCK;
				valuex = 1;
				valuey = 0;
			} else if (typeReal.equals(ModuleType.CANTEEN)) {
				type = ModuleType.CANTEEN;
				valuex = 2;
				valuey = 1;
			} else if (typeReal.equals(ModuleType.POWER)) {
				type = ModuleType.POWER;
				valuex = 0;
				valuey = 1;
			} else if (typeReal.equals(ModuleType.FOOD_AND_WATER)) {
				type = ModuleType.FOOD_AND_WATER;
				valuex = 1;
				valuey = 2;
			} else if (typeReal.equals(ModuleType.DORMITORY)) {
				type = ModuleType.DORMITORY;
				valuex = 1 + 2;
				valuey = 2;
			} else if (typeReal.equals(ModuleType.MEDICAL)) {
			    type = ModuleType.MEDICAL;
			} else {
			    type = ModuleType.GYM_AND_RELAXATION;
			}
			// Create type and point for minimum object.
			if ((adjustX > 0) || (adjustY > 0)) {
			    valuex += adjustX;
			    valuey += adjustY;
			} else {
			    valuex += (int) centroid.getX();
			    valuey += (int) centroid.getY();
			}
			point = new Point(valuex, valuey);
			Minimum min = new Minimum(type, point);
			minArray.add(min);
		}

		MinimumConfigPage.setUpMinConfig(minArray);
	}
//*********************************************************************
	/**
	 * setSecondConfig will display the second minimum
	 * configuration.
	 */
	public final void setSecondConfig() {
	    ArrayList<Minimum> minA = new ArrayList<Minimum>();
	    int total = (2 * 2 * 2 + 2); // 10.
		int valuex = 0;
		int valuey = 0;
		ModuleType type;
		Point point;

		for (int i = 0; i < total; i++) {

			if ((i >= 0) && (i < (1 + 2))) {
				type = ModuleType.PLAIN;
				if (i == 0) {
					valuex = 1;
					valuey = 1;
				} else if (i == 1) {
					valuex = 1;
					valuey = 2;
				} else if (i == 2) {
					valuex = 2;
					valuey = 2;
				}
			} else if (i == (1 + 2)) {
				type = ModuleType.SANITATION;
				valuex = 1 + 2;
				valuey = 2;
			} else if (i == (2 + 2)) {
				type = ModuleType.CONTROL;
				valuex = 0;
				valuey = 1;
			} else if (i == (2 * 2 + 1)) {
				type = ModuleType.AIRLOCK;
				valuex = 1;
				valuey = 0;
			} else if (i == (1 + 2) * 2) {
				type = ModuleType.MEDICAL;
				valuex = 1;
				valuey = 1 + 2;
			} else if (i == 7) {
				type = ModuleType.POWER;
				valuex = 2;
				valuey = 1;
			} else if (i == 8) {
				type = ModuleType.FOOD_AND_WATER;
				valuex = 0;
				valuey = 2;
			} else {
				type = ModuleType.DORMITORY;
				valuex = 2;
				valuey = 3;
			}
			point = new Point(valuex, valuey);
			Minimum min = new Minimum(type, point);
			minArray.add(min);
		}
		this.minArray = minA;
		setMinConfig(minArray);
	}
//*******************************************************************
	/**
	 * getMinArray() returns arraylist of Minimum objects.
	 * This array holds the modules that will be used in the min
	 * configuration.
	 * @return ArrayList<Minimum>
	 */
	public final ArrayList<Minimum> getMinArray(ModuleList mods) {
	    ArrayList<Minimum> minimum
	    = new ArrayList<Minimum>();
        // Go through modList and copy pertinent information.
        for (int k = 0; k < mods.size(); k++) {
            Module tMod = mods.get(k);
            Point tPoint = new Point(
                    Integer.parseInt(tMod.getXCoor()) - 1,
                    50 - Integer.parseInt(tMod.getYCoor()));
            ModuleType tType = tMod.getType();
            minimum.add(new Minimum(tType, tPoint));
        }
        // Now the array has been created.
	    return minimum;
	}
//*******************************************************************
	/**
	 * This will find the centroid of the set of n 2d coordinates.
	 * A point is returned holding (x, y) - these coordinates
	 * correspond to the map and NOT the java grid coordinates.
	 * The centroid is found by:
	 * X = (x1 + x2 + ... + xn)/n
	 * Y = (y1 + y2 + ... + yn)/n
	 * @return Point of the centroid
	 */
	public final static Point findCentroid() {
		int xSum = 0;
		int ySum = 0;
		String temp = "";

		for (int i = 0; i < ModuleList.get().size(); i++) {
			// Add up all x and y values
			temp = ModuleList.get().get(i).getXCoor();
			xSum += Integer.parseInt(temp);
			temp = ModuleList.get().get(i).getYCoor();
			ySum += Integer.parseInt(temp);
		}
		if (ModuleList.get().size() > 0) {
		// divide values by n
		    xSum = xSum / ModuleList.get().size() + 1;
		    ySum = ySum / ModuleList.get().size() + 1;
		}
		// Initialize centroid
		xSum += -1;
		ySum = 50 - ySum;
		Point centroid = new Point(xSum, ySum);

		return centroid;
	}
}