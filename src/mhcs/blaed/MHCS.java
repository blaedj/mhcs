package mhcs.blaed;

import java.util.ArrayList;

import mhcs.dalton.WeatherFeed;
import mhcs.dan.Logging;
import mhcs.danielle.FullConfigPage;
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
 * @author Blaed Johnston
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MHCS implements EntryPoint {


	transient private TabPanel baseTabPanel;
	transient private TabBar baseTabBar;	//
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
		final TextBox userNameInput = new TextBox();
		final PasswordTextBox passwordInput = new PasswordTextBox();
		final Button confirm = new Button("Confirm");
		final FlexTable mainTable = new FlexTable();
		final SoundPlayer sound = new SoundPlayer();
		userNameInput.setText("Username");
		passwordInput.setText("Password");

		mainTable.setWidget(1,0,userNameInput);
		mainTable.setWidget(1,5,passwordInput);
		mainTable.setWidget(1,6,confirm);

		confirm.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				final String password = passwordInput.getText();
				final String userName = userNameInput.getText();
				ArrayList<String> validUsernames = new ArrayList<String>();
				validUsernames.add("Astro");
				validUsernames.add("Blaed");
				validUsernames.add("Dalton");
				validUsernames.add("Dan");
				validUsernames.add("Danielle");
				// check if username and password are correct
				if(validUsernames.contains(userName) && password.equals("Mars")){
					logIn(mainTable);
					sound.playWelcome();
				} else {
					sound.playAccessDenied();
					Window.alert("Wrong Username/Password. Try Again");
				}
			}
		});
		mainTable.addStyleName("loginWr");
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


		// the wrappers to add into the tabs
		FlowPanel loggingPageWrap = new FlowPanel();
		FlowPanel modLocationsWrap = new FlowPanel();
		FlowPanel minConfigWrap = new FlowPanel();
		FlowPanel fullConfigWrap = new FlowPanel();

		// the functionality widgets
		Logging logPage = new Logging();
		ModuleLocations locations = new ModuleLocations();
		MinimumConfigPage minConfig = new MinimumConfigPage();
		WeatherFeed weather = new WeatherFeed();
		FullConfigPage fullConfig = new FullConfigPage();
		
		minConfigWrap.add(minConfig.createMinConfig());
		loggingPageWrap.add(logPage.getLoggingPage());
		modLocationsWrap.add(locations.createMainPanel());
		fullConfigWrap.add(fullConfig.createFullConfig());
		
		// add the tabs 
		baseTabPanel.add(loggingPageWrap, "Module Logging Page");
		baseTabPanel.add(modLocationsWrap, "View Module locations");
		baseTabPanel.add(minConfigWrap, "Minimum Configuration");
		baseTabPanel.add(fullConfigWrap, "Full Configurations");
		baseTabPanel.add( weather.getweatherfeed(), "View the Weather forecast");
		
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
