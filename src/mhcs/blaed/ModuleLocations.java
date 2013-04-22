package mhcs.blaed;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;


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

	public Panel createMainPanel(){
		intitializeMembers();

		HabitatModule moduleList[] = getModuleList();

		for (int i = 0; i < landingAreaGrid.getRowCount(); i++) {
			for (int j = 0; j < landingAreaGrid.getColumnCount(); j++) {
				landingAreaGrid.getCellFormatter().setStylePrimaryName(i, j, "tableCell");
			}
		}
		landingAreaGrid.setPixelSize(500, 250);

		// actually plot the modules according to their location
		// pass in the list of modules and the image to plot them on.
		plotModuleLocations(moduleList, landingAreaGrid);
		containerPanel.add(landingAreaGrid);
		containerPanel.addStyleName("landingMap");
		
		return containerPanel;
	}

	private void intitializeMembers() {
		containerPanel = new FlowPanel();
		landingAreaGrid = new Grid(50, 100);
	}

	private void plotModuleLocations(HabitatModule[] moduleList, Grid mapGrid){
		// make buttons for each object
		// set the button's image according to the type of the module? or just a dot w/a number
		// add a clickEventHandler to each button that calls a popup w/ details by module number.
		// add each button to the grid based on coordinates.
		HabitatModule module;
		for (int i = 0; i < moduleList.length; i++) {
			// make a new button
			module = moduleList[i];
			assert module != null;
			mapGrid.setWidget(module.getX(), module.getY(), createButton(module));
		}
	}

	private PushButton createButton(final HabitatModule module) {

		final PushButton moduleButton = new PushButton( ((Integer)module.getCode()).toString() );
		//final PushButton moduleButton = new PushButton("  ");
		moduleButton.setPixelSize(5, 5);

		moduleButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				PopupPanel alertPanel = new PopupPanel();
				FlowPanel infoPanel = new FlowPanel();
				Label idLabel = new Label("Module ID:" + ((Integer)module.getCode()).toString());
				Label typeLabel = new Label("Module Type:" + module.getType());
				Label coordLabelX = new Label("X coordinate:" + ((Integer)module.getX()).toString());
				Label coordLabelY = new Label("Y coordinate:" + ((Integer)module.getY()).toString());
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
				alertPanel.add(infoPanel);
				alertPanel.setModal(false);
				alertPanel.setAutoHideEnabled(true);
				alertPanel.center();
			}
		});

		return moduleButton;
	}

	private HabitatModule[] getModuleList() {
		/** 
		 * TODO method stub, finish this when module class is figured out. 
		 */
		HabitatModule list[] = new HabitatModule[10];
		list[0] = new HabitatModule(9, 3, 20);
		assert list[0] != null;
		// HabitatModule(x, y, code)
		list[1] = new HabitatModule(0, 0, 21);
		list[2] = new HabitatModule(0, 1, 22);
		list[3] = new HabitatModule(16, 13, 23);
		list[4] = new HabitatModule(19, 19, 24);
		list[5] = new HabitatModule(5, 44, 34);
		list[6] = new HabitatModule(5, 4, 4);
		list[7] = new HabitatModule(39, 14, 36);
		list[8] = new HabitatModule(40, 30, 9);
		list[9] = new HabitatModule(17, 29, 18);
		return list;
	}
}
