package com.example.nezarsaleh.shareknitest.OnBoardDir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.example.nezarsaleh.shareknitest.HomePage;
import com.example.nezarsaleh.shareknitest.LoginApproved;
import com.example.nezarsaleh.shareknitest.R;
import com.example.nezarsaleh.shareknitest.RegisterNewTest;
import com.example.nezarsaleh.shareknitest.SearchOptions;
import com.example.nezarsaleh.shareknitest.Sharekni;
import com.example.nezarsaleh.shareknitest.SplashActivity;
import com.example.nezarsaleh.shareknitest.TestVedio;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by nezar on 8/11/2015.
 */
public class OnboardingActivity extends FragmentActivity {



    private ImageView btn_top_rides;
    private ImageView btn_search;

    private ImageView btn_log_in;
    private ImageView btn_register;

    private ViewPager pager;
    private SmartTabLayout indicator;

    static OnboardingActivity onboardingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onboardingActivity=this;

        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        String ID = myPrefs.getString("account_id",null);





        if (ID != null){
            Log.d("ID = :", ID);
            Intent in = new Intent(this, HomePage.class);
            startActivity(in);
        }

       setContentView(R.layout.activity_log_in_form_concept_one);
        pager = (ViewPager)findViewById(R.id.pager);
            indicator = (SmartTabLayout)findViewById(R.id.indicator);
        indicator.setVisibility(View.INVISIBLE);

        btn_register = (ImageView) findViewById(R.id.fr_register);
        btn_search = (ImageView) findViewById(R.id.fr_search);
        btn_top_rides = (ImageView) findViewById(R.id.fr_top_rides_id);
        btn_log_in = (ImageView) findViewById(R.id.fr_login);




        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),RegisterNewTest.class);
                startActivity(intent);
            }
        });




        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getBaseContext(), LoginApproved.class);
                startActivity(intent);

            }
        });


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchOptions.class);
                startActivity(intent);
            }
        });



        btn_top_rides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0 : return new OnboardingFragment1();
                    case 1 : return new OnboardingFragment2();
                    default: return null;
                }


            }

            @Override
            public int getCount() {

            return 2;
            }
        };


        pager.setAdapter(adapter);
        indicator.setViewPager(pager);


        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                if (position == 2) {
//                    skip.setVisibility(View.GONE);
//                    next.setText("Done");
//                } else {
//                    skip.setVisibility(View.VISIBLE);
//                    next.setText("Next");
//
//
//                }

            }


        });



        

    }//oncreate

    public void finishOnBoarding() {
        // Get the shared preferences
        SharedPreferences preferences = getSharedPreferences("my_onboard_preferences", MODE_PRIVATE);

        // Set onboarding_complete to true
        preferences.edit()
                .putBoolean("onboarding_complete",true).apply();

        // Launch the main Activity, called MainActivity
        Intent main = new Intent(this, Sharekni.class);
        startActivity(main);

        // Close the OnboardingActivity
        finish();


    }


    public static OnboardingActivity getInstance(){
        return  onboardingActivity ;
    }


}
