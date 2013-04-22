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
	//private int length;

	 public void onModuleLoad() {
	 }

	public Widget createMinConfig(){
		
	 // Create horizontal Panel, Grids, Buttons, etc.
	      HorizontalPanel horiz = new HorizontalPanel();
	      Grid grid = new Grid(3,1);
	      Grid buttonGrid = new Grid(1,2);
	      Button recalc = new Button("Recalculate");
	      Button enterSave = new Button("Enter & Save");
	      
	      
	      FlowPanel decImage = new FlowPanel();
	      Grid imageGrid = new Grid(50,100);
	      
	      
	      
	      decImage.add(imageGrid);
		  decImage.addStyleName("landingMap");
		  
		  
	      ScrollPanel scrollList = new ScrollPanel();
	      DecoratorPanel modList = new DecoratorPanel();
	      DecoratorPanel modDetails = new DecoratorPanel();
	      DecoratorPanel decButton = new DecoratorPanel();
	      final TextArea ta = new TextArea();
	      ListBox lb = new ListBox();

	      // Set up text area for module detail display
	      ta.setCharacterWidth(37);
	      ta.setVisibleLines(7);

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
	      MinimumConfiguration minConfig = new MinimumConfiguration(ModuleList.moduleList);
	      ArrayList<Minimum> minList = minConfig.getMinArray();
	      
	      for(int i = 0; i < minList.size(); i++){
	    	  
	    	  Minimum pt = minList.get(i);
	    	  Point coor = pt.getPoint();
	    	  Image im;
	    	  CoordinateCalculator coorcalc = new CoordinateCalculator((int)coor.getX(), (int)coor.getY());
	    	  if(pt.getCode().equalsIgnoreCase("air")){
	    		  im = new Image("images/airlock.jpg");
	    	  }else if(pt.getCode().equalsIgnoreCase("med")){
	    		  im = new Image("images/medical.png");
	    	  }else if(pt.getCode().equalsIgnoreCase("dorm")){
	    		  im = new Image("images/dorm.jpg");
	    	  }else if(pt.getCode().equalsIgnoreCase("plain")){
	    		  im = new Image("images/plain.png");
	    	  }else if(pt.getCode().equalsIgnoreCase("power")){
	    		  im = new Image("images/power.jpg");
	    	  }else if(pt.getCode().equalsIgnoreCase("canteen")){
	    		  im = new Image("images/canteen.png");
	    	  }else if(pt.getCode().equalsIgnoreCase("sanitation")){
	    		  im = new Image("images/sanitation.jpg");
	    	  }else if(pt.getCode().equalsIgnoreCase("gym")){
	    		  im = new Image("images/gym.png");
	    	  }else if(pt.getCode().equalsIgnoreCase("control")) {
	    		  im = new Image("images/control.jpg");
	    	  }else{
	    		  im = new Image("images/storage.jpg");
	    	  }
	    	  imageGrid.setWidget(coorcalc.xCoorGrid()+20, coorcalc.yCoorGrid()+20, im);
	      }

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

	      grid.setWidget(0, 0, modList);
	      grid.setWidget(1, 0, modDetails);
	      grid.setWidget(2, 0, decButton);

	      horiz.add(decImage);
	      horiz.add(grid);

	      // Add the widgets to the root panel.
	      //RootPanel.get().add(horiz);
	      FlowPanel wrapper = new FlowPanel();
	      wrapper.add(horiz);
	      //return horiz;
	      return wrapper;
	   }
}
