//import static org.junit.Assert.*;

import java.util.ArrayList;

import mhcs.dan.Module;
import mhcs.dan.ModuleList;
import mhcs.danielle.FullConfiguration;
import mhcs.danielle.Maximum;
import mhcs.danielle.MinimumConfiguration;

import org.junit.Before;
import org.junit.Test;


/**
 * @author daniellestewart
 *
 */
public class TestFullConfig {
    /**
     * Test list of modules.
     */
    private ModuleList testMods;
    /**
     * Test configuration object.
     */
    private FullConfiguration maxConf;
	/**
	 * Sets up the test list and a full configuration object.
	 * from the test list.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	    testMods = new ModuleList();
        testMods.add(new Module("1","UNDAMAGED", "25", "11", "1"));
        testMods.add(new Module("2", "UNDAMAGED", "35", "21", "1"));
        testMods.add(new Module("3", "UNDAMAGED", "41", "91", "1"));
        testMods.add(new Module("4", "UNDAMAGED", "35", "63", "1"));
        testMods.add(new Module("5", "UNDAMAGED", "18", "81", "1"));
        testMods.add(new Module("6", "UNDAMAGED", "31", "21", "1"));
        testMods.add(new Module("7", "UNDAMAGED", "1", "21", "1"));
        testMods.add(new Module("8", "UNDAMAGED", "17", "81", "1"));
        testMods.add(new Module("9", "UNDAMAGED", "9", "9", "1"));
        testMods.add(new Module("10", "UNDAMAGED", "0", "0", "1"));
        testMods.add(new Module("11","UNDAMAGED", "25", "11", "1"));
        testMods.add(new Module("12", "UNDAMAGED", "35", "21", "1"));
        testMods.add(new Module("13", "UNDAMAGED", "41", "91", "1"));
        testMods.add(new Module("14", "UNDAMAGED", "35", "63", "1"));
        testMods.add(new Module("15", "UNDAMAGED", "18", "81", "1"));
        testMods.add(new Module("16", "UNDAMAGED", "31", "21", "1"));
        testMods.add(new Module("17", "UNDAMAGED", "1", "21", "1"));
        testMods.add(new Module("18", "UNDAMAGED", "17", "81", "1"));
        testMods.add(new Module("19", "UNDAMAGED", "9", "9", "1"));
        testMods.add(new Module("20", "UNDAMAGED", "0", "0", "1"));
        testMods.add(new Module("21","UNDAMAGED", "25", "11", "1"));
        testMods.add(new Module("22", "UNDAMAGED", "35", "21", "1"));
        testMods.add(new Module("23", "UNDAMAGED", "41", "91", "1"));
        testMods.add(new Module("24", "UNDAMAGED", "35", "63", "1"));
        testMods.add(new Module("61", "UNDAMAGED", "18", "81", "1"));
        testMods.add(new Module("62", "UNDAMAGED", "31", "21", "1"));
        testMods.add(new Module("63", "UNDAMAGED", "1", "21", "1"));
        testMods.add(new Module("64", "UNDAMAGED", "17", "81", "1"));
        testMods.add(new Module("65", "UNDAMAGED", "9", "9", "1"));
        testMods.add(new Module("66", "UNDAMAGED", "0", "0", "1"));
        testMods.add(new Module("67","UNDAMAGED", "25", "11", "1"));
        testMods.add(new Module("68", "UNDAMAGED", "35", "21", "1"));
        testMods.add(new Module("69", "UNDAMAGED", "41", "91", "1"));
        testMods.add(new Module("70", "UNDAMAGED", "35", "63", "1"));
        testMods.add(new Module("71", "UNDAMAGED", "18", "81", "1"));
        testMods.add(new Module("72", "UNDAMAGED", "31", "21", "1"));
        testMods.add(new Module("91", "UNDAMAGED", "1", "21", "1"));
        testMods.add(new Module("92", "UNDAMAGED", "17", "81", "1"));
        testMods.add(new Module("93", "UNDAMAGED", "9", "9", "1"));
        testMods.add(new Module("94", "UNDAMAGED", "0", "0", "1"));
        testMods.add(new Module("95","UNDAMAGED", "25", "11", "1"));
        testMods.add(new Module("96", "UNDAMAGED", "35", "21", "1"));
        testMods.add(new Module("97", "UNDAMAGED", "41", "91", "1"));
        testMods.add(new Module("111", "UNDAMAGED", "35", "63", "1"));
        testMods.add(new Module("112", "UNDAMAGED", "18", "81", "1"));
        testMods.add(new Module("113", "UNDAMAGED", "31", "21", "1"));
        testMods.add(new Module("114", "UNDAMAGED", "1", "21", "1"));
        testMods.add(new Module("115", "UNDAMAGED", "17", "81", "1"));
        testMods.add(new Module("116", "UNDAMAGED", "9", "9", "1"));
        testMods.add(new Module("117", "UNDAMAGED", "0", "0", "1"));
        testMods.add(new Module("131","UNDAMAGED", "25", "11", "1"));
        testMods.add(new Module("132", "UNDAMAGED", "35", "21", "1"));
        testMods.add(new Module("133", "UNDAMAGED", "41", "91", "1"));
        testMods.add(new Module("141", "UNDAMAGED", "35", "63", "1"));
        testMods.add(new Module("142", "UNDAMAGED", "18", "81", "1"));
        testMods.add(new Module("143", "UNDAMAGED", "31", "21", "1"));
        testMods.add(new Module("151", "UNDAMAGED", "1", "21", "1"));
        testMods.add(new Module("152", "UNDAMAGED", "17", "81", "1"));
        testMods.add(new Module("153", "UNDAMAGED", "9", "9", "1"));
        testMods.add(new Module("161", "UNDAMAGED", "0", "0", "1"));
        testMods.add(new Module("162","UNDAMAGED", "25", "11", "1"));
        testMods.add(new Module("163", "UNDAMAGED", "35", "21", "1"));
        testMods.add(new Module("171", "UNDAMAGED", "41", "91", "1"));
        testMods.add(new Module("172", "UNDAMAGED", "35", "63", "1"));
        testMods.add(new Module("173", "UNDAMAGED", "18", "81", "1"));
        testMods.add(new Module("181", "UNDAMAGED", "31", "21", "1"));
        testMods.add(new Module("182", "UNDAMAGED", "1", "21", "1"));
        testMods.add(new Module("183", "UNDAMAGED", "17", "81", "1"));
        maxConf = new FullConfiguration(testMods);
	}

	@Test
	public void test() {
		// Make sure the testMods are added correctly.
        assert (testMods.size() > 0);
        // Make sure the FullConfiguration constructor is working.
        assert (maxConf.getMaxArray() != null);
        // Get the maxArray and test it's size.
        ArrayList<Maximum> maxArray = maxConf.getMaxArray();
        assert (maxArray.size() > 0);
	}

}
