package mhcs.blaed;

import mhcs.dan.Module;
import mhcs.dan.ModuleList;
import mhcs.dan.test.ModuleListTest;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;

/**
 *
 * @author Blaed
 * Storage and retrieval of the module list to and from local storage.
 */
public class ModuleSerializer {

	transient final private ModuleList globalList;

	/**
	 * creates a new module serializer using the static ModuleList.get()
	 */
	public ModuleSerializer() {
		globalList = ModuleList.get();
	}

	/**
	 * save the current list of logged modules to local storage.
	 * @pre the module list contains 1+ modules
	 * @post all the modules in the module list are save to local storage
	 */
	public void saveToLocal(final String listKey) {

		final Storage localStorage = Storage.getLocalStorageIfSupported();
		final SoundPlayer player =  new SoundPlayer();
		if(localStorage == null){
			Window.alert("Local Storage not supported. Some functionality will be limited.");
			player.playErrorOccured();
		}
		else{
			localStorage.setItem(listKey, setJsonArray().toString());			
		}
	}

	/**
	 * Load a previously saved list of modules
	 * @param listKey the key of the list of modules to retrieve, equal to key of a save list
	 * @pre Browser supports localStorage and listKey is a valid localStorage key
	 * @post the contents of the local storage value specified by 'listKey' are put into the moduleList.
	 */
	public boolean retrieveModuleList(final String listKey) {
		Storage localStorage = Storage.getLocalStorageIfSupported();
		JSONArray requestedArray = (JSONArray)JSONParser.parseStrict(localStorage.getItem(listKey));
		SoundPlayer player = new SoundPlayer();
		if( processArray(requestedArray)){
			player.playModulesLoaded();
			return true;
		}
		else {
			player.playErrorOccured();
			return false;
		}
	}

	/**
	 * Load a previously saved list of modules, loads the first list it finds. If the current list is not 
	 * empty, it will be emptied prior to adding any modules.
	 * @pre Browser supports local storage and a list has been save to local storage
	 * @post the contents of the first list in local storage has been loaded into the moduleList
	 */
	public boolean retrieveModuleList() {
		Storage localStorage = Storage.getLocalStorageIfSupported();
		JSONArray requestedArray = null;
		int index;
		boolean valid = false;
		boolean success = false;
		SoundPlayer player = new SoundPlayer();
		if(localStorage.getLength() > 0){
			ModuleList.clearList();
		}
		else {
			player.playErrorOccured();
			return success;
		}
		for (index = 0; index < localStorage.getLength(); index++){
			String key = localStorage.key(index);
			valid = inspectArray(JSONParser.parseStrict( localStorage.getItem(key) ));
			
			if(valid){
				requestedArray = (JSONArray)JSONParser.parseLenient(localStorage.getItem(key));	
				break;
			} 
		}
		// load the modules into the global list and set a success code.
		if(requestedArray != null && requestedArray.isNull() != JSONNull.getInstance()){
			success = processArray(requestedArray); 
		}
		if (success) {
			player.playModulesLoaded();
		} else { // the processing did not succeed, play an error message.
			player.playErrorOccured();
		}
		return success;
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
	private JSONObject toJson(final Module module) {
		JSONObject jmodule = new JSONObject();
		jmodule.put("code", new JSONNumber(Double.parseDouble(module.getCode())));
		jmodule.put("damage", new JSONString(module.getDamage()));
		jmodule.put("xCoor", new JSONNumber(Double.parseDouble(module.getXCoor())));
		jmodule.put("yCoor", new JSONNumber(Double.parseDouble(module.getYCoor())));
		jmodule.put("turns", new JSONNumber(Double.parseDouble(module.getTurns())));

		return jmodule;
	}




	/**
	 *  Check to see if a JSONValue is a valid JSONArray
	 * @param subject the JSONValue to inspect
	 * @return the subject is a JSONArray
	 */
	private boolean inspectArray(final JSONValue subject) {
		return subject.isArray() == null || subject.toString().length() == 0;
	}

	/**
	 * @param requestedArray the array to process into the moduleList
	 */
	private boolean processArray(final JSONArray requestedArray) {
		boolean success = false;
		if (requestedArray.size() > 0){
			success = true;

			JSONObject jModule;
			for(int index = 0; index < requestedArray.size(); index++) {
				jModule = (JSONObject)requestedArray.get(index);
				ModuleList.addModule(jsonToModule(jModule));
			}
		}
		return success;
	}

	/**
	 * Converts a JSONObject representation of a Module to an actual Module object
	 * @param jsonModule the json representation to be used as a model for the Module object
	 * @return a Module object based on the JSONObject passed in.
	 */
	private Module jsonToModule( final JSONObject jsonModule) {
		String code = jsonModule.get("code").toString();
		String damage = jsonModule.get("damage").toString();
		damage = damage.substring(1, damage.length() - 1);
		String xCoor = jsonModule.get("xCoor").toString();
		String yCoor = jsonModule.get("yCoor").toString();
		String turns = jsonModule.get("turns").toString();

		return new Module(code, damage, xCoor, yCoor, turns);
	}

}
