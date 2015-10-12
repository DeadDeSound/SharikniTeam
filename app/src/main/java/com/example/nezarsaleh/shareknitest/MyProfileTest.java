package com.example.nezarsaleh.shareknitest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.nezarsaleh.shareknitest.Arafa.Activities.Route;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.ImageDecoder;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.VolleySingleton;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestRouteDataModel;
import com.example.nezarsaleh.shareknitest.Arafa.DataModelAdapter.BestRouteDataModelAdapter;
import com.pkmmte.view.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyProfileTest extends AppCompatActivity {

    TextView TopName,NationalityEnName,FullName,EmployerEnName,AccountTypeEnName,Mobile,GenderEn,PrefLanguageEnName;
    TextView Ride;
    ListView lv_driver;
    CircularImageView circularImageView;
    String url = "http://sharekni-web.sdg.ae/_mobfiles/CLS_MobRoute.asmx/GetDriverDetailsByAccountId?AccountId=";
    SharedPreferences myPrefs;
    private Toolbar toolbar;

    int Passenger_ID;
    int Driver_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (LoginApproved.getInstance() != null ){
                LoginApproved.getInstance().finish();
            }else{

            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        setContentView(R.layout.activity_my_profile_tet);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        lv_driver= (ListView) findViewById(R.id.driver_rides_afterlogin);
        Ride = (TextView) findViewById(R.id.lv_test);
        TopName = (TextView) findViewById(R.id.TopName);
        NationalityEnName = (TextView) findViewById(R.id.NationalityEnName);
        FullName = (TextView) findViewById(R.id.FullName);
        EmployerEnName = (TextView) findViewById(R.id.EmployerEnName);
        AccountTypeEnName = (TextView) findViewById(R.id.AccountTypeEnName);
        Mobile = (TextView) findViewById(R.id.Mobile);
        GenderEn = (TextView) findViewById(R.id.GenderEn);
        PrefLanguageEnName = (TextView) findViewById(R.id.PrefLanguageEnName);
        circularImageView = (CircularImageView)findViewById(R.id.profilephoto);
        circularImageView.setBorderWidth(5);
        circularImageView.setSelectorStrokeWidth(5);
        circularImageView.addShadow();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        GetData j = new GetData();
        new rideJson().execute();
        myPrefs = this.getSharedPreferences("myPrefs", 0);
        Driver_ID = Integer.parseInt(myPrefs.getString("account_id", null));

        try {
            JSONObject json = j.GetDriverById(Driver_ID);
            if(json.getString("EmployerEnName")== "null"){
                EmployerEnName.setText("Not Employee");
            }else{
                EmployerEnName.setText(json.getString("EmployerEnName"));
            }
            if(json.getString("Mobile")== "null"){
                Mobile.setText("No Mobile Available");
            }else{
                Mobile.setText(json.getString("Mobile"));
            }
            TopName.setText(json.getString("FirstName")+" "+json.getString("MiddleName"));
            FullName.setText(json.getString("FirstName")+" "+json.getString("MiddleName")+" "+json.getString("LastName"));
            NationalityEnName.setText(json.getString("NationalityEnName"));
            AccountTypeEnName.setText(json.getString("AccountTypeEnName"));
            GenderEn.setText(json.getString("GenderEn"));
            ImageDecoder im = new ImageDecoder();
            im.stringRequest(json.getString("PhotoPath"),circularImageView,this);
            PrefLanguageEnName.setText(json.getString("PrefLanguageEnName"));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    private class rideJson extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            // Get a RequestQueue
            RequestQueue queue = VolleySingleton.getInstance(getBaseContext().getApplicationContext()).getRequestQueue();
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url+Driver_ID,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            response = response.replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>","");
                            response = response.replaceAll("<string xmlns=\"http://tempuri.org/\">", "");
                            response = response.replaceAll("</string>", "");
                            // Display the first 500 characters of the response string.
                            String data = response.substring(40);
                            try {
                                JSONArray jArray=new JSONArray(data);
                                final BestRouteDataModel[] driver =new BestRouteDataModel[jArray.length()];
                                JSONObject json;
                                for (int i=0; i<jArray.length(); i++) {
                                    try{
                                        final BestRouteDataModel item = new BestRouteDataModel(Parcel.obtain());
                                        json = jArray.getJSONObject(i);
                                        item.setID(json.getInt("ID"));
                                        item.setFromEm(json.getString("FromEmirateEnName"));
                                        item.setFromReg(json.getString("FromRegionEnName"));
                                        item.setToEm(json.getString("ToEmirateEnName"));
                                        item.setToReg(json.getString("ToRegionEnName"));
                                        driver[i] = item;
                                        BestRouteDataModelAdapter arrayAdapter = new BestRouteDataModelAdapter(MyProfileTest.this, R.layout.top_rides_custom_row, driver);
                                        lv_driver.setAdapter(arrayAdapter);
                                        lv_driver.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Intent in = new Intent(MyProfileTest.this, Route.class);
                                                in.putExtra("RouteID", item.getID());
                                                in.putExtra("PassengerID",Passenger_ID);
                                                in.putExtra("DriverID",Driver_ID);
                                                Bundle b = new Bundle();
                                                b.putParcelable("Data", item);
                                                in.putExtras(b);
                                                MyProfileTest.this.startActivity(in);
                                            }
                                        });
                                    } catch ( JSONException e) {
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
                    Log.d("Error : ",error.toString());
                    Ride.setText("That didn't work! : " + error.toString());
                }
            });
            // Add the request to the RequestQueue.
            VolleySingleton.getInstance(MyProfileTest.this).addToRequestQueue(stringRequest);
            return null;
        }
    }

    public Bitmap stringRequest(String url, final ImageView im, final Context context) {
        String url1 = "http://www.sharekni-web.sdg.ae/_mobfiles/CLS_MobAccount.asmx/GetPhotoPath?s_FileName=";
        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(context.getApplicationContext()).getRequestQueue();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1 + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.replaceAll("<base64Binary xmlns=\"http://tempuri.org/\">", "");
                        response = response.replaceAll("</base64Binary>", "");
                        // Display the first 500 characters of the response string.
                        try {
                            String data = response.substring(40);
                            //decodeBase64(data);
                            byte[] decodedByte = Base64.decode(data, Base64.DEFAULT);
                            Bitmap decoded = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                            im.setImageBitmap(decoded);
                            try {
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error : ", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //Add a request (in this example, called stringRequest) to your RequestQueue.
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        return null;
    }
}