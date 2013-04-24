 package mhcs.blaed;

import mhcs.dan.Logging;
import mhcs.dan.Module;
import mhcs.dan.ModuleList;
import mhcs.danielle.MinimumConfigPage;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MHCS implements EntryPoint {


    private TabPanel baseTabPanel;
    private TabBar baseTabBar;
    public static ModuleList moduleList;
    /*
     *
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    public final void onModuleLoad() {
	
	//RootPanel.get().add(getMainPanel());
    	RootPanel.get().add(createLogin());
    
    }

    /**
     * 
     * @return
     */
    private Widget createLogin() {
		final TextBox tb = new TextBox();
	    final PasswordTextBox ptb = new PasswordTextBox();
	    Button confirm = new Button("Confirm");
	    final FlexTable mainTable = new FlexTable();
	    mainTable.setText(0,0,"Username");
	    mainTable.setText(0,5,"Password");
	    mainTable.setWidget(1,0,tb);
	    mainTable.setWidget(1,5,ptb); 
	    mainTable.setWidget(1,6,confirm);
	    confirm.addClickHandler(new ClickHandler() {
	    	//checks to see if password is mars and if username is Dalton, Blaed, Dan or Danielle
        public void onClick(ClickEvent event) {
        	final String password = ptb.getText();
        	final String userName = tb.getText();
        	 if(!password.equals("mars"))         	 
        	 Window.alert("Wrong Password. Try Again");
        	 else if (!userName.equals("Astro")){
        		 Window.alert("Wrong Username. Try again");        		 
        	 }
        	 else {
        		 // everything is correct!
        		 logIn(mainTable);
        	 }
        }
	    });
	    return mainTable;
    }

    private void logIn(Widget toRemove){
    	TabPanel panel = getMainPanel();
    	panel.setVisible(false);
    	RootPanel.get().remove(toRemove);
    	panel.setVisible(true);
    	RootPanel.get().add(panel);

    }
    
	/*
     * creates the bare skeleton for a main panel
     * @return baseTabPanel the main tabbed panel
     */
    private TabPanel getMainPanel() {

	baseTabPanel   =  new TabPanel();
	baseTabBar     = baseTabPanel.getTabBar();

	
	FlowPanel loggingPageWrapper = new FlowPanel();
	FlowPanel moduleLocationsWrapper = new FlowPanel();

	Logging logPage = new Logging();

	ModuleLocations locations = new ModuleLocations();

//	FlowPanel minConfigWrapper = new FlowPanel();
//	MinimumConfigPage minConfig = new MinimumConfigPage();
//	minConfigWrapper.add(minConfig.createMinConfig());
//	baseTabPanel.add(minConfigWrapper, "Minimum Configuration");	
	
	loggingPageWrapper.add(logPage.getLoggingPage());
	moduleLocationsWrapper.add(locations.createMainPanel());


	baseTabPanel.add(loggingPageWrapper, "Module Logging Page");
	baseTabPanel.add(moduleLocationsWrapper, "View Module locations");



	baseTabPanel.selectTab(0);

	baseTabPanel.setVisible(true);
	baseTabPanel.setWidth("90%");
	baseTabPanel.setHeight("90%");
	baseTabPanel.addStyleName("baseTabber");
	return baseTabPanel;
    }

    /* switches the state of the tab, if enable then disable and vice versa
     * @param tabIndex int the index of the tab to toggle
     * @post the disabled/enabled state of the tab is switched
     */
    public final void toggleTab(final int tabIndex) {
	if (baseTabBar.isTabEnabled(tabIndex)) {
	    baseTabBar.setTabEnabled(tabIndex, false);
	} else {
	    baseTabBar.setTabEnabled(tabIndex, true);
	}
    }

    public final void setTabContents(final FlowPanel contents, final String title, final int tabIndex) {
	baseTabBar.insertTab(contents, tabIndex - 1);
	baseTabBar.removeTab(tabIndex);
    }

} // end MHCS
