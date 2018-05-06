package jhotel.jhotel_android_ramdhaidfitri;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BuatPesananActivity extends AppCompatActivity {
    private int currentUserId, banyakHari, idHotel;
    private double tariff;
    private String roomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);
        final TextView total_biaya = (TextView) findViewById(R.id.total_biaya);
        final TextView tarif = (TextView) findViewById(R.id.tariff);
        final TextView room_number = (TextView) findViewById(R.id.room_number);
        final EditText durasi_hari = (EditText) findViewById(R.id.durasi_hari);
        final Button hitung = (Button) findViewById(R.id.hitung);
        final Button pesan = (Button) findViewById(R.id.pesan);

        pesan.setVisibility(Button.INVISIBLE);
        room_number.setText(roomNumber);
        tarif.setText(Double.toString(tariff));
        total_biaya.setText("0");

        Intent pesanInt = getIntent();
        currentUserId = pesanInt.getIntExtra("id customer", 1);
        roomNumber = pesanInt.getStringExtra("nomor kamar");
        idHotel = pesanInt.getIntExtra("id hotel", 1);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banyakHari = Integer.parseInt(durasi_hari.getText().toString());
                total_biaya.setText(Double.toString(tariff*banyakHari));
                hitung.setVisibility(Button.INVISIBLE);
                pesan.setVisibility(Button.VISIBLE);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            if(jsonResponse!=null){
                                AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                                builder.setMessage("Ordering Success")
                                        .create()
                                        .show();
                            }
                        } catch(JSONException e){
                            AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                            builder.setMessage("Ordering Failed.")
                                    .create()
                                    .show();
                        }
                    }
                };
            }
        });
    }

}
