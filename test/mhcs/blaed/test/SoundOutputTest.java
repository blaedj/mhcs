package mhcs.blaed.test;

import mhcs.blaed.SoundPlayer;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class SoundOutputTest extends GWTTestCase{

	SoundPlayer player;
	
	@Before
	public void gwtSetUp() throws Exception {
		player = new SoundPlayer(); 
	}
	
	public void gwtTearDown() {
		
	}
	@Test
	public void testPlayWelcome() {
		player.playWelcome();
		fail("don't know how to test this");
	}

	@Override
	public String getModuleName() {
		return "mhcs.MHCS";
	}

	
	
}
