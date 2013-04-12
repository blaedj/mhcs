package mhcs.blaed;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MHCS implements EntryPoint {

    private TabPanel baseTabPanel;
    private TabBar baseTabBar;

    @Override

    public void onModuleLoad() {

	baseTabPanel = new TabPanel();
	baseTabBar     = baseTabPanel.getTabBar();

	baseTabPanel.add(new HTML("<div><h1>This is the first tab, Module Logging</h1><p>lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum</p><img src='http://lorempixel.com/output/city-q-g-640-480-5.jpg' /></div>"), "Module Logging");
	baseTabPanel.add(new HTML("<div><img src='http://lorempixel.com/output/nature-q-g-640-480-5.jpg' /></div>"), "Not Logging Page");

	baseTabPanel.selectTab(0);

	for ( int i=1;i<baseTabPanel.getTabBar().getTabCount(); i++ ) {
	    baseTabBar.setTabEnabled(i, false);
	}

	Button enabler = new Button();
	enabler.setText("enable all tabs");
	enabler.addClickHandler( new ClickHandler(){
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			enableAllTheTabs();
		}
		
	});
	
	RootLayoutPanel.get().add(baseTabPanel);
	RootLayoutPanel.get().add(enabler);
	enabler.setVisible(true);
	baseTabPanel.setVisible(true);
	baseTabPanel.addStyleName("baseTabber");
    }
    
    protected void enableAllTheTabs() {
    	for ( int i=1;i<baseTabPanel.getTabBar().getTabCount(); i++ ) {
    	    baseTabBar.setTabEnabled(i, true);
    	}	
	}
    /*
     * @param tabIndex int the index of the tab to insert contents into
     * @param content Widget the widget (content) to insert into the tab
     * @param tabTitle string the new title of the tab
     * @post the tab at the index specified is removed and a new tab is added with contents provided
     */
    //public void changeTabContents( int tabIndex, Widget contentWidget, String tabTitle ){

    //	baseTabPanel.remove(baseTabPanel.getWidget(tabIndex));
    //	baseTabPanel.add(contentWidget, tabTitle);
    //    }

    /* switches the state of the tab, if enable then disable and vice versa
     * @param tabIndex int the index of the tab to toggle
     * @post the disabled/enabled state of the tab is switched
     */
    /*public void toggleTab(int tabIndex){
    //Tab tab = baseTabBar.getTab(tabIndex);
    if(baseTabBar.isTabEnabled(tabIndex)){
    baseTabBar.setTabEnabled(tabIndex, false);
    }
    else {
    baseTabBar.setTabEnabled(tabIndex, true);
    }
    }*/

    // TODO add a method that keeps the order of the tabs correct?
} // end MHCS
