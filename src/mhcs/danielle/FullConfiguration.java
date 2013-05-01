package mhcs.danielle;

import java.util.ArrayList;

import mhcs.dan.Module;
//import mhcs.dan.ModuleList;
/**
 * This class creates a full configuration.
 * @author Danielle Stewart
 *
 */
public class FullConfiguration {
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
	 * this is the public constructor.
	 * @param mods
	 */
	public FullConfiguration(ArrayList<Module> mods){
	    maxList = new ArrayList<Maximum>();
		listSize = mods.size();
		testSizes();
	}
	/**
	 * test the size of the list coming in.
	 */
	private void testSizes(){
		if((listSize > 10)&&(listSize < 21)){
			smallList();
		}else if ((listSize >= 21)&&(listSize < 31)) {
			mediumList();
		}else if ((listSize >= 31)&&(listSize < 51)) {
			largeList();
		}else {
			maxList();
		}
	}
	/**
	 * makes small list.
	 */
	private void smallList(){
		makeSmallSkeleton();
	}
	/**
	 * makes medium list.
	 */
	private void mediumList(){
		
	}
	/**
	 * makes large list.
	 */
	private void largeList(){
		
	}
	/**
	 * makes max list.
	 */
	private void maxList(){
		
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
