package mhcs.blaed;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;

import mhcs.dan.Logging;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MHCS implements EntryPoint {

	private TabPanel baseTabPanel;
	private TabBar baseTabBar;

	@Override
	public void onModuleLoad() {
		RootLayoutPanel.get().add(getMainPanel());
	}

	/*
	 * creates the bare skeleton for a main panel
	 * @return baseTabPanel the main tabbed panel
	 */
	
	private TabPanel getMainPanel() {
		baseTabPanel = new TabPanel();
		baseTabBar     = baseTabPanel.getTabBar();

		FlowPanel flowPanel1 = new FlowPanel();
		FlowPanel flowPanel2 = new FlowPanel();
		Logging logPage = new Logging();

		TextBox textbox= new TextBox();
		textbox.setText("test Text");

		flowPanel1.add(textbox);
		flowPanel1.add(new HTML("<div><img src='http://lorempixel.com/output/nature-q-g-640-480-5.jpg' /></div>"));

		//flowPanel2.add(new HTML("<div><img src='http://lorempixel.com/output/nature-q-g-640-480-5.jpg' /></div>"));

		flowPanel2.add(logPage.getLoggingPage());

		baseTabPanel.add(flowPanel1, "One");
		baseTabPanel.add(flowPanel2, "Two");
		baseTabPanel.selectTab(0);

		baseTabPanel.setVisible(true);
		baseTabPanel.addStyleName("baseTabber");
		return baseTabPanel;
	}

	protected void enableAllTheTabs() {
		for ( int i=0; i < baseTabPanel.getTabBar().getTabCount(); i++ ) {
			baseTabBar.setTabEnabled(i, true);
		}
	}

	/* switches the state of the tab, if enable then disable and vice versa
	 * @param tabIndex int the index of the tab to toggle
	 * @post the disabled/enabled state of the tab is switched
	 */
	public void toggleTab(int tabIndex){
		if(baseTabBar.isTabEnabled(tabIndex)){
			baseTabBar.setTabEnabled(tabIndex, false);
		}
		else {
			baseTabBar.setTabEnabled(tabIndex, true);
		}
	}

	public void setTabContents(FlowPanel contents, String title, int tabIndex) {
		baseTabBar.insertTab(contents, tabIndex - 1);
		baseTabBar.removeTab(tabIndex);
	}

} // end MHCS
