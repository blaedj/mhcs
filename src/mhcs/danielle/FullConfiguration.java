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
		
	}
	
	private void testSizes(){
		if((listSize > 12)&&(listSize < 21)){
			smallList();
		}
	}
	
	private void smallList(){
		
	}
	private void mediumList(){
		
	}
	private void largeList(){
		
	}
	private void maxList(){
		
	}
}
