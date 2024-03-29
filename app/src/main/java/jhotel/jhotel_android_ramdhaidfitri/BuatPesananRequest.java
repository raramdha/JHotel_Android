package jhotel.jhotel_android_ramdhaidfitri;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramdha on 06/05/2018.
 */

public class BuatPesananRequest extends StringRequest {
    private static final String Pesan_URL = "http://192.168.43.113:8080/bookpesanan";
    private Map<String, String> params;

    public BuatPesananRequest(int jumlah_hari, int id_customer, int id_hotel, String nomor_kamar,
                           Response.Listener<String> listener) {
        super(Method.POST, Pesan_URL, listener, null);
        params = new HashMap<>();
        params.put("jumlah hari", String.valueOf(jumlah_hari));
        params.put("id customer", String.valueOf(id_customer));
        params.put("id hotel", String.valueOf(id_hotel));
        params.put("nomor kamar", nomor_kamar);
    }

    @Override
    public Map<String, String> getParams(){ return params; }

}
