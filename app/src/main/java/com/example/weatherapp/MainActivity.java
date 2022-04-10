package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
    Button btn_getWeatherByCityID,btn_getWeatherByName,btn_CityID;
    EditText et_dataInput;
    ListView lv_WeatherReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // assigning values to each control in the layout
        btn_CityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByCityID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_WeatherReports = findViewById(R.id.lv_weatherReports);

        // click listeners
        btn_getWeatherByCityID.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"You clicked me 1", Toast.LENGTH_SHORT);
                System.out.println("You clicked me 1");
            }
        });
        btn_CityID.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.metaweather.com/api/location/search/?query=" + et_dataInput.getText().toString();

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
                    /**
                     * Called when a response is received.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONArray response) {
                        //sout to generate System.out.println();
                        String cityID = "";
                        try {
                            JSONObject cityInfo = response.getJSONObject(0);
                            cityID = cityInfo.getString("woeid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("CityID = " + cityID);
                    }
                }, new Response.ErrorListener() {
                    /**
                     * Callback method that an error has been occurred with the provided error code and optional
                     * user-readable message.
                     *
                     * @param error
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Something went wrong with errorListener");
                    }
                });
                // Add the request to the RequestQueue.
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);

                Toast.makeText(MainActivity.this,"You clicked me 2", Toast.LENGTH_SHORT);
                System.out.println("You clicked me 2");
            }
        });
        btn_getWeatherByName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"You typed"+ et_dataInput.getText().toString(), Toast.LENGTH_SHORT);
                System.out.println("You typed "+ et_dataInput.getText().toString());
            }
        });
    }


}