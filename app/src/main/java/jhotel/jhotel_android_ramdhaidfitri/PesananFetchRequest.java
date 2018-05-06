package jhotel.jhotel_android_ramdhaidfitri;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class PesananFetchRequest extends StringRequest {
    private static final String Regis_URL = "http://10.5.78.152/pesanancustomer/{id_customer}";
    private int id_customer;

    public PesananFetchRequest(int id_customer, Response.Listener<String> listener) {
        super(Method.GET, Regis_URL, listener, null);

    }
}
