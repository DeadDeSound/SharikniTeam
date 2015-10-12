package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nezarsaleh.shareknitest.Arafa.Activities.Route;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.VolleySingleton;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestRouteDataModel;
import com.example.nezarsaleh.shareknitest.Arafa.DataModelAdapter.ProfileRideAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DriverCreatedRides extends AppCompatActivity {

    String url = "http://sharekni-web.sdg.ae/_mobfiles/CLS_MobRoute.asmx/GetDriverDetailsByAccountId?AccountId=";

    String days;

    Toolbar toolbar;
    int Driver_ID;

    ListView user_ride_created;

    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_created_rides);
        user_ride_created = (ListView) findViewById(R.id.user_ride_created);
        initToolbar();
        myPrefs = this.getSharedPreferences("myPrefs", 0);
        String ID = myPrefs.getString("account_id",null);
//        Bundle in = getIntent().getExtras();
//        Log.d("Intent Id :", String.valueOf(in.getInt("DriverID")));
        Driver_ID = Integer.parseInt(ID);
        Log.d("Driverid1", String.valueOf(Driver_ID));

        new rideJson().execute();

    }


    private class rideJson extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            // Get a RequestQueue
            RequestQueue queue = VolleySingleton.getInstance(getBaseContext().getApplicationContext()).getRequestQueue();
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url + Driver_ID,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            response = response.replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
                            response = response.replaceAll("<string xmlns=\"http://tempuri.org/\">", "");
                            response = response.replaceAll("</string>", "");
                            // Display the first 500 characters of the response string.
                            String data = response.substring(40);
                            Log.d("url", url + Driver_ID);
                            try {
                                JSONArray jArray = new JSONArray(data);
                                final BestRouteDataModel[] driver = new BestRouteDataModel[jArray.length()];
                                JSONObject json;
                                for (int i = 0; i < jArray.length(); i++) {
                                    try {
                                        BestRouteDataModel item = new BestRouteDataModel(Parcel.obtain());
                                        days = "";
                                        json = jArray.getJSONObject(i);
                                        item.setID(json.getInt("ID"));
                                        item.setFromEm(json.getString("FromEmirateEnName"));
                                        item.setFromReg(json.getString("FromRegionEnName"));
                                        item.setToEm(json.getString("ToEmirateEnName"));
                                        item.setToReg(json.getString("ToRegionEnName"));
                                        item.setRouteName(json.getString("RouteEnName"));
                                        item.setStartFromTime(json.getString("StartFromTime"));
                                        item.setEndToTime_(json.getString("EndToTime_"));

                                        if (json.getString("Saturday").equals("true")) {
                                            days += "Sat , ";
                                        }
                                        if (json.getString("Sunday").equals("true")) {
                                            days += "Sun , ";

                                        }
                                        if (json.getString("Monday").equals("true")) {
                                            days += "Mon , ";

                                        }
                                        if (json.getString("Tuesday").equals("true")) {
                                            days += "Tue , ";
                                        }
                                        if (json.getString("Wednesday").equals("true")) {
                                            days += "Wed , ";
                                        }
                                        if (json.getString("Thursday").equals("true")) {
                                            days += "Thu , ";

                                        }
                                        if (json.getString("Friday").equals("true")) {
                                            days += "Fri ";
                                        }


                                        item.setDriver_profile_dayWeek(days);
                                        days = "";

                                        driver[i] = item;
                                        Log.d("ID", String.valueOf(json.getInt("ID")));
                                        Log.d("FromEmlv", json.getString("FromEmirateEnName"));
                                        Log.d("FromReglv", json.getString("FromRegionEnName"));
                                        Log.d("TomEmlv", json.getString("ToEmirateEnName"));
                                        Log.d("ToReglv", json.getString("ToRegionEnName"));

                                        ProfileRideAdapter arrayAdapter = new ProfileRideAdapter(DriverCreatedRides.this, R.layout.driver_created_rides_lits_item, driver);
                                        user_ride_created.setAdapter(arrayAdapter);
                                        user_ride_created.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Intent in = new Intent(DriverCreatedRides.this, Route.class);
                                                in.putExtra("RouteID", driver[i].getID());
                                                in.putExtra("DriverID", Driver_ID);
                                                DriverCreatedRides.this.startActivity(in);


                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error : ", error.toString());
                    //Ride.setText("That didn't work! : " + error.toString());
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
//      Add a request (in this example, called stringRequest) to your RequestQueue.
            VolleySingleton.getInstance(DriverCreatedRides.this).addToRequestQueue(stringRequest);
            return null;
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Created Rides");
//        toolbar.setElevation(10);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
