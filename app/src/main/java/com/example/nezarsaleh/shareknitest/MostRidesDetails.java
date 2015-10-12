package com.example.nezarsaleh.shareknitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestRouteDataModel;


public class MostRidesDetails extends AppCompatActivity {

    Toolbar toolbar;
    ListView lv;
    int FromEmirateId,ToEmirateId,FromRegionId,ToRegionId;

    BestRouteDataModel data;

    TextView txt_FromEm;
    TextView txt_FromReg;
    TextView txt_ToEm;
    TextView txt_ToReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_rides_details);
        lv = (ListView) findViewById(R.id.lv_most_rides_details);
        txt_FromEm= (TextView) findViewById(R.id.From_Emirate_best);
        txt_FromReg = (TextView) findViewById(R.id.From_Reg_best);
        txt_ToEm = (TextView) findViewById(R.id.To_Emirate_best);
        txt_ToReg = (TextView) findViewById(R.id.To_Reg_best);

        initToolbar();

        Bundle in = getIntent().getExtras();
        data= (BestRouteDataModel) in.getParcelableArrayList("Data");



        assert data != null;
        Log.d("Emirate name from",data.getFromEm());
        Log.d("Emirate name to",data.getToEm());
        Log.d("REg nameto ",data.getToReg());
        Log.d("REg From",data.getFromReg());

        try {
            assert data != null;
            FromEmirateId=data.getFromEmId();
            FromRegionId=data.getFromRegid();

            ToEmirateId=data.getToEmId();
            ToRegionId=data.getToRegId();

            Log.d("test most Em From: ",String.valueOf(FromEmirateId));
            Log.d("test most Reg From: ",String.valueOf(FromRegionId));
            Log.d("test most  To Emirate: ",String.valueOf(ToEmirateId));
            Log.d("test most : To Region ",String.valueOf(ToRegionId));


        }catch (NullPointerException e){
        Toast.makeText(MostRidesDetails.this, e.toString(), Toast.LENGTH_SHORT).show();
    }

        String url="http://sharekni-web.sdg.ae/_mobfiles/CLS_MobRoute.asmx/GetMostDesiredRideDetails?AccountID="+0+"&FromEmirateID="+FromEmirateId+"&FromRegionID="+FromRegionId+"&ToEmirateID="+ToEmirateId+"&ToRegionID="+ToRegionId;
        Log.wtf("url :",url);
        GetData j = new GetData();
        j.bestRouteStringRequestDetails(url,lv,this);

        txt_FromEm.setText(data.getFromEm());
        txt_ToEm.setText(data.getFromReg());
        txt_FromReg.setText(data.getToReg());
        txt_ToReg.setText(data.getToEm());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_most_rides_details, menu);
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



    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
