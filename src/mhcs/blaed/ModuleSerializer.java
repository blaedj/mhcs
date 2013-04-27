package mhcs.blaed;

import mhcs.dan.Module;
import mhcs.dan.ModuleList;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;

/**
 *
 * @author blaed
 * Storage and retrieval of the module list to and from local storage.
 */
public class ModuleSerializer {

	private ModuleList globalList;

	public ModuleSerializer(ModuleList list) {
		globalList = list;
	}

	/**
	 * @throws Exception
	 * @pre the module list contains 1+ modules
	 * @post all the modules in the module list are save to local storage
	 */
	public void saveToLocal(String listKey) throws Exception {

		JSONArray moduleArray = new JSONArray();
		moduleArray = setJsonArray();//

		Storage localStorage = Storage.getLocalStorageIfSupported();
		if(localStorage != null){
			localStorage.setItem(listKey, moduleArray.toString());
		}
		else{
			System.err.println("Local storage does not seem to be supported for this browser");
		}

	}

    /**
     * creates a JSONArray from the contents of the class membet 'globalList'
     * @return JSONArray representation of globalList
     */
	private JSONArray setJsonArray() {
		JSONArray jsonArray = new JSONArray();
		int index = 0;
		for( Module module : globalList){
			jsonArray.set(index, toJson(module));
			index++;
		}
		return jsonArray;
	}

	/**
	 * Converts a Module into a JSONObject.
	 * @param module the Module to convert to a JSONObject.
	 * @return a JSONObject representation of the parameter module.
	 */
	private JSONObject toJson(Module module) {
		JSONObject jmodule = new JSONObject();
		jmodule.put("code", new JSONNumber(Double.parseDouble(module.getCode())));
		jmodule.put("damage", new JSONString(module.getDamage()));
		jmodule.put("xCoor", new JSONNumber(Double.parseDouble(module.getXCoor())));
		jmodule.put("yCoor", new JSONNumber(Double.parseDouble(module.getYCoor())));
		jmodule.put("turns", new JSONNumber(Double.parseDouble(module.getTurns())));

		return jmodule;
	}

	/**
	 * Adds the contents of the list of modules
	 * @param listKey the key of the list of logged modules to retrieve, must equal the key of a previously save module
	 * @pre Browser supports localStorage and listKey is a valid localStorage key
	 * @post the contents of the local storage value specified by 'listKey' are inserted into the moduleList.
	 */
	public void retrieveModuleList(String listKey) {
		Storage localStorage = Storage.getLocalStorageIfSupported();
		JSONArray requestedArray =(JSONArray)JSONParser.parseStrict(localStorage.getItem(listKey));

		JSONObject jModule = new JSONObject();
		for(int index = 0; index < requestedArray.size(); index++){
			jModule = (JSONObject)requestedArray.get(index);
			ModuleList.moduleList.add(jsonToModule(jModule));
		}
	}

	
	/**
	 * Converts a JSONObject representation of a Module to an actual Module object 
	 * @param jsonModule the json representation to be used as a model for the Module object
	 * @return a Module object based on the JSONObject passed in.
	 */
	private Module jsonToModule(JSONObject jsonModule) {
	    String code = jsonModule.get("code").toString();
	    String damage = jsonModule.get("damage").toString();
	    damage = damage.substring(1, damage.length() - 1);
	    String xCoor = jsonModule.get("xCoor").toString();
	    String yCoor = jsonModule.get("yCoor").toString();
	    String turns = jsonModule.get("turns").toString();

	    Module module = new Module(code, damage, xCoor, yCoor, turns);
	    return module;
	}





}






// adding padding
