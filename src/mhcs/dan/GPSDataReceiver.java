package mhcs.dan;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;


public class GPSDataReceiver {

    private final static String PROXY = "http://www.d.umn.edu/~hammo190/proxy.php?url=";
    //    private final static String PROXY = "http://www.d.umn.edu/~joh04413/proxy.php?url=";
    private static String url = PROXY + "http://www.d.umn.edu/~abrooks/SomeTests.php?q=";

    /**
     * get module information from test cases page.
     * @param testCase the number of the test case
     */
    public void loadDataByNumber(final int testCase) {
        url = url + testCase;
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

        try {
            Request request = builder.sendRequest(null, new RequestCallback() {

                public void onError(final Request request, final Throwable exception) {
                    Window.alert("onError: Couldn't retrieve JSON");
                }

                public void onResponseReceived(final Request request, final Response response) {
                    // if response is valid
                    if (200 == response.getStatusCode()) {
                        String reply = response.getText();
                        addModules(reply);
                    } else {
                        // if not a valid response
                        Window.alert("Couldn't retrieve JSON (" + response.getStatusText()
                                + ")");
                    }
                }
            });
        } catch (RequestException e) {
            Window.alert("RequestException: Couldn't retrieve JSON");
        }
    }
    
    private void addModules(final String reply) {
        JSONArray jsonArray = (JSONArray)JSONParser.parseLenient(reply);
        String code;
        String damage;
        String xcoor;
        String ycoor;
        String turns;
        
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject)jsonArray.get(i);
            code = ((JSONString)jsonObject.get("code")).stringValue();
            damage = ((JSONString)jsonObject.get("status")).stringValue();
            xcoor = ((JSONString)jsonObject.get("X")).stringValue();
            ycoor = ((JSONString)jsonObject.get("Y")).stringValue();
            turns = ((JSONString)jsonObject.get("turns")).stringValue();
            ModuleList.addModule(new Module(code, damage, xcoor, ycoor, turns));
        }       
    }
}
