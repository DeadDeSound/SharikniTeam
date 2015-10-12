package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

public class Advanced_Search extends AppCompatActivity implements View.OnClickListener {


    int Single_Periodic_ID;

    int From_Em_Id;
    int From_Reg_Id;
    int To_Em_Id;
    int To_Reg_Id;

    String To_EmirateEnName, From_EmirateEnName, To_RegionEnName, From_RegionEnName;

    List<TreeMap<String, String>> Advanced_Emirates_List = new ArrayList<>();
    List<TreeMap<String, String>> Advanced_Regions_List = new ArrayList<>();
    List<TreeMap<String, String>> Advanced_Country_List = new ArrayList<>();
    List<TreeMap<String, String>> Advanced_Lang_List = new ArrayList<>();
    List<TreeMap<String, String>> Advanced_AgeRanges_List = new ArrayList<>();

    Dialog Advanced_MainDialog;
    Dialog Languages_Dilaog;
    AutoCompleteTextView Advanced_txt_regions;
    Spinner Advanced_spinner;
    RelativeLayout Advanced_pickup_relative;
    SimpleAdapter Advanced_EmAdapter;
    Button Advanced_btn_submit_pickUp;
    String Advanced_txt_PickUp;


    AutoCompleteTextView advanced_search_Nat;


    TextView Advanced_txt_Selecet_Start_Point;
    RelativeLayout Advanced_dropOff_relative;

    TextView Advanced_txt_Select_Dest;

    String Advanced_txt_Drop_Off;

    RelativeLayout Advanced_calendar_relative;
    static final int DILOG_ID = 0;

    int year_x, month_x, day_x;

    TextView Advanced_txt_year;
    TextView Advanced_txt_beforeCal;
    String Advanced_full_date;

    private int hour;
    private int minute;

    static final int TIME_DIALOG_ID = 999;

    RelativeLayout Advanced_time_relative;

    TextView Advanced_txt_time_selected;
    TextView Advanced_before_Time;

    Button Advanced_btn_search_page;


    int MyId;
    int Nationality_ID;


    TextView maleFemaleTxt;
    TextView FemaleMaleTxt;
    TextView maleFemaleTxt2;
    TextView FemaleMaleTxt2;
    TextView advanced_search_Preferred_Lang_txt;


    ImageView Periodic_SingleRide, singleRide_Periodic, Advanced_malefemale1, Advanced_femalemale2;
    private Toolbar toolbar;


    ListView lang_lv;
    Context mContext;

    TextView advanced_search_Age_Range_txt;

    Button quickSearch_pickUp;
    Button quickSearch_Dropoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        setContentView(R.layout.activity_advanced__search);
        showDialogOnButtonClick();
        showTimeDialogOnButtonClick();
        mContext = this;

        initToolbar();


        Intent intent = getIntent();
        MyId = intent.getIntExtra("ID", 0);


        Advanced_txt_year = (TextView) findViewById(R.id.Advanced_search_txt_yaer);
        Advanced_txt_beforeCal = (TextView) findViewById(R.id.Advanced_textview50);
        Advanced_pickup_relative = (RelativeLayout) findViewById(R.id.advanced_search_pickup_relative);
        Advanced_txt_Selecet_Start_Point = (TextView) findViewById(R.id.Advanced_txt_Selecet_Start_Point);
        Advanced_dropOff_relative = (RelativeLayout) findViewById(R.id.advanced_search_dropOff_relative);
        Advanced_txt_Select_Dest = (TextView) findViewById(R.id.Advanced_txt_Select_Dest);
        Advanced_txt_time_selected = (TextView) findViewById(R.id.Advanced_txt_time_selected);
        Advanced_before_Time = (TextView) findViewById(R.id.Advanced_textview51);
        Advanced_btn_search_page = (Button) findViewById(R.id.btn_advanced_search_page);

        Periodic_SingleRide = (ImageView) findViewById(R.id.Periodic_SingleRide);
        singleRide_Periodic = (ImageView) findViewById(R.id.singleRide_Periodic);
        Advanced_malefemale1 = (ImageView) findViewById(R.id.Advanced_malefemale1);
        Advanced_femalemale2 = (ImageView) findViewById(R.id.Advanced_femalemale2);

        advanced_search_Nat = (AutoCompleteTextView) findViewById(R.id.advanced_search_Nat);


        maleFemaleTxt = (TextView) findViewById(R.id.malefemale_txt);
        FemaleMaleTxt = (TextView) findViewById(R.id.femalemale_txt);

        maleFemaleTxt2 = (TextView) findViewById(R.id.malefemale_txt2);
        FemaleMaleTxt2 = (TextView) findViewById(R.id.femalemale_txt2);


        advanced_search_Preferred_Lang_txt = (TextView) findViewById(R.id.advanced_search_Preferred_Lang_txt);

        advanced_search_Age_Range_txt = (TextView) findViewById(R.id.advanced_search_Age_Range_txt);
        quickSearch_pickUp = (Button) findViewById(R.id.quickSearch_pickUp);
        quickSearch_Dropoff = (Button) findViewById(R.id.advanced_search__Dropoff);


        quickSearch_pickUp.setOnClickListener(this);
        Advanced_pickup_relative.setOnClickListener(this);
        quickSearch_Dropoff.setOnClickListener(this);
        Advanced_dropOff_relative.setOnClickListener(this);


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


        Advanced_malefemale1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Advanced_malefemale1.setVisibility(View.INVISIBLE);
                Advanced_femalemale2.setVisibility(View.VISIBLE);
                maleFemaleTxt.setTextColor(Color.GRAY);
                FemaleMaleTxt.setTextColor(Color.RED);

            }
        });


        Advanced_femalemale2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Advanced_femalemale2.setVisibility(View.INVISIBLE);
                Advanced_malefemale1.setVisibility(View.VISIBLE);
                maleFemaleTxt.setTextColor(Color.RED);
                FemaleMaleTxt.setTextColor(Color.GRAY);

            }
        });


        FemaleMaleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleFemaleTxt.setTextColor(Color.GRAY);
                FemaleMaleTxt.setTextColor(Color.RED);
                Advanced_malefemale1.setVisibility(View.INVISIBLE);
                Advanced_femalemale2.setVisibility(View.VISIBLE);


            }
        });


        maleFemaleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                maleFemaleTxt.setTextColor(Color.RED);
                FemaleMaleTxt.setTextColor(Color.GRAY);
                Advanced_malefemale1.setVisibility(View.VISIBLE);
                Advanced_femalemale2.setVisibility(View.INVISIBLE);
            }
        });


        //  code to get nationals and set the adapter to the autotext complete

        try {
            JSONArray j = new GetData().GetNationalities();
            for (int i = 0; i < j.length(); i++) {
                TreeMap<String, String> valuePairs = new TreeMap<>();
                JSONObject jsonObject = j.getJSONObject(i);
                valuePairs.put("ID", jsonObject.getString("ID"));
                valuePairs.put("NationalityEnName", jsonObject.getString("NationalityEnName"));
                Advanced_Country_List.add(valuePairs);
            }

            //Toast.makeText(RegisterNewTest.this, "test pref lang" + Lang_List.toString(), Toast.LENGTH_LONG).show();
            Log.d("test pref lang", Advanced_Country_List.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Toast.makeText(RegisterNewTest.this, "test pref lang" +Lang_List.toString(), Toast.LENGTH_LONG).show();
        Log.d("test pref lang  2 :", Advanced_Country_List.toString());

        SimpleAdapter adapterCountry = new SimpleAdapter(Advanced_Search.this, Advanced_Country_List
                , R.layout.autocomplete_row
                , new String[]{"ID", "NationalityEnName"}
                , new int[]{R.id.row_id, R.id.row_name});

        advanced_search_Nat.setAdapter(adapterCountry);
        advanced_search_Nat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt_lang_name = (TextView) view.findViewById(R.id.row_name);
                TextView txt_lang_id = (TextView) view.findViewById(R.id.row_id);
                Nationality_ID = Integer.parseInt(txt_lang_id.getText().toString());
                advanced_search_Nat.setText(txt_lang_name.getText().toString());
                Log.d("id of lang", "" + Nationality_ID);
            }
        });


        // code to get Languages and set it to the SPinner
        try {
            JSONArray j = new GetData().GetPrefLanguage();
            for (int i = 0; i < j.length(); i++) {

                TreeMap<String, String> valuePairs = new TreeMap<>();
                JSONObject jsonObject = j.getJSONObject(i);
                valuePairs.put("LanguageId", jsonObject.getString("LanguageId"));
                valuePairs.put("LanguageEnName", jsonObject.getString("LanguageEnName"));
                Advanced_Lang_List.add(valuePairs);


            }
            //Toast.makeText(RegisterNewTest.this, "test pref lang" + Lang_List.toString(), Toast.LENGTH_LONG).show();
            Log.d("test pref lang", Advanced_Lang_List.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Toast.makeText(RegisterNewTest.this, "test pref lang" +Lang_List.toString(), Toast.LENGTH_LONG).show();
        Log.d("test pref lang  2 :", Advanced_Lang_List.toString());


        final SimpleAdapter adapter2 = new SimpleAdapter(Advanced_Search.this, Advanced_Lang_List
                , R.layout.autocomplete_row
                , new String[]{"LanguageId", "LanguageEnName"}
                , new int[]{R.id.row_id, R.id.row_name});


        advanced_search_Preferred_Lang_txt.setOnClickListener(new View.OnClickListener() {
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
                        //   Language_ID = Integer.parseInt(txt_lang_id.getText().toString());
                        advanced_search_Preferred_Lang_txt.setText(txt_lang_name.getText().toString());
                        // Log.d("id of lang", "" + Language_ID);
                        Languages_Dilaog.dismiss();
                    }
                });


            }
        });


        // get age ranges and set it to the spineer

        try {
            JSONArray j = new GetData().GetAgeRanges();
            for (int i = 0; i < j.length(); i++) {

                TreeMap<String, String> valuePairs = new TreeMap<>();
                JSONObject jsonObject = j.getJSONObject(i);
                valuePairs.put("RangeId", jsonObject.getString("RangeId"));
                valuePairs.put("Range", jsonObject.getString("Range"));
                Advanced_AgeRanges_List.add(valuePairs);


            }
            //Toast.makeText(RegisterNewTest.this, "test pref lang" + Lang_List.toString(), Toast.LENGTH_LONG).show();
            Log.d("test pref lang", Advanced_AgeRanges_List.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Toast.makeText(RegisterNewTest.this, "test pref lang" +Lang_List.toString(), Toast.LENGTH_LONG).show();
        Log.d("test pref lang  2 :", Advanced_AgeRanges_List.toString());

        final SimpleAdapter AgeRangesAdapter = new SimpleAdapter(Advanced_Search.this, Advanced_AgeRanges_List
                , R.layout.autocomplete_row
                , new String[]{"RangeId", "Range"}
                , new int[]{R.id.row_id, R.id.row_name});


        advanced_search_Age_Range_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Languages_Dilaog = new Dialog(mContext);
                ;
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
                        //   Language_ID = Integer.parseInt(txt_lang_id.getText().toString());
                        advanced_search_Age_Range_txt.setText(txt_lang_name.getText().toString());
                        // Log.d("id of lang", "" + Language_ID);
                        Languages_Dilaog.dismiss();
                    }
                });


            }
        });


        Advanced_btn_search_page.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(), QuickSearchResults.class);
                intent1.putExtra("From_Em_Id", From_Em_Id);
                intent1.putExtra("To_Em_Id", To_Em_Id);
                intent1.putExtra("From_Reg_Id", From_Reg_Id);
                intent1.putExtra("To_Reg_Id", To_Reg_Id);
                intent1.putExtra("From_EmirateEnName", From_EmirateEnName);
                intent1.putExtra("From_RegionEnName", From_RegionEnName);
                intent1.putExtra("To_EmirateEnName", To_EmirateEnName);
                intent1.putExtra("To_RegionEnName", To_RegionEnName);
                startActivity(intent1);
            }
        });


    }   //  on create


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DILOG_ID) {
            return new DatePickerDialog(this, dPickerListener, year_x, month_x, day_x);
        }

        if (id == TIME_DIALOG_ID) {


            return new TimePickerDialog(this,
                    timePickerListener, hour, minute, false);
        }


        return null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Advanced_txt_beforeCal.setVisibility(View.INVISIBLE);

            // Toast.makeText(RegisterNewTest.this, "hi"+year_x+" :"+month_x+" :"+day_x, Toast.LENGTH_SHORT).show();
            String year_string = String.valueOf(year_x);
            String month_string = String.valueOf(month_x);
            String day_string = String.valueOf(day_x);
            Advanced_full_date = day_string + "/" + month_string + "/" + year_string;
            Advanced_txt_year.setText(Advanced_full_date);
            Log.d("Calendar test", Advanced_full_date);
        }
    };


    public void showDialogOnButtonClick() {

        Advanced_calendar_relative = (RelativeLayout) findViewById(R.id.advanced_search_calendar_relative);
        Advanced_calendar_relative.setOnClickListener(new View.OnClickListener() {
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
                    Advanced_before_Time.setVisibility(View.INVISIBLE);

                    // set current time into textview
                    Advanced_txt_time_selected.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(minute)));


                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    public void showTimeDialogOnButtonClick() {

        Advanced_time_relative = (RelativeLayout) findViewById(R.id.advanced_search_time_relative);
        Advanced_time_relative.setOnClickListener(new View.OnClickListener() {
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
        textView.setText("Advanced Search");
//        toolbar.setElevation(10);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onClick(View v) {


        if (v == Advanced_pickup_relative || v == quickSearch_pickUp) {


            Advanced_Emirates_List.clear();
            try {
                JSONArray j = new GetData().GetEmitares();
                for (int i = 0; i < j.length(); i++) {

                    TreeMap<String, String> valuePairs = new TreeMap<>();
                    JSONObject jsonObject = j.getJSONObject(i);
                    valuePairs.put("EmirateId", jsonObject.getString("EmirateId"));
                    valuePairs.put("EmirateEnName", jsonObject.getString("EmirateEnName"));
                    Advanced_Emirates_List.add(valuePairs);
                }
                Log.d("test Emirates ", Advanced_Emirates_List.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Advanced_EmAdapter = new SimpleAdapter(Advanced_Search.this, Advanced_Emirates_List
                    , R.layout.dialog_pick_emirate_lv_row
                    , new String[]{"EmirateId", "EmirateEnName"}
                    , new int[]{R.id.row_id_search, R.id.row_name_search});

            Advanced_MainDialog = new Dialog(Advanced_Search.this);
            Advanced_MainDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Advanced_MainDialog.setContentView(R.layout.main_search_dialog);
            TextView Address = (TextView) Advanced_MainDialog.findViewById(R.id.Lang_Dialog_txt_id);
            Address.setText("Pick Up");
            Advanced_btn_submit_pickUp = (Button) Advanced_MainDialog.findViewById(R.id.btn_submit_puckup);
//                MainDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            Advanced_txt_regions = (AutoCompleteTextView) Advanced_MainDialog.findViewById(R.id.mainDialog_Regions_auto);
            Advanced_spinner = (Spinner) Advanced_MainDialog.findViewById(R.id.Emirates_spinner);
            Advanced_spinner.setAdapter(Advanced_EmAdapter);

            Advanced_MainDialog.show();


            Advanced_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Advanced_txt_PickUp = "";
                    TextView txt_em_name = (TextView) view.findViewById(R.id.row_name_search);
                    TextView txt_em_id = (TextView) view.findViewById(R.id.row_id_search);
                    From_Em_Id = Integer.parseInt(txt_em_id.getText().toString());
                    From_EmirateEnName = txt_em_name.getText().toString();

                    Advanced_txt_PickUp += txt_em_name.getText().toString();
                    Advanced_txt_PickUp += ", ";
                    Log.d("id of lang", "" + From_Em_Id);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            Advanced_txt_regions.setOnClickListener(new View.OnClickListener() {
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
                            Advanced_Regions_List.add(valuePairs);
                        }
                        Log.d("test Regions search ", Advanced_Regions_List.toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    final SimpleAdapter RegAdapter = new SimpleAdapter(Advanced_Search.this, Advanced_Regions_List
                            , R.layout.dialog_pick_emirate_lv_row
                            , new String[]{"ID", "RegionEnName"}
                            , new int[]{R.id.row_id_search, R.id.row_name_search});

                    Advanced_txt_regions.setAdapter(RegAdapter);


                    Advanced_txt_regions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView txt_reg_name = (TextView) view.findViewById(R.id.row_name_search);
                            TextView txt_reg_id = (TextView) view.findViewById(R.id.row_id_search);
                            From_Reg_Id = Integer.parseInt(txt_reg_id.getText().toString());
                            From_RegionEnName = txt_reg_name.getText().toString();
                            Advanced_txt_regions.setText(txt_reg_name.getText().toString());
                            Advanced_txt_PickUp += txt_reg_name.getText().toString();
                        }
                    });


                }
            });


            Advanced_btn_submit_pickUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Advanced_txt_Selecet_Start_Point.setText(Advanced_txt_PickUp);
                    Advanced_MainDialog.dismiss();
                }
            });


        }


        if (v == Advanced_dropOff_relative || v == quickSearch_Dropoff) {


            Advanced_Emirates_List.clear();
            try {
                JSONArray j = new GetData().GetEmitares();
                for (int i = 0; i < j.length(); i++) {

                    TreeMap<String, String> valuePairs = new TreeMap<>();
                    JSONObject jsonObject = j.getJSONObject(i);
                    valuePairs.put("EmirateId", jsonObject.getString("EmirateId"));
                    valuePairs.put("EmirateEnName", jsonObject.getString("EmirateEnName"));
                    Advanced_Emirates_List.add(valuePairs);
                }
                Log.d("test Emirates ", Advanced_Emirates_List.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Advanced_EmAdapter = new SimpleAdapter(Advanced_Search.this, Advanced_Emirates_List
                    , R.layout.dialog_pick_emirate_lv_row
                    , new String[]{"EmirateId", "EmirateEnName"}
                    , new int[]{R.id.row_id_search, R.id.row_name_search});


            Advanced_MainDialog = new Dialog(Advanced_Search.this);
            Advanced_MainDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Advanced_MainDialog.setContentView(R.layout.main_search_dialog);

            TextView Address = (TextView) Advanced_MainDialog.findViewById(R.id.Lang_Dialog_txt_id);
            Address.setText("Drop Off");


            Advanced_btn_submit_pickUp = (Button) Advanced_MainDialog.findViewById(R.id.btn_submit_puckup);
//                MainDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            Advanced_txt_regions = (AutoCompleteTextView) Advanced_MainDialog.findViewById(R.id.mainDialog_Regions_auto);
            Advanced_spinner = (Spinner) Advanced_MainDialog.findViewById(R.id.Emirates_spinner);
            Advanced_spinner.setAdapter(Advanced_EmAdapter);

            Advanced_MainDialog.show();


            Advanced_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Advanced_txt_Drop_Off = "";
                    TextView txt_em_name = (TextView) view.findViewById(R.id.row_name_search);
                    TextView txt_em_id = (TextView) view.findViewById(R.id.row_id_search);
                    To_Em_Id = Integer.parseInt(txt_em_id.getText().toString());
                    To_EmirateEnName = txt_em_name.getText().toString();

                    Advanced_txt_Drop_Off += txt_em_name.getText().toString();
                    Advanced_txt_Drop_Off += ", ";
                    Log.d("id of lang", "" + To_Em_Id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            Advanced_txt_regions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    GetData getData = new GetData();
                    try {
                        JSONArray jsonArray = getData.GetRegionsByEmiratesID(To_Em_Id);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            TreeMap<String, String> valuePairs = new TreeMap<>();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            valuePairs.put("ID", jsonObject.getString("ID"));
                            valuePairs.put("RegionEnName", jsonObject.getString("RegionEnName"));
                            Advanced_Regions_List.add(valuePairs);
                        }
                        Log.d("test Regions search ", Advanced_Regions_List.toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    final SimpleAdapter RegAdapter = new SimpleAdapter(Advanced_Search.this, Advanced_Regions_List
                            , R.layout.dialog_pick_emirate_lv_row
                            , new String[]{"ID", "RegionEnName"}
                            , new int[]{R.id.row_id_search, R.id.row_name_search});

                    Advanced_txt_regions.setAdapter(RegAdapter);


                    Advanced_txt_regions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView txt_reg_name = (TextView) view.findViewById(R.id.row_name_search);
                            TextView txt_reg_id = (TextView) view.findViewById(R.id.row_id_search);
                            To_Reg_Id = Integer.parseInt(txt_reg_id.getText().toString());
                            To_RegionEnName = txt_reg_name.getText().toString();
                            Advanced_txt_regions.setText(txt_reg_name.getText().toString());
                            Advanced_txt_Drop_Off += txt_reg_name.getText().toString();
                        }
                    });


                }
            });


            Advanced_btn_submit_pickUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Advanced_txt_Select_Dest.setText(Advanced_txt_Drop_Off);
                    Advanced_MainDialog.dismiss();
                }
            });


        }


    }
}    //class


