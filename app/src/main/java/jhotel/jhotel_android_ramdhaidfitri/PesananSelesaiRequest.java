package jhotel.jhotel_android_ramdhaidfitri;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananSelesaiRequest extends StringRequest {
    private static final String Regis_URL = "http://10.5.78.152/finishpesanan";
    private Map<String, Integer> params;

    public PesananSelesaiRequest(int id_customer, Response.Listener<String> listener) {
        super(Method.POST, Regis_URL, listener, null);
        params = new HashMap<>();
        params.put("id customer", id_customer);
    }
}
