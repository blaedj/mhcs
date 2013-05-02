
import static org.junit.Assert.*;

import mhcs.dan.Module.ModuleType;
import mhcs.danielle.Minimum;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.touch.client.Point;

/**
 * 
 */

/**
 * Tests the object Minimum.
 * @author daniellebennett
 *
 */
public class TestMinimum {
	/**
 	* the first private test variable Minimum.
 	*/
	private Minimum min1;
	/**
 	* the 2nd private test variable Minimum.
 	*/
	private Minimum min2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		min1 = new Minimum(ModuleType.AIRLOCK, new Point(3, 7));
		min2 = new Minimum(ModuleType.CONTROL, new Point(11, 13));
	}

	@Test
	public void testStrings() {
		assertEquals(min1.getCode().toString(), "airlock");
		assertEquals(min2.getCode().toString(), "control");
	}
	@Test
	public void testPoints() {
        assertEquals((int) min1.getPoint().getX(), 3);
        assertEquals((int) min2.getPoint().getX(), 11);
        assertEquals((int) min1.getPoint().getY(), 7);
        assertEquals((int) min2.getPoint().getY(), 13);
	    
	}
}
