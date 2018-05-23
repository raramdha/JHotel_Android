package jhotel.jhotel_android_ramdhaidfitri;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramdha on 06/05/2018.
 */

public class PesananSelesaiRequest extends StringRequest {
    private static final String Finish_URL = "http://192.168.43.113:8080/finishpesanan";
    private Map<String, String> params;

    public PesananSelesaiRequest(int id_pesanan, Response.Listener<String> listener) {
        super(Method.POST, Finish_URL, listener, null);
        params = new HashMap<>();
        params.put("id_pesanan", String.valueOf(id_pesanan));
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
