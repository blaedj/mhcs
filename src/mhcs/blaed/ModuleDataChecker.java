package mhcs.blaed;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public class ModuleDataChecker implements HasHandlers{

	private HandlerManager 	handlerManager;
	
	@Override
	public void fireEvent(GwtEvent<?> event) {
		handlerManager.fireEvent(event);
	}

	public HandlerRegistration addDataRecordedEventHandler(DataRecordedEventHandler handler){
		
		return handlerManager.addHandler(DataRecordedEvent.TYPE, handler);
	}
}
