package mhcs.danielle;

import java.util.ArrayList;

import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.Window;

import mhcs.dan.Module;
import mhcs.dan.Module.ModuleType;
import mhcs.dan.ModuleList;
/**
 * This class creates a full configuration
 * based on how many modules are in the list.
 * @author Danielle Stewart
 *
 */
public class FullConfiguration {
    int countPl;
    int countD;
    int countS;
    int countB;
    int countP;
    int countM;
    int countCa;
    int countC;
    int countA;
    int countG;
    /**
     * the list of modules.
     */
    private ModuleList theList;
    /**
     * private data member listSize is the size of the mod list
     * that comes in.
     */
	private int listSize;
	/**
	 * centroidX is the x coord of the centroid in terms of grid.
	 */
	private int centroidX;
	/**
	 * centroidY is y coord of centroid in terms of grid.
	 */
	private int centroidY;
	/**
	 * data member is the list of modules coming in
	 * in terms of Maximum objects.
	 */
	private ArrayList<Maximum> maxList;
	/**
	 * list of modules and their types for counting.
	 */
	private int[] codes;
//***//****//****//****//*****//*******CONSTRUCTOR
	/**
	 * This is the public constructor.
	 */
	public FullConfiguration(ModuleList modules) {
	    // The module list must not be empty.

	    // Again, the module list must not be empty.
	    if (modules.size() > 0) {
	        theList = new ModuleList();
	        // As long as it's not empty, we will
	        // copy each module into private theList.
	        for (int i = 0; i < modules.size(); i++) {
	            theList.add(modules.get(i));
	        }
	        maxList = new ArrayList<Maximum>();
	        listSize = theList.size();
	        // Make sure (again) that size isn't wonky.
	        codes = new int[2 * 2 * 2 + 2]; // size = 10.
	        // Initialize codes to all zero elements.
	        for (int i = 0; i < codes.length; i++) {
	            codes[i] = 0;
	        }
	        // Initialize all count variables.
	        countPl = 0;
	        countD = 0;
	        countS = 0;
	        countB = 0;
	        countP = 0;
	        countM = 0;
	        countCa = 0;
	        countC = 0;
	        countA = 0;
	        countG = 0;
	        // Collect the array[] of codes.
	        countTypes();
	        // Get the centroid values set up.
	        findCentroid();
	        // Test the size of our list to determine
	        // the size of configuration.
	        testSizes();
	    } else {
	        // The size is wonky and so this object is null.
	        maxList = null;
	    }
	}
//********************************************
	/**
	 * findCentroid finds the centroid and adjusts
	 * if it is in the sandy area.
	 */
	private void findCentroid() {
	    int adjustX = 0;
	    int adjustY = 0;
	    Point centroid = MinimumConfiguration.findCentroid();
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
        if (adjustX > 0) {
           centroidX = adjustX;
        } else if (adjustY > 0) {
            centroidY = adjustY;
        } else {
            centroidX = (int) centroid.getX();
            centroidY = (int) centroid.getY();
        }
	}
// ******************************************
	/**
	 * private method will count the type of each undamaged
	 * module for configuration build.
	 */
	private void countTypes() {
	    String damaged = "damaged";
	    Module temp;
	 // Traverse list of modules counting types
        for (int i = 0; i < theList.size(); i++) {

            assert (listSize == theList.size());

            if (damaged.equalsIgnoreCase(
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
            if (codeTmp.equals(ModuleType.PLAIN)) {
                codes[0]++;
            } else if (codeTmp.equals(ModuleType.POWER)) {
                codes[1]++;
            } else if (codeTmp.equals(ModuleType.FOOD_AND_WATER)) {
                codes[2]++;
            } else if (codeTmp.equals(ModuleType.AIRLOCK)) {
                codes[3]++;
            } else if (codeTmp.equals(ModuleType.CANTEEN)) {
                codes[4]++;
            } else if (codeTmp.equals(ModuleType.DORMITORY)) {
                codes[5]++;
            } else if (codeTmp.equals(ModuleType.SANITATION)) {
                codes[6]++;
            } else if (codeTmp.equals(ModuleType.CONTROL)) {
                codes[7]++;
            } else if (codeTmp.equals(ModuleType.MEDICAL)) {
                codes[8]++;
            } else if (codeTmp.equals(ModuleType.GYM_AND_RELAXATION)) {
                codes[9]++;
            }
        }
	}
//***********************************************
	/**
	 * test the size of the list coming in.
	 */
	private void testSizes() {
	    boolean valid = true;
	    int flag = 0;
		if ((listSize > 10)
		        && (listSize < 21)) {
		    // Small list.
		    flag = 1;
		    valid = testList(flag);
		} else if ((listSize >= 32)
		        && (listSize < 41)) {
		    // Medium list.
		    flag = 2;
		    valid = testList(flag);
		} else if ((listSize >= 41)
		        && (listSize < 71)) {
		    // Large list.
		    flag = 2 + 1;
		    valid = testList(flag);
		} else if ((listSize >= 71)
		        && (listSize < 104)) {
		    // Large list 2.
		    flag = 2 * 2;
		    valid =  testList(flag);
		} else {
		    // Maximum configuration list.
		    flag = 2 * 2 + 1;
		    valid = testList(flag);
		}
		boolean temp = true;
		if (!valid) {
		    // Check what value the flag is and try the
		    // next lowest config.
		    while (!temp && (flag > 1)) {
		        flag += -1;
		        temp = testList(flag);
		    }
		}
		// If valid is false, we cannot make a full.
		if (!valid) {
		    Window.alert("Sorry! Unable to make full confligration.");
		} else {
		    if (flag == 1) {
		        makeSmall();
		    } else if (flag == 2) {
		        makeMedium();
		    } else if (flag == 2 + 1) {
		        makeLarge();
		    } else if (flag == 2 + 2) {
		        makeLarge2();
		    } else {
		        makeMax();
		    }
		}
	}
//*********************************************
	/**
	 * Tests list.
	 * @param flag tells what size we want.
	 * @return boolean true if we can make a configuration.
	 */
	private boolean testList(final int flag) {
	    boolean valid = false;
	    // Case: small config test
	    if (flag == 0) {
	        //Make sure there are the correct number of modules.
	        if (codes[0] >= 4) {
	            // Then we can check all the others
	            for (int i = 1; i < 10; i++) {
	                if (codes[i] < 1) {
	                    valid = false;
	                }
	                valid = true;
	            }
	        } else { // There aren't enough plain mods.
	            valid = false;
	        } // End of Small Configuration Test

	    } else if (flag == 1) {
	        // Case: med config test
	        if (codes[0] >= 16) {
	           if ((codes[5] >= 8)
	                   && (codes[6] >= 4)
	                   && (codes[8] >= 1)) {
	               // Then we have enough dorm, med, and sanitation
	               if ((codes[2] >= 3)
	                       && (codes[4] >= 3)
	                       && (codes[1] >= 2)
	                       && (codes[3] >= 2)
	                       && (codes[9] >= 2)) {
	                   // Now we have enough of everything
	                   valid = true;
	               }
	           }
	        } // End of medium configuration test.
	    } else if (flag == 2 * 2) {
	        // Case: large config
	        if (codes[0] >= 24) {
	            if ((codes[5] >= 11) && (codes[6] >= 6)) {
	                valid = true;
	                for (int i = 0; i < 10; i++) {
	                    // If anything is less than 2
	                    // we can't have this config.
	                    if (codes[i] < 2) {
	                        valid = false;
	                    }
	                }
	            }
	        } else {
	            valid = false;
	            // Not enough plain.
	        }
	        // End of large config test.
	    } else if (flag == (2 * 2 + 1)) {
	        // Case: large 2 configuration.
	        if (codes[0] >= 32) {
	            if ((codes[5] >= 18) && (codes[6] >= 9)) {
	                if(codes[2] >= 7) {
	                    valid = true;
	                    for (int i = 0; i < 10; i++) {
	                        if (codes[i] < 3) {
	                            valid = false;
	                            // there is something we 
	                            // don't have enough of.
	                        }
	                    }
	                }
	            }
	        } else {
	            // Not enough plain.
	            valid = false;
	        }
	    } else {
	        // Max configuration
	        valid = true;
	    }
	    return valid;
	}
//****************************************
	/**
	 * makeSmallSkeleton will create a skeleton framework
	 * for the configuration. This framework by itself follows 
	 * configuration rules. 
	 * This skeleton is then used to add other modules to the 
	 * habitat as needed. 
	 * This skeleton (small) consists of 12 modules:
	 * 4 plain, 2 dorm, 1 bath, 1 air, 1 canteen, 1 power, 
	 * 1 med, 1 storage
	 */
	private void makeSmall() {
	    int total = 2 * 2 * 2 + 2; // 10.
        ModuleType type = ModuleType.PLAIN;
        ModuleType typeReal;
        int valuex = 0;
        int valuey = 0;
        /** Traverse modules and set up list of Minimum objects.
         * The points are set as the adjustment needed
         * for the configuration placement.
         */
        for (int i = 0; i < total; i++) {
            // Get the real module type.
            typeReal = theList.get(i).getType();
            // Start testing and setting up minimum list.
            if (typeReal.equals(ModuleType.PLAIN)) {
                type = ModuleType.PLAIN;
                if (countPl == 0) {
                    valuex = 2 + 1;
                    valuey = 1;
                } else if (countPl == 1) {
                    valuex = 1 + 2;
                    valuey = 2;
                } else if (countPl == 2) {
                    valuex = 2;
                    valuey = 2;
                } else {
                    valuex = 1;
                    valuey = 2;
                }
                // Increment plain so we know how many
                // we have collected.
                countPl++;
                // Done with plain setup.
            } else if (typeReal.equals(ModuleType.SANITATION)) {
                type = ModuleType.SANITATION;
                valuex = 2;
                valuey = 1;
            } else if (typeReal.equals(ModuleType.CONTROL)) {
                type = ModuleType.CONTROL;
                valuex = 2;
                valuey = 1 + 2;
            } else if (typeReal.equals(ModuleType.AIRLOCK)) {
                type = ModuleType.AIRLOCK;
                valuex = 0;
                valuey = 2;
            } else if (typeReal.equals(ModuleType.CANTEEN)) {
                type = ModuleType.CANTEEN;
                valuex = 2 * 2;
                valuey = 1;
            } else if (typeReal.equals(ModuleType.POWER)) {
                type = ModuleType.POWER;
                valuex = 2 + 1;
                valuey = 2 + 1;
            } else if (typeReal.equals(ModuleType.FOOD_AND_WATER)) {
                type = ModuleType.FOOD_AND_WATER;
                valuex = 1;
                valuey = 1;
            } else if (typeReal.equals(ModuleType.DORMITORY)) {
                type = ModuleType.DORMITORY;
                valuex = 1 + 2;
                valuey = 0;
            } else if (typeReal.equals(ModuleType.MEDICAL)) {
                type = ModuleType.MEDICAL;
                valuex = 1;
                valuey = 1 + 2;
            } else {
                type = ModuleType.GYM_AND_RELAXATION;
            }
        } // End for loop.
     // Create type and point for minimum object.
        valuex += centroidX;
        valuey += centroidY;
        Point point = new Point(valuex, valuey);
        Maximum min = new Maximum(type, point);
        maxList.add(min);
        FullConfigPage.setUpFullConfig(maxList);
    }
//*************************************
	/**
	 * makeMedium follows all layout rules.
	 * Consists of
	 */
	private void makeMedium() {
	    int total = 2 * 2 * 2 + 2; // 10.
        ModuleType type = ModuleType.PLAIN;
        ModuleType typeReal;
        int valuex = 0;
        int valuey = 0;
        /** Traverse modules and set up list of Minimum objects.
         * The points are set as the adjustment needed
         * for the configuration placement.
         */
        for (int i = 0; i < total; i++) {
            // Get the real module type.
            typeReal = maxList.get(i).getCode();
            // Start testing and setting up minimum list.
            if (typeReal.equals(ModuleType.PLAIN)) {
                type = ModuleType.PLAIN;
                if (countPl == 0) {
                    valuex = 2 + 1;
                    valuey = 1;
                } else if (countPl == 1) {
                    valuex = 1 + 2;
                    valuey = 2;
                } else if (countPl == 2) {
                    valuex = 3;
                    valuey = 4;
                } else if (countPl == 3){
                    valuex = 3;
                    valuey = 5;
                } else if (countPl == 4) {
                    valuex = 3;
                    valuey = 6;
                } else if (countPl == 5) {
                    valuex = 3;
                    valuey = 7;
                } else if (countPl == 6) {
                    valuex = 3;
                    valuey = 8;
                } else if (countPl == 7) {
                    valuex = 3;
                    valuey = 9;
                } else if (countPl == 8) {
                    valuex = 3;
                    valuey = 10;
                } else if (countPl == 9) {
                    valuex = 3;
                    valuey = 11;
                } else if (countPl == 10) {
                    valuex = 4;
                    valuey = 11;
                } else if (countPl == 11) {
                    valuex = 5;
                    valuey = 11;
                } else if (countPl == 12) {
                    valuex = 4;
                    valuey = 9;
                } else if (countPl == 13) {
                    valuex = 3;
                    valuey = 3;
                } else if (countPl == 14) {
                    valuex = 2;
                    valuey = 6;
                } else {
                    valuex = 1;
                    valuey = 6;
                }
                // Increment plain so we know how many
                // we have collected.
                countPl++;
                // Done with plain setup.
            } else if (typeReal.equals(ModuleType.SANITATION)) {
                type = ModuleType.SANITATION;
                if (countB == 0) {
                    valuex = 2 + 2;
                    valuey = 2;
                } else if (countB == 1) {
                    valuex = 2 + 2;
                    valuey = 2 + 2;
                } else if (countB == 2) {
                    valuex = 2;
                    valuey = 5;
                } else {
                    valuex = 2;
                    valuey = 7;
                }
                countB++;
            } else if (typeReal.equals(ModuleType.CONTROL)) {
                type = ModuleType.CONTROL;
                if (countC == 0) {
                    valuex = 2;
                    valuey = 2;
                } else {
                    valuex = 2;
                    valuey = 2 * (2 * 2 + 1);
                }
                countC++;
            } else if (typeReal.equals(ModuleType.AIRLOCK)) {
                type = ModuleType.AIRLOCK;
                valuex = 2 + 1;
                valuey = 0;
            } else if (typeReal.equals(ModuleType.CANTEEN)) {
                type = ModuleType.CANTEEN;
                if (countCa == 0) {
                   valuex = 2 + 2;
                   valuey = 2 * 2 + 2;
                } else {
                    valuex = 2;
                    valuey = (2 + 1) * 2;
                }
                countCa++;
            } else if (typeReal.equals(ModuleType.POWER)) {
                type = ModuleType.POWER;
                if (countP == 0) {
                    valuex = 2 + 2;
                    valuey = 1;
                } else {
                    valuex = 2 * 2 + 1;
                    valuey = 2 * (2 * 2 + 1);
                }
                countP++;
            } else if (typeReal.equals(ModuleType.FOOD_AND_WATER)) {
                type = ModuleType.FOOD_AND_WATER;
                if (countS == 0) {
                   valuex = 4;
                   valuey = 7;
                } else if (countS == 1) {
                    valuex = 4;
                    valuey = 10;
                } else {
                    valuex = 2;
                    valuey = 10;
                }
                countS++;
            } else if (typeReal.equals(ModuleType.DORMITORY)) {
                type = ModuleType.DORMITORY;
                if (countD == 0) {
                    valuex = 4;
                    valuey = 3;
                } else if (countD == 1) {
                    valuex = 1;
                    valuey = 3;
                } else if (countD == 2) {
                    valuex = 2;
                    valuey = 4;
                } else if (countD == 3){
                    valuex = 1;
                    valuey = 5;
                } else if (countD == 4) {
                    valuex = 0;
                    valuey = 6;
                } else if (countD == 5) {
                    valuex = 1;
                    valuey = 7;
                } else if (countD == 6) {
                    valuex = 4;
                    valuey = 8;
                } else  {
                    valuex = 5;
                    valuey = 9;
                }
                countD++;
            } else if (typeReal.equals(ModuleType.MEDICAL)) {
                type = ModuleType.MEDICAL;
                valuex = 2;
                valuey = 1;
            } else {
                type = ModuleType.GYM_AND_RELAXATION;
                if (countG == 0) {
                   valuex = 4;
                   valuey = 5;
                } else {
                    valuex = 2;
                    valuey = 8;
                }
                countG++;
            }
        } // End for loop.
     // Create type and point for minimum object.
        valuex += centroidX;
        valuey += centroidY;
        Point point = new Point(valuex, valuey);
        Maximum min = new Maximum(type, point);
        maxList.add(min);
        FullConfigPage.setUpFullConfig(maxList);

	}
//**************************************
	/**
	 * makeLarge follows all layout rules.
	 */
	private void makeLarge() {
	    int total = 2 * 2 * 2 + 2; // 10.
        ModuleType type = ModuleType.PLAIN;
        ModuleType typeReal;
        int valuex = 0;
        int valuey = 0;
        /** Traverse modules and set up list of Minimum objects.
         * The points are set as the adjustment needed
         * for the configuration placement.
         */
        for (int i = 0; i < theList.size(); i++) {
            // Get the real module type.
            typeReal = theList.get(i).getType();
            // Start testing and setting up minimum list.
            // This sets grid coordinates of 24 plain modules.
            if (typeReal.equals(ModuleType.PLAIN)) {
                type = ModuleType.PLAIN;
                if (countPl == 0) {
                    valuex = 2 + 1;
                    valuey = 1;
                } else if (countPl == 1) {
                    valuex = 2 + 1;
                    valuey = 2;
                } else if (countPl == 2) {
                    valuex = 2 + 1;
                    valuey = 2 + 1;
                } else if (countPl == (2 + 1)) {
                    valuex = 2 + 1;
                    valuey = 2 + 2;
                } else if (countPl == 4) {
                    valuex = 2 + 1;
                    valuey = 2 * 2 + 1;
                } else if (countPl == 5) {
                    valuex = 3;
                    valuey = 6;
                } else if (countPl == 6) {
                    valuex = 3;
                    valuey = 7;
                } else if (countPl == 7) {
                    valuex = 2;
                    valuey = 7;
                } else if (countPl == 8) {
                    valuex = 1;
                    valuey = 7;
                } else if (countPl == 9) {
                    valuex = 4;
                    valuey = 7;
                } else if (countPl == 10) {
                    valuex = 3;
                    valuey = 8;
                } else if (countPl == 11) {
                    valuex = 3;
                    valuey = 9;
                } else if (countPl == 12) {
                    valuex = 3;
                    valuey = 10;
                } else if (countPl == 13) {
                    valuex = 3;
                    valuey = 11;
                } else if (countPl == 14) {
                    valuex = 3;
                    valuey = 12;
                } else if (countPl == 15) {
                    valuex = 3;
                    valuey = 13;
                } else if (countPl == 16) {
                    valuex = 2;
                    valuey = 12;
                } else if (countPl == 17) {
                    valuex = 4;
                    valuey = 12;
                } else if (countPl == 18) {
                    valuex = 5;
                    valuey = 12;
                } else if (countPl == 19) {
                    valuex = 6;
                    valuey = 12;
                } else if (countPl == 20) {
                    valuex = 7;
                    valuey = 12;
                } else if (countPl == 21) {
                    valuex = 8;
                    valuey = 12;
                } else if (countPl == 22) {
                    valuex = 9;
                    valuey = 12;
                } else {
                    valuex = 10;
                    valuey = 12;
                }
                // Increment plain so we know how many
                // we have collected.
                countPl++;
                // Done with plain setup.
            } else if (typeReal.equals(ModuleType.SANITATION)) {
                type = ModuleType.SANITATION;
                // Makes 6 bathrooms.
                if (countB == 0) {
                    valuex = 2 + 2;
                    valuey = 3;
                } else if (countB == 1) {
                    valuex = 2 + 2;
                    valuey = 2 + 2 + 1;
                } else if (countB == 2) {
                    valuex = 3;
                    valuey = 6;
                } else if (countB == 3) {
                    valuex = 3;
                    valuey = 8;
                } else if (countB == 4) {
                    valuex = 2;
                    valuey = 13;
                } else {
                    valuex = 9;
                    valuey = 11;
                }
                countB++;
            } else if (typeReal.equals(ModuleType.CONTROL)) {
                type = ModuleType.CONTROL;
                // Make 2 control.
                if (countC == 0) {
                    valuex = 4;
                    valuey = 1;
                } else {
                    valuex = 6;
                    valuey = 13;
                }
                countC++;
            } else if (typeReal.equals(ModuleType.AIRLOCK)) {
                type = ModuleType.AIRLOCK;
                // Make 2 airlocks.
                if (countA == 0) {
                    valuex = 3;
                    valuey = 0;
                } else {
                    valuex = 11;
                    valuey = 12;
                }
                countA++;
            } else if (typeReal.equals(ModuleType.CANTEEN)) {
                type = ModuleType.CANTEEN;
                // Make 2 canteens.
                if (countCa == 0) {
                   valuex = 4;
                   valuey = 8;
                } else {
                    valuex = 7;
                    valuey = 11;
                }
                countCa++;
            } else if (typeReal.equals(ModuleType.POWER)) {
                type = ModuleType.POWER;
                // Make 2 power.
                if (countP == 0) {
                    valuex = 4;
                    valuey = 2;
                } else {
                    valuex = 5;
                    valuey = 11;
                }
                countP++;
            } else if (typeReal.equals(ModuleType.FOOD_AND_WATER)) {
                type = ModuleType.FOOD_AND_WATER;
                // Make 2 storage.
                if (countS == 0) {
                    valuex = 4;
                    valuey = 9;
                } else if (countS == 1) {
                    valuex = 2 + 2;
                    valuey = 10;
                } else if (countS == 2) {
                    valuex = 2 + 2;
                    valuey = 11;
                } else if (countS == 3) {
                    valuex = 6;
                    valuey = 11;
                } else if (countS == 4) {
                    valuex = 7;
                    valuey = 13;
                } else {
                    valuex = 8;
                    valuey = 13;
                }
                countS++;
            } else if (typeReal.equals(ModuleType.DORMITORY)) {
                type = ModuleType.DORMITORY;
                // Make 11 dorms.
                if (countD == 0) {
                    valuex = 2;
                    valuey = 3;
                } else if (countD == 1) {
                    valuex = 2;
                    valuey = 2 + 2;
                } else if (countD == 2) {
                    valuex = 2;
                    valuey = 2 * 2 + 1;
                } else if (countD == (2 + 1)){
                    valuex = 1;
                    valuey = 6;
                } else if (countD == 4) {
                    valuex = 0;
                    valuey = 7;
                } else if (countD == 5) {
                    valuex = 4;
                    valuey = 4;
                } else if (countD == 6) {
                    valuex = 2;
                    valuey = 11;
                } else if (countD == 7) {
                    valuex = 4;
                    valuey = 13;
                } else if (countD == 8) {
                    valuex = 9;
                    valuey = 13;
                } else if (countD == 9) {
                    valuex = 8;
                    valuey = 11;
                } else {
                    valuex = 1;
                    valuey = 8;
                }
                countD++;
            } else if (typeReal.equals(ModuleType.MEDICAL)) {
                type = ModuleType.MEDICAL;
                // make 2 medical.
                if (countM == 0) {
                    valuex = 2;
                    valuey = 1;
                } else {
                    valuex = 10;
                    valuey = 13;
                }
                countM++;
            } else {
                type = ModuleType.GYM_AND_RELAXATION;
                // Make 2 gyms.
                if (countG == 0) {
                   valuex = 2;
                   valuey = 9;
                } else {
                    valuex = 10;
                    valuey = 11;
                }
                countG++;
            }
        } // End for loop.
     // Create type and point for minimum object.
        valuex += centroidX;
        valuey += centroidY;
        Point point = new Point(valuex, valuey);
        Maximum min = new Maximum(type, point);
        maxList.add(min);
        FullConfigPage.setUpFullConfig(maxList);
	}
//**************************************
	/**
	 * makeLarge2 follows layout rules.
	 */
	private void makeLarge2() {
	    int total = 2 * 2 * 2 + 2; // 10.
        ModuleType type = ModuleType.PLAIN;
        ModuleType typeReal;
        int valuex = 0;
        int valuey = 0;
        /** Traverse modules and set up list of Minimum objects.
         * The points are set as the adjustment needed
         * for the configuration placement.
         */
        for (int i = 0; i < total; i++) {
            // Get the real module type.
            typeReal = maxList.get(i).getCode();
            // Start testing and setting up minimum list.
            if (typeReal.equals(ModuleType.PLAIN)) {
                type = ModuleType.PLAIN;
                if (countPl == 0) {
                    valuex = 2 + 1;
                    valuey = 1;
                } else if (countPl == 1) {
                    valuex = 2 + 1;
                    valuey = 2;
                } else if (countPl == 2) {
                    valuex = 2 + 1;
                    valuey = 2 + 1;
                } else if (countPl == 3){
                    valuex = 2 + 1;
                    valuey = 2 + 2;
                } else if (countPl == 4) {
                    valuex = 2 + 1;
                    valuey = 2 * 2 + 1;
                } else if (countPl == 5) {
                    valuex = 3;
                    valuey = 6;
                } else if (countPl == 6) {
                    valuex = 3;
                    valuey = 7;
                } else if (countPl == 7) {
                    valuex = 2;
                    valuey = 7;
                } else if (countPl == 8) {
                    valuex = 1;
                    valuey = 7;
                } else if (countPl == 9) {
                    valuex = 4;
                    valuey = 7;
                } else if (countPl == 10) {
                    valuex = 3;
                    valuey = 8;
                } else if (countPl == 11) {
                    valuex = 3;
                    valuey = 9;
                } else if (countPl == 12) {
                    valuex = 3;
                    valuey = 10;
                } else if (countPl == 13) {
                    valuex = 3;
                    valuey = 11;
                } else if (countPl == 14) {
                    valuex = 3;
                    valuey = 12;
                } else if (countPl == 15) {
                    valuex = 3;
                    valuey = 13;
                } else if (countPl == 16) {
                    valuex = 2;
                    valuey = 12;
                } else if (countPl == 17) {
                    valuex = 4;
                    valuey = 12;
                } else if (countPl == 18) {
                    valuex = 5;
                    valuey = 12;
                } else if (countPl == 19) {
                    valuex = 6;
                    valuey = 12;
                } else if (countPl == 20) {
                    valuex = 7;
                    valuey = 12;
                } else if (countPl == 21) {
                    valuex = 8;
                    valuey = 12;
                } else if (countPl == 22) {
                    valuex = 9;
                    valuey = 12;
                } else if (countPl == 23) {
                    valuex = 3;
                    valuey = 14;
                } else if (countPl == 24) {
                    valuex = 3;
                    valuey = 15;
                } else if (countPl == 25) {
                    valuex = 3;
                    valuey = 16;
                } else if (countPl == 26) {
                    valuex = 3;
                    valuey = 17;
                } else if (countPl == 27) {
                    valuex = 3;
                    valuey = 18;
                } else if (countPl == 28) {
                    valuex = 3;
                    valuey = 19;
                } else if (countPl == 29) {
                    valuex = 3;
                    valuey = 20;
                } else if (countPl == 30) {
                    valuex = 3;
                    valuey = 21;
                } else {
                    valuex = 10;
                    valuey = 12;
                }
                // Increment plain so we know how many
                // we have collected.
                countPl++;
                // Done with plain setup.
            } else if (typeReal.equals(ModuleType.SANITATION)) {
                type = ModuleType.SANITATION;
                if (countB == 0) {
                    valuex = 2 + 2;
                    valuey = 3;
                } else if (countB == 1) {
                    valuex = 2 + 2;
                    valuey = 2 + 2 + 1;
                } else if (countB == 2) {
                    valuex = 3;
                    valuey = 6;
                } else if (countB == 3) {
                    valuex = 3;
                    valuey = 8;
                } else if (countB == 4) {
                    valuex = 2;
                    valuey = 13;
                } else if (countB == 5) {
                    valuex = 9;
                    valuey = 11;
                } else if (countB == 6) {
                    valuex = 4;
                    valuey = 15;
                } else if (countB == 7) {
                    valuex = 4;
                    valuey = 17;
                } else {
                    valuex = 2;
                    valuey = 16;
                }
                countB++;
            } else if (typeReal.equals(ModuleType.CONTROL)) {
                type = ModuleType.CONTROL;
                if (countC == 0) {
                    valuex = 4;
                    valuey = 1;
                } else if (countC == 1) {
                    valuex = 6;
                    valuey = 13;
                } else {
                    valuex = 4;
                    valuey = 20;
                }
                countC++;
            } else if (typeReal.equals(ModuleType.AIRLOCK)) {
                type = ModuleType.AIRLOCK;
                if (countA == 0) {
                    valuex = 3;
                    valuey = 0;
                } else if (countA == 1){
                    valuex = 11;
                    valuey = 12;
                } else {
                    valuex = 3;
                    valuey = 22;
                }
                countA++;
            } else if (typeReal.equals(ModuleType.CANTEEN)) {
                type = ModuleType.CANTEEN;
                if (countCa == 0) {
                   valuex = 4;
                   valuey = 8;
                } else if (countCa == 1){
                    valuex = 7;
                    valuey = 11;
                } else {
                    valuex = 2;
                    valuey = 21;
                }
                countCa++;
            } else if (typeReal.equals(ModuleType.POWER)) {
                type = ModuleType.POWER;
                if (countP == 0) {
                    valuex = 4;
                    valuey = 2;
                } else if (countP == 1){
                    valuex = 5;
                    valuey = 11;
                } else {
                    valuex = 2;
                    valuey = 19;
                }
                countP++;
            } else if (typeReal.equals(ModuleType.FOOD_AND_WATER)) {
                type = ModuleType.FOOD_AND_WATER;
                if (countS == 0) {
                    valuex = 4;
                    valuey = 9;
                } else if (countS == 1) {
                    valuex = 2 + 2;
                    valuey = 10;
                } else if (countS == 2) {
                    valuex = 2 + 2;
                    valuey = 11;
                } else if (countS == 3) {
                    valuex = 6;
                    valuey = 11;
                } else if (countS == 4) {
                    valuex = 8;
                    valuey = 12;
                } else if (countS == 5) {
                    valuex = 8;
                    valuey = 13;
                } else {
                    valuex = 2;
                    valuey = 20;
                }
                countS++;
            } else if (typeReal.equals(ModuleType.DORMITORY)) {
                type = ModuleType.DORMITORY;
                if (countD == 0) {
                    valuex = 2;
                    valuey = 3;
                } else if (countD == 1) {
                    valuex = 2;
                    valuey = 2 + 2;
                } else if (countD == 2) {
                    valuex = 2;
                    valuey = 2 * 2 + 1;
                } else if (countD == 3) {
                    valuex = 1;
                    valuey = 6;
                } else if (countD == 4) {
                    valuex = 0;
                    valuey = 7;
                } else if (countD == 5) {
                    valuex = 4;
                    valuey = 4;
                } else if (countD == 6) {
                    valuex = 2;
                    valuey = 11;
                } else if (countD == 7) {
                    valuex = 4;
                    valuey = 13;
                } else if (countD == 8) {
                    valuex = 9;
                    valuey = 13;
                } else if (countD == 9) {
                    valuex = 8;
                    valuey = 11;
                } else if (countD == 10) {
                    valuex = 1;
                    valuey = 8;
                } else if (countD == 11){
                    valuex = 2;
                    valuey = 14;
                } else if (countD == 12) {
                    valuex = 2;
                    valuey = 15;
                } else if (countD == 13) {
                    valuex = 4;
                    valuey = 14;
                } else if (countD == 14) {
                    valuex = 4;
                    valuey = 18;
                } else if (countD == 15) {
                    valuex = 4;
                    valuey = 19;
                } else if (countD == 16) {
                    valuex = 2;
                    valuey = 17;
                } else {
                    valuex = 2;
                    valuey = 18;
                }
                countD++;
            } else if (typeReal.equals(ModuleType.MEDICAL)) {
                type = ModuleType.MEDICAL;
                if (countM == 0) {
                    valuex = 2;
                    valuey = 1;
                } else if (countM == 1) {
                    valuex = 10;
                    valuey = 13;
                } else {
                    valuex = 4;
                    valuey = 21;
                }
                countM++;
            } else {
                type = ModuleType.GYM_AND_RELAXATION;
                if (countG == 0) {
                   valuex = 2;
                   valuey = 9;
                } else if (countG == 1) {
                    valuex = 10;
                    valuey = 11;
                } else {
                    valuex = 4;
                    valuey = 16;
                }
                countG++;
            }
        } // End for loop.
     // Create type and point for minimum object.
        valuex += centroidX;
        valuey += centroidY;
        Point point = new Point(valuex, valuey);
        Maximum min = new Maximum(type, point);
        maxList.add(min);
        FullConfigPage.setUpFullConfig(maxList);
	}
//************************************
	/**
	 * makeMax is the full config with 104 modules.
	 */
	private void makeMax() {
	    int total = 2 * 2 * 2 + 2; // 10.
        ModuleType type = ModuleType.PLAIN;
        ModuleType typeReal;
        int valuex = 0;
        int valuey = 0;
        /** Traverse modules and set up list of Minimum objects.
         * The points are set as the adjustment needed
         * for the configuration placement.
         */
        for (int i = 0; i < total; i++) {
            // Get the real module type.
            typeReal = maxList.get(i).getCode();
            // Start testing and setting up minimum list.
            if (typeReal.equals(ModuleType.PLAIN)) {
                type = ModuleType.PLAIN;
                if (countPl == 0) {
                    valuex = 9;
                    valuey = 1;
                } else if (countPl == 1) {
                    valuex = 9;
                    valuey = 2;
                } else if (countPl == 2) {
                    valuex = 9;
                    valuey = 2 + 1;
                } else if (countPl == 3){
                    valuex = 9;
                    valuey = 2 + 2;
                } else if (countPl == 4) {
                    valuex = 9;
                    valuey = 2 * 2 + 1;
                } else if (countPl == 5) {
                    valuex = 9;
                    valuey = 6;
                } else if (countPl == 6) {
                    valuex = 9;
                    valuey = 7;
                } else if (countPl == 7) {
                    valuex = 9;
                    valuey = 8;
                } else if (countPl == 8) {
                    valuex = 9;
                    valuey = 9;
                } else if (countPl == 9) {
                    valuex = 9;
                    valuey = 10;
                } else if (countPl == 10) {
                    valuex = 9;
                    valuey = 11;
                } else if (countPl == 11) {
                    valuex = 9;
                    valuey = 12;
                } else if (countPl == 12) {
                    valuex = 9;
                    valuey = 13;
                } else if (countPl == 13) {
                    valuex = 9;
                    valuey = 14;
                } else if (countPl == 14) {
                    valuex = 9;
                    valuey = 15;
                } else if (countPl == 15) {
                    valuex = 9;
                    valuey = 16;
                } else if (countPl == 16) {
                    valuex = 9;
                    valuey = 17;
                } else if (countPl == 17) {
                    valuex = 9;
                    valuey = 18;
                } else if (countPl == 18) {
                    valuex = 9;
                    valuey = 19;
                } else if (countPl == 19) {
                    valuex = 9;
                    valuey = 20;
                } else if (countPl == 20) {
                    valuex = 9;
                    valuey = 21;
                    // Horizontal plain strip.
                } else if (countPl == 21) {
                    valuex = 10;
                    valuey = 7;
                } else if (countPl == 22) {
                    valuex = 8;
                    valuey = 7;
                } else if (countPl == 23) {
                    valuex = 7;
                    valuey = 7;
                    // 1st Left vertical (4).
                } else if (countPl == 24) {
                    valuex = 8;
                    valuey = 12;
                } else if (countPl == 25) {
                    valuex = 10;
                    valuey = 12;
                } else if (countPl == 26) {
                    valuex = 11;
                    valuey = 12;
                } else if (countPl == 27) {
                    valuex = 12;
                    valuey = 12;
                } else if (countPl == 28) {
                    valuex = 13;
                    valuey = 12;
                } else if (countPl == 29) {
                    valuex = 14;
                    valuey = 12;
                } else if (countPl == 30) {
                    valuex = 15;
                    valuey = 12;
                } else if (countPl == 31) {
                    valuex = 16;
                    valuey = 12;
                } else if (countPl == 32) {
                    valuex = 7;
                    valuey = 12;
                } else if (countPl == 33) {
                    valuex = 6;
                    valuey = 12;
                } else if (countPl == 34) {
                    valuex = 5;
                    valuey = 12;
                } else if (countPl == 35) {
                    valuex = 5;
                    valuey = 13;
                } else if (countPl == 36) {
                    valuex = 4;
                    valuey = 13;
                } else if (countPl == 37) {
                    valuex = 3;
                    valuey = 13;
                } else if (countPl == 38) {
                    valuex = 2;
                    valuey = 13;
                } else {
                    valuex = 1;
                    valuey = 13;
                }
                // Increment plain so we know how many
                // we have collected.
                countPl++;
                // Done with plain setup.
            } else if (typeReal.equals(ModuleType.SANITATION)) {
                type = ModuleType.SANITATION;
                if (countB == 0) {
                    valuex = 10;
                    valuey = 3;
                } else if (countB == 1) {
                    valuex = 10;
                    valuey = 2 + 2 + 1;
                } else if (countB == 2) {
                    valuex = 8;
                    valuey = 6;
                } else if (countB == 3) {
                    valuex = 8;
                    valuey = 8;
                } else if (countB == 4) {
                    valuex = 15;
                    valuey = 11;
                } else if (countB == 5) {
                    valuex = 8;
                    valuey = 13;
                } else if (countB == 6) {
                    valuex = 10;
                    valuey = 15;
                } else if (countB == 7) {
                    valuex = 10;
                    valuey = 17;
                } else if (countB == 8) {
                    valuex = 8;
                    valuey = 16;
                } else {
                    valuex = 5;
                    valuey = 11;
                }
                countB++;
            } else if (typeReal.equals(ModuleType.CONTROL)) {
                type = ModuleType.CONTROL;
                if (countC == 0) {
                    valuex = 10;
                    valuey = 1;
                } else if (countC == 1) {
                    valuex = 12;
                    valuey = 13;
                } else if (countC == 2) {
                    valuex = 10;
                    valuey = 20;
                } else {
                    valuex = 2;
                    valuey = 12;
                }
                countC++;
            } else if (typeReal.equals(ModuleType.AIRLOCK)) {
                type = ModuleType.AIRLOCK;
                if (countA == 0) {
                    valuex = 9;
                    valuey = 0;
                } else if (countA == 1){
                    valuex = 17;
                    valuey = 12;
                } else if (countA == 2) {
                    valuex = 9;
                    valuey = 22;
                } else {
                    valuex = 0;
                    valuey = 13;
                }
                countA++;
            } else if (typeReal.equals(ModuleType.CANTEEN)) {
                type = ModuleType.CANTEEN;
                if (countCa == 0) {
                   valuex = 10;
                   valuey = 8;
                } else if (countCa == 1){
                    valuex = 13;
                    valuey = 11;
                } else if (countCa == 2) {
                    valuex = 8;
                    valuey = 21;
                } else {
                    valuex = 5;
                    valuey = 14;
                }
                countCa++;
            } else if (typeReal.equals(ModuleType.POWER)) {
                type = ModuleType.POWER;
                if (countP == 0) {
                    valuex = 10;
                    valuey = 2;
                } else if (countP == 1){
                    valuex = 11;
                    valuey = 11;
                } else if (countP == 2) {
                    valuex = 8;
                    valuey = 19;
                } else {
                    valuex = 1;
                    valuey = 14;
                }
                countP++;
            } else if (typeReal.equals(ModuleType.FOOD_AND_WATER)) {
                type = ModuleType.FOOD_AND_WATER;
                if (countS == 0) {
                    valuex = 10;
                    valuey = 9;
                } else if (countS == 1) {
                    valuex = 10;
                    valuey = 10;
                } else if (countS == 2) {
                    valuex = 10;
                    valuey = 11;
                } else if (countS == 3) {
                    valuex = 12;
                    valuey = 11;
                } else if (countS == 4) {
                    valuex = 13;
                    valuey = 12;
                } else if (countS == 5) {
                    valuex = 14;
                    valuey = 12;
                } else if (countS == 6) {
                    valuex = 8;
                    valuey = 20;
                } else if (countS == 7) {
                    valuex = 6;
                    valuey = 13;
                } else if (countS == 8) {
                    valuex = 3;
                    valuey = 14;
                } else {
                    valuex = 4;
                    valuey = 14;
                }
                countS++;
            } else if (typeReal.equals(ModuleType.DORMITORY)) {
                type = ModuleType.DORMITORY;
                if (countD == 0) {
                    valuex = 8;
                    valuey = 3;
                } else if (countD == 1) {
                    valuex = 8;
                    valuey = 2 + 2;
                } else if (countD == 2) {
                    valuex = 8;
                    valuey = 2 * 2 + 1;
                } else if (countD == 3) {
                    valuex = 10;
                    valuey = 4;
                } else if (countD == 4) {
                    valuex = 7;
                    valuey = 6;
                } else if (countD == 5) {
                    valuex = 6;
                    valuey = 7;
                } else if (countD == 6) {
                    valuex = 7;
                    valuey = 8;
                } else if (countD == 7) {
                    valuex = 8;
                    valuey = 11;
                } else if (countD == 8) {
                    valuex = 10;
                    valuey = 13;
                } else if (countD == 9) {
                    valuex = 10;
                    valuey = 14;
                } else if (countD == 10) {
                    valuex = 8;
                    valuey = 14;
                } else if (countD == 11){
                    valuex = 14;
                    valuey = 11;
                } else if (countD == 12) {
                    valuex = 15;
                    valuey = 13;
                } else if (countD == 13) {
                    valuex = 8;
                    valuey = 15;
                } else if (countD == 14) {
                    valuex = 8;
                    valuey = 17;
                } else if (countD == 15) {
                    valuex = 8;
                    valuey = 18;
                } else if (countD == 16) {
                    valuex = 10;
                    valuey = 18;
                }else if (countD == 17) {
                    valuex = 10;
                    valuey = 19;
                } else if (countD == 18) {
                    valuex = 3;
                    valuey = 12;
                } else {
                    valuex = 4;
                    valuey = 12;
                }
                countD++;
            } else if (typeReal.equals(ModuleType.MEDICAL)) {
                type = ModuleType.MEDICAL;
                if (countM == 0) {
                    valuex = 8;
                    valuey = 1;
                } else if (countM == 1) {
                    valuex = 1;
                    valuey = 12;
                } else if (countM == 2) {
                    valuex = 16;
                    valuey = 13;
                } else {
                    valuex = 9;
                    valuey = 22;
                }
                countM++;
            } else {
                type = ModuleType.GYM_AND_RELAXATION;
                if (countG == 0) {
                   valuex = 8;
                   valuey = 9;
                } else if (countG == 1) {
                    valuex = 6;
                    valuey = 11;
                } else if (countG == 2) {
                    valuex = 16;
                    valuey = 11;
                } else {
                    valuex = 10;
                    valuey = 16;
                }
                countG++;
            }
        } // End for loop.
     // Create type and point for minimum object.
        valuex += centroidX;
        valuey += centroidY;
        Point point = new Point(valuex, valuey);
        Maximum min = new Maximum(type, point);
        maxList.add(min);
        FullConfigPage.setUpFullConfig(maxList);
	}
//*************************************
	/**
	 * getter for the max array.
	 */
	public ArrayList<Maximum> getMaxArray() {
	    return this.maxList;
	}
}
