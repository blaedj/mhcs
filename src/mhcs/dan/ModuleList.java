package mhcs.dan;

import java.util.ArrayList;

import com.google.gwt.storage.client.Storage;

/**
 *
 * @author Daniel Hammond
 *
 */
public class ModuleList extends ArrayList<Module> {

     public static ArrayList<Module> moduleList;

    /**
     *
     */
    public ModuleList() {
    moduleList = new ArrayList<Module>();
    }

    public static int length() {
    	return moduleList.size();
    }

    public static void addModule(Module module) {

    	moduleList.add(module);
    	Storage modListLocal = Storage.getSessionStorageIfSupported(); 
    	//Storage modListLocal = Storage.getLocalStorageIfSupported();
    	
    	modListLocal.setItem(module.getCode(), "{" +
    	"moduleType: " + module.getType().toString() + ",\n" +
    	"xCoord: " + module.getXCoor() + ",\n" +
    	"yCoord: " + module.getYCoor() + ",\n" +
    	"damage: " + module.getDamage() + ",\n" +
    	"turns: " + module.getDamage() + ",\n" +"}");
    }

    public static ModuleList getModuleList(){
    	return null;//ModuleList;
    }

    /**
     * @param code code of the module
     * @return the index of the element or -1 if not in the list
     */
    public final static int getIndexByCode(final String code) {
        int index = -1;
        for (Module mod : moduleList) {
            if (mod.getCode().equals(code)) {
                index = moduleList.indexOf(mod);
            }
        }
        return index;
    }

}
