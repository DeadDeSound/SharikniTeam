package com.example.nezarsaleh.shareknitest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nezarsaleh.shareknitest.Arafa.Activities.BestDriversBeforeLogin;
import com.example.nezarsaleh.shareknitest.Arafa.Activities.BestRideBeforeLogin;
import com.example.nezarsaleh.shareknitest.MainNavigationDrawerFragment.NavigationDrawerFragment;
import com.example.nezarsaleh.shareknitest.OnBoardDir.OnboardingActivity;


public class Sharekni extends ActionBarActivity {
    private Toolbar toolbar;
    Button TopRIDebutton;
    Button TopDRiversBtn;
    Button MapLookUp;
    Button btnHomePage;
    Button LogINBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

               SharedPreferences preferences =
                getSharedPreferences("my_onboard_preferences", MODE_PRIVATE);

        

        if(!preferences.getBoolean("onboarding_complete",false)){

            Intent onboarding = new Intent(this, OnboardingActivity.class);
            startActivity(onboarding);

            finish();
            return;
        }


        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        TopRIDebutton = (Button) findViewById(R.id.TopRideBtn);
        TopDRiversBtn = (Button) findViewById(R.id.TopDriversBtn);
        MapLookUp = (Button) findViewById(R.id.MapLOOkUP);
        LogINBtn = (Button) findViewById(R.id.LogInForm);
        btnHomePage = (Button) findViewById(R.id.homepagetest);


        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);


            }
        });

        LogINBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginApproved.class);
                startActivity(intent);
            }
        });


        MapLookUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//                startActivity(intent);

            }
        });
        TopDRiversBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), BestDriversBeforeLogin.class);
                startActivity(intent2);
            }
        });
        TopRIDebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BestRideBeforeLogin.class);
                startActivity(intent);
            }
        });



        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowHomeEnabled(true);


        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawwer);

        drawerFragment.setUp(R.id.fragment_navigation_drawwer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

            Intent intent= new Intent(getBaseContext(), WelcomePage.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }
}
