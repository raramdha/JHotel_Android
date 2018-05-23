package jhotel.jhotel_android_ramdhaidfitri;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramdha on 06/05/2018.
 */

public class PesananFetchRequest extends StringRequest {
    private static final String Fetch_URL = "http://192.168.43.113:8080/pesanancustomer/{id_customer}";
    private Map<String, String> params;

    public PesananFetchRequest(int id_customer, Response.Listener<String> listener) {
        super(Method.GET, Fetch_URL + id_customer, listener, null);
        params = new HashMap<>();
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
