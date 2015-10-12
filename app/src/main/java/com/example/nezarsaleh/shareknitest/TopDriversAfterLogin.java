package com.example.nezarsaleh.shareknitest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.Activities.Profile;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestDriverDataModel;
import com.example.nezarsaleh.shareknitest.Arafa.DataModelAdapter.BestDriverDataModelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopDriversAfterLogin extends AppCompatActivity {

    TextView tv;
    ListView lv;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_drivers_after_login);
        tv = (TextView) findViewById(R.id.info);
        lv = (ListView) findViewById(R.id.lvMain2);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading" + "...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        new jsoning(lv, pDialog, this).execute();
    }

    public class jsoning extends AsyncTask {

        ListView lv;
        Activity con;
        private ProgressDialog pDialog;
        private List<BestDriverDataModel> arr = new ArrayList<>();


        public jsoning(final ListView lv, ProgressDialog pDialog, final Activity con) {

            this.lv = lv;
            this.con = con;
            this.pDialog = pDialog;

            BestDriverDataModelAdapter adapter = new BestDriverDataModelAdapter(con, arr);
            lv.setAdapter(adapter);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Object o) {
            lv.requestLayout();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in = new Intent(con, Profile.class);
                    in.putExtra("ID", arr.get(i).getID());
                    Log.d("Array Id :", String.valueOf(arr.get(i).getID()));
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                response = new GetData().GetAllDrivers();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject obj = response.getJSONObject(i);
                    final BestDriverDataModel driver = new BestDriverDataModel(Parcel.obtain());
                    driver.setID(obj.getInt("ID"));
                    driver.setName(obj.getString("FirstName"));
                    driver.setPhotoURL(obj.getString("PhotoPath"));
                    driver.setNationality(obj.getString("NationalityEnName"));
                    //driver.setRating(obj.getInt("Rating"));
                    arr.add(driver);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //hidePDialog();
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_drivers_after_login, menu);
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
