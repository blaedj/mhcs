package mhcs.dalton;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.http.client.URL;



/* Public class Weatherfeed implements Entry point. */
	public class WeatherFeed {
		/* on Module Load is main method. Creates requestbuilder and gets current_observations and then takes
		 * temperature and visibility and puts them onto vertical panel.*/
		public void onModuleLoad(){
		    //RootPanel.get().add(getweatherfeed());
}

		public FlowPanel getweatherfeed() {
			final FlowPanel flowP = new FlowPanel();
			String url =
					"http://api.wunderground.com/api/c707aad1f33b2355/conditions/q/55812.json?";{//TAKE CARE OVER END OF URL
					url = URL.encode(url);
					final JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
					jsonp.setCallbackParam("callback");
					jsonp.requestObject(url, new AsyncCallback<JavaScriptObject>() {
						/*Checks to make sure that JSONP doesn't fail*/
					public void onFailure(final Throwable caught) {
					Window.alert("JSONP onFailure");
					}

					/* If it does get the JSONP take out the visibility and temperature and output them*/
					public void onSuccess(final JavaScriptObject scriptO) {
					JSONObject obj = new JSONObject(scriptO);
					String result = obj.toString();

					Image logo = new Image("images/wundergroundLogo_4c.png");
					logo.setWidth("200");
					logo.setHeight("150");
					logo.setPixelSize(200, 150);
					JSONObject jsonA =
							(JSONObject)JSONParser.parseLenient(result);
							JSONValue jTry = jsonA.get("current_observation");
							JSONObject jsonB =
							(JSONObject)JSONParser.parseLenient(jTry.toString());
					JSONValue temp = jsonB.get("temp_c");
					JSONValue visibility = jsonB.get("visibility_km");
					String sTemp = temp.toString();
					String sVisibility = visibility.toString();
					flowP.add(logo);
					flowP.add(new Label("Tempearture " +sTemp +" celsius")); //TO VIEW
					flowP.add(new Label("Visibility " +sVisibility +" kilometers")); //TO VIEW

					}
					});

}
					return flowP;
		}
	}
