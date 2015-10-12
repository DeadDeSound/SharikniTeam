package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;


public class DriverCreateCarPool extends AppCompatActivity implements View.OnClickListener {


    int id = 1;
    int Single_Periodic_ID = 0;
    int From_Em_Id = -1;
    int From_Reg_Id = -1;
    int To_Em_Id = -1;
    int To_Reg_Id = -1;
    int year_x, month_x, day_x;
    int MyId = -1;
    int Nationality_ID = 36;
    int Vehicle_Id = -1;
    int Age_ID = 1;
    int Language_ID = 1;

    String To_EmirateEnName, From_EmirateEnName, To_RegionEnName, From_RegionEnName;

    List<TreeMap<String, String>> Create_CarPool_Emirates_List = new ArrayList<>();
    List<TreeMap<String, String>> Create_CarPool_Regions_List = new ArrayList<>();
    List<TreeMap<String, String>> Create_CarPool_Country_List = new ArrayList<>();
    List<TreeMap<String, String>> Create_CarPool_Lang_List = new ArrayList<>();
    List<TreeMap<String, String>> Create_CarPool_AgeRanges_List = new ArrayList<>();
    List<TreeMap<String, String>> Create_CarPool_Vehicles_List = new ArrayList<>();

    private Toolbar toolbar;
    static final int DILOG_ID = 0;
    static final int TIME_DIALOG_ID = 999;
    private int hour;
    private int minute;

    Dialog Create_CarPool_MainDialog;
    Dialog Languages_Dilaog;
    AutoCompleteTextView Create_CarPool_txt_regions;
    AutoCompleteTextView Create_CarPool_search_Nat;
    Spinner Create_CarPool_spinner;
    RelativeLayout Create_CarPool_pickup_relative;
    RelativeLayout Create_CarPool_dropOff_relative;
    RelativeLayout Create_CarPool_calendar_relative;
    RelativeLayout Create_CarPool_time_relative;
    SimpleAdapter Create_CarPool_EmAdapter;
    Button Create_CarPool_btn_submit_pickUp;
    Button Create_CarPool_pickUp;
    Button Create_CarPool_Dropoff;
    Button create;
    String Create_CarPool_txt_PickUp;
    String Create_CarPool_txt_Drop_Off;
    String Create_CarPool_full_date;
    EditText edit_route_name;
    TextView Create_CarPool_txt_Selecet_Start_Point;
    TextView Create_CarPool_txt_Select_Dest;
    TextView Create_CarPool_txt_year;
    TextView Create_CarPool_txt_beforeCal;
    TextView Create_CarPool_txt_time_selected;
    TextView Create_CarPool_before_Time;
    TextView maleFemaleTxt;
    TextView FemaleMaleTxt;
    TextView maleFemaleTxt2;
    TextView FemaleMaleTxt2;
    TextView Create_CarPool_Preferred_Lang_txt;
    TextView Create_CarPool_Age_Range_txt;
    TextView createCarPool_Vehicles;
    ListView lang_lv;
    Context mContext;
    ImageView seat1_off, seat2_off, seat3_off, seat4_off;
    ImageView Periodic_SingleRide, singleRide_Periodic, Create_CarPool_malefemale1, Create_CarPool_femalemale2;
    ImageView seat1_on, seat2_on, seat3_on, seat4_on;
    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        setContentView(R.layout.activity_driver_create_car_pool);
        showDialogOnButtonClick();
        showTimeDialogOnButtonClick();
        mContext = this;

        initToolbar();

        myPrefs = this.getSharedPreferences("myPrefs", 0);
        String ID = myPrefs.getString("account_id", null);
        MyId = Integer.parseInt(ID);

        edit_route_name = (EditText) findViewById(R.id.createCarPool_EnName);

        Create_CarPool_txt_year = (TextView) findViewById(R.id.createCarPool_search_txt_yaer);
        Create_CarPool_txt_beforeCal = (TextView) findViewById(R.id.createCarPool_textview50);
        Create_CarPool_pickup_relative = (RelativeLayout) findViewById(R.id.createCarPool_pickup_relative);
        Create_CarPool_txt_Selecet_Start_Point = (TextView) findViewById(R.id.createCarPool_txt_Selecet_Start_Point);
        Create_CarPool_dropOff_relative = (RelativeLayout) findViewById(R.id.createCarPool_dropOff_relative);
        Create_CarPool_txt_Select_Dest = (TextView) findViewById(R.id.createCarPool_txt_Select_Dest);
        Create_CarPool_txt_time_selected = (TextView) findViewById(R.id.createCarPool_txt_time_selected);
        Create_CarPool_before_Time = (TextView) findViewById(R.id.createCarPool_textview51);
        create = (Button) findViewById(R.id.createCarPool);

        Periodic_SingleRide = (ImageView) findViewById(R.id.createCarPool_Periodic_SingleRide);
        singleRide_Periodic = (ImageView) findViewById(R.id.createCarPool_singleRide_Periodic);
        Create_CarPool_malefemale1 = (ImageView) findViewById(R.id.createCarPool_malefemale1);
        Create_CarPool_femalemale2 = (ImageView) findViewById(R.id.createCarPool_femalemale2);
        Create_CarPool_search_Nat = (AutoCompleteTextView) findViewById(R.id.createCarPool_search_Nat);

        maleFemaleTxt = (TextView) findViewById(R.id.createCarPool_malefemale_txt);
        FemaleMaleTxt = (TextView) findViewById(R.id.createCarPool_femalemale_txt);
        maleFemaleTxt2 = (TextView) findViewById(R.id.createCarPool_malefemale_txt2);
        FemaleMaleTxt2 = (TextView) findViewById(R.id.createCarPool_femalemale_txt2);

        Create_CarPool_Preferred_Lang_txt = (TextView) findViewById(R.id.createCarPool_Preferred_Lang_txt);
        Create_CarPool_Age_Range_txt = (TextView) findViewById(R.id.createCarPool_Age_Range_txt);
        Create_CarPool_pickUp = (Button) findViewById(R.id.createCarPool_pickUp);
        Create_CarPool_Dropoff = (Button) findViewById(R.id.createCarPool_search__Dropoff);

        seat1_off = (ImageView) findViewById(R.id.seat1_off);
        seat2_off = (ImageView) findViewById(R.id.seat2_off);
        seat3_off = (ImageView) findViewById(R.id.seat3_off);
        seat4_off = (ImageView) findViewById(R.id.seat4_off);

        seat1_on = (ImageView) findViewById(R.id.seat1_on);
        seat2_on = (ImageView) findViewById(R.id.seat2_on);
        seat3_on = (ImageView) findViewById(R.id.seat3_on);
        seat4_on = (ImageView) findViewById(R.id.seat4_on);


        createCarPool_Vehicles = (TextView) findViewById(R.id.createCarPool_Vehicles);
        Create_CarPool_pickUp.setOnClickListener(this);
        create.setOnClickListener(this);
        Create_CarPool_pickup_relative.setOnClickListener(this);
        Create_CarPool_Dropoff.setOnClickListener(this);
        Create_CarPool_dropOff_relative.setOnClickListener(this);

        singleRide_Periodic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleRide_Periodic.setVisibility(View.INVISIBLE);
                Periodic_SingleRide.setVisibility(View.VISIBLE);
                Single_Periodic_ID = 1;
                maleFemaleTxt2.setTextColor(Color.GRAY);
                FemaleMaleTxt2.setTextColor(Color.RED);
            }
        });

        Periodic_SingleRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Periodic_SingleRide.setVisibility(View.INVISIBLE);
                singleRide_Periodic.setVisibility(View.VISIBLE);
                Single_Periodic_ID = 0;
                maleFemaleTxt2.setTextColor(Color.RED);
                FemaleMaleTxt2.setTextColor(Color.GRAY);
            }
        });

        FemaleMaleTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleFemaleTxt2.setTextColor(Color.GRAY);
                FemaleMaleTxt2.setTextColor(Color.RED);
                Periodic_SingleRide.setVisibility(View.VISIBLE);
                singleRide_Periodic.setVisibility(View.INVISIBLE);
            }
        });

        maleFemaleTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleFemaleTxt2.setTextColor(Color.RED);
                FemaleMaleTxt2.setTextColor(Color.GRAY);
                Periodic_SingleRide.setVisibility(View.INVISIBLE);
                singleRide_Periodic.setVisibility(View.VISIBLE);
            }
        });

        //Create_CarPool
        Create_CarPool_malefemale1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_CarPool_malefemale1.setVisibility(View.INVISIBLE);
                Create_CarPool_femalemale2.setVisibility(View.VISIBLE);
                maleFemaleTxt.setTextColor(Color.GRAY);
                FemaleMaleTxt.setTextColor(Color.RED);
            }
        });

        Create_CarPool_femalemale2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_CarPool_femalemale2.setVisibility(View.INVISIBLE);
                Create_CarPool_malefemale1.setVisibility(View.VISIBLE);
                maleFemaleTxt.setTextColor(Color.RED);
                FemaleMaleTxt.setTextColor(Color.GRAY);
            }
        });

        FemaleMaleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleFemaleTxt.setTextColor(Color.GRAY);
                FemaleMaleTxt.setTextColor(Color.RED);
                Create_CarPool_malefemale1.setVisibility(View.VISIBLE);
                Create_CarPool_femalemale2.setVisibility(View.INVISIBLE);
            }
        });

        maleFemaleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleFemaleTxt.setTextColor(Color.RED);
                FemaleMaleTxt.setTextColor(Color.GRAY);
                Create_CarPool_malefemale1.setVisibility(View.INVISIBLE);
                Create_CarPool_femalemale2.setVisibility(View.VISIBLE);
            }
        });

        seat1_on.setOnClickListener(this);
        seat2_on.setOnClickListener(this);
        seat3_on.setOnClickListener(this);
        seat4_on.setOnClickListener(this);
        seat1_off.setOnClickListener(this);
        seat2_off.setOnClickListener(this);
        seat3_off.setOnClickListener(this);
        seat4_off.setOnClickListener(this);

        // Create_CarPool
        //  code to get nationals and set the adapter to the autotext complete

        new getNationalities().execute();

        //Toast.makeText(RegisterNewTest.this, "test pref lang" +Lang_List.toString(), Toast.LENGTH_LONG).show();
        Log.d("test pref lang  2 :", Create_CarPool_Country_List.toString());

        // code to get Vehicles and set it to txt view

        new getVehicles().execute();

        //Toast.makeText(RegisterNewTest.this, "test pref lang" +Lang_List.toString(), Toast.LENGTH_LONG).show();
        Log.d("test pref lang  2 :", Create_CarPool_Vehicles_List.toString());


        //Create_CarPool
        // code to get Languages and set it to the SPinner

        new getLanguages().execute();

        //Toast.makeText(RegisterNewTest.this, "test pref lang" +Lang_List.toString(), Toast.LENGTH_LONG).show();
        Log.d("test pref lang  2 :", Create_CarPool_Lang_List.toString());

        //Create_CarPool
        // get age ranges and set it to the spineer

        new getAgeRanges().execute();

        //Toast.makeText(RegisterNewTest.this, "test pref lang" +Lang_List.toString(), Toast.LENGTH_LONG).show();
        Log.d("test pref lang  2 :", Create_CarPool_AgeRanges_List.toString());

    }

    private class getNationalities extends AsyncTask{

        @Override
        protected void onPostExecute(Object o) {
            SimpleAdapter adapterCountry = new SimpleAdapter(DriverCreateCarPool.this, Create_CarPool_Country_List
                    , R.layout.autocomplete_row
                    , new String[]{"ID", "NationalityEnName"}
                    , new int[]{R.id.row_id, R.id.row_name});

            Create_CarPool_search_Nat.setAdapter(adapterCountry);
            Create_CarPool_search_Nat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView txt_lang_name = (TextView) view.findViewById(R.id.row_name);
                    TextView txt_lang_id = (TextView) view.findViewById(R.id.row_id);
                    Nationality_ID = Integer.parseInt(txt_lang_id.getText().toString());
                    Create_CarPool_search_Nat.setText(txt_lang_name.getText().toString());
                    Log.d("id of lang", "" + Nationality_ID);
                }
            });
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                JSONArray j = new GetData().GetNationalities();
                for (int i = 0; i < j.length(); i++) {
                    TreeMap<String, String> valuePairs = new TreeMap<>();
                    JSONObject jsonObject = j.getJSONObject(i);
                    valuePairs.put("ID", jsonObject.getString("ID"));
                    valuePairs.put("NationalityEnName", jsonObject.getString("NationalityEnName"));
                    Create_CarPool_Country_List.add(valuePairs);
                }

                //Toast.makeText(RegisterNewTest.this, "test pref lang" + Lang_List.toString(), Toast.LENGTH_LONG).show();
                Log.d("test pref lang", Create_CarPool_Country_List.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class getVehicles extends AsyncTask{

        @Override
        protected void onPostExecute(Object o) {
            final SimpleAdapter adapter3 = new SimpleAdapter(DriverCreateCarPool.this, Create_CarPool_Vehicles_List
                    , R.layout.autocomplete_row
                    , new String[]{"ID", "ManufacturingEnName"}
                    , new int[]{R.id.row_id, R.id.row_name});

            createCarPool_Vehicles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Languages_Dilaog = new Dialog(mContext);
                    Languages_Dilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Languages_Dilaog.setContentView(R.layout.languages_dialog);
                    TextView Lang_Dialog_txt_id = (TextView) Languages_Dilaog.findViewById(R.id.Lang_Dialog_txt_id);
                    Lang_Dialog_txt_id.setText("Vehicles");
                    lang_lv = (ListView) Languages_Dilaog.findViewById(R.id.Langs_list);
                    lang_lv.setAdapter(adapter3);
                    Languages_Dilaog.show();
                    lang_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView txt_lang_name = (TextView) view.findViewById(R.id.row_name);
                            TextView txt_lang_id = (TextView) view.findViewById(R.id.row_id);
                            Vehicle_Id = Integer.parseInt(txt_lang_id.getText().toString());
                            createCarPool_Vehicles.setText(txt_lang_name.getText().toString());
                            // Log.d("id of lang", "" + Language_ID);
                            Languages_Dilaog.dismiss();
                        }
                    });


                }
            });
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                JSONArray j = new GetData().GetVehiclesForCreateCarPool();
                for (int i = 0; i < j.length(); i++) {
                    TreeMap<String, String> valuePairs = new TreeMap<>();
                    JSONObject jsonObject = j.getJSONObject(i);
                    valuePairs.put("ID", jsonObject.getString("ID"));
                    valuePairs.put("ManufacturingEnName", jsonObject.getString("ManufacturingEnName"));
                    Create_CarPool_Vehicles_List.add(valuePairs);
                }
                //Toast.makeText(RegisterNewTest.this, "test pref lang" + Lang_List.toString(), Toast.LENGTH_LONG).show();
                Log.d("test pref lang", Create_CarPool_Vehicles_List.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class getLanguages extends AsyncTask{

        @Override
        protected void onPostExecute(Object o) {
            final SimpleAdapter adapter2 = new SimpleAdapter(DriverCreateCarPool.this, Create_CarPool_Lang_List
                    , R.layout.autocomplete_row
                    , new String[]{"LanguageId", "LanguageEnName"}
                    , new int[]{R.id.row_id, R.id.row_name});

            Create_CarPool_Preferred_Lang_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Languages_Dilaog = new Dialog(mContext);
                    Languages_Dilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Languages_Dilaog.setContentView(R.layout.languages_dialog);
                    lang_lv = (ListView) Languages_Dilaog.findViewById(R.id.Langs_list);
                    lang_lv.setAdapter(adapter2);
                    Languages_Dilaog.show();
                    lang_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView txt_lang_name = (TextView) view.findViewById(R.id.row_name);
                            TextView txt_lang_id = (TextView) view.findViewById(R.id.row_id);
                            Language_ID = Integer.parseInt(txt_lang_id.getText().toString());
                            Create_CarPool_Preferred_Lang_txt.setText(txt_lang_name.getText().toString());
                            // Log.d("id of lang", "" + Language_ID);
                            Languages_Dilaog.dismiss();
                        }
                    });
                }
            });
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                JSONArray j = new GetData().GetPrefLanguage();
                for (int i = 0; i < j.length(); i++) {

                    TreeMap<String, String> valuePairs = new TreeMap<>();
                    JSONObject jsonObject = j.getJSONObject(i);
                    valuePairs.put("LanguageId", jsonObject.getString("LanguageId"));
                    valuePairs.put("LanguageEnName", jsonObject.getString("LanguageEnName"));
                    Create_CarPool_Lang_List.add(valuePairs);

                }
                //Toast.makeText(RegisterNewTest.this, "test pref lang" + Lang_List.toString(), Toast.LENGTH_LONG).show();
                Log.d("test pref lang", Create_CarPool_Lang_List.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class getAgeRanges extends AsyncTask{

        @Override
        protected void onPostExecute(Object o) {

            final SimpleAdapter AgeRangesAdapter = new SimpleAdapter(DriverCreateCarPool.this, Create_CarPool_AgeRanges_List
                    , R.layout.autocomplete_row
                    , new String[]{"RangeId", "Range"}
                    , new int[]{R.id.row_id, R.id.row_name});

            Create_CarPool_Age_Range_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Languages_Dilaog = new Dialog(mContext);
                    Languages_Dilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Languages_Dilaog.setContentView(R.layout.languages_dialog);
                    TextView Lang_Dialog_txt_id = (TextView) Languages_Dilaog.findViewById(R.id.Lang_Dialog_txt_id);
                    Lang_Dialog_txt_id.setText("Age Ranges");
                    lang_lv = (ListView) Languages_Dilaog.findViewById(R.id.Langs_list);
                    lang_lv.setAdapter(AgeRangesAdapter);
                    Languages_Dilaog.show();
                    lang_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView txt_lang_name = (TextView) view.findViewById(R.id.row_name);
                            TextView txt_lang_id = (TextView) view.findViewById(R.id.row_id);
                            Age_ID = Integer.parseInt(txt_lang_id.getText().toString());
                            Create_CarPool_Age_Range_txt.setText(txt_lang_name.getText().toString());
                            // Log.d("id of lang", "" + Language_ID);
                            Languages_Dilaog.dismiss();
                        }
                    });
                }
            });
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                JSONArray j = new GetData().GetAgeRanges();
                for (int i = 0; i < j.length(); i++) {
                    TreeMap<String, String> valuePairs = new TreeMap<>();
                    JSONObject jsonObject = j.getJSONObject(i);
                    valuePairs.put("RangeId", jsonObject.getString("RangeId"));
                    valuePairs.put("Range", jsonObject.getString("Range"));
                    Create_CarPool_AgeRanges_List.add(valuePairs);
                }
                //Toast.makeText(RegisterNewTest.this, "test pref lang" + Lang_List.toString(), Toast.LENGTH_LONG).show();
                Log.d("test pref lang", Create_CarPool_AgeRanges_List.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_driver_create_car_pool, menu);
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


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DILOG_ID) {
            return new DatePickerDialog(this, dPickerListener, year_x, month_x, day_x);
        }
        if (id == TIME_DIALOG_ID) {
            return new TimePickerDialog(this, timePickerListener, hour, minute, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Create_CarPool_txt_beforeCal.setVisibility(View.INVISIBLE);
            // Toast.makeText(RegisterNewTest.this, "hi"+year_x+" :"+month_x+" :"+day_x, Toast.LENGTH_SHORT).show();
            String year_string = String.valueOf(year_x);
            String month_string = String.valueOf(month_x);
            String day_string = String.valueOf(day_x);
            Create_CarPool_full_date = day_string + "/" + month_string + "/" + year_string;
            Create_CarPool_txt_year.setText(Create_CarPool_full_date);
            Log.d("Calendar test", Create_CarPool_full_date);
        }
    };

    public void showDialogOnButtonClick() {
        Create_CarPool_calendar_relative = (RelativeLayout) findViewById(R.id.createCarPool_calendar_relative);
        Create_CarPool_calendar_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID);
            }
        });

    }


    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;
                    Create_CarPool_before_Time.setVisibility(View.INVISIBLE);
                    // set current time into textview
                    Create_CarPool_txt_time_selected.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));

                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void showTimeDialogOnButtonClick() {

        Create_CarPool_time_relative = (RelativeLayout) findViewById(R.id.createCarPool_time_relative);
        Create_CarPool_time_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Create Ride");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v==create){
            if (edit_route_name.getText()!=null&&edit_route_name.getText().toString()!="Ride Name"&&From_Em_Id != -1&&To_Em_Id != -1&&From_Reg_Id != -1&&To_Reg_Id != -1&&Single_Periodic_ID != -1&&Vehicle_Id != -1&&id != -1&&Create_CarPool_txt_beforeCal.getText().toString()!="Click to Select"&&Create_CarPool_txt_time_selected.getText()!=null&&Create_CarPool_txt_time_selected.getText().toString() !="Click to Select") {
                String is_Rounded;
                String EnName = edit_route_name.getText().toString();
                int FromEmId = From_Em_Id;   // dubai
                int ToEmId = To_Em_Id;   // 3agman
                int FromRegId = From_Reg_Id;//
                int ToRegId = To_Reg_Id;   // 3agman mueseum
                if (Single_Periodic_ID == 1){
                    is_Rounded = "true";
                }else{
                    is_Rounded = "false";
                }
                String Time="10:00";
                String Saturday="1";
                String Sunday="0";
                String Monday="0";
                String Tuesday="0";
                String Wednesday="0";
                String Thursday="0";
                String Friday="0";
                char gender = 'N';
                int Vehicle_ID = Vehicle_Id;
                int No_OF_Seats = id;
                double Start_Lat = 25.19757887867318;
                double Start_Lng = 55.27437007440332;
                double End_Lat = 25.32912394868096;
                double End_Lng = 55.51227235846654;
                int pref_lnag = Language_ID;
                int pref_nat = Nationality_ID;
                int Age_Ranged_id = Age_ID;
                String StartDate = "15/6/2015";

                GetData j = new GetData();
                j.DriverCreateCarPoolFrom(MyId,EnName,FromEmId,ToEmId,FromRegId,ToRegId
                        ,is_Rounded,Time,Saturday,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday
                        ,gender,Vehicle_ID,No_OF_Seats,Start_Lat,Start_Lng,End_Lat,End_Lng
                        ,pref_lnag,pref_nat,Age_Ranged_id,StartDate,this);

            }else{
                Toast.makeText(DriverCreateCarPool.this, "Make Sure Your Fill All Required Filds", Toast.LENGTH_SHORT).show();
            }
        }


        if (v == Create_CarPool_pickup_relative || v == Create_CarPool_pickUp) {
            Create_CarPool_Emirates_List.clear();
            try {
                JSONArray j = new GetData().GetEmitares();
                for (int i = 0; i < j.length(); i++) {

                    TreeMap<String, String> valuePairs = new TreeMap<>();
                    JSONObject jsonObject = j.getJSONObject(i);
                    valuePairs.put("EmirateId", jsonObject.getString("EmirateId"));
                    valuePairs.put("EmirateEnName", jsonObject.getString("EmirateEnName"));
                    Create_CarPool_Emirates_List.add(valuePairs);
                }
                Log.d("test Emirates ", Create_CarPool_Emirates_List.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Create_CarPool_EmAdapter = new SimpleAdapter(DriverCreateCarPool.this, Create_CarPool_Emirates_List
                    , R.layout.dialog_pick_emirate_lv_row
                    , new String[]{"EmirateId", "EmirateEnName"}
                    , new int[]{R.id.row_id_search, R.id.row_name_search});

            Create_CarPool_MainDialog = new Dialog(DriverCreateCarPool.this);
            Create_CarPool_MainDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Create_CarPool_MainDialog.setContentView(R.layout.main_search_dialog);
            Create_CarPool_btn_submit_pickUp = (Button) Create_CarPool_MainDialog.findViewById(R.id.btn_submit_puckup);
            TextView Lang_Dialog_txt_id = (TextView) Create_CarPool_MainDialog.findViewById(R.id.Lang_Dialog_txt_id);
            Lang_Dialog_txt_id.setText("Pick Up");
            Create_CarPool_txt_regions = (AutoCompleteTextView) Create_CarPool_MainDialog.findViewById(R.id.mainDialog_Regions_auto);
            Create_CarPool_spinner = (Spinner) Create_CarPool_MainDialog.findViewById(R.id.Emirates_spinner);
            Create_CarPool_spinner.setAdapter(Create_CarPool_EmAdapter);
            Create_CarPool_MainDialog.show();
            Create_CarPool_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Create_CarPool_txt_PickUp = "";
                    TextView txt_em_name = (TextView) view.findViewById(R.id.row_name_search);
                    TextView txt_em_id = (TextView) view.findViewById(R.id.row_id_search);
                    From_Em_Id = Integer.parseInt(txt_em_id.getText().toString());
                    From_EmirateEnName = txt_em_name.getText().toString();
                    Create_CarPool_txt_PickUp += txt_em_name.getText().toString();
                    Create_CarPool_txt_PickUp += ", ";
                    Log.d("id of lang", "" + From_Em_Id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Create_CarPool_txt_regions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetData getData = new GetData();
                    try {
                        JSONArray jsonArray = getData.GetRegionsByEmiratesID(From_Em_Id);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            TreeMap<String, String> valuePairs = new TreeMap<>();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            valuePairs.put("ID", jsonObject.getString("ID"));
                            valuePairs.put("RegionEnName", jsonObject.getString("RegionEnName"));
                            Create_CarPool_Regions_List.add(valuePairs);
                        }
                        Log.d("test Regions search ", Create_CarPool_Regions_List.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final SimpleAdapter RegAdapter = new SimpleAdapter(DriverCreateCarPool.this, Create_CarPool_Regions_List
                            , R.layout.dialog_pick_emirate_lv_row
                            , new String[]{"ID", "RegionEnName"}
                            , new int[]{R.id.row_id_search, R.id.row_name_search});
                    Create_CarPool_txt_regions.setAdapter(RegAdapter);
                    Create_CarPool_txt_regions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView txt_reg_name = (TextView) view.findViewById(R.id.row_name_search);
                            TextView txt_reg_id = (TextView) view.findViewById(R.id.row_id_search);
                            From_Reg_Id = Integer.parseInt(txt_reg_id.getText().toString());
                            From_RegionEnName = txt_reg_name.getText().toString();
                            Create_CarPool_txt_regions.setText(txt_reg_name.getText().toString());
                            Create_CarPool_txt_PickUp += txt_reg_name.getText().toString();
                        }
                    });
                }
            });

            Create_CarPool_btn_submit_pickUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Create_CarPool_txt_Selecet_Start_Point.setText(Create_CarPool_txt_PickUp);
                    Create_CarPool_MainDialog.dismiss();
                }
            });
        }

        if (v == Create_CarPool_dropOff_relative || v == Create_CarPool_Dropoff) {
            Create_CarPool_Emirates_List.clear();
            try {
                JSONArray j = new GetData().GetEmitares();
                for (int i = 0; i < j.length(); i++) {
                    TreeMap<String, String> valuePairs = new TreeMap<>();
                    JSONObject jsonObject = j.getJSONObject(i);
                    valuePairs.put("EmirateId", jsonObject.getString("EmirateId"));
                    valuePairs.put("EmirateEnName", jsonObject.getString("EmirateEnName"));
                    Create_CarPool_Emirates_List.add(valuePairs);
                }
                Log.d("test Emirates ", Create_CarPool_Emirates_List.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Create_CarPool_EmAdapter = new SimpleAdapter(DriverCreateCarPool.this, Create_CarPool_Emirates_List
                    , R.layout.dialog_pick_emirate_lv_row
                    , new String[]{"EmirateId", "EmirateEnName"}
                    , new int[]{R.id.row_id_search, R.id.row_name_search});

            Create_CarPool_MainDialog = new Dialog(DriverCreateCarPool.this);
            Create_CarPool_MainDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Create_CarPool_MainDialog.setContentView(R.layout.main_search_dialog);
            TextView Lang_Dialog_txt_id = (TextView) Create_CarPool_MainDialog.findViewById(R.id.Lang_Dialog_txt_id);
            Lang_Dialog_txt_id.setText("Drop Off");
            Create_CarPool_btn_submit_pickUp = (Button) Create_CarPool_MainDialog.findViewById(R.id.btn_submit_puckup);
            Create_CarPool_txt_regions = (AutoCompleteTextView) Create_CarPool_MainDialog.findViewById(R.id.mainDialog_Regions_auto);
            Create_CarPool_spinner = (Spinner) Create_CarPool_MainDialog.findViewById(R.id.Emirates_spinner);
            Create_CarPool_spinner.setAdapter(Create_CarPool_EmAdapter);
            Create_CarPool_MainDialog.show();
            Create_CarPool_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Create_CarPool_txt_Drop_Off = "";
                    TextView txt_em_name = (TextView) view.findViewById(R.id.row_name_search);
                    TextView txt_em_id = (TextView) view.findViewById(R.id.row_id_search);
                    To_Em_Id = Integer.parseInt(txt_em_id.getText().toString());
                    To_EmirateEnName = txt_em_name.getText().toString();
                    Create_CarPool_txt_Drop_Off += txt_em_name.getText().toString();
                    Create_CarPool_txt_Drop_Off += ", ";
                    Log.d("id of lang", "" + To_Em_Id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            Create_CarPool_txt_regions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Create_CarPool_Regions_List.clear();
                    GetData getData = new GetData();
                    try {
                        JSONArray jsonArray = getData.GetRegionsByEmiratesID(To_Em_Id);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            TreeMap<String, String> valuePairs = new TreeMap<>();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            valuePairs.put("ID", jsonObject.getString("ID"));
                            valuePairs.put("RegionEnName", jsonObject.getString("RegionEnName"));
                            Create_CarPool_Regions_List.add(valuePairs);
                        }
                        Log.d("test Regions search ", Create_CarPool_Regions_List.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final SimpleAdapter RegAdapter = new SimpleAdapter(DriverCreateCarPool.this, Create_CarPool_Regions_List
                            , R.layout.dialog_pick_emirate_lv_row
                            , new String[]{"ID", "RegionEnName"}
                            , new int[]{R.id.row_id_search, R.id.row_name_search});

                    Create_CarPool_txt_regions.setAdapter(RegAdapter);
                    Create_CarPool_txt_regions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView txt_reg_name = (TextView) view.findViewById(R.id.row_name_search);
                            TextView txt_reg_id = (TextView) view.findViewById(R.id.row_id_search);
                            To_Reg_Id = Integer.parseInt(txt_reg_id.getText().toString());
                            To_RegionEnName = txt_reg_name.getText().toString();
                            Create_CarPool_txt_regions.setText(txt_reg_name.getText().toString());
                            Create_CarPool_txt_Drop_Off += txt_reg_name.getText().toString();
                        }
                    });
                }
            });
            Create_CarPool_btn_submit_pickUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Create_CarPool_txt_Select_Dest.setText(Create_CarPool_txt_Drop_Off);
                    Create_CarPool_MainDialog.dismiss();
                }
            });
        }   // drop oof relative

        if (v == seat1_off && id == 1) {
            seat1_on.setVisibility(View.VISIBLE);
            seat1_off.setVisibility(View.INVISIBLE);
            id = 2;
        }
        if (v == seat1_on && id == 2) {
            seat1_on.setVisibility(View.INVISIBLE);
            seat1_off.setVisibility(View.VISIBLE);
            id = 1;
        }
        if (v == seat2_off && id == 2) {
            seat2_off.setVisibility(View.INVISIBLE);
            seat2_on.setVisibility(View.VISIBLE);
            id = 3;
        }
        if (v == seat2_on && id == 3) {
            seat2_off.setVisibility(View.VISIBLE);
            seat2_on.setVisibility(View.INVISIBLE);
            id = 2;
        }
        if (v == seat3_off && id == 3) {
            seat3_off.setVisibility(View.INVISIBLE);
            seat3_on.setVisibility(View.VISIBLE);
            id = 4;
        }
        if (v == seat3_on && id == 4) {
            seat3_on.setVisibility(View.INVISIBLE);
            seat3_off.setVisibility(View.VISIBLE);
            id = 3;
        }
        if (v == seat4_off && id == 4) {
            seat4_off.setVisibility(View.INVISIBLE);
            seat4_on.setVisibility(View.VISIBLE);
            id = 5;
        }

        if (v == seat4_on && id == 5) {
            seat4_on.setVisibility(View.INVISIBLE);
            seat4_off.setVisibility(View.VISIBLE);
            id = 4;
        }
    }
}
