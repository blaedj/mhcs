package mhcs.danielle;

import mhcs.dan.ModuleList;
/**
 * 
 * @author Danielle Stewart
 *
 */
public class FullConfiguration {
	private int listSize;
	
	public FullConfiguration(ModuleList mods){
		listSize = mods.size();
		testSizes();
		
	}
	
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
	
	private void smallList(){
		makeSmallSkeleton();
	}
	private void mediumList(){
		
	}
	private void largeList(){
		
	}
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
}
