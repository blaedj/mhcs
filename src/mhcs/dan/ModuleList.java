package mhcs.dan;

import java.util.ArrayList;

/**
 *
 * @author Daniel Hammond
 *
 */
public class ModuleList extends ArrayList<Module> {

    /**
     *
     */
    public static ModuleList moduleList = new ModuleList();
    
    /**
     *
     * @param code code of the module
     * @return the index of the element or -1 if not in the list
     */
    public final int getIndexByCode(final String code) {
        int index = -1;
        for (Module mod : this) {
            if (mod.getCode().equals(code)) {
                index = this.indexOf(mod);
            }
        }
        return index;
    }

}
