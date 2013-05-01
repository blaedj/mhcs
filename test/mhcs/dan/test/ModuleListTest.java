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
        ModuleList.moduleList.clear();
        createTestModules();
    }
    
    @After
    public void gwtTearDown() {
        ModuleList.moduleList.clear();
    }
    
    private void createTestModules() {
        ModuleList.moduleList.add(new Module("1", "undamaged", "2", "3", "0"));
        ModuleList.moduleList.add(new Module("61", "damaged", "5", "7", "2"));
        ModuleList.moduleList.add(new Module("91", "uncertain", "20", "30", "1"));
        ModuleList.moduleList.add(new Module("111", "undamaged", "11", "12", "0"));
        ModuleList.moduleList.add(new Module("131", "undamaged", "10", "10", "0"));
        ModuleList.moduleList.add(new Module("141", "undamaged", "10", "10", "0"));
        ModuleList.moduleList.add(new Module("151", "undamaged", "10", "10", "0"));
        ModuleList.moduleList.add(new Module("161", "undamaged", "10", "10", "0"));
        ModuleList.moduleList.add(new Module("171", "undamaged", "10", "10", "0"));
        ModuleList.moduleList.add(new Module("181", "undamaged", "10", "10", "0"));       
    }
    
    @Test
    public void testGetIndexByCode() {
        assertEquals(ModuleList.getIndexByCode("1"), 0);
        assertEquals(ModuleList.getIndexByCode("111"), 3);
        assertEquals(ModuleList.getIndexByCode("181"), 9);
    }
    
    @Test
    public void testGetModuleByCode() {
        assertEquals(ModuleList.getModuleByCode("1"), ModuleList.moduleList.get(0));
        assertEquals(ModuleList.getModuleByCode("111"), ModuleList.moduleList.get(3));
        assertEquals(ModuleList.getModuleByCode("181"), ModuleList.moduleList.get(9));
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
