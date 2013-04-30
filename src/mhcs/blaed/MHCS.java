package mhcs.blaed;

import java.util.ArrayList;

import mhcs.dan.Logging;
import mhcs.danielle.MinimumConfigPage;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
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
 * @author Blaed Johnston
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MHCS implements EntryPoint {


    private TabPanel baseTabPanel;
    private TabBar baseTabBar;
    //public static ModuleList moduleList;
    /*
     *
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    public final void onModuleLoad() {

	//RootPanel.get().add(getMainPanel());
	RootPanel.get().add(createLogin());
    }

    /**
     * Login interface that checks for Username and password
     * @return a widget that wraps the login page interface
     */
    private Widget createLogin() {
	final TextBox tb = new TextBox();
	final PasswordTextBox ptb = new PasswordTextBox();
	Button confirm = new Button("Confirm");
	final FlexTable mainTable = new FlexTable();
	final SoundPlayer sound = new SoundPlayer();
	mainTable.setText(0,0,"Username");
	mainTable.setText(0,5,"Password");
	mainTable.setWidget(1,0,tb);
	mainTable.setWidget(1,5,ptb);
	mainTable.setWidget(1,6,confirm);

	confirm.addClickHandler(new ClickHandler() {
		public void onClick(final ClickEvent event) {
		    final String password = ptb.getText();
		    final String userName = tb.getText();
		    ArrayList<String> validUsernames = new ArrayList<String>();
		    validUsernames.add("Astro");
		    validUsernames.add("Blaed");
		    validUsernames.add("Dalton");
		    validUsernames.add("Dan");
		    validUsernames.add("Danielle");
		    if(validUsernames.contains(userName) && password.equals("mars")){
			    logIn(mainTable);
			    sound.playWelcome();
		    } else {
			sound.playAccessDenied();
			Window.alert("Wrong Username/Password. Try Again");
		    }
		}
	    });
	return mainTable;
    }

    /**
     *
     * Remove the 'login' widget from the root panel and adds the mainpanel
     * @param the widget to remove from the login screen,
     * should be the login page widget,
     * @pre the login was successful
     * @post the main tabpanel is visible
     */
    private void logIn(final Widget toRemove){
	TabPanel panel = getMainPanel();
	panel.setVisible(false);
	RootPanel.get().remove(toRemove);
	panel.setVisible(true);
	RootPanel.get().add(panel);

    }

    /**
     * Creates the main panel interface, grabs all the pages
     * needed and puts them into tabs.
     * @return baseTabPanel the main tabbed panel
     */
    private TabPanel getMainPanel() {

	baseTabPanel   =  new TabPanel();
	baseTabBar     = baseTabPanel.getTabBar();


	FlowPanel loggingPageWrapper = new FlowPanel();
	FlowPanel moduleLocationsWrapper = new FlowPanel();

	Logging logPage = new Logging();

	ModuleLocations locations = new ModuleLocations();

	FlowPanel minConfigWrapper = new FlowPanel();
	MinimumConfigPage minConfig = new MinimumConfigPage();

	minConfigWrapper.add(minConfig.createMinConfig());
	baseTabPanel.add(minConfigWrapper, "Minimum Configuration");

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

    /**
     * switches the state of the tab, if enable then disable and vice versa
     * @param tabIndex int the index of the tab to toggle
     * @post the disabled/enabled state of the tab is opposite its pre-method state
     */
    public final void toggleTab(final int tabIndex) {
	if (baseTabBar.isTabEnabled(tabIndex)) {
	    baseTabBar.setTabEnabled(tabIndex, false);
	} else {
	    baseTabBar.setTabEnabled(tabIndex, true);
	}
    }
} // end MHCS
