import static org.junit.Assert.*;

import java.util.ArrayList;
import mhcs.dan.Module;
import mhcs.danielle.Minimum;
import mhcs.danielle.MinimumConfiguration;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests MinConfigPage and MinimumConfiguration.
 * @author daniellestewart
 *
 */
public class TestMinConfig {
	/**
	 * private data member testMods is the test array of modules.
	 */
	private ArrayList<Module> testMods;
	/**
	 * Private member minList is the list of Minimum objects
	 * returned by MinimumConfiguration object.
	 */
	private ArrayList<Minimum> minList;
	/**
	 * private member minConf is the MinimumConfiguration object
	 * being tested.
	 */
	private MinimumConfiguration minConf;
	/**
	 * Initialize all data members.
	 */
	@Before
	public void initializeTest() {
	    testMods = new ArrayList<Module>();
        testMods.add(new Module("11","UNDAMAGED", "25", "11", "1"));
        testMods.add(new Module("13", "UNDAMAGED", "35", "21", "1"));
        testMods.add(new Module("15", "UNDAMAGED", "41", "91", "1"));
        testMods.add(new Module("63", "UNDAMAGED", "35", "63", "1"));
        testMods.add(new Module("99", "UNDAMAGED", "18", "81", "1"));
        testMods.add(new Module("113", "UNDAMAGED", "31", "21", "1"));
        testMods.add(new Module("141", "UNDAMAGED", "1", "21", "1"));
        testMods.add(new Module("153", "UNDAMAGED", "17", "81", "1"));
        testMods.add(new Module("163", "UNDAMAGED", "9", "9", "1"));
        testMods.add(new Module("171", "UNDAMAGED", "0", "0", "1"));
	    minConf = new MinimumConfiguration();
	    minList = minConf.getMinArray();
	}
	@Test
	/**
	 * Initializes a test list of modules.
	 * Calls private functions that perform the specific tests.
	 */
	public void testConstructor() {
		assertNotNull(testMods);
		assertEquals(10, testMods.size());
		assert (minConf != null);
        assert (minList.size() == testMods.size());
	}
	/**
	 * This method tests the creation and retrieval of
	 * MinimumConfiguration object.
	 */
	@Test
	public void testMinConfStrings() {
		/**
		 * local string set to expected string value of minList objects.
		 */
		String expected1 = "plain";
		/**
         * local string set to actual string value of minList objects.
         */
        String actual;
		// Tests if the plain is actually saved as "PLAIN."
		actual = minList.get(1).getCode().toString();
		assertEquals(expected1, actual);
		// Tests to see if power is actually "POWER."
		actual = minList.get(7).getCode().toString();
	}
	/**
	 * Testing constructor of minConfig.
	 */
	@Test
	public void testMinConfCodes() {
	    /**
         * local to test module name.
         */
        String expected2 = "power";
        /**
         * local string set to actual string value of minList objects.
         */
        String actual;
        actual = minList.get(7).getCode().toString();
        assertEquals(expected2, actual);
	}
}
