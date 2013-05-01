package mhcs.danielle;

import java.util.ArrayList;

import mhcs.dan.Module;
import mhcs.dan.Module.ModuleType;
import mhcs.dan.ModuleList;
//import mhcs.dan.ModuleList;
/**
 * This class creates a full configuration.
 * @author Danielle Stewart
 *
 */
public class FullConfiguration {
    /**
     * the list of modules.
     */
    ModuleList theList;
    /**
     * private data member listSize is the size of the mod list
     * that comes in.
     */
	private int listSize;
	/**
	 * data member is the list of modules coming in
	 * in terms of Maximum objects.
	 */
	private ArrayList<Maximum> maxList;
	/**
	 * list of modules and their types for counting.
	 */
	private int codes[];
	/**
	 * this is the public constructor.
	 * @param mods
	 */
	public FullConfiguration(ArrayList<Module> mods){
	    theList = ModuleList.get();
	    maxList = new ArrayList<Maximum>();
	    listSize = mods.size();
	    codes = new int[10];
	    // Initialize codes to all zero elements.
	    for(int i = 0; i < codes.length; i++) {
	        codes[i] = 0;
	    }
	    
	    countTypes();
		testSizes();
	}
	/**
	 * private method will count the type of each undamaged
	 * module for configuration build.
	 */
	private void countTypes() {
	    String damaged = "damaged";
	    Module temp;
	 // Traverse list of modules counting types
        for(int i = 0; i < theList.size(); i++) {
            
            assert (listSize == theList.size());
            
            if(damaged.equalsIgnoreCase(
                    theList.get(i).getDamage())) {
                continue;
            }
            
            temp = theList.get(i);
            /**
             * Counts each module type in our list.
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
             * 8....medical
             * 9....gym
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
            } else if(codeTmp.equals(ModuleType.MEDICAL)) {
                codes[8]++;
            } else if(codeTmp.equals(ModuleType.GYM_AND_RELAXATION)) {
                codes[9]++;
            }
        }
	}
	/**
	 * test the size of the list coming in.
	 */
	private void testSizes(){
	    boolean valid = true;
		if((listSize > 10)&&(listSize < 21)){
			valid = testSmallList();
		}else if ((listSize >= 21)&&(listSize < 31)) {
			valid = testMediumList();
		}else if ((listSize >= 31)&&(listSize < 51)) {
			valid = testLargeList();
		}else {
			valid = testMaxList();
		}
		if(valid == false) {
		    
		}
	}
	/**
	 * makes small list.
	 */
	private boolean testSmallList(){
	    boolean valid = true;
	    if(codes[0] >= 4) {
	        
	    } else {
	       valid = false; 
	    }
	    for(int i = 1; i < 10; i++) {
	        if(codes[i] < 1) {
	            valid = false;
	        }
	    }
	    return valid;
	}
	/**
	 * makes medium list.
	 */
	private boolean testMediumList(){
		
	}
	/**
	 * makes large list.
	 * @return 
	 */
	private boolean testLargeList(){
		
	}
	/**
	 * makes max list.
	 * @return 
	 */
	private boolean testMaxList(){
		
	}
	
	/**
	 * makeSmallSkeleton will create a skeleton framework
	 * for the configuration. This framework by itself follows 
	 * configuration rules. 
	 * This skeleton is then used to add other modules to the 
	 * habitat as needed. 
	 * This skeleton (small) consists of 12 modules:
	 * 5 plain, 2 dorm, 1 bath, 1 air, 1 canteen, 1 power, 
	 * 1 med, 1 storage
	 */
	private void makeSmallSkeleton() {
		
	}
	/**
	 * getter for the max array.
	 */
	public ArrayList<Maximum> getMaxArray() {
	    return this.maxList;
	}
}
