package jhotel.jhotel_android_ramdhaidfitri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BuatPesananActivity extends AppCompatActivity {
    private int currentUserId, banyakHari, idHotel;
    private double tariff;
    private String roomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);
    }
}
