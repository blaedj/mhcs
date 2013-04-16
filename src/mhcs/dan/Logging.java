package mhcs.dan;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Logging implements EntryPoint {
    
    private AbsolutePanel ap = new AbsolutePanel();
    
    private Grid g = new Grid(12, 1);
    private Label codeLabel = new Label("Module Code");
    private TextBox codeTextBox = new TextBox();
    private Label damageLabel = new Label("Module Damage");
    private ListBox damageListBox = new ListBox();
    private Label xCoorLabel = new Label("X Coordinate");
    private TextBox xCoorBox = new TextBox();
    private Label yCoorLabel = new Label("Y Coordinate");
    private TextBox yCoorBox = new TextBox();
    private Label turnsLabel = new Label("Turns to Upright");
    private ListBox turnsListBox = new ListBox();
    private Button enterButton = new Button("Enter Module");
    private Button deleteButton = new Button("Delete Entry");
    
    public void onModuleLoad() {
        
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
        
        RootPanel.get().add(ap);
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