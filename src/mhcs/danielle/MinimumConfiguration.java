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
 *   @author Danielle Stewart 
 */
public class MinimumConfiguration{
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
	 * temp. 
	 */
	private Module temp;


	/**
	 * count integer. 
	 */
	private int count;



	/**
	 * MinimumConfiguration constructor creates
	 * an object that consists of two lists: one of 
	 * module type and one of coordinate points.
	 *  
	 * This coordinate point is returned as Java 
	 * coordinates and *not* map coordinates.
	 * 
	 * @param moduleList is the list of modules
	 */

	public MinimumConfiguration(ArrayList<Module> theList){


		this.theList = theList;
		count = theList.size();
		allGood = testMinConfig();
		minArray = new ArrayList<Minimum>();

		if(allGood==true){
			setMinConfig();
		}else{
			count = 0;
			minArray.clear();
		}
	}

	/**
	 * Tests to see if min config is possible
	 * @return bool showing if min config is possible
	 */
	public boolean testMinConfig(){
		// Code code;
		int i = 0;
		boolean allGood = true;

		// create array for type count, initialize all to zero
		int[] codes = new int[10];
		for(i = 0; i < 10; i++){
			codes[i] = 0;
		}
		assert(codes[7]==0);
		assert(codes[9]==0);

		// Traverse list of modules counting types
		for(i = 0; i < count; i++){
			assert(count == theList.size());

			temp = theList.get(i);
			/**
			 * Counts each module type in our list
			 * 
			 * index - code value
			 * 0....plain
			 * 1....med
			 * 2....power
			 * 3....storage	
			 * 4....air
			 * 5....canteen
			 * 6....dorm
			 * 7....sanitation
			 * 8....gym
			 * 9....control
			 */
			ModuleType codeTmp = temp.getType();
			if(codeTmp.equals(ModuleType.PLAIN)){
				codes[0]++;
			}else if(codeTmp.equals(ModuleType.MEDICAL)){
				codes[1]++;
			}else if(codeTmp.equals(ModuleType.POWER)){
				codes[2]++;
			}else if(codeTmp.equals(ModuleType.FOOD_AND_WATER)){
				codes[3]++;
			}else if(codeTmp.equals(ModuleType.AIRLOCK)){
				codes[4]++;
			}else if(codeTmp.equals(ModuleType.CANTEEN)){
				codes[5]++;
			}else if(codeTmp.equals(ModuleType.DORMITORY)){
				codes[6]++;
			}else if(codeTmp.equals(ModuleType.SANITATION)){
				codes[7]++;
			}else if(codeTmp.equals(ModuleType.GYM_AND_RELAXATION)){
				codes[8]++;
			}else if(codeTmp.equals(ModuleType.CONTROL)){
				codes[9]++;
			}

		}

		// Test to make sure we aren't missing any modules
		for(i = 0; i < 10; i++){
			if(codes[i]==0){
				allGood = false;
			}
		}
		return allGood;
	}

	/**
	 * setMinConfig will set grid coord for map. 
	 */
	public void setMinConfig(){
		int total = 10;
		int valuex = 0;
		int valuey = 0;
		ModuleType type;

		// Traverse list and collect coord. of modules used
		for(int i = 0; i < total; i++){

			Point pt = new Point(valuex, valuey);
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
			Minimum min = new Minimum(type,pt);
			minArray.add(min);
		}
	}

	/**
	 * setSecondConfig will display the second minimum 
	 * configuration. 
	 */
	public void setSecondConfig(){
		int total = 10;
		int valuex = 0;
		int valuey = 0;
		ModuleType type;

		for (int i = 0; i < total; i++) {

			Point pt = new Point(valuex, valuey);
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
			Minimum min = new Minimum(type,pt);
			minArray.add(min);

		}
	}

	/**
	 * getMinArray() returns arraylist of Minimum objects. 
	 * This array holds the modules that will be used in the min
	 * configuration. 
	 * @return ArrayList<Minimum>
	 */
	public ArrayList<Minimum> getMinArray(){
		return minArray;
	}
}