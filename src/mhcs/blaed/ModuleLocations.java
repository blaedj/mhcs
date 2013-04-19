package mhcs.blaed;

import mhcs.danielle.MinimumConfigPage;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;


/*
 * This is the map that shows the landing locations of all the modules
 * and allows the user to see details about each module.
 * @author Blaed J
 * @class sets up a UI that can be returned via a static method
 */
public class ModuleLocations {

    private HorizontalPanel containerPanel;
    private Grid grid;
    private Grid buttonGrid;
    private Grid landingAreaGrid;
    private DecoratorPanel decImage;
    private ScrollPanel scrollList;
    private DecoratorPanel modList;
    private DecoratorPanel modDetails;
    private DecoratorPanel decButton;
    private TextArea moduleDetailsBox;
    private ListBox modListBox;

    private FlowPanel mainPanel;
    // getModuleLocations();
    // localStorage.getAll();
    // foreach(module in moduleList{ plotOnMap(module)};

    public Panel createMainPanel(){
	intitializeMembers();

	moduleDetailsBox.setCharacterWidth(37);
	moduleDetailsBox.setVisibleLines(7);
	HabitatModule moduleList[] = getModuleList();


	/// for testing
	modListBox.addItem("module 001 ");
	modListBox.addItem("module 112 ");
	modListBox.addItem("module 103 ");
	modListBox.addItem("module 075 ");
	modListBox.addItem("module 116 ");
	modListBox.addItem("module 117 ");
	/// for testing

	// Make enough room for 19 items, then use scrollbar
	// Can select item and 'display' will show details
	modListBox.setVisibleItemCount(19);
	modListBox.setPixelSize(250, 451);
	scrollList.add(modListBox);

	modList.add(scrollList);
	modDetails.add(moduleDetailsBox);

	//buttonGrid.setWidth("255px");

	decImage.add(new Image("images/MarsModuleLandingArea.jpg"));
	//decButton.setWidth("255px");
	//decButton.add(buttonGrid);

	grid.setWidget(0, 0, modList);
	grid.setWidget(1, 0, modDetails);
	//grid.setWidget(2, 0, decButton);

	// actually plot the modules according to their location
	// pass in the list of modules and the image to plot them on.
	plotModuleLocations(moduleList, landingAreaGrid);

	containerPanel.add(decImage);
	containerPanel.add(grid);

	mainPanel = new FlowPanel();
	mainPanel.add(containerPanel);
	return mainPanel;
    }

    private void intitializeMembers() {
	containerPanel = new HorizontalPanel();
	grid = new Grid(3,1);
	landingAreaGrid = new Grid(50, 100);
	decImage = new DecoratorPanel();
	scrollList = new ScrollPanel();
	modList = new DecoratorPanel();
	modDetails = new DecoratorPanel();
	moduleDetailsBox = new TextArea();
	modListBox = new ListBox();
    }

    private void plotModuleLocations(HabitatModule[] moduleList, Grid mapGrid){
	// make buttons for each object
	// set the button's image according to the type of the module? or just a dot w/a number
	// add a clickEventHandler to each button that calls a popup w/ details by module number.
	// add each button to the grid based on coordinates.

	for (HabitatModule module : moduleList) {
	    // make a new button
	    mapGrid.setWidget(module.getX(), module.getY(), createButton(module));
	}

    }

    private Button createButton(final HabitatModule module) {
	// create a button based on the module attributes
	Button moduleButton = new Button();

	String moduleCode = ((Integer) module.getCode()).toString();
	moduleButton.setText(moduleCode);
	moduleButton.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    // TODO Auto-generated method stub
		    PopupPanel alertPanel = new PopupPanel();
		    //FlowPanel infoPanel = new FlowPanel();
		    Label idLabel = new Label("Module ID:" + ((Integer)module.getCode()).toString());
		    Label typeLabel = new Label("Module Type:" + module.getType());
		    Label coordLabelX = new Label("X coordinate:" + ((Integer)module.getX()).toString());
		    Label coordLabelY = new Label("Y coordinate:" + ((Integer)module.getY()).toString());
		    alertPanel.add(idLabel);
		    alertPanel.add(typeLabel);
		    alertPanel.add(coordLabelX);
		    alertPanel.add(coordLabelY);
		    alertPanel.center();
		    //alertPanel.add(infoPanel);
		}
	    });

	return moduleButton;
    }

    private HabitatModule[] getModuleList(){
    	JsArray<HabitatModule> listOfMods;
    	HabitatModule list[]; 
    	list.
    	
    	return null;
    }


}
