package mhcs.danielle;

import java.util.ArrayList;

import mhcs.dan.Module;
import mhcs.dan.Module.ModuleType;
import mhcs.dan.ModuleList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
/**
 * 
 * @author Danielle Stewart
 *
 */
public class MinimumConfigPage implements EntryPoint {
	
	ArrayList<Minimum> minList;
	Button enterSave;
	Button recalc;
	
	Grid buttonGrid;
	Grid grid;
	
	HorizontalPanel horiz;
	FlowPanel decImage;
	Grid imageGrid;
	
	ScrollPanel scrollList;
	DecoratorPanel modList;
	DecoratorPanel modDetails;
	DecoratorPanel decButton;
	
	TextArea ta;
	ListBox lb;
	
	MinimumConfiguration minConfig;
	CoordinateCalculator coorcalc;
	Image im;
	Point coor;
	Minimum minItem;

	public void onModuleLoad() {
	}

	public Widget createMinConfig(){
		
		makeEverything();
		setChangeHandlers();
	    setUpMinConfig(); 
	    makeListBox();
	    
	    horiz.add(decImage);
	    horiz.add(grid);  

	    FlowPanel wrapper = new FlowPanel();
	    wrapper.add(horiz);
	
	    return wrapper;
	 }
	
	/**
	 * sets the change handlers in the list box to display 
	 * module information
	 * Assumes access to ModuleList.moduleList
	 */
	private void setChangeHandlers(){
		
		for(int i = 0; i < ModuleList.moduleList.size(); i++) {
	          final Module item = ModuleList.moduleList.get(i);
	          ModuleType enumType = item.getType();
	          String type = enumType.toString();
	    	  lb.addItem(type);
	    	  lb.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					
					ta.setText("Code: "+item.getCode()+"\n"+"X coordinate: "+item.getXCoor()
							+"\nY coordinate: "+item.getYCoor()+"\n"
							+"\nDamage: "+item.getDamage()
							+"\nTurns to upright: "+item.getTurns());
				}
	    	  });
	      }
	}
	
	/**
	 * 
	 */
	private void makeEverything(){
		// Create horizontal Panel, Grids, Buttons, etc.
	      horiz = new HorizontalPanel();
	      grid = new Grid(3,1);
	      buttonGrid = new Grid(1,2);
	      recalc = new Button("Recalculate");
	      enterSave = new Button("Enter & Save");
	      
	      
	      decImage = new FlowPanel();
	      imageGrid = new Grid(50,100);
		  
		  
	      scrollList = new ScrollPanel();
	      modList = new DecoratorPanel();
	      modDetails = new DecoratorPanel();
	      decButton = new DecoratorPanel();
	      ta = new TextArea();
	      lb = new ListBox();

	      // Set up text area for module detail display
	      ta.setCharacterWidth(37);
	      ta.setVisibleLines(7);
	      
	}
	
	/**
	 * 
	 */
	private void setUpMinConfig(){
		
		// Set up objects
		minConfig = new MinimumConfiguration(ModuleList.moduleList);
	    minList = minConfig.getMinArray();
	    
	    for(int i = 0; i < minList.size(); i++){
	    	  
	    	  minItem = minList.get(i);
	    	  coor = minItem.getPoint();
	    	  int tmpX = (int)coor.getX();
	    	  int tmpY = (int)coor.getY();
	    	  
	    	  // object coorcalc has the minList(i) grid value
	    	  // for x,y coord.
	    	  coorcalc = new CoordinateCalculator(tmpX, tmpY);
	    	  
	    	  if(minItem.getCode().equalsIgnoreCase("air")){
	    		  im = new Image("images/airlock.jpg");
	    	  }else if(minItem.getCode().equalsIgnoreCase("med")){
	    		  im = new Image("images/medical.png");
	    	  }else if(minItem.getCode().equalsIgnoreCase("dorm")){
	    		  im = new Image("images/dorm.jpg");
	    	  }else if(minItem.getCode().equalsIgnoreCase("plain")){
	    		  im = new Image("images/plain.png");
	    	  }else if(minItem.getCode().equalsIgnoreCase("power")){
	    		  im = new Image("images/power.jpg");
	    	  }else if(minItem.getCode().equalsIgnoreCase("canteen")){
	    		  im = new Image("images/canteen.png");
	    	  }else if(minItem.getCode().equalsIgnoreCase("sanitation")){
	    		  im = new Image("images/sanitation.jpg");
	    	  }else if(minItem.getCode().equalsIgnoreCase("gym")){
	    		  im = new Image("images/gym.png");
	    	  }else if(minItem.getCode().equalsIgnoreCase("control")) {
	    		  im = new Image("images/control.jpg");
	    	  }else{
	    		  im = new Image("images/storage.jpg");
	    	  }

	    	  im.setSize("5px", "5px");
	    	  
	    	  assert coorcalc.yCoorGrid() >= 0;
	    	  assert coorcalc.xCoorGrid() >= 0;
	    	  
	    	  imageGrid.setWidget(coorcalc.xCoorGrid(), coorcalc.yCoorGrid(), im);
	    	  decImage.add(imageGrid);
			  decImage.addStyleName("landingMap");
	      }
	}
	
	/**
	 * 
	 */
	private void makeListBox(){
		// Make enough room for 19 items, then use scrollbar
	      // Can select item and 'display' will show details
	      lb.setVisibleItemCount(19);
	      lb.setPixelSize(250, 451);
	      scrollList.add(lb);

	      modList.add(scrollList);
	      modDetails.add(ta);

	      buttonGrid.setWidth("255px");
	      buttonGrid.setWidget(0, 0, recalc);
	      buttonGrid.setWidget(0,1, enterSave);

	      decButton.setWidth("255px");
	      decButton.add(buttonGrid);

	      grid.setVisible(true);
	      grid.setWidget(0, 0, modList);
	      grid.setWidget(1, 0, modDetails);
	      grid.setWidget(2, 0, decButton);

	      
	}
}
