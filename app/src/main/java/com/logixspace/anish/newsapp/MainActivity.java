package com.logixspace.anish.newsapp;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    List<Model> Main_List;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = (SwipeRefreshLayout) findViewById(R.id.swipe);
        sp.setOnRefreshListener(this);


        Main_List = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int numberOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
//        Main_List.add(new Model(R.drawable.sachin,"sachin","Rating 5 out of 5"));
//        Main_List.add(new Model(R.drawable.dravid,"Dravid","Rating 4 out of 5"));
//        Main_List.add(new Model(R.drawable.sewag,"Sewag","Rating 3 out of 5"));
//        Main_List.add(new Model(R.drawable.sachin,"sachin","Rating 5 out of 5"));
//        Main_List.add(new Model(R.drawable.sachin,"sachin","Rating 5 out of 5"));

        CallApi();
    }

    private void CallApi() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=9b6ac262eea44bcbbf80ae1b064f631d",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String resfromserver = jsonObject.getString("status");
                            if (resfromserver.equals("ok")) {

                                if (sp.isRefreshing()) {
                                    sp.setRefreshing(false);
                                    Main_List.clear();
                                }
                                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String Name = object.getString("title").trim();
                                    String Age = object.getString("description").trim();
                                    String Urls = object.getString("urlToImage").trim();
                                    String Time = object.getString("publishedAt").trim();
                                    Model model = new Model(Urls, Name, Age, Time);
                                    Main_List.add(model);
                                }
                                adapter = new CustomAdapter(Main_List, getApplicationContext());
                                recyclerView.setAdapter(adapter);
                            } else {
                                Toast.makeText(getApplicationContext(), "Error " + resfromserver, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException ob) {
                            Toast.makeText(getApplicationContext(), "Exception  " + ob, Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onRefresh() {

        CallApi();


    }
}
