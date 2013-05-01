package mhcs.dan.test;

import mhcs.dan.Module;
import mhcs.dan.ModuleList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class ModuleListTest extends GWTTestCase {
    
    ModuleList modlist = null;
    
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
    public void testGetIndexByCode() {
        assertEquals(ModuleList.getIndexByCode("1"), 0);
        assertEquals(ModuleList.getIndexByCode("111"), 3);
        assertEquals(ModuleList.getIndexByCode("181"), 9);
    }
    
    @Test
    public void testGetModuleByCode() {
        ModuleList modList = ModuleList.get();
        assertEquals(ModuleList.getModuleByCode("1"), modList.get(0));
        assertEquals(ModuleList.getModuleByCode("111"), modList.get(3));
        assertEquals(ModuleList.getModuleByCode("181"), modList.get(9));
    }
    
    @Test
    public void testLength() {
        assertEquals(ModuleList.length(), 10);
    }
    
    @Test
    public void testNull() {
        assertTrue(modlist == null);
    }
    
    @Override
    public String getModuleName() {
        return "mhcs.MHCS";
    }
}
