package mhcs.danielle;

import java.util.ArrayList;

import mhcs.dan.Module;
import mhcs.dan.Module.ModuleType;
import mhcs.dan.ModuleList;
//import mhcs.dan.ModuleList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;


/**
 * Class creates the min configuration tab.
 * @author daniellestewart
 *
 */
public class MinimumConfigPage implements EntryPoint {
	/**
	 * Creates the page and returns the widget to be put into 
	 * a tab.
	 * @return this page as a widget.
	 */
	public Widget createMinConfig() {
		initialize();
		setUpMinConfig();

		FlowPanel wrapper = new FlowPanel();
		wrapper.add(mainPanel);
		return wrapper;
	}
	//**********************************************
    /**
     * Initializes everything .
     * (during testing, also holds module list creation).
     */
    private void initialize() {
        mainPanel = new HorizontalPanel();
        imagePanel = new ScrollPanel();
        mainListPanelDecorator = new DecoratorPanel();
        mainListPanel = new FlowPanel();
        scrollList = new ScrollPanel();
        detailList = new FlowPanel();
        details = new TextArea();
        details.setText("Select a module to see details");
        details.setCharacterWidth(37);
        details.setVisibleLines(7);
        scroll = new ListBox();
        imageGrid = new Grid(50,100);
        buttonGrid = new Grid(1,2);
        mainButtonGrid = new Grid(3,1);
        recalculate = new Button("Recalculate");
        enterSave = new Button("Enter & Save");
        // All for testing
        tmpModList = new ModuleList();
        tmpModList.add(new Module("1","DAMAGED", "1", "1", "0"));
        tmpModList.add(new Module("2", "UNDAMAGED", "1","2", "1"));
        tmpModList.add(new Module("3", "UNDAMAGED", "1", "3", "2"));
        tmpModList.add(new Module("61", "UNDAMAGED", "1", "4", "0"));
        tmpModList.add(new Module("91", "UNDAMAGED", "1", "5", "1"));
        tmpModList.add(new Module("111", "UNDAMAGED", "1", "6", "2"));
        tmpModList.add(new Module("141", "UNDAMAGED", "1", "7", "0"));
        tmpModList.add(new Module("151", "UNDAMAGED", "1", "8", "1"));
        tmpModList.add(new Module("161", "UNDAMAGED", "1", "9", "2"));
        tmpModList.add(new Module("171", "UNDAMAGED", "1", "10", "0"));

        minConfig1 = new MinimumConfiguration();
        minArray = minConfig1.getMinArray();

        // Creating scroll list of modules
        createScrollList();
        createButtonPanel();
        setUpImageGrid();
        setUpPanelDetails();

    }
//***************************************
	/**
	 * setUpMinConfig will create the min configuration 1
	 * to create minConfig 2, user must push "recalculate".
	 */
	private void setUpMinConfig() {
	    // Set up the array of minimum objects
		minArray = minConfig1.getMinArray();
		// Collect all their types and set up images
		for (int i = 0; i < minArray.size(); i++) {
			Minimum minItem = minArray.get(i);
			Point tmpPoint = minItem.getPoint();

			if (minItem.getCode().equals(ModuleType.AIRLOCK)) {
				image = new Image("images/airlock.jpg");
			} else if (minItem.getCode().equals(ModuleType.MEDICAL)) {
				image = new Image("images/medical.png");
			} else if (minItem.getCode().equals(ModuleType.DORMITORY)) {
				image = new Image("images/dorm.jpg");
			} else if (minItem.getCode().equals(ModuleType.PLAIN)) {
				image = new Image("images/plain.png");
			} else if (minItem.getCode().equals(ModuleType.POWER)) {
				image = new Image("images/power.jpg");
			} else if (minItem.getCode().equals(ModuleType.CANTEEN)) {
				image = new Image("images/canteen.png");
			} else if (minItem.getCode().equals(ModuleType.SANITATION)) {
				image = new Image("images/sanitation.jpg");
			} else if (minItem.getCode().equals(ModuleType.GYM_AND_RELAXATION)) {
				image = new Image("images/gym.png");
			} else if (minItem.getCode().equals(ModuleType.CONTROL)) {
				image = new Image("images/control.jpg");
			} else {
				image = new Image("images/storage.jpg");
			}
			// Set image properties
			image.setSize("5px", "5px");
			image.setVisible(true);
			// Try to use flowPanel
			FlowPanel flow = new FlowPanel();
			flow.setHeight("5px");
			flow.setWidth("5px");
			flow.setVisible(true);
			flow.add(image);
			// Set image on grid
			imageGrid.setWidget((int)tmpPoint.getY(),
					(int)tmpPoint.getX(), 
					flow);

			/**
			 * Testing on why I can't see module images on grid.
			if(imageGrid.isVisible()) {
				Window.alert("The image grid is visible");
			} else {
				Window.alert("I am invisible... but that doesn't mean I am not watching you...");
			}
			if(imageGrid.isCellPresent(21, 21)) {
				Window.alert("The image grid cell (21, 21) exists. For what it's worth...");
			} else {
				Window.alert("Why don't I exist?");
			}
			*/
		}
	}

	//*********************************************
	/**
	 * setUpImageGrid will set up grid that lays over the map image
	 * and holds images of modules in their configuration
	 * as well as the sandy spot marked in x's.
	 */
	private void setUpImageGrid() {
		imagePanel.setSize("551px", "777px");
		imagePanel.getElement().getStyle().setOverflow(Overflow.AUTO);
		imageGrid.setVisible(true);
		// Set sandy part of map
		for(int i = 0; i < 10; i++) {
			for(int j = 39; j < 49; j++) {
				imageGrid.setText(i, j, "X");
			}
		}
		imagePanel.add(imageGrid);
		imagePanel.addStyleName("landingMap");
	}
//*************************************************
	/**
	 * Separates out the important additions to 
	 * the main panel framework. 
	 */
	private void setUpPanelDetails() {
		mainPanel.add(imagePanel);
		mainPanel.add(mainListPanelDecorator);
	}
//**************************************************
	/**
	 * This creates the scrollable list that holds modules used in 
	 * min configuration. Also sets up action handlers for selectable
	 * modules in the list.
	 */
	private void createScrollList() {
		scrollList.setAlwaysShowScrollBars(true);
		scrollList.setTitle("Modules");
		scroll.setPixelSize(250, 451);
		scroll.setVisibleItemCount(109);

		for(int i = 0; i < tmpModList.size(); i++) {
			final Module mod = tmpModList.get(i);
			final String item = mod.getType().toString();
			scroll.addItem(mod.getCode() + ": " + item);
			scroll.addChangeHandler(new ChangeHandler() {

			// Here is where we attach the handlers to each 
			// selectable module on the list. 
				@Override
				public void onChange(final ChangeEvent event) {
					int selection = scroll.getSelectedIndex();
					String selString = scroll.getItemText(selection);
					int semicolon = selString.indexOf(':');
					String finalString = selString.substring(0, semicolon);
					// TESTING STUFF
					Module tempMod;
					int code = Integer.parseInt(finalString);
					if((code == 1)) {
						tempMod = tmpModList.get(0); 
					} else if(code == 2) {
						tempMod = tmpModList.get(1); 
					} else if(code == 3) {
						tempMod = tmpModList.get(2);
					} else if(code == 61) {
						tempMod = tmpModList.get(3);
					} else if(code == 91) {
						tempMod = tmpModList.get(4);
					}else if(code == 111) {
						tempMod = tmpModList.get(5);
					} else if(code == 141) {
						tempMod = tmpModList.get(6);
					} else if(code == 151) {
						tempMod = tmpModList.get(7);
					} else if(code == 161) {
						tempMod = tmpModList.get(8);
					} else {
						tempMod = tmpModList.get(9);
					}
					//@SuppressWarnings("static-access")
					//int tmpIndex = ModuleList.moduleList.getIndexByCode(finalString);
					//Module tempMod = ModuleList.moduleList.get(tmpIndex);

					details.setText("Code: " + tempMod.getCode()
							+ "\n"+"X coordinate: " + tempMod.getXCoor()
							+ "\nY coordinate: " + tempMod.getYCoor()+"\n"
							+ "\nDamage: " + tempMod.getDamage()
							+ "\nTurns to upright: " + tempMod.getTurns());
				}
			});
		}
		scrollList.add(scroll);
		mainListPanel.add(scrollList);
		mainListPanel.setTitle("Modules");
		detailList.add(details);

		// Now we attach onChange handler 
		// to each item in the scroll list
	}
	/**
	 * This creates the buttons and sets them in a grid panel.
	 */
	private void createButtonPanel() {
	    // Set up listeners for recalculate and enter/Save
	    recalculate.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	            // Get second configuration
	            minConfig1.setSecondConfig();
	          }
	        });
	    enterSave.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // handle the enterSave event
                
              }
            });
		buttonGrid.setWidth("225px");
		buttonGrid.setWidget(0, 0, recalculate);
		buttonGrid.setWidget(0, 1, enterSave);
		mainButtonGrid.setVisible(true);
		mainButtonGrid.setWidget(0 ,0, mainListPanel);
		mainButtonGrid.setWidget(1, 0, detailList);
		mainButtonGrid.setWidget(2, 0, buttonGrid);
		mainListPanelDecorator.add(mainButtonGrid);
	}
	/**
	 * main panel - holds it all
	 */
	HorizontalPanel mainPanel;
	/**
	 * image held here.
	 */
	ScrollPanel imagePanel;
	/**
	 * holds list of modules.
	 */
	FlowPanel mainListPanel;
	/**
	 * decorates list.
	 */
	DecoratorPanel mainListPanelDecorator;
	/**
	 * scrollable list.
	 */
	ScrollPanel scrollList;
	/**
	 * text area holds selected module details
	 */
	FlowPanel detailList;
	/**
	 * text area holds string details.
	 */
	TextArea details;
	/**
	 * scroll is the list of modules.
	 */
	ListBox scroll;
	/**
	 * image grid is what plots locations and holds images. 
	 */
	Grid imageGrid;
	/**
	 * button grid holds the buttons in place (Enter&Save, Recalc).
	 */
	Grid buttonGrid;
	/**
	 * Yet another cause interfaces are complicated to code. 
	 */
	Grid mainButtonGrid;
	/**
	 * Button for recalc the configuration.
	 */
	Button recalculate;
	/**
	 * enterSave is to say "yes" to this configuration.
	 */
	Button enterSave;
	/**
	 * The min config object
	 */
	MinimumConfiguration minConfig1;
	/**
	 * An array list of objects to put on grid and where. 
	 */
	ArrayList<Minimum> minArray;
	/**
	 * The little image for each mod on the map. 
	 */
	Image image;
	/**
	 * tmp mod list is used for testing purposes.
	 */
	private ModuleList tmpModList;
	
	/**
	 * Empty for now (Ask Blaed). 
	 */
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
	}
}