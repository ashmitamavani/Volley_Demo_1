package com.example.volley_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

public class MainActivity extends AppCompatActivity {

    JSONArray jsonArray;
    ArrayList<DataModel> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/users";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: " + response.substring(0,500));

                        JSONObject mainObj = null;

                        try {
                            jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                               mainObj=jsonArray.getJSONObject(i);
                               int id=mainObj.getInt("id");
                               String name=mainObj.getString("name");
                               String username=mainObj.getString("username");
                               String email=mainObj.getString("email");
                               JSONObject address=mainObj.getJSONObject("address");
                               String city=address.getString("city");
                               DataModel dataModel=new DataModel(id,name,username,email,city);
                               list.add(dataModel);
                            }
                        } catch (JSONException e)
                        {
                            throw new RuntimeException(e);
                        }

                        Log.d("TTT", "onResponse: Object="+list.get(0));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // textView.setText("That didn't work!");
                Log.e("TTT", "onErrorResponse: Error=",error );
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}