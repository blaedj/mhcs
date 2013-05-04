package mhcs.blaed.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import mhcs.blaed.ConfigSaver;
import mhcs.dan.Module.ModuleType;
import mhcs.danielle.Minimum;
import mhcs.danielle.MinimumConfiguration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.touch.client.Point;

public class ConfigSaverTest extends GWTTestCase {

    private ArrayList<Minimum> configuration;

    @Before
    public void gwtSetUp() throws Exception {
	// setup a configuration
	configuration = new ArrayList<Minimum>();

	configuration.add(new Minimum(ModuleType.PLAIN, new Point(11.0, 1.0)));
	configuration.add(new Minimum(ModuleType.FOOD_AND_WATER, new Point(23.0, 2.0)));
	configuration.add(new Minimum(ModuleType.GYM_AND_RELAXATION, new Point(31.0, 3.0)));
	configuration.add(new Minimum(ModuleType.PLAIN, new Point(49.0, 4.0)));
	configuration.add(new Minimum(ModuleType.PLAIN, new Point(5.0,  5.0)));

    }

    @After
    public void getSetDown() throws Exception {
	configuration.clear();
    }

    @Test
    public void testStore() {
	ConfigSaver saver = new ConfigSaver();
	String configKey = "testKey";
	saver.saveConfig(configKey, configuration);

	Storage local = Storage.getLocalStorageIfSupported();

	JSONArray requestedArray = (JSONArray) JSONParser.parseLenient(local.getItem(configKey));

	ArrayList<Minimum> expected = new ArrayList<Minimum>();

	ArrayList<Minimum> requestedConverted = new ArrayList<Minimum>();

	expected.add(new Minimum(ModuleType.PLAIN, new Point(11.0, 1.0)));
	expected.add(new Minimum(ModuleType.FOOD_AND_WATER, new Point(23.0, 2.0)));
	expected.add(new Minimum(ModuleType.GYM_AND_RELAXATION, new Point(31.0, 3.0)));
	expected.add(new Minimum(ModuleType.PLAIN, new Point(49.0, 4.0)));
	expected.add(new Minimum(ModuleType.PLAIN, new Point(5.0,  5.0)));

	for(int i = 0; i < requestedArray.size(); i++) {
		requestedConverted.add(ConfigSaver.jsonToModule((JSONObject)requestedArray.get(i)));
	}

	assertEquals("Failure - The lists are not equal", expected, requestedConverted );
}

    @Test
    public void testLoad() {
    	ConfigSaver saver = new ConfigSaver();
    	String configKey = "testKey2";

	saver.saveConfig(configKey, configuration);

	ArrayList<Minimum> actualRetrieved = new ArrayList<Minimum>();
	ArrayList<Minimum> expected = new ArrayList<Minimum>();
	expected.add(new Minimum(ModuleType.PLAIN, new Point(11.0, 1.0)));
	expected.add(new Minimum(ModuleType.FOOD_AND_WATER, new Point(23.0, 2.0)));
	expected.add(new Minimum(ModuleType.GYM_AND_RELAXATION, new Point(31.0, 3.0)));
	expected.add(new Minimum(ModuleType.PLAIN, new Point(49.0, 4.0)));
	expected.add(new Minimum(ModuleType.PLAIN, new Point(5.0,  5.0)));
	
	actualRetrieved = saver.loadConfig(configKey);

	assertEquals("Failure - the lists are not equal", expected, actualRetrieved);
	
	
}



    	@Override
	public String getModuleName() {
		return "mhcs.MHCS";
	}

}
