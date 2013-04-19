package mhcs.dan;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Logging { // !implements EntryPoint

    /* properties */

    private AbsolutePanel ap;
    private Grid g;
    private Label codeLabel;
    private TextBox codeTextBox;
    private Label damageLabel;
    private ListBox damageListBox;
    private Label xCoorLabel;
    private TextBox xCoorBox;
    private Label yCoorLabel;
    private TextBox yCoorBox;
    private Label turnsLabel;
    private ListBox turnsListBox;
    private Button enterButton;
    private Button deleteButton;

    public Logging() {

	ap = new AbsolutePanel();
	g = new Grid(12, 1);
	codeLabel = new Label("Module Code");
	codeTextBox = new TextBox();
	damageLabel = new Label("Module Damage");
	damageListBox = new ListBox();
	xCoorLabel = new Label("X Coordinate");
	xCoorBox = new TextBox();
	yCoorLabel = new Label("Y Coordinate");
	yCoorBox = new TextBox();
	turnsLabel = new Label("Turns to Upright");
	turnsListBox = new ListBox();
	enterButton = new Button("Enter Module");
	deleteButton = new Button("Delete Entry");
    }

    public void onModuleLoad() {
	RootLayoutPanel.get().add(getLoggingPage());
    }

    public Widget getLoggingPage() {
	 ap = new AbsolutePanel();
	 g = new Grid(12, 1);
	 codeLabel = new Label("Module Code");
	 codeTextBox = new TextBox();
	 damageLabel = new Label("Module Damage");
	 damageListBox = new ListBox();
	 xCoorLabel = new Label("X Coordinate");
	 xCoorBox = new TextBox();
	 yCoorLabel = new Label("Y Coordinate");
	 yCoorBox = new TextBox();
	 turnsLabel = new Label("Turns to Upright");
	 turnsListBox = new ListBox();
	 enterButton = new Button("Enter Module");
	 deleteButton = new Button("Delete Entry");

	enterButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
		    try {
			int code = Integer.parseInt(codeTextBox.getText());
			int xcoor = Integer.parseInt(xCoorBox.getText());
			int ycoor = Integer.parseInt(yCoorBox.getText());
			checkCode(code);
			checkCoords(xcoor, ycoor);
			addModule(new Module(codeTextBox.getText(),
					     damageListBox.getItemText(damageListBox.getSelectedIndex()),
					     xCoorBox.getText(), yCoorBox.getText(),
					     turnsListBox.getItemText(turnsListBox.getSelectedIndex())));
			//                    updateHistogram();
		    }
		    catch (NumberFormatException e) {
			Window.alert("Input Not A Number");
		    }
		    catch (Exception e) {
			Window.alert(e.getMessage());
		    }
		}
	    });

	damageListBox.addItem("Undamaged");
	damageListBox.addItem("Damaged");
	damageListBox.addItem("Unceratin");

	turnsListBox.addItem("1");
	turnsListBox.addItem("2");
	turnsListBox.addItem("3");

	g.setWidget(0, 0, codeLabel);
	g.setWidget(1, 0, codeTextBox);
	g.setWidget(2, 0, damageLabel);
	g.setWidget(3, 0, damageListBox);
	g.setWidget(4, 0, xCoorLabel);
	g.setWidget(5, 0, xCoorBox);
	g.setWidget(6, 0, yCoorLabel);
	g.setWidget(7, 0, yCoorBox);
	g.setWidget(8, 0, turnsLabel);
	g.setWidget(9, 0, turnsListBox);
	g.setWidget(10, 0, enterButton);
	g.setWidget(11, 0, deleteButton);

	ap.add(g);

	FlowPanel wrapper = new FlowPanel();
	wrapper.add(ap);
	return wrapper;
    }

    //    private void updateHistrogram() {
    //
    //    }

    private void checkCoords(int x, int y) throws Exception {
	if (x < 0 || x > 150) {
	    throw new Exception("Invalid X Coordinate");
	}
	if (y < 0 || y > 50) {
	    throw new Exception("Invalid Y Coordinate");
	}
    }

    
    private void checkCode(int code) throws Exception {
	boolean isValid = true;
	if (code < 1) {
	    isValid = false;
	}
	if (code > 40 && code < 61) {
	    isValid = false;
	}
	if (code > 80 && code < 91) {
	    isValid = false;
	}
	if (code > 100 && code < 111) {
	    isValid = false;
	}
	if (code > 120 && code < 131) {
	    isValid = false;
	}
	if (code > 134 && code < 141) {
	    isValid = false;
	}
	if (code > 144 && code < 151) {
	    isValid = false;
	}
	if (code > 154 && code < 161) {
	    isValid = false;
	}
	if (code > 164 && code < 171) {
	    isValid = false;
	}
	if (code > 174 && code < 181) {
	    isValid = false;
	}
	if (code > 184) {
	    isValid = false;
	}
	if (!isValid) {
	    throw new Exception("Invalid Module Code");
	}
    }

    private void addModule(Module module) {
	Window.alert(module.toString());
    }
}
