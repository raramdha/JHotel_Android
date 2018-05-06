package jhotel.jhotel_android_ramdhaidfitri;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelesaiPesananActivity extends AppCompatActivity {
    private int currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);
        Intent i = getIntent();
        currentUserId = i.getIntExtra("id customer", 1);


        final TextView stat_idpesanan = (TextView) findViewById(R.id.staticIdPesanan);
        final TextView stat_jumlahHari = (TextView) findViewById(R.id.staticjumlahHari);
        final TextView stat_biaya = (TextView) findViewById(R.id.staticBiaya);
        final TextView stat_tanggalPesan = (TextView) findViewById(R.id.staticTanggal);
        final TextView id_pesanan = (TextView) findViewById(R.id.id_pesanan);
        final TextView jumlahHari = (TextView) findViewById(R.id.jumlahHari);
        final TextView biaya = (TextView) findViewById(R.id.biaya);
        final TextView tanggalPesan = (TextView) findViewById(R.id.tanggalPesan);
        final Button selesai = (Button) findViewById(R.id.selesai);
        final Button batal = (Button) findViewById(R.id.batal);

        stat_idpesanan.setVisibility(TextView.INVISIBLE);
        stat_jumlahHari.setVisibility(TextView.INVISIBLE);
        stat_biaya.setVisibility(TextView.INVISIBLE);
        stat_tanggalPesan.setVisibility(TextView.INVISIBLE);
        id_pesanan.setVisibility(TextView.INVISIBLE);
        jumlahHari.setVisibility(TextView.INVISIBLE);
        biaya.setVisibility(TextView.INVISIBLE);
        tanggalPesan.setVisibility(TextView.INVISIBLE);
        selesai.setVisibility(Button.INVISIBLE);
        batal.setVisibility(Button.INVISIBLE);

        fetchPesanan();
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Penyelesaian Pesanan Berhasil!")
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Penyelesaian Pesanan Gagall!")
                                    .create()
                                    .show();
                        }
                    }
                };

                PesananSelesaiRequest selesaiRequest = new PesananSelesaiRequest(currentUserId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(selesaiRequest);

            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Pembatalan Pesanan Berhasil!")
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Pembatalan Pesanan Gagall!")
                                    .create()
                                    .show();
                        }
                    }
                };

                PesananBatalRequest batalRequest = new PesananBatalRequest(currentUserId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(batalRequest);

            }
        });
    }


        public void fetchPesanan(){
            Response.Listener<String> responseListener = new Response.Listener<String> () {
                @Override
                public void onResponse (String response) {
                    try{
                        JSONObject jsonResponse = new JSONObject(response);
                        if(jsonResponse != null){
                            /*stat_idpesanan.setVisibility(TextView.VISIBLE);
                            stat_jumlahHari.setVisibility(TextView.VISIBLE);
                            stat_biaya.setVisibility(TextView.VISIBLE);
                            stat_tanggalPesan.setVisibility(TextView.VISIBLE);
                            id_pesanan.setVisibility(TextView.VISIBLE);
                            jumlahHari.setVisibility(TextView.VISIBLE);
                            biaya.setVisibility(TextView.VISIBLE);
                            tanggalPesan.setVisibility(TextView.VISIBLE);
                            selesai.setVisibility(Button.VISIBLE);
                            batal.setVisibility(Button.VISIBLE);*/
                        }
                    }
                    catch (JSONException ex){
                        ex.getMessage();
                    }
                }

            };
            PesananFetchRequest fetchRequest = new PesananFetchRequest(currentUserId, responseListener);
            RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
            queue.add(fetchRequest);


        }

}
