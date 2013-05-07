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
    private static ModuleList list = new ModuleList();

    /**
     * Global list of damaged modules.
     */
    private static ModuleList damagedList = new ModuleList();

    /**
     * Gets the list of undamaged modules.
     * @return a copy of the modules list
     */
    public static ModuleList get() {
        return list;
    }
    
    /**
     * Gets the list of damaged modules.
     * @return a copy of the damaged modules list
     */
    public static ModuleList getDamaged() {
        return damagedList;
    }

    /**
     * Clears the list of modules.
     */
    public static void clearList() {
        list.clear();
        damagedList.clear();
    }

    /**
     * 
     * @param mod
     * @return
     */
    public static boolean addModule(final Module mod) {
        if (isValid(mod)) {
            if (mod.getDamage().equals("undamaged")) {
                list.add(mod);
                return true;
            } else if (mod.getDamage().equals("damaged")) {
                damagedList.add(mod);
                return true;
            }
        }
        return false;
    }

    /**
     * Attempts to remove a given module from the list.
     * @param mod the module to be removed
     * @return true if the module is successfully removed
     */
    public static boolean removeModule(final Module mod) {
        if (mod.getDamage().equals("undamaged")) {
            return list.remove(mod);
        } else {
            return damagedList.remove(mod);
        }
    }
    
    /**
     * Checks if both lists are empty.
     * @return true if both lists are empty
     */
    public static boolean isEmptyList() {
        return list.isEmpty() && damagedList.isEmpty();
    }

    /**
     * Checks if the module input data is valid input.
     * @param mod the module to check
     * @return true if the module is valid
     */
    private static boolean isValid(final Module mod) {
        if (checkCode(Integer.parseInt(mod.getCode()))
                && !checkDuplicate(mod.getCode())
                && checkCoords(Integer.parseInt(mod.getXCoor()),
                        Integer.parseInt(mod.getYCoor()))) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param code the code to check if already in list
     * @return true if already in list or false if not in list
     */
    private static boolean checkDuplicate(final String code) {
        if (getModuleByCode(code) == null) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param x x coordinate
     * @param y y coordinate
     * @throws Exception invalid exception
     */
    private static boolean checkCoords(final int xCoor, final int yCoor) {
        if (xCoor < 0 || xCoor > 100) {
            return false;
        }
        if (yCoor < 0 || yCoor > 50) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param code the module code being entered
     * @throws Exception invalid code exception
     */
    private static boolean checkCode(final int code) {
        if (code < 1) {
            return false;
        } else if (code > 40 && code < 61) {
            return false;
        } else if (code > 80 && code < 91) {
            return false;
        } else if (code > 100 && code < 111) {
            return false;
        } else if (code > 120 && code < 131) {
            return false;
        } else if (code > 134 && code < 141) {
            return false;
        } else if (code > 144 && code < 151) {
            return false;
        } else if (code > 154 && code < 161) {
            return false;
        } else if (code > 164 && code < 171) {
            return false;
        } else if (code > 174 && code < 181) {
            return false;
        } else if (code > 184) {
            return false;
        }
        return true;
    }

    /**
     * Accesses the length of the list.
     * @return the length of the module list.
     */
    public static int length() {
        return list.size();
    }

    /**
     * Finds the index in the module list of the module with the
     * given module code.
     * @param code code of the module
     * @return the index of the element or -1 if not in the list
     */
    public static final int getIndexByCode(final String code) {
        for (Module mod : list) {
            if (mod.getCode().equals(code)) {
                return list.indexOf(mod);
            }
        }
        return -1;
    }

    /**
     * Returns the module in the module list with the given code.
     * @param code the code of the requested module
     * @return the module with given code or null if not present
     */
    public static final Module getModuleByCode(final String code) {
        for (Module mod : list) {
            if (mod.getCode().equals(code)) {
                return mod;
            }
        }
        for (Module mod : damagedList) {
            if (mod.getCode().equals(code)) {
                return mod;
            }
        }
        return null;
    }

    /**
     * Accessor for modules by index.
     * @param index the index to retrieve the module from
     * @return the module at the given index
     */
    public static final Module getModuleByIndex(final int index) {
        return list.get(index);
    }

}
