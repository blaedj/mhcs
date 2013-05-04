import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import mhcs.dan.Module;
import mhcs.dan.ModuleList;
import mhcs.danielle.Minimum;
import mhcs.danielle.MinimumConfiguration;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author daniellestewart
 *
 */
public class testSetUpMinConfig {
    /**
     * Test list #1.
     */
    private ModuleList testMods1;
    /** 
     * Test minConf #1.
     */
    private MinimumConfiguration minConf;
    /**
     * Test minList #1.
     */
    private ArrayList<Minimum> minList1;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        testMods1 = new ModuleList();
        testMods1.add(new Module("11","UNDAMAGED", "25", "11", "1"));
        testMods1.add(new Module("13", "UNDAMAGED", "35", "21", "1"));
        testMods1.add(new Module("15", "UNDAMAGED", "41", "91", "1"));
        testMods1.add(new Module("63", "UNDAMAGED", "35", "63", "1"));
        testMods1.add(new Module("99", "UNDAMAGED", "18", "81", "1"));
        testMods1.add(new Module("113", "UNDAMAGED", "31", "21", "1"));
        testMods1.add(new Module("141", "UNDAMAGED", "1", "21", "1"));
        testMods1.add(new Module("153", "UNDAMAGED", "17", "81", "1"));
        testMods1.add(new Module("163", "UNDAMAGED", "9", "9", "1"));
        testMods1.add(new Module("171", "UNDAMAGED", "0", "0", "1"));
        minConf = new MinimumConfiguration();
        minList1 = minConf.getMinArray(testMods1);
    }

    @Test
    public void test() {
        assertTrue(testMods1.size() > 0);
        assertTrue(minList1.size() > 0);
    }

}
