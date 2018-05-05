package jhotel.jhotel_android_ramdhaidfitri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel, ArrayList<Room>> childMapping = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);
        ExpandableListAdapter listAdapter = new MenuListAdapter(MainActivity.this, listHotel, childMapping);
        expListView.setAdapter(listAdapter);
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
