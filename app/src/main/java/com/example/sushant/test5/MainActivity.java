package com.example.sushant.test5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;


import java.util.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mNews;
    private newsadapter nAdapter;
    RequestQueue requestQueue;
    String URL;

    List<news> udata= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Make call to AsyncTask
        //new jsonparse().execute();

        Log.d("alupoint","aksdjhfkasdjhf");

        requestQueue = Volley.newRequestQueue(this);
        URL="https://bqhcfadj.herokuapp.com/news";

        JsonObjectRequest getData = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {
               // Log.d("alupoint2","ya ni ayi pugiyo");
                try {
                 //   Log.d("this is the response",""+resp);
                    JSONArray json_data = resp.getJSONArray("news_stories");
                    for (int i = 0; i < json_data.length(); i++) {
                      //  Log.v("test","This is the data"+json_data);
                        news News = new news();
                        JSONObject temp = json_data.getJSONObject(i);
                      //  News.setTitle(json_data.getString(i));
                        News.setTitle(temp.getString("title"));
                        News.setShortdesc(temp.getString("abstract"));
                        News.setImage(temp.getString("image_url"));
                        //News.setShortdesc(json_data.getString("abstract"));
                        //News.setImage(json_data.getString("image"));
                        udata.add(News);
                    }
                    mNews = findViewById(R.id.newsList);
                    nAdapter = new newsadapter(MainActivity.this, udata);
                    mNews.setAdapter(nAdapter);
                    mNews.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(getData);
    }
}