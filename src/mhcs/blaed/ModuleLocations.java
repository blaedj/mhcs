package mhcs.blaed;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import mhcs.dan.Module;
import mhcs.dan.ModuleList;


/*
 * This is the map that shows the landing locations of all the modules
 * and allows the user to see details about each module.
 * @author Blaed J
 * @class sets up a UI that can be returned via a static method
 */
public class ModuleLocations {

    private FlowPanel containerPanel;
    private Grid landingAreaGrid;
    //private DecoratorPanel decImage;


    /**
     *
     * @return the main interface panel for the application
     */
    public Panel createMainPanel(){
	intitializeMembers();

	final ArrayList<Module> moduleList = getModuleList();

	for (int i = 0; i < landingAreaGrid.getRowCount(); i++) {
	    for (int j = 0; j < landingAreaGrid.getColumnCount(); j++) {
		landingAreaGrid.getCellFormatter().setStylePrimaryName(i, j, "tableCell");
	    }
	}
	landingAreaGrid.setPixelSize(500, 250);

	// actually plot the modules according to their location
	// pass in the list of modules and the image to plot them on.
	plotModuleLocations(moduleList, landingAreaGrid);

	Button clearButton = new Button("Clear the map");
	Button refreshButton = new Button("Refresh the map");

	clearButton.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    landingAreaGrid.clear(true);
		}
	    });

	refreshButton.addClickHandler(new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
		    plotModuleLocations(ModuleList.moduleList, landingAreaGrid);
		}
	    });

	containerPanel.add(landingAreaGrid);
	containerPanel.addStyleName("landingMap");

	containerPanel.addHandler(new DataRecordedEventHandler() {
		@Override
		public void onDataRecorded(DataRecordedEvent event) {
		    plotModuleLocations(moduleList, landingAreaGrid);
		}

	    }, DataRecordedEvent.TYPE);

	containerPanel.add(refreshButton);
	containerPanel.add(clearButton);
	return containerPanel;
    }

    /**
     * initalize necessary members
     */
    private void intitializeMembers() {
	containerPanel = new FlowPanel();
	landingAreaGrid = new Grid(50, 100);
    }

    /**
     *
     * @param moduleList the module to plot on the map
     * @param mapGrid the map to plot the modules from moduleList on
     */
    private void plotModuleLocations( ArrayList<Module> moduleList, Grid mapGrid){

	mapGrid.clear(true);
   	Module module;
	for (int i = 0; i < moduleList.size(); i++) {
	    // make a new button
	    module = moduleList.get(i);
	    assert module != null;

	    mapGrid.setWidget(50 - Integer.parseInt(module.getYCoor()), Integer.parseInt(module.getXCoor()) - 1, createButton(module));


	}
    }

    /**
     *
     * @param module the module to base the button on, uses module code and coordinates
     * @return the button representing the module specified
     */
    private PushButton createButton(final Module module) {

    	final PushButton moduleButton = new PushButton( module.getCode() );

    	moduleButton.setPixelSize(5, 5);

    	moduleButton.addClickHandler(new ClickHandler() {
    		@Override
    		public void onClick(ClickEvent event) {
		    PopupPanel alertPanel = new PopupPanel();
		    FlowPanel infoPanel = new FlowPanel();
		    Label idLabel = new Label("Module ID:" + module.getCode());
		    Label typeLabel = new Label("Module Type:" + module.getType());
		    Label coordLabelX = new Label("X coordinate:" + module.getXCoor());
		    Label coordLabelY = new Label("Y coordinate:" + module.getYCoor());
		    moduleButton.addStyleName("moduleSelected");
		    infoPanel.add(idLabel);
		    infoPanel.add(typeLabel);
		    infoPanel.add(coordLabelX);
		    infoPanel.add(coordLabelY);
		    infoPanel.addStyleName("largerFont");
		    alertPanel.addCloseHandler(new CloseHandler<PopupPanel>() {
			    @Override
    				public void onClose(CloseEvent<PopupPanel> event) {
				moduleButton.setStyleName("moduleSelected", false);
			    }
    			});

		    switch(module.getType()){
		    case PLAIN:
			infoPanel.add(new Image("images/plain.png"));
			break;
		    case  DORMITORY:
			infoPanel.add(new Image("images/dorm.jpg"));
			break;
		    case SANITATION:
			infoPanel.add(new Image("images/sanitation.jpg"));
			break;
		    case FOOD_AND_WATER:
			infoPanel.add(new Image("images/storage.jpg"));
			break;
		    case GYM_AND_RELAXATION:
			infoPanel.add(new Image("images/gym.png"));
			break;
		    case CANTEEN:
			infoPanel.add(new Image("images/canteen.png"));
			break;
		    case POWER:
			infoPanel.add(new Image("images/power.jpg"));
			break;
		    case CONTROL:
			infoPanel.add(new Image("images/control.jpg"));
			break;
		    case AIRLOCK:
			infoPanel.add(new Image("images/airlock.jpg"));
			break;
		    case MEDICAL:
			infoPanel.add(new Image("images/medical.png"));
			break;
		    default:

		    }



		    alertPanel.add(infoPanel);
		    alertPanel.setModal(false);
		    alertPanel.setAutoHideEnabled(true);
		    alertPanel.center();
    		}
	    });

    	return moduleButton;
    }

    /**
     *
     * @return the list of modules that have landed.
     */
    private ArrayList<Module> getModuleList() {

    	/// for testing only
    	//ModuleList.addModule(new Module("21", "fine", "2", "3", "0"));

    	//ModuleList.addModule(new Module("22", "fine", "1", "1", "0"));

    	//ModuleList.addModule(new Module("23", "fine", "10", "13", "0"));

    	//ModuleList.addModule(new Module("24", "fine", "8", "2", "0"));
    	ModuleList.addModule(new Module("25", "fine", "42", "17", "0"));
    	/// for testing only

    	return ModuleList.moduleList;
    }
}
