package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;


public class QuickSearchResults extends AppCompatActivity {


    int From_Em_Id;
    int From_Reg_Id;
    int To_Em_Id;
    int To_Reg_Id;
    String To_EmirateEnName,From_EmirateEnName,To_RegionEnName,From_RegionEnName;

    TextView To_EmirateEnName_txt;
    TextView From_EmirateEnName_txt;
    TextView  To_RegionEnName_txt  ;
    TextView From_RegionEnName_txt;

    ListView lvResult;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_search_results);
        lvResult= (ListView) findViewById(R.id.lv_searchResult);

        initToolbar();
        Intent intent = getIntent();
        From_Em_Id= intent.getIntExtra("From_Em_Id",0);
        From_Reg_Id=intent.getIntExtra("From_Reg_Id",0);
        To_Em_Id=intent.getIntExtra("To_Em_Id",0);
        To_Reg_Id=intent.getIntExtra("To_Reg_Id",0);



        To_EmirateEnName=intent.getStringExtra("To_EmirateEnName");
        From_EmirateEnName=intent.getStringExtra("From_EmirateEnName");
        To_RegionEnName=intent.getStringExtra("To_RegionEnName");
        From_RegionEnName=intent.getStringExtra("From_RegionEnName");



        From_EmirateEnName_txt= (TextView) findViewById(R.id.quick_search_em_from);
        From_RegionEnName_txt= (TextView) findViewById(R.id.quick_search_reg_from);
        To_EmirateEnName_txt= (TextView) findViewById(R.id.quick_search_em_to);
        To_RegionEnName_txt= (TextView) findViewById(R.id.quick_search_reg_to);

        From_EmirateEnName_txt.setText(From_EmirateEnName);
        To_EmirateEnName_txt.setText(To_EmirateEnName);
        From_RegionEnName_txt.setText(From_RegionEnName);
        To_RegionEnName_txt.setText(To_RegionEnName);





        char gender='N';
        String Time="";
        int FromEmId=2;
        int FromRegId=4;
        int ToEmId=4;
        int ToRegId=20;
        int pref_lnag=0;
        int pref_nat=0;
        int Age_Ranged_id=0;
        String StartDate="";
        int saveFind=0;



        GetData j = new GetData();
        j.QuickSearchForm(0,gender,Time,From_Em_Id
                ,From_Reg_Id,To_Em_Id,To_Reg_Id,pref_lnag,pref_nat
                ,Age_Ranged_id,StartDate,saveFind
                ,lvResult,this);
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_quick_search_results, menu);
//        return true;
//    }


//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }




    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Search Results");
//        toolbar.setElevation(10);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
