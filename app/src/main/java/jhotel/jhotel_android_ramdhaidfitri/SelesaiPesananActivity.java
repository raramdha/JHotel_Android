package jhotel.jhotel_android_ramdhaidfitri;

import android.content.DialogInterface;
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
import static com.android.volley.toolbox.Volley.newRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

public class SelesaiPesananActivity extends AppCompatActivity {
    private int id_customer;
    private int fetch_id_pesanan;
    private double fetch_biaya;
    private int fetch_hari;
    private String fetch_tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);
        Intent finishIntent= getIntent();
        id_customer = finishIntent.getIntExtra("id customer", 1);


        TextView stat_idpesanan = (TextView) findViewById(R.id.staticIdPesanan);
        TextView stat_jumlahHari = (TextView) findViewById(R.id.staticjumlahHari);
        TextView stat_biaya = (TextView) findViewById(R.id.staticBiaya);
        TextView stat_tanggalPesan = (TextView) findViewById(R.id.staticTanggal);

        final Button selesai = (Button) findViewById(R.id.selesai);
        final Button batal = (Button) findViewById(R.id.batal);

        /*stat_idpesanan.setVisibility(TextView.INVISIBLE);
        stat_jumlahHari.setVisibility(TextView.INVISIBLE);
        stat_biaya.setVisibility(TextView.INVISIBLE);
        stat_tanggalPesan.setVisibility(TextView.INVISIBLE);
        id_pesanan.setVisibility(TextView.INVISIBLE);
        jumlahHari.setVisibility(TextView.INVISIBLE);
        biaya.setVisibility(TextView.INVISIBLE);
        tanggalPesan.setVisibility(TextView.INVISIBLE);
        selesai.setVisibility(Button.INVISIBLE);
        batal.setVisibility(Button.INVISIBLE); */

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
                                builder.setMessage("Your order finished.")
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Your order failed to finish!")
                                    .create()
                                    .show();
                        }
                    }
                };

                PesananSelesaiRequest selesaiRequest = new PesananSelesaiRequest(fetch_id_pesanan, responseListener);
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
                                builder.setMessage("Your order canceled.")
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Your order failed to cancel!")
                                    .create()
                                    .show();
                        }
                    }
                };

                PesananBatalRequest batalRequest = new PesananBatalRequest(fetch_id_pesanan, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(batalRequest);

            }
        });
    }


    public void fetchPesanan(){
        final TextView id_pesanan = (TextView) findViewById(R.id.id_pesanan);
        final TextView jumlahHari = (TextView) findViewById(R.id.jumlahHari);
        final TextView biaya = (TextView) findViewById(R.id.biaya);
        final TextView tanggalPesan = (TextView) findViewById(R.id.tanggalPesan);

        Response.Listener<String> responseListener = new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    fetch_id_pesanan = jsonResponse.getInt("id_pesanan");
                    fetch_biaya = jsonResponse.getDouble("biaya");
                    fetch_hari = jsonResponse.getInt("jumlahHari");
                    fetch_tanggal = jsonResponse.getString("tanggalPesan");

                            /*stat_idpesanan.setVisibility(TextView.VISIBLE);
                            stat_jumlahHari.setVisibility(TextView.VISIBLE);
                            stat_biaya.setVisibility(TextView.VISIBLE);
                            stat_tanggalPesan.setVisibility(TextView.VISIBLE);
                            id_pesanan.setVisibility(TextView.VISIBLE);
                            jumlahHari.setVisibility(TextView.VISIBLE);
                            biaya.setVisibility(TextView.VISIBLE);
                            tanggalPesan.setVisibility(TextView.VISIBLE);
                            selesai.setVisibility(Button.VISIBLE);
                            batal.setVisibility(Button.VISIBLE);
                            */
                    id_pesanan.setText(Integer.toString(fetch_id_pesanan));
                    biaya.setText(Double.toString(fetch_biaya));
                    jumlahHari.setText(Integer.toString(fetch_hari));
                    tanggalPesan.setText(fetch_tanggal);

                }catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                    builder.setMessage("Error.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int fetch_id_pesanan) {
                                    Intent backIntent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                    backIntent.putExtra("id customer", id_customer);
                                    SelesaiPesananActivity.this.startActivity(backIntent);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        };
        PesananFetchRequest fetchRequest = new PesananFetchRequest(id_customer,responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(fetchRequest);
    }
}


