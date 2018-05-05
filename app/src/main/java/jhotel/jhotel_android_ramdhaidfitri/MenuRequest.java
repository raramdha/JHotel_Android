package jhotel.jhotel_android_ramdhaidfitri;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MenuRequest extends StringRequest {
    private static final String Regis_URL = "http://10.5.78.152/vacantrooms";

    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, Regis_URL, listener, null);

    }




}
