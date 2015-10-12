package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.AppController;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.CircularNetworkImageView;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DriverRequestDetails extends AppCompatActivity {

    List<DriverAlertsForRequestDataModel> arr = new ArrayList<>();
    private Toolbar toolbar;
    String PassengerName;
    String RouteName, NationalityEnName, AccountPhoto, PassengerMobile, Remarks, RequestDate;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    int RequestId;
    String URL = "http://www.sharekni-web.sdg.ae/uploads/personalphoto/";
    TextView RouteName_txt, NationalityEnName_txt, AccountPhoto_txt, PassengerMobile_txt, Remarks_txt, RequestDate_txt, PassengerName_txt;

    Button Alert_Decline,Alert_Accept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_request_details);
        initToolbar();
        Intent intent = getIntent();
        PassengerName = intent.getStringExtra("PassengerName");
        RouteName = intent.getStringExtra("RouteName");
        NationalityEnName = intent.getStringExtra("NationalityEnName");
        AccountPhoto = intent.getStringExtra("AccountPhoto");
        PassengerMobile = intent.getStringExtra("PassengerMobile");
        Remarks = intent.getStringExtra("Remarks");
        RequestDate = intent.getStringExtra("RequestDate");
        RequestId = intent.getIntExtra("RequestId", 0);

        Log.d("RouteName", RouteName);
        Log.d("PassengerName", PassengerName);


        RouteName_txt = (TextView) findViewById(R.id.RouteName);
        PassengerName_txt = (TextView) findViewById(R.id.PassengerName);
        NationalityEnName_txt = (TextView) findViewById(R.id.NationalityEnName);
        PassengerMobile_txt = (TextView) findViewById(R.id.PassengerMobile);
        Remarks_txt = (TextView) findViewById(R.id.Remarks);
        RequestDate_txt = (TextView) findViewById(R.id.RequestDate);
        Alert_Decline= (Button) findViewById(R.id.Alert_Decline);
        Alert_Accept= (Button) findViewById(R.id.Alert_Accept);


        RouteName_txt.setText(RouteName);
        PassengerName_txt.setText(PassengerName);
        NationalityEnName_txt.setText(NationalityEnName);
        PassengerMobile_txt.setText(PassengerMobile);
        Remarks_txt.setText(Remarks);
        RequestDate_txt.setText(RequestDate);

        if (imageLoader == null) imageLoader = AppController.getInstance().getImageLoader();
        CircularNetworkImageView Photo = (CircularNetworkImageView) findViewById(R.id.AccountPhoto);
        Photo.setImageUrl(URL + AccountPhoto, imageLoader);




        Alert_Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetData j = new GetData();
                try {
                    j.DriverAcceptPassenger(RequestId,1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                finish();
            }
        });


        Alert_Decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData j =new GetData();
                try {
                    j.DriverAcceptPassenger(RequestId,0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                finish();
            }
        });

    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Most Rides");
        //   toolbar.setElevation(10);


        setSupportActionBar(toolbar);
//        TextView mytext = (TextView) toolbar.findViewById(R.id.mytext_appbar);
//        mytext.setText("Most Rides");


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}

