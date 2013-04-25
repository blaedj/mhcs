package mhcs.dan;

import java.util.ArrayList;

import com.google.gwt.storage.client.Storage;

/**
 *
 * @author Daniel Hammond
 *
 */
public class ModuleList extends ArrayList<Module> {

	public static ModuleList moduleList = new ModuleList();


	public static int length() {
		return moduleList.size();
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
