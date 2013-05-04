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
		setUpMinConfig(minConfig1);

		FlowPanel wrapper = new FlowPanel();
		wrapper.add(mainPanel);
		return wrapper;
	}
	//**********************************************
    /**
     * Initializes everything .
     */
    private void initialize() {
        theModList = ModuleList.get();
        mainPanel = new HorizontalPanel();
        imagePanel = new ScrollPanel();
        mainListPanelDecorator = new DecoratorPanel();
        mainListPanel = new FlowPanel();
        scrollList = new ScrollPanel();
        detailList = new FlowPanel();
        details = new TextArea();
        details.setText("Select a module to see details");
        details.setCharacterWidth(2 * 2 * 2 * 2 * 2); // 32.
        details.setVisibleLines((2 * 2 * 2) - 1); // 7.
        scroll = new ListBox();
        imageGrid = new Grid((2 * 2 + 1) * (2 * 2 * 2 + 2),
                2 * ((2 * 2 + 1) * (2 * 2 * 2 + 2)));
             // The grid is (50, 100)
        buttonGrid = new Grid(1, 2);
        mainButtonGrid = new Grid((1 + 2), 1);
        recalculate = new Button("Recalculate");
        enterSave = new Button("Enter & Save");
        minConfig1 = new MinimumConfiguration();
        minArray = minConfig1.getMinArray(theModList);

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
	 * @param minConfig configuration to use.
	 */
	public static void setUpMinConfig(
	        final MinimumConfiguration minConfig) {

	    // Set up the array of minimum objects
		ArrayList<Minimum> minArray = minConfig.
		        getMinArray(ModuleList.get());
		//assert (minConfig != null);
        //assert (minArray.size() > 0);
		// Collect all their types and set up images
		for (int i = 0; i < minArray.size(); i++) {
			Minimum minItem = minArray.get(i);
			Point tmpPoint = minItem.getPoint();

			// This messy if code is just setting up
			// images for each module on the map.
			if (minItem.getCode().
			        equals(ModuleType.AIRLOCK)) {
				image = new Image("images/airlock.jpg");
			} else if (minItem.getCode().
			        equals(ModuleType.MEDICAL)) {
				image = new Image("images/medical.png");
			} else if (minItem.getCode().
			        equals(ModuleType.DORMITORY)) {
				image = new Image("images/dorm.jpg");
			} else if (minItem.getCode().
			        equals(ModuleType.PLAIN)) {
				image = new Image("images/plain.png");
			} else if (minItem.getCode().
			        equals(ModuleType.POWER)) {
				image = new Image("images/power.jpg");
			} else if (minItem.getCode().
			        equals(ModuleType.CANTEEN)) {
				image = new Image("images/canteen.png");
			} else if (minItem.getCode().
			        equals(ModuleType.SANITATION)) {
				image = new Image("images/sanitation.jpg");
			} else if (minItem.getCode().
			        equals(ModuleType.GYM_AND_RELAXATION)) {
				image = new Image("images/gym.png");
			} else if (minItem.getCode().
			        equals(ModuleType.CONTROL)) {
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
			imageGrid.setWidget((int) tmpPoint.getY(),
					(int) tmpPoint.getX(),
					flow);
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
		for (int i = 0; i < 10; i++) {
			for (int j = 39; j < 49; j++) {
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

		for (int i = 0; i < theModList.size(); i++) {
			final Module mod = theModList.get(i);
			final String item = mod.getType().toString();
			scroll.addItem(mod.getCode() + ": " + item);
			scroll.addChangeHandler(new ChangeHandler() {

			// Here is where we attach the handlers to each
			// selectable module on the list.
				@Override
				public void onChange(final ChangeEvent event) {
					int selection =
					        scroll.getSelectedIndex();
					String selString =
					        scroll.getItemText(selection);
					int semicolon = selString.indexOf(':');
					String finalString =
					        selString.substring(0,
					                semicolon);
					// TESTING STUFF
					/*if((code == 1)) {
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
					}*/
					@SuppressWarnings("static-access")
					int tmpIndex = theModList.
					    getIndexByCode(finalString);
					Module tempMod =
					        theModList.get(tmpIndex);

					details.setText("Code: "
					        + tempMod.getCode()
							+ "\n"
					        + "X coordinate: "
					        + tempMod.getXCoor()
							+ "\nY coordinate: "
					        + tempMod.getYCoor() + "\n"
							+ "\nDamage: "
					        + tempMod.getDamage()
							+ "\nTurns to upright: "
					        + tempMod.getTurns());
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
	        public void onClick(final ClickEvent event) {
	            // Get second configuration
	            minConfig1.setSecondConfig();
	          }
	        });
	    enterSave.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                // handle the enterSave event
              }
            });
		buttonGrid.setWidth("225px");
		buttonGrid.setWidget(0, 0, recalculate);
		buttonGrid.setWidget(0, 1, enterSave);
		mainButtonGrid.setVisible(true);
		mainButtonGrid.setWidget(0, 0, mainListPanel);
		mainButtonGrid.setWidget(1, 0, detailList);
		mainButtonGrid.setWidget(2, 0, buttonGrid);
		mainListPanelDecorator.add(mainButtonGrid);
	}
	/**
	 * main panel - holds it all.
	 */
	private HorizontalPanel mainPanel;
	/**
	 * image held here.
	 */
	private ScrollPanel imagePanel;
	/**
	 * holds list of modules.
	 */
	private FlowPanel mainListPanel;
	/**
	 * decorates list.
	 */
	private DecoratorPanel mainListPanelDecorator;
	/**
	 * scrollable list.
	 */
	private ScrollPanel scrollList;
	/**
	 * text area holds selected module details.
	 */
	private FlowPanel detailList;
	/**
	 * text area holds string details.
	 */
	private TextArea details;
	/**
	 * scroll is the list of modules.
	 */
	private ListBox scroll;
	/**
	 * image grid is what plots locations and holds images.
	 */
	private static Grid imageGrid;
	/**
	 * button grid holds the buttons in place (Enter&Save, Recalc).
	 */
	private Grid buttonGrid;
	/**
	 * Yet another cause interfaces are complicated to code.
	 */
	private Grid mainButtonGrid;
	/**
	 * Button for recalc the configuration.
	 */
	private Button recalculate;
	/**
	 * enterSave is to say "yes" to this configuration.
	 */
	private Button enterSave;
	/**
	 * The min config object.
	 */
	private static MinimumConfiguration minConfig1;
	/**
	 * An array list of objects to put on grid and where.
	 */
	private ArrayList<Minimum> minArray;
	/**
	 * The little image for each mod on the map.
	 */
	private static Image image;
	/**
	 * tmp mod list is used for testing purposes.
	 */
	private ModuleList theModList;
	/**
	 * Empty for now.
	 */
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
	}
}