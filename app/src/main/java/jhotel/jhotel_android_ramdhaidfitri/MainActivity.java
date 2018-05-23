package jhotel.jhotel_android_ramdhaidfitri;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ramdha on 03/05/2018.
 */
public class MainActivity extends AppCompatActivity {
    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel, ArrayList<Room>> childMapping = new HashMap<>();
    int currentUserId;
    private int i;
    private MenuListAdapter listAdapter;
    private ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshList();

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new MenuListAdapter(MainActivity.this, listHotel, childMapping);
        expListView.setAdapter(listAdapter);
        Intent myIntent= getIntent();
        currentUserId = myIntent.getIntExtra("id customer", 1);

        Button pesanan = (Button) findViewById(R.id.pesanan);

        pesanan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent finIntent = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                finIntent.putExtra("id customer", currentUserId);
                startActivity(finIntent);
            }
        });


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                Room selected = childMapping.get(listHotel.get(groupPosition)).get(childPosition);
                Hotel hotel = listHotel.get(groupPosition);
                String nomor_kamar = selected.getRoomNumber();
                Intent bookInt = new Intent(MainActivity.this, BuatPesananActivity.class);
                bookInt.putExtra("id customer", currentUserId);
                bookInt.putExtra("nomor kamar", nomor_kamar);
                bookInt.putExtra("id hotel", hotel.getID());
                startActivity(bookInt);
                return true;
            }
        });
    }


    public void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String> () {
            @Override
            public void onResponse (String response) {
                try{
                    JSONArray jsonResponse = new JSONArray(response);
                    if (jsonResponse != null){

                        JSONObject e = jsonResponse.getJSONObject(0).getJSONObject("hotel");
                        JSONObject lokasi = e.getJSONObject("lokasi");
                        Hotel hotel = new Hotel(e.getInt("id"), e.getString("nama"),
                                new Lokasi(lokasi.getDouble("x"), lokasi.getDouble("y"),
                                        lokasi.getString("deskripsi")), e.getInt("bintang"));
                        listHotel.add(hotel);
                        for (i=0; i<jsonResponse.length();i++){
                            JSONObject r = jsonResponse.getJSONObject(i);
                            listRoom.add(new Room(r.getString("nomorKamar"), r.getString("statusKamar"),
                                    r.getDouble("dailyTariff"), r.getString("tipeKamar")));
                        }
                        childMapping.put(listHotel.get(0), listRoom);
                    }
                }
                catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Failed to load.")
                            .create()
                            .show();
                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }
}
