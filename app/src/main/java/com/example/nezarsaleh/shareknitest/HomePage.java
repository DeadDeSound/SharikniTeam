package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.Activities.BestDriversBeforeLogin;
import com.example.nezarsaleh.shareknitest.Arafa.Activities.Profile;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.ImageDecoder;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestDriverDataModel;
import com.example.nezarsaleh.shareknitest.MainNavigationDrawerFragment.NavigationDrawerFragment;
import com.example.nezarsaleh.shareknitest.OnBoardDir.OnboardingActivity;
import com.pkmmte.view.CircularImageView;

import org.json.JSONException;
import org.json.JSONObject;


public class HomePage extends ActionBarActivity implements View.OnClickListener {


    String AccountType;
    CircularImageView circularImageView;
    TextView name, nat, Lnag_home;
    SharedPreferences myPrefs;
    RelativeLayout btn_create;
    int Driver_ID;
    RelativeLayout Relative_quickSearch;

    TextView VehiclesCount, PassengerJoinedRidesCount, DriverMyRidesCount, DriverMyAlertsCount;

    RelativeLayout Home_Relative_Notify;


    String DriverMyRidesCount_str, PassengerJoinedRidesCount_str, VehiclesCount_str;

    Thread t;

    Toolbar toolbar;
    RelativeLayout Home_Relative_Permit, Home_Realtive_Vehicles, driver_rides_Created;


    String name_str,nat_str;

    String Firstname,LastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (LoginApproved.getInstance() != null) {
                LoginApproved.getInstance().finish();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (LoginApproved.pDialog != null) {
            LoginApproved.pDialog.dismiss();
            LoginApproved.pDialog = null;
        }


        try {
            if (RegisterNewTest.getInstance() != null) {
                RegisterNewTest.getInstance().finish();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();


        }
        try {
            if (OnboardingActivity.getInstance() != null) {
                OnboardingActivity.getInstance().finish();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();


        }

        setContentView(R.layout.home_page_approved);
        initToolbar();

        name = (TextView) findViewById(R.id.tv_name_home);
        nat = (TextView) findViewById(R.id.nat_home);
        Lnag_home = (TextView) findViewById(R.id.lang_Home);
        btn_create = (RelativeLayout) findViewById(R.id.btn_createCarPool);
        Home_Relative_Permit = (RelativeLayout) findViewById(R.id.Home_Relative_Permit);
        Home_Realtive_Vehicles = (RelativeLayout) findViewById(R.id.Home_Realtive_Vehicles);
        driver_rides_Created = (RelativeLayout) findViewById(R.id.driver_rides_Created);

        btn_create.setOnClickListener(this);

        VehiclesCount = (TextView) findViewById(R.id.VehiclesCount);
        PassengerJoinedRidesCount = (TextView) findViewById(R.id.PassengerJoinedRidesCount);
        DriverMyRidesCount = (TextView) findViewById(R.id.DriverMyRidesCount);
        DriverMyAlertsCount = (TextView) findViewById(R.id.DriverMyAlertsCount);
        Relative_quickSearch = (RelativeLayout) findViewById(R.id.Relative_quickSearch);

        Home_Relative_Notify = (RelativeLayout) findViewById(R.id.Home_Relative_Notify);

        driver_rides_Created = (RelativeLayout) findViewById(R.id.driver_rides_Created);

        circularImageView = (CircularImageView) findViewById(R.id.profilepic);
        circularImageView.setBorderWidth(5);
        circularImageView.setSelectorStrokeWidth(5);
        circularImageView.addShadow();

        myPrefs = this.getSharedPreferences("myPrefs", 0);
        final String ID = myPrefs.getString("account_id", null);
        AccountType = myPrefs.getString("account_type", null);
        Log.d("pass is",ID);
        Driver_ID = Integer.parseInt(ID);

        final GetData j = new GetData();

        new loading().execute();

        Home_Relative_Notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), DriverAlertsForRequest.class);
                startActivity(intent);

            }
        });


        Relative_quickSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), SearchOptions.class);
                intent.putExtra("PassengerId",ID);
                startActivity(intent);

            }
        });


        driver_rides_Created.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), DriverCreatedRides.class);
                intent.putExtra("DriverID", Driver_ID);
                startActivity(intent);


            }
        });

        t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject jsonArray = j.GetDriverById(Integer.parseInt(ID));
                                    VehiclesCount_str="";
                                    VehiclesCount_str += "(";
                                    VehiclesCount_str += (jsonArray.getString("VehiclesCount"));
                                    VehiclesCount_str += ")";
                                    VehiclesCount.setText(VehiclesCount_str);
                                    PassengerJoinedRidesCount_str="";
                                    PassengerJoinedRidesCount_str += "(";
                                    PassengerJoinedRidesCount_str += (jsonArray.getString("PassengerJoinedRidesCount"));
                                    PassengerJoinedRidesCount_str += ")";
                                    PassengerJoinedRidesCount.setText(PassengerJoinedRidesCount_str);
                                    DriverMyRidesCount_str="";
                                    DriverMyRidesCount_str += "(";
                                    DriverMyRidesCount_str += (jsonArray.getString("DriverMyRidesCount"));
                                    DriverMyRidesCount_str += ")";
                                    DriverMyRidesCount.setText(DriverMyRidesCount_str);
                                    DriverMyAlertsCount.setText(jsonArray.getString("DriverMyAlertsCount"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }  // on create

    private class loading extends AsyncTask{
        JSONObject jsonArray;

        @Override
        protected void onPostExecute(Object o) {
            try {
                name_str="";
                nat_str="";
                Firstname="";
                LastName="";
                Firstname=(jsonArray.getString("FirstName"));
                Firstname=  Firstname.substring(0, 1).toUpperCase() + Firstname.substring(1);
                LastName= (jsonArray.getString("LastName"));
                LastName=  LastName.substring(0, 1).toUpperCase() + LastName.substring(1);
                name_str=Firstname+" "+LastName;
                //  name_str = (jsonArray.getString("FirstName") + " " + jsonArray.getString("LastName"));
                nat_str = (jsonArray.getString("NationalityEnName"));
                //   name_str=  name_str.substring(0, 1).toUpperCase() + name_str.substring(1);
                nat_str= nat_str.substring(0, 1).toUpperCase() + nat_str.substring(1);
                name.setText(name_str);
                nat.setText(nat_str);
                //   str.substring(0, 1).toUpperCase() + str.substring(1);
                VehiclesCount_str="";
                VehiclesCount_str += "(";
                VehiclesCount_str += (jsonArray.getString("VehiclesCount"));
                VehiclesCount_str += ")";
                VehiclesCount.setText(VehiclesCount_str);
                PassengerJoinedRidesCount_str="";
                PassengerJoinedRidesCount_str += "(";
                PassengerJoinedRidesCount_str += (jsonArray.getString("PassengerJoinedRidesCount"));
                PassengerJoinedRidesCount_str += ")";
                PassengerJoinedRidesCount.setText(PassengerJoinedRidesCount_str);
                DriverMyRidesCount_str="";
                DriverMyRidesCount_str += "(";
                DriverMyRidesCount_str += (jsonArray.getString("DriverMyRidesCount"));
                DriverMyRidesCount_str += ")";
                DriverMyRidesCount.setText(DriverMyRidesCount_str);
                DriverMyAlertsCount.setText(jsonArray.getString("DriverMyAlertsCount"));
                assert AccountType != null;
                if (!AccountType.equals("D")) {
                    btn_create.setBackgroundColor(Color.LTGRAY);
                    Home_Relative_Permit.setBackgroundColor(Color.LTGRAY);
                    Home_Realtive_Vehicles.setVisibility(View.INVISIBLE);
                    driver_rides_Created.setVisibility(View.INVISIBLE);
                }
                if (jsonArray.getString("GenderEn").equals("Female")) {
                    circularImageView.setImageResource(R.drawable.defaultdriverfemale);
                }
                ImageDecoder im = new ImageDecoder();
                im.stringRequest(jsonArray.getString("PhotoPath"), circularImageView, HomePage.this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            final GetData j = new GetData();
            try {
                jsonArray = j.GetDriverById(Integer.parseInt(String.valueOf(Driver_ID)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_navigate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement





//
//        if (id == R.id.change_Pass_menu) {
//            Intent intent = new Intent(this, ChangePasswordTest.class);
//            startActivity(intent);
//        }
//
//        if (id == R.id.EditProfile_Pass_menu) {
//
//            Intent intent = new Intent(this, EditProfileTest.class);
//            startActivity(intent);
//
//        }





        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (AccountType.equals("D")) {
            if (v == btn_create) {
                Intent intent = new Intent(getBaseContext(), DriverCreateCarPool.class);
                intent.putExtra("ID", Driver_ID);
                startActivity(intent);
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Home Page");
//        toolbar.setElevation(10);

        setSupportActionBar(toolbar);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawwer);

        drawerFragment.setUp(R.id.fragment_navigation_drawwer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

    }

}
