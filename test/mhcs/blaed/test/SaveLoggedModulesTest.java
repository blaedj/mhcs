package mhcs.blaed.test;

import java.util.List;

import mhcs.blaed.ModuleSerializer;
import mhcs.dan.Module;
import mhcs.dan.ModuleList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.storage.client.Storage;


public class SaveLoggedModulesTest extends GWTTestCase {

	private Storage localStorage;
	private ModuleSerializer saver;
	
	public SaveLoggedModulesTest(){
		super();
		localStorage = Storage.getLocalStorageIfSupported();
		saver = new ModuleSerializer(ModuleList.get());
	}
	
	@Before
	public void gwtSetUp() throws Exception {
		// create some test modules
		createTestModules();
		saver = new ModuleSerializer(ModuleList.get());
		saver.saveToLocal("first");
		localStorage = Storage.getLocalStorageIfSupported();
	}

	@After
	public void gwtTearDown() throws Exception {
		localStorage.clear();
		ModuleList.clearList();
	}

	/**
	 * ensures that there are some modules in the list
	 */
	private void createTestModules() {
		
		ModuleList.addModule(new Module("20", "undamaged", "2", "3", "0"));
	}

	@Test
	public void testNullList() {
		localStorage.clear(); // make sure the list is empty to start with
		ModuleList.clearList();
		saver.retreiveModuleList();
		assertTrue(ModuleList.length() == 0);
	}
	
	@Test
	public void testSaveModuleListLocal() {

		String storageActual = localStorage.getItem("first");
		String expectedStorage = "";
		JSONArray expectedArray = new JSONArray();

		JSONObject module = new JSONObject();
		module.put("code", new JSONNumber(20.0));
		module.put("damage", new JSONString("undamaged"));
		module.put("xCoor", new JSONNumber(2.0));
		module.put("yCoor", new JSONNumber(3.0));
		module.put("turns", new JSONNumber(0.0));
		expectedArray.set(0, module );
		expectedStorage = expectedArray.toString();

		assertEquals("Failure - The storage value is not as expected", expectedStorage, storageActual);
	}

	@Test
	public void testRetrieveModuleList() {

		Module testModule = new Module("20", "undamaged", "2", "3", "0");
		ModuleList.clearList();
		
		saver.retrieveModuleList("first");

		for(Module mod : ModuleList.get()){
			System.out.println(mod.toString());
		}
		System.out.println("\nThis is the testModule: " + testModule.toString() + "\n" );
		assertTrue("the module list does not contain the expected value.", ModuleList.get().contains(testModule));
	}

	@Test
	public void testRetrieveFirstModuleList(){
		ModuleList.clearList();
		saver.retreiveModuleList();
		Module testModule = new Module("20", "undamaged", "2", "3", "0");
		assertTrue("the module list does not contain the expected value.", ModuleList.get().contains(testModule));		
	}

	@Override
	public String getModuleName() {
		return "mhcs.MHCS";
	}

}
