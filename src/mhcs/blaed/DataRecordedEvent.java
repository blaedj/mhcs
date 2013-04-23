package mhcs.blaed;

import mhcs.dan.Module;

import com.google.gwt.event.shared.GwtEvent;

public class DataRecordedEvent extends GwtEvent<DataRecordedEventHandler>{

	public static Type<DataRecordedEventHandler> TYPE = new Type<DataRecordedEventHandler>();
	private Module module;	 

	public DataRecordedEvent(Module module){
		this.module = module;
	}	
			
	@Override
	public Type<DataRecordedEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(DataRecordedEventHandler handler) {
		// TODO Auto-generated method stub
	}

	public Module getModule() {
		return module;
	}
	
}