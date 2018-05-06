package jhotel.jhotel_android_ramdhaidfitri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private int currentUserId;
    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel, ArrayList<Room>> childMapping = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);
        final ExpandableListAdapter listAdapter = new MenuListAdapter(MainActivity.this, listHotel, childMapping);
        expListView.setAdapter(listAdapter);
        Intent i = getIntent();
        currentUserId = i.getIntExtra("id customer", 1);

        final Button pesanan = (Button) findViewById(R.id.pesanan);

        pesanan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                i.putExtra("id customer", currentUserId);
                startActivity(i);
            }
        });


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                Room selected = childMapping.get(listHotel.get(groupPosition)).get(childPosition);
                Hotel hotel = listHotel.get(groupPosition);
                String nomor_kamar = selected.getRoomNumber();
                Intent bookInt = new Intent(MainActivity.this, BuatPesananActivity.class);
                bookInt.putExtra("id customer", currentUserId);
                bookInt.putExtra("nomor kamar", nomor_kamar);
                bookInt.putExtra("id hotel", hotel.getID());
                startActivity(bookInt);
                return false;
            }
        });
    }


    public void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String> () {
            @Override
            public void onResponse (String response) {
                try{
                    JSONArray jsonResponse = new JSONArray(response);
                    JSONObject e = jsonResponse.getJSONObject(0).getJSONObject("hotel");
                    JSONObject lokasi = e.getJSONObject("lokasi");
                    JSONObject room = e.getJSONObject("room");
                    listHotel.add(new Hotel(e.getInt("id"),e.getString("nama"), new Lokasi(lokasi.getDouble("x_coord"),lokasi.getDouble("y_coord"),lokasi.getString("deskripsi")), e.getInt("bintang")));
                    listRoom.add(new Room(room.getString("roomNumber"),room.getString("statusKamar"),room.getDouble("dailyTariff"),room.getString("tipeKamar")));
                    childMapping.put(listHotel.get(0), listRoom);
                }
                catch (JSONException ex){
                    ex.getMessage();
                }
            }

        };

    }

}
