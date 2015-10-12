package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RideDetailsPassenger extends AppCompatActivity implements OnMapReadyCallback {


    TextView
            FromRegionEnName, ToRegionEnName, FromEmirateEnName, ToEmirateEnName, StartFromTime, EndToTime_, AgeRange, PreferredGender, IsSmoking, ride_details_day_of_week, NationalityEnName, PrefLanguageEnName;


    String Gender_ste, Nat_txt, Smokers_str;


    String str_StartFromTime, str_EndToTime_;

    String days;

    final JSONArray[] myJsonArray = new JSONArray[1];
    private Toolbar toolbar;


    int Route_ID;
    int Passenger_ID;
    int Driver_ID;
    Button Join_Ride_btn;
    ListView ride_details_passengers;

    private GoogleMap mMap;

    double StartLat, StartLng, EndLat, EndLng;

    Activity con;

    private List<DriverGetReviewDataModel> driverGetReviewDataModels_arr = new ArrayList<>();

    ListView Driver_get_Review_lv;
    int Pass_id;


    SharedPreferences myPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details_passenger);

        initToolbar();
        con = this;
        ride_details_passengers = (ListView) findViewById(R.id.ride_details_passengers);

        FromRegionEnName = (TextView) findViewById(R.id.FromRegionEnName);
        ToRegionEnName = (TextView) findViewById(R.id.ToRegionEnName);

        StartFromTime = (TextView) findViewById(R.id.StartFromTime);
        EndToTime_ = (TextView) findViewById(R.id.EndToTime_);

        FromEmirateEnName = (TextView) findViewById(R.id.FromEmirateEnName);
        ToEmirateEnName = (TextView) findViewById(R.id.ToEmirateEnName);

        NationalityEnName = (TextView) findViewById(R.id.NationalityEnName);
        PrefLanguageEnName = (TextView) findViewById(R.id.PrefLanguageEnName);

        AgeRange = (TextView) findViewById(R.id.AgeRange);
        PreferredGender = (TextView) findViewById(R.id.PreferredGender);
        IsSmoking = (TextView) findViewById(R.id.IsSmoking);
        Join_Ride_btn = (Button) findViewById(R.id.Join_Ride_btn);
        ride_details_day_of_week = (TextView) findViewById(R.id.ride_details_day_of_week);
        Driver_get_Review_lv = (ListView) findViewById(R.id.Driver_get_Review_lv);
        // setListViewHeightBasedOnChildren(Driver_get_Review_lv);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        myPrefs = this.getSharedPreferences("myPrefs", 0);
        Passenger_ID = Integer.parseInt(myPrefs.getString("account_id", "0"));

        Bundle in = getIntent().getExtras();
        Driver_ID = in.getInt("DriverID");
        Route_ID = in.getInt("RouteID");

        Log.d("Test Driver id", String.valueOf(Driver_ID));
        Log.d("test Route id", String.valueOf(Route_ID));
        Log.d("test Passenger id 2", String.valueOf(Passenger_ID));

        GetData GD = new GetData();
        try {
            days = "";
            JSONObject json = GD.GetRouteById(Route_ID);
            FromRegionEnName.setText(json.getString("FromRegionEnName"));
            ToRegionEnName.setText(json.getString("ToRegionEnName"));

            FromEmirateEnName.setText(json.getString("FromEmirateEnName"));
            ToEmirateEnName.setText(json.getString("ToEmirateEnName"));

            str_StartFromTime = json.getString("StartFromTime");
            str_EndToTime_ = json.getString("EndToTime_");

            str_StartFromTime = str_StartFromTime.substring(Math.max(0, str_StartFromTime.length() - 7));
            Log.d("string", str_StartFromTime);

            str_EndToTime_ = str_EndToTime_.substring(Math.max(0, str_EndToTime_.length() - 7));
            Log.d("time to", str_EndToTime_);

            StartFromTime.setText(str_StartFromTime);
            EndToTime_.setText(str_EndToTime_);
            Nat_txt = (json.getString("NationalityEnName"));
            if (Nat_txt.equals("null")) {
                Nat_txt = "Not Specified";
                NationalityEnName.setText(Nat_txt);
            } else {
                NationalityEnName.setText(Nat_txt);
            }
            PrefLanguageEnName.setText(json.getString("PrefLanguageEnName"));
            AgeRange.setText(json.getString("AgeRange"));
            Gender_ste = "";
            Gender_ste = json.getString("PreferredGender");
            switch (Gender_ste) {
                case "M":
                    Gender_ste = "Male";
                    break;
                case "F":
                    Gender_ste = "Female";
                    break;
                default:
                    Gender_ste = "Not Specified";
                    break;
            }
            PreferredGender.setText(Gender_ste);
            Smokers_str = "";
            Smokers_str = json.getString("IsSmoking");
            if (Smokers_str.equals("true")) {
                Smokers_str = "Yes";
            } else if (Smokers_str.equals("false")) {
                Smokers_str = "No";
            }
            IsSmoking.setText(Smokers_str);
            StartLat = json.getDouble("StartLat");
            StartLng = json.getDouble("StartLng");
            EndLat = json.getDouble("EndLat");
            EndLng = json.getDouble("EndLng");
            Log.d("S Lat", String.valueOf(StartLat));

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
            ride_details_day_of_week.setText(days);
            days = "";


        } catch (JSONException e) {
            e.printStackTrace();
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_ride_details);
        mapFragment.getMapAsync(this);

        Join_Ride_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData j = new GetData();
                if (Passenger_ID==0){
                    final Dialog dialog= new Dialog(RideDetailsPassenger.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.noroutesdialog);
                    Button btn= (Button) dialog.findViewById(R.id.noroute_id);
                    TextView Text_3= (TextView) dialog.findViewById(R.id.Text_3);
                    Text_3.setText("PLease Login First");
                    btn.setText("Log In");
                    dialog.show();

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intent = new Intent(RideDetailsPassenger.this, LoginApproved.class);
                            RideDetailsPassenger.this.startActivity(intent);

                        }
                    });
                }else {
                    try {
                        String response = j.Passenger_SendAlert(Driver_ID, Passenger_ID, Route_ID, "TestCase2");
                        if (response.equals("\"-1\"")  || response.equals("\"-2\'") ) {
                            Toast.makeText(RideDetailsPassenger.this, "Cannot Join This Route", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RideDetailsPassenger.this, "successfully  Joined", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("join ride res", String.valueOf(response));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        JSONArray response1 = null;
        try {
            response1 = new GetData().Driver_GetReview(Driver_ID, Route_ID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < response1.length(); i++) {
            try {
                JSONObject obj = response1.getJSONObject(i);
                final DriverGetReviewDataModel review = new DriverGetReviewDataModel(Parcel.obtain());

                review.setAccountName(obj.getString("AccountName"));
                review.setAccountNationalityEn(obj.getString("AccountNationalityEn"));
                review.setReview(obj.getString("Review"));
                driverGetReviewDataModels_arr.add(review);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        DriverGetReviewAdapter arrayAdapter = new DriverGetReviewAdapter(con, driverGetReviewDataModels_arr);
        Driver_get_Review_lv.setAdapter(arrayAdapter);
        setListViewHeightBasedOnChildren(Driver_get_Review_lv);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                (new LatLng(StartLat, EndLng), 8.1f));

        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(StartLat, StartLng))
                .add(new LatLng(EndLat, EndLng))
                .color(R.color.primaryColor)
                .width(6);  // North of the previous point, but at the same longitude
        // Closes the polyline.


// Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(rectOptions);

        final Marker markerZero = mMap.addMarker(new MarkerOptions().
                position(new LatLng(StartLat, StartLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.anchor)));

        final Marker markerZero2 = mMap.addMarker(new MarkerOptions().
                position(new LatLng(EndLat, EndLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.anchor)));


    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Ride Details");
//        toolbar.setElevation(10);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, RadioGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}
