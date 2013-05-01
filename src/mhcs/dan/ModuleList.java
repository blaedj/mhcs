package mhcs.dan;

import java.util.ArrayList;

/**
 *
 * @author Daniel Hammond
 *
 */
@SuppressWarnings("serial")
public class ModuleList extends ArrayList<Module> {
    
    /**
     * Global list of all modules.
     */
    public static ModuleList moduleList = new ModuleList();

    public ModuleList() {
        
    }

    /**
     * Accesses the length of the list.
     * @return the length of the module list.
     */
    public static int length() {
        return moduleList.size();
    }

    /**
     * Finds the index in the module list of the module with the
     * given module code.
     * @param code code of the module
     * @return the index of the element or -1 if not in the list
     */
    public static final int getIndexByCode(final String code) {
        int index = -1;
        for (Module mod : moduleList) {
            if (mod.getCode().equals(code)) {
                index = moduleList.indexOf(mod);
            }
        }
        return index;
    }

    /**
     * Returns the module in the module list with the given code.
     * @param code the code of the requested module
     * @return the module with given code or null if not present
     */
    public static final Module getModuleByCode(final String code) {
        Module retMod = null;
        for (Module mod : moduleList) {
            if (mod.getCode().equals(code)) {
                retMod = mod;
            }
        }
        return retMod;
    }

}
