package mhcs.danielle;

import mhcs.dan.ModuleList;

import com.google.gwt.core.client.EntryPoint;
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

public class MinimumConfigPage implements EntryPoint {
	private ModuleList moduleList;
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
	      TextArea ta = new TextArea();
	      ListBox lb = new ListBox();

	      // Set up text area for module detail display
	      ta.setCharacterWidth(37);
	      ta.setVisibleLines(7);

	      for(int i = 0; i < moduleList.size(); i++) {
	          String item = moduleList(i).toString();
	    	  lb.addItem(item);
	    	  item.addChangeListener(new ChangeListener() {
	    	  	public void onChange() {
	    	  		// code to access details of module for textarea
	    	  	}
	    	  });
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
