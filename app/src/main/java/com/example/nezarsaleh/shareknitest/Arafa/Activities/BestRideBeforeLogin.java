package com.example.nezarsaleh.shareknitest.Arafa.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestRouteDataModel;
import com.example.nezarsaleh.shareknitest.Arafa.DataModelAdapter.BestRouteDataModelAdapter;
import com.example.nezarsaleh.shareknitest.MostRidesDetails;
import com.example.nezarsaleh.shareknitest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BestRideBeforeLogin extends AppCompatActivity {


    ListView lv;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rides);
        lv = (ListView) findViewById(R.id.lv_top_rides);
        initToolbar();

//        GetData j = new GetData();
//        j.GetBestRoutes(lv, this);

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading" + "...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        new jsoning(lv,pDialog, this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    public class jsoning extends AsyncTask {

        ListView lv;
        Activity con;
        private ProgressDialog pDialog;
        BestRouteDataModel[] driver;

        public jsoning(final ListView lv, ProgressDialog pDialog, final Activity con) {

            this.lv = lv;
            this.con = con;
            this.pDialog = pDialog;
        }

        @Override
        protected void onPostExecute(Object o) {
            BestRouteDataModelAdapter arrayAdapter = new BestRouteDataModelAdapter(con, R.layout.top_rides_custom_row, driver);
            lv.setAdapter(arrayAdapter);
            lv.requestLayout();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in = new Intent(con, MostRidesDetails.class);
                    in.putExtra("ID", i);
                    Bundle b = new Bundle();
                    b.putParcelable("Data", driver[i]);
                    in.putExtras(b);
                    con.startActivity(in);
                }
            });
            hidePDialog();
        }

        private void hidePDialog() {
            if (pDialog != null) {
                pDialog.dismiss();
                pDialog = null;
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {

            JSONArray response = null;
            try {
                response = new GetData().MostDesiredRoutes();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            assert response != null;
            driver = new BestRouteDataModel[response.length()];

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject json = response.getJSONObject(i);
                    final BestRouteDataModel item = new BestRouteDataModel(Parcel.obtain());
                    item.setFromEm(json.getString("FromEmirateNameEn"));
                    item.setFromReg(json.getString("FromRegionNameEn"));
                    item.setToEm(json.getString("ToEmirateNameEn"));
                    item.setToReg(json.getString("ToRegionNameEn"));
                    item.setFromEmId(json.getInt("FromEmirateId"));
                    item.setFromRegid(json.getInt("FromRegionId"));
                    item.setToEmId(json.getInt("ToEmirateId"));
                    item.setToRegId(json.getInt("ToRegionId"));
                    item.setRouteName(String.valueOf(new GetData().GetDesiredCount(json.getInt("FromEmirateId"), json.getInt("FromRegionId"), json.getInt("ToEmirateId"), json.getInt("ToRegionId"))));
//                    arr.add(item);
                    driver[i] = item;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //hidePDialog();
            }
            return null;
        }
    }






    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Most Rides");
        //   toolbar.setElevation(10);


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
