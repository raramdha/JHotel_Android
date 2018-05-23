package jhotel.jhotel_android_ramdhaidfitri;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ramdha on 03/05/2018.
 */

public class MenuRequest extends StringRequest {
    private static final String Vacant_URL = "http://192.168.43.113:8080/vacantrooms";

    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, Vacant_URL, listener, null);

    }




}
