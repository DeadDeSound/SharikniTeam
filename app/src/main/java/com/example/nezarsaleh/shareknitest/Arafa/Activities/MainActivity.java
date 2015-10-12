package com.example.nezarsaleh.shareknitest.Arafa.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.nezarsaleh.shareknitest.R;


public class MainActivity extends TabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabHost mTabHost = getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("Drivers").setIndicator("Best Drivers").setContent(new Intent(this, BestDriversBeforeLogin.class)));
        mTabHost.addTab(mTabHost.newTabSpec("Route").setIndicator("Best Routes").setContent(new Intent(this, BestRideBeforeLogin.class)));
        mTabHost.setCurrentTab(0);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
