package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.Activities.BestRideBeforeLogin;

public class SearchOptions extends AppCompatActivity {


    String MyID;
    RelativeLayout im_lookup;
    RelativeLayout im_quickSearch;
    RelativeLayout im_advancedSearch;
    RelativeLayout search_top_rides;


    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);

        initToolbar();
        im_lookup = (RelativeLayout) findViewById(R.id.map_look_up);
        im_quickSearch = (RelativeLayout) findViewById(R.id.quick_search);
        im_advancedSearch= (RelativeLayout) findViewById(R.id.advanced_search);
        search_top_rides = (RelativeLayout) findViewById(R.id.search_top_rides_im);

        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        MyID =  myPrefs.getString("account_id", null);


        Log.d("test pass 2","test");

        search_top_rides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =  new Intent(getBaseContext(), BestRideBeforeLogin.class);
                startActivity(intent1);
            }
        });

        im_lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);

            }
        });

        im_quickSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =  new Intent(getBaseContext(),QSearch.class);
                intent1.putExtra("ID",MyID);
                startActivity(intent1);
                Log.d("test search id 2 ", String.valueOf(MyID));
            }
        });


        im_advancedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Advanced_Search.class);
                intent.putExtra("ID",MyID);
                startActivity(intent);
                Log.d("id advanced ", String.valueOf(MyID));

            }
        });

    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Search Options");
      //  toolbar.setElevation(10);

        setSupportActionBar(toolbar);
//        TextView mytext = (TextView) toolbar.findViewById(R.id.mytext_appbar);
//        mytext.setText("Most Rides");


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
