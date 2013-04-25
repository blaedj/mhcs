
package mhcs.danielle;

import java.util.ArrayList;

import mhcs.dan.Module;
import mhcs.dan.Module.ModuleType;
import mhcs.dan.ModuleList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.Button;
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
 * @author Danielle Stewart
 *
 */
public class MinimumConfigPage implements EntryPoint {

	/**
	 * Min list.
	 */
	private ArrayList<Minimum> minList;

	/**
	 * Enter save.
	 */
	private Button enterSave;

	/**
	 * recalc button. 
	 */
	private Button recalc;

	/**
	 * buttonGrid. 
	 */
	private Grid buttonGrid;

	/**
	 * grid. 
	 */
	private Grid grid;

	/**
	 * horizontal panel. 
	 */
	private HorizontalPanel horiz;

	/**
	 * decImage holds map. 
	 */
	private FlowPanel decImage;

	/**
	 * imageGrid. 
	 */
	private Grid imageGrid;

	/**
	 * grid. 
	 */
	private ScrollPanel scrollPanel;

	/**
	 * modList. 
	 */
	private DecoratorPanel modList;

	/**
	 * modDetails. 
	 */
	private DecoratorPanel modDetails;


	/**
	 * decButton. 
	 */
	private DecoratorPanel decButton;

	/**
	 * textarea. 
	 */
	private TextArea ta;

	/**
	 * minConfig. 
	 */
	private MinimumConfiguration minConfig;

	/**
	 * grid. 
	 */
	private CoordinateCalculator coorcalc;

	
	/**
	 * Scroll list is for the lsit of modules. 
	 */
	private ListBox scrollList;
	/**
	 * image. 
	 */
	private Image im;

	/**
	 * coordinate for grid. 
	 */
	private Point coor;

	/**
	 * minItem is type Minimum. 
	 */
	private Minimum minItem;

	/**
	 * onModuleLoad is empty for now. 
	 */
	public void onModuleLoad() {
	}

	/**
	 * createMinConfig() will make a new configuration and return
	 * the page to MHCS.java
	 * @return Widget containing this configuration page
	 */
	public Widget createMinConfig(){

		makeEverything();
		setUpMinConfig(); 
		makeListBox();

		horiz.add(decImage);
		horiz.add(grid);

		FlowPanel wrapper = new FlowPanel();
		wrapper.add(horiz);

		return wrapper;
	}

	

	/**
	 * makeEverything throws it all together - buttons, 
	 * grids, etc. 
	 */
	private void makeEverything() {
		// Create horizontal Panel, Grids, Buttons, etc.
		horiz = new HorizontalPanel();
		grid = new Grid(3, 1);
		buttonGrid = new Grid(1, 2);
		recalc = new Button("Recalculate");
		enterSave = new Button("Enter & Save");


		decImage = new FlowPanel();
		imageGrid = new Grid(50, 100);


		scrollPanel = new ScrollPanel(); // Here are some changes I madez
		scrollList = new ListBox();
		createScrollList();
		scrollPanel.add(scrollList);
		scrollPanel.ensureVisible(scrollList);
	
		
		modList = new DecoratorPanel();
		modDetails = new DecoratorPanel();
		decButton = new DecoratorPanel();
		ta = new TextArea();

		// Set up text area for module detail display
		ta.setCharacterWidth(37);
		ta.setVisibleLines(7);

	}
	/**
	 * setUpMinConfig will put together the first min config
	 * ready for the map.
	 */
	private void setUpMinConfig() {
		
		// Set up objects
		minConfig = new MinimumConfiguration(ModuleList.moduleList);
		minList = minConfig.getMinArray();

		for (int i = 0; i < ModuleList.moduleList.size(); i++){

			minItem = minList.get(i);
			coor = minItem.getPoint();

			// local variables in testing getX/getY functions
			int tmpX = (int) coor.getX();
			int tmpY = (int) coor.getY();

			// object coorcalc has the minList(i) grid value
			// for x,y coord.
			coorcalc = new CoordinateCalculator(tmpX, tmpY);

			
			
			if (minItem.getCode().equals(ModuleType.AIRLOCK)) {
				im = new Image("images/airlock.jpg");
			}else if (minItem.getCode().equals(ModuleType.MEDICAL)) {
				im = new Image("images/medical.png");
			}else if (minItem.getCode().equals(ModuleType.DORMITORY)) {
				im = new Image("images/dorm.jpg");
			}else if (minItem.getCode().equals(ModuleType.PLAIN)) {
				im = new Image("images/plain.png");
			}else if (minItem.getCode().equals(ModuleType.POWER)) {
				im = new Image("images/power.jpg");
			}else if (minItem.getCode().equals(ModuleType.CANTEEN)) {
				im = new Image("images/canteen.png");
			}else if (minItem.getCode().equals(ModuleType.SANITATION)) {
				im = new Image("images/sanitation.jpg");
			}else if (minItem.getCode().equals(ModuleType.GYM_AND_RELAXATION)) {
				im = new Image("images/gym.png");
			}else if (minItem.getCode().equals(ModuleType.CONTROL)) {
				im = new Image("images/control.jpg");
			}else{
				im = new Image("images/storage.jpg");
			}

			im.setSize("5px", "5px");

			assert coorcalc.yCoorGrid() >= 0;
			assert coorcalc.xCoorGrid() >= 0;

			// Need x to be col and y to be rows for grid
			imageGrid.setWidget(coorcalc.xCoorGrid(),
					coorcalc.yCoorGrid(), im);
			decImage.add(imageGrid);
			decImage.addStyleName("landingMap");
			decImage.setVisible(true);
		}
	}

	/**
	 * 
	 */
	private void createScrollList() {
		scrollPanel.setAlwaysShowScrollBars(true);
		scrollPanel.setTitle("Modules");
		
		scrollList.setPixelSize(250, 451);
		scrollList.setVisibleItemCount(101);
		
		for (int i = 0; i < ModuleList.moduleList.size(); i++) {
			final Module mod = ModuleList.moduleList.get(i);
			final String item = mod.getType().toString();
			
			scrollList.addItem(item + ": " + mod.getCode());
			scrollList.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(final ChangeEvent event) {
					String code = ta.getSelectedText();
					int tempIndex = ModuleList.getIndexByCode(code);
					Module tempMod = ModuleList.moduleList.get(tempIndex);
					
					ta.setText("Code: " + tempMod.getCode()
							+ "\n"+"X coordinate: " + tempMod.getXCoor()
							+ "\nY coordinate: " + tempMod.getYCoor()+"\n"
							+ "\nDamage: " + tempMod.getDamage()
							+ "\nTurns to upright: " + tempMod.getTurns());
				}
			});
			
		}
	}
	

	/**
	 * makeListBox puts the modules into list box.
	 */
	private void makeListBox() {
		
		
		modList.add(scrollPanel);
		modList.setTitle("Modules");
		modDetails.add(ta);

		buttonGrid.setWidth("255px");
		buttonGrid.setWidget(0, 0, recalc);
		buttonGrid.setWidget(0, 1, enterSave);

		decButton.setWidth("255px");
		decButton.add(buttonGrid);

		grid.setVisible(true);
		grid.setWidget(0, 0, modList);
		grid.setWidget(1, 0, modDetails);
		grid.setWidget(2, 0, decButton);


	}
}