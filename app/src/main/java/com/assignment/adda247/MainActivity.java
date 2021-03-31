package com.assignment.adda247;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "https://gorest.co.in/public-api/users";

    //listview object
    ListView listView;

    //the data list where we will store all the data objects after parsing json
    List<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing listview and data list
        listView = (ListView) findViewById(R.id.listView);
        dataList = new ArrayList<>();

        //this method will fetch and parse the data
        loadDataList();
    }

    private void loadDataList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named data inside the object
                            //so here we are getting that json array
                            JSONArray dataArray = obj.getJSONArray("data");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < dataArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject dataObject = dataArray.getJSONObject(i);

                                //creating a data object and giving them the values from json object
                                Data data = new Data(dataObject.getString("name"), dataObject.getString("email"), dataObject.getString("gender"), dataObject.getString("status"));

                                //adding the data to datalist
                                dataList.add(data);
                            }

                            //creating custom adapter object
                            adapter adapter = new adapter(dataList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}