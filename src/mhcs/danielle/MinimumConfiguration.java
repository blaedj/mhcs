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
	 * minArray.
	 */
	private ArrayList<Minimum> minArray;
	/**
	 * theList of modules.
	 */
	private ArrayList<Module> theList;
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
	 * @param moduleList is the list of modules
	 */

	public MinimumConfiguration(ArrayList<Module> theList) {
		// Initialize data members
		adjustX = 0;
		adjustY = 0;

		this.theList = theList;
		allGood = testMinConfig();
		minArray = new ArrayList<Minimum>();

		if(allGood == true) {
			setMinConfig();
		} else {
			minArray.clear();
		}
	}
	/**
	 * Tests to see if minimum configuration is possible.
	 * @return boolean showing if minimum configuration is possible.
	 */
	public static boolean testMinConfig() {
		// Set up some local variables for static function
		ArrayList<Module> theList = ModuleList.moduleList;
		int count = theList.size();
		Module temp;
		String damaged = "damaged";
;		int i = 0;
		boolean allGood = true;

		// create array for type count, initialize all to zero
		int[] codes = new int[10];
		for(i = 0; i < 10; i++) {
			codes[i] = 0;
		}
		assert(codes[7]==0);
		assert(codes[9]==0);

		// Traverse list of modules counting types
		for(i = 0; i < count; i++) {
			assert (count == theList.size());
			if(damaged.equalsIgnoreCase(theList.get(i).getDamage())) {
			    continue;
			}
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
			if(codeTmp.equals(ModuleType.PLAIN)) {
				codes[0]++;
			} else if(codeTmp.equals(ModuleType.POWER)) {
				codes[1]++;
			} else if(codeTmp.equals(ModuleType.FOOD_AND_WATER)) {
				codes[2]++;
			} else if(codeTmp.equals(ModuleType.AIRLOCK)) {
				codes[3]++;
			} else if(codeTmp.equals(ModuleType.CANTEEN)) {
				codes[4]++;
			} else if(codeTmp.equals(ModuleType.DORMITORY)) {
				codes[5]++;
			} else if(codeTmp.equals(ModuleType.SANITATION)) {
				codes[6]++;
			} else if(codeTmp.equals(ModuleType.CONTROL)) {
				codes[7]++;
			}

		}
		/*
		// If we don't have 3 plain, we can't have a minimum
		if(codes[0] == 3) {
		// Test to make sure we aren't missing any modules
			for(i = 1; i < 8; i++){
			
				if(codes[i]==0){
					allGood = false; // There is a module missing
				}
			}
		}else { // there isn't 3 plain
			allGood = false;
		}
		*/
		return allGood;
	}
//*********************************************************************
	/**
	 * setMinConfig will set *grid* coord for map. 
	 */
	public void setMinConfig(){
		
		// Find centroid of the modules in landing area
		// Returns the MAP coordinate, NOT the grid coordinate.
	
		Point centroid = findCentroid();
		int total = 10;
		ModuleType type;
		int valuex = 0;
		int valuey = 0;
		Point point;
		
		// if x is between 40 and 50, we might be in sandy- check y
		if((centroid.getX() >= 40) && (centroid.getX() <= 50)) {
			
			// if y is between 40 and 50, we are in the sandy area
			if((centroid.getY() >= 40) && (centroid.getY() <= 50)) {
				
				// Case 1: Left side of sandy
				if(centroid.getX() < 45) {
					// Case 1.1: Left upper
					if(centroid.getY() >= 45) {
						adjustX = 35;
						adjustY = 3;
					} else {
						// Case 1.2: Left Lower
						adjustY = 30;
						adjustX = 35;
						
					}
				// End of Case 1
				// Case 2: Right side of sandy
				} else {		
					
					// Case 2.1: Right upper
					if(centroid.getX() >= 45) {
						adjustX = 59;
						adjustY = 3;
					} else {
					// Case 2.2: Right lower
						adjustX = 59;
						adjustY = 45;
					}
				} // End of Case 2
			}
		}

		// Traverse list and collect coord. of modules used
		for(int i = 0; i < total; i++){

			
			if((i >=0)&&(i < 3)){
				type = ModuleType.PLAIN;
				if(i == 0){
					valuex = 1;
					valuey = 1;
				}else if(i == 1){
					valuex = 3;
					valuey = 1;
				}else if(i == 2){
					valuex = 2;
					valuey = 2;
				}
			}else if(i == 3){
				type = ModuleType.SANITATION;
				valuex = 3;
				valuey = 0;
			}else if(i == 4){
				type = ModuleType.CONTROL;
				valuex = 2;
				valuey = 3;
			}else if(i == 5){
				type = ModuleType.AIRLOCK;
				valuex = 1;
				valuey = 0;
			}else if(i ==6){
				type = ModuleType.CANTEEN;
				valuex = 2;
				valuey = 1;
			}else if(i == 7){
				type = ModuleType.POWER;
				valuex = 0;
				valuey = 1;
			}else if(i == 8){
				type = ModuleType.FOOD_AND_WATER;
				valuex = 1;
				valuey = 2;
			}else {
				type = ModuleType.DORMITORY;
				valuex = 3;
				valuey = 2;
			}
			point = new Point(valuex + adjustX, valuey + adjustY);
			Minimum min = new Minimum(type,point);
			minArray.add(min);
		}
	}
//*********************************************************************
	/**
	 * setSecondConfig will display the second minimum 
	 * configuration. 
	 */
	public void setSecondConfig(){
		int total = 10;
		int valuex = 0;
		int valuey = 0;
		ModuleType type;
		Point point;

		for (int i = 0; i < total; i++) {

			if ((i >= 0) && (i < 3)) {
				type = ModuleType.PLAIN;
				if (i == 0){
					valuex = 1;
					valuey = 1;
				} else if (i == 1) {
					valuex = 1;
					valuey = 2;
				} else if (i == 2) {
					valuex = 2;
					valuey = 2;
				}
			} else if (i == 3) {
				type = ModuleType.SANITATION;
				valuex = 3;
				valuey = 2;
			} else if (i == 4) {
				type = ModuleType.CONTROL;
				valuex = 0;
				valuey = 1;
			} else if (i == 5) {
				type = ModuleType.AIRLOCK;
				valuex = 1;
				valuey = 0;
			} else if (i ==6) {
				type = ModuleType.MEDICAL;
				valuex = 1;
				valuey = 3;
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
			point = new Point(valuex + adjustX, valuey + adjustY);
			Minimum min = new Minimum(type,point);
			minArray.add(min);

		}
	}
//*******************************************************************
	/**
	 * getMinArray() returns arraylist of Minimum objects. 
	 * This array holds the modules that will be used in the min
	 * configuration. 
	 * @return ArrayList<Minimum>
	 */
	public ArrayList<Minimum> getMinArray(){
		return minArray;
	}
//*******************************************************************	
	/**
	 * This will find the centroid of the set of n 2d coordinates.
	 * A point is returned holding (x, y) - these coordinates
	 * correspond to the map and NOT the java grid coordinates.
	 * The centroid is found by: X = (x1 + x2 + ... + xn)/n
	 * 							 Y = (y1 + y2 + ... + yn)/n
	 * @return Point of the centroid
	 */
	public Point findCentroid() {
		int xSum = 0;
		int ySum = 0;
		String temp = "";
		
		for(int i = 0; i < theList.size(); i++) {
			// Add up all x and y values
			temp = theList.get(i).getXCoor();
			xSum += Integer.parseInt(temp);
			temp = theList.get(i).getYCoor();
			ySum += Integer.parseInt(temp);
		}
		// divide values by n 
		xSum = xSum/theList.size();
		ySum = ySum/theList.size();
		
		// Initialize centroid
		Point centroid = new Point(xSum,ySum);
		
		return centroid;
	}
}