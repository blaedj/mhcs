package mhcs.dalton;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
//@author Dalton Johnson
     





// This class implements the log in page. It implements the entry point class.
public class LogIn implements EntryPoint {
	// this method loads the log in page's interface to browser.
	public void onModuleLoad() {
		//creates textbox, password text box, button, flex table, and two string variables.
		//Loads button and boxes onto flex table.
	    final TextBox tb = new TextBox();
	    final PasswordTextBox ptb = new PasswordTextBox();
	    Button confirm = new Button("Confirm");
	    FlexTable t = new FlexTable();
	    t.setText(0,0,"Username");
	    t.setText(0,5,"Password");
	    t.setWidget(1,0,tb);
	    t.setWidget(1,5,ptb); 
	    t.setWidget(1,6,confirm);
	    confirm.addClickHandler(new ClickHandler() {
	    	//checks to see if password is mars and if username is Dalton, Blaed, Dan or Danielle
        public void onClick(ClickEvent event) {
        	final String s = ptb.getText();
        	final String s2 = tb.getText();
        	 if(!s.equals("mars"))         	 
        	 Window.alert("Wrong Password. Try Again");
        	 else if (!s2.equals("Astro"))
        		 Window.alert("Wrong Username. Try again");
        	 else
        		 Window.alert("Log In Correct");
        		 
        }
	    });
	    RootPanel.get().add(t);
	}
}


