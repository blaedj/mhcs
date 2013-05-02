import static org.junit.Assert.*;

import mhcs.dan.Module.ModuleType;
import mhcs.danielle.Maximum;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.touch.client.Point;

/**
 * 
 */

/**
 * @author daniellebennett
 *
 */
public class TestMaximum {

	/**
 	* the first private test variable Maximum.
 	*/
	private Maximum max1;
	/**
 	* the 2nd private test variable Maximum.
 	*/
	private Maximum max2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		max1 = new Maximum(ModuleType.AIRLOCK, new Point(3, 7));
		max2 = new Maximum(ModuleType.CONTROL, new Point(11, 13));
	}
	/**
	 * testing maximum objects.
	 * @Test
	 */
	@Test
	public void test() {
		assertEquals(max1.getCode().toString(), "airlock");
		assertEquals(max2.getCode().toString(), "control");
		assertEquals((int) max1.getPoint().getX(), 3);
		assertEquals((int) max2.getPoint().getX(), 11);
		assertEquals((int) max1.getPoint().getY(), 7);
		assertEquals((int) max2.getPoint().getY(), 13);
	}

}
