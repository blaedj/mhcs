package mhcs.blaed;

import java.util.ArrayList;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.Window;

import mhcs.dan.Module;
import mhcs.dan.Module.ModuleType;
import mhcs.dan.ModuleList;
import mhcs.danielle.Minimum;


/**
 *
 * @author blaed
 * Saves habitat configurations into local storage.
 */
public class ConfigSaver {

	private ArrayList<Minimum> configuration;

	public ArrayList<Minimum> getConfig() {
		return null;
	}


	public void saveConfig(String configKey, ArrayList<Minimum> configuration) {
		final Storage localStorage = Storage.getLocalStorageIfSupported();
		final SoundPlayer player = new SoundPlayer();
		if ( localStorage == null){
			Window.alert("Local Storage not supported. Some functionality will be limited.");
			player.playErrorOccured();
		} else {
			localStorage.setItem(configKey, setJsonArray(configuration).toString());
		}
	}


	public static JSONObject toJson(final Minimum module) {
		JSONObject jmodule = new JSONObject();

		jmodule.put("code", new JSONString(module.getCode().toString()));
		jmodule.put("xCoor", new JSONNumber(module.getPoint().getX()));
		jmodule.put("yCoor", new JSONNumber(module.getPoint().getY()));
		return jmodule;
	}

	/**
	 * creates a JSONArray from the contents of the parameter
	 * @return JSONArray representation of the parameter.
	 */
	private JSONArray setJsonArray(ArrayList<Minimum> list) {
		JSONArray jsonArray = new JSONArray();
		int index = 0;
		for( Minimum module : list) {
			jsonArray.set(index, toJson(module));
			index++;
		}
		return jsonArray;
	}

	/**
	 * Converts a JSONObject representation of a Minimum into an actual minumum
	 * @param jsonModule the json representation to be used as a model for the Minimum object
	 * @return a Module object based on the JSONObject passed in.
	 */
	public static Minimum jsonToModule( final JSONObject jsonModule) {
		String code = jsonModule.get("code").toString();
		System.out.println(code);
		code = code.substring(1, code.length() -1);
		code = code.toUpperCase();
		code = code.replaceAll("\\s", "_");
		Double xCoor = Double.parseDouble(jsonModule.get("xCoor").toString());
		Double yCoor = Double.parseDouble(jsonModule.get("yCoor").toString());
		Point point = new Point(xCoor, yCoor);

		return new Minimum( ModuleType.valueOf(code), point);
	}


	public ArrayList<Minimum> loadConfig(String configKey) {
		Storage localStorage = Storage.getLocalStorageIfSupported();
		JSONArray requestedArray = (JSONArray) JSONParser.parseStrict(localStorage.getItem(configKey));
		return processArray(requestedArray);
	}

	
	 /**
     * @param requestedArray the array to process into the moduleList
     */
    private ArrayList<Minimum> processArray(final JSONArray requestedArray) {
	//boolean success = false;
    	ArrayList<Minimum> processedArray = new ArrayList<Minimum>();
    	
	if (requestedArray.size() > 0){
	    JSONObject jModule;
	    for(int index = 0; index < requestedArray.size(); index++) {
	    	jModule = (JSONObject)requestedArray.get(index);
	    	processedArray.add(jsonToModule(jModule));
	    }
	}
	return 	processedArray;
    }
	
	/**
     *  Check to see if a JSONValue is a valid JSONArray
     * @param subject the JSONValue to inspect
     * @return the subject is a JSONArray
     */
    private boolean inspectArray(final JSONValue subject) {
	return subject.isArray() == null || subject.toString().length() == 0;
    }

}
