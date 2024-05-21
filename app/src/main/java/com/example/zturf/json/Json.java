package com.example.zturf.json;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.zturf.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class Json extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        // Initialize Volley RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // URL of the JSON data
        String url = " https://jsonkeeper.com/b/YJHL";

        // Request a JSON response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<HashMap<String, String>> turfList = new ArrayList<>();
                            ListView listView = findViewById(R.id.turf_list_view);

                            // Parsing JSON data
                            JSONArray jsonArray = response.getJSONArray("turf_availability");

                            // Iterating through JSON array and adding turf availability to the list
                            for (int i = 0; i < jsonArray.length(); i++) {
                                HashMap<String, String> turf = new HashMap<>();
                                JSONObject obj = jsonArray.getJSONObject(i);
                                turf.put("name", obj.getString("name"));
                                turf.put("date", obj.getString("date"));
                                turf.put("time", obj.getString("time"));
                                turfList.add(turf);
                            }

                            // Creating adapter for listview
                            ListAdapter adapter = new SimpleAdapter(Json.this, turfList, R.layout.turf_list_item,
                                    new String[]{"name", "date", "time"},
                                    new int[]{R.id.turf_name, R.id.turf_date, R.id.turf_time});
                            listView.setAdapter(adapter);

                        } catch (JSONException ex) {
                            Log.e("JSON Parsing", "Exception", ex);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", "Error fetching JSON data: " + error.getMessage());
            }
        });

        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest);
    }
}
