package jhotel.jhotel_android_ramdhaidfitri;

import android.app.AlertDialog;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Ramdha on 01/05/2018.
 */

public class RegisterRequest extends StringRequest {
    private static final String Regis_URL = "http://10.5.78.152/newcustomer";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, String password,
                           Response.Listener<String> listener) {
        super(Method.POST, Regis_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}