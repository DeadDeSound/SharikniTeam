package com.example.nezarsaleh.shareknitest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nezarsaleh.shareknitest.Taps.SlidingTabLayout;


public class ViewProfileActivity extends AppCompatActivity {

    TabLayout tabLayout;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private  MyAdapterPager mAdapter;


    FloatingActionButton fabBtn;
    // FrameLayout rootLayout;
    CoordinatorLayout rootLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    NavigationView navigation;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_design_approved);
//        initToolbar();
//        initInstances();


    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_view_profile);
        setSupportActionBar(toolbar);
    }

    private void initInstances() {


        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MyAdapterPager(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setViewPager(mPager);

        mPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_view_profile);
        drawerToggle = new ActionBarDrawerToggle(ViewProfileActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout_view_profile);
        collapsingToolbarLayout.setTitle("Nezar Saleh Hasan");
        navigation = (NavigationView) findViewById(R.id.navigation_view_profile);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        Toast.makeText(ViewProfileActivity.this, "item " + id, Toast.LENGTH_SHORT).show();
                    case R.id.navItem2:
                        Toast.makeText(ViewProfileActivity.this, "item" + id, Toast.LENGTH_SHORT).show();
                    case R.id.navItem3:
                        Toast.makeText(ViewProfileActivity.this, "item" + id, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_plus));


        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout_view_profile);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn_view_profile);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(rootLayout, "Hello. I am Snackbar!", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();

            }
        });


    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

        class  MyAdapterPager extends FragmentPagerAdapter {

            String [] tabs;
            FragmentManager fragmentManager;
            public MyAdapterPager(FragmentManager fm) {
                super(fm);

                tabs = getResources().getStringArray(R.array.tabs);
                fragmentManager = fm;

            }

            @Override
            public Fragment getItem(int position) {
                MyFragment  myFragment = MyFragment.getInstance(position);


                return myFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                    return tabs[position];


            }

            @Override
            public int getCount() {
                return 3;
            }
        } // MyAdapter Fragment


    public static class MyFragment extends Fragment {

        TextView textview;

      public static MyFragment getInstance(int position){
          MyFragment myFragment = new MyFragment();
          Bundle args =  new Bundle();
          args.putInt("position",position);
          myFragment.setArguments(args);
          return myFragment;
      }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View layout=inflater.inflate(R.layout.view_profile_tab_my_fragment,container,false);
            textview = (TextView) layout.findViewById(R.id.position);
            Bundle bundle = getArguments();
            if (bundle!=null){
                textview.setText("The Page Selected is"+bundle.getInt("position"));
            }
            return layout;
        }
    }

}
