package mhcs.dan.test;

import java.util.ArrayList;

import mhcs.dan.Module;
import mhcs.dan.Module.ModuleType;
import mhcs.dan.ModuleList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class ModuleTest extends GWTTestCase {
    
    @Before
    public void gwtSetUp() {
        ModuleList.clearList();
        createTestModules();
    }
    
    @After
    public void gwtTearDown() {
        ModuleList.clearList();
    }
    
    private void createTestModules() {
        ModuleList.addModule(new Module("1", "undamaged", "2", "3", "0"));
        ModuleList.addModule(new Module("61", "damaged", "5", "7", "2"));
        ModuleList.addModule(new Module("91", "uncertain", "20", "30", "1"));
        ModuleList.addModule(new Module("111", "undamaged", "11", "12", "0"));
        ModuleList.addModule(new Module("131", "undamaged", "10", "10", "0"));
        ModuleList.addModule(new Module("141", "undamaged", "10", "10", "0"));
        ModuleList.addModule(new Module("151", "undamaged", "10", "10", "0"));
        ModuleList.addModule(new Module("161", "undamaged", "10", "10", "0"));
        ModuleList.addModule(new Module("171", "undamaged", "10", "10", "0"));
        ModuleList.addModule(new Module("181", "undamaged", "10", "10", "0"));
    }
    
    @Test
    public void testModuleType() {
        ArrayList<ModuleType> typeList = new ArrayList<ModuleType>();
        typeList.add(ModuleType.PLAIN);
        typeList.add(ModuleType.DORMITORY);
        typeList.add(ModuleType.SANITATION);
        typeList.add(ModuleType.FOOD_AND_WATER);
        typeList.add(ModuleType.GYM_AND_RELAXATION);
        typeList.add(ModuleType.CANTEEN);
        typeList.add(ModuleType.POWER);
        typeList.add(ModuleType.CONTROL);
        typeList.add(ModuleType.AIRLOCK);
        typeList.add(ModuleType.MEDICAL);
        int i = 0;
        for (Module mod : ModuleList.get()) {
            assertEquals(mod.getType(), typeList.get(i));
            i++;
        }
    }

    @Test
    public void testEquals() {
        Module mod = ModuleList.getModuleByIndex(0);
        assertTrue(mod.equals(new Module("1", "undamaged", "2", "3", "0")));
    }
    
    @Test
    public void testAccessors() {
        Module mod = ModuleList.getModuleByIndex(0);
        assertEquals(mod.getCode(), "1");
        assertEquals(mod.getXCoor(), "2");
        assertEquals(mod.getYCoor(), "3");
        assertEquals(mod.getDamage(), "undamaged");
        assertEquals(mod.getTurns(), "0");
    }
    
    @Test
    public void testToString() {
        Module mod = ModuleList.getModuleByIndex(0);
        assertEquals(mod.toString(), "1 undamaged 2 3 0");
    }
    
    @Override
    public String getModuleName() {
        return "mhcs.MHCS";
    }
}
