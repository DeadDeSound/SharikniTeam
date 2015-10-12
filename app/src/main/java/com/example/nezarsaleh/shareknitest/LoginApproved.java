package com.example.nezarsaleh.shareknitest;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.VolleySingleton;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestDriverDataModel;


import org.json.JSONException;
import org.json.JSONObject;

public class LoginApproved extends AppCompatActivity {
    EditText username, password;
    Button loginBtn;
    String user, pass;
    TextView txt_forgetpass, txt_noaccountsignup;
    String url = "http://www.sharekni-web.sdg.ae/_mobfiles/CLS_MobAccount.asmx/CheckLogin?";
    static LoginApproved loginActivity;
    private Toolbar toolbar;
    protected static ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginActivity = this;
        setContentView(R.layout.login_design_approved);
       initToolbar();
//        AndroidBug5497Workaround.assistActivity(this);
//
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.TYPE_INPUT_METHOD);
//
        username = (EditText) findViewById(R.id.txt_email_id);
        password = (EditText) findViewById(R.id.txt_pass_id);
        loginBtn = (Button) findViewById(R.id.btn_login);
        txt_forgetpass = (TextView) findViewById(R.id.login_forgertpass);
        txt_noaccountsignup = (TextView) findViewById(R.id.login_NoAccountsignup);
        username.setHint("Your Email(i.e Mohamed@gmail.com)");
        password.setHint("Password");

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setHint("");

            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setHint("");
            }
        });

//        pDialog = new ProgressDialog(LoginApproved.this);
//        pDialog.setMessage("Loading" + "...");
//        pDialog.setCancelable(false);
//        pDialog.setCanceledOnTouchOutside(false);

        txt_noaccountsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterNewTest.class);
                startActivity(intent);
            }
        });

        txt_forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if (user == null || pass.length() <= 4) {
                    Toast.makeText(getBaseContext(), "Check ur Username and Password", Toast.LENGTH_SHORT).show();
                } else {
                    new loginProcces().execute();
                }
            }
        });
    }

    public static LoginApproved getInstance() {
        return loginActivity;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_approved, menu);
        return true;
    }


    private class loginProcces extends AsyncTask {

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(LoginApproved.this);
            pDialog.setMessage("Loading" + "...");
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            LoginApproved.pDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url+ "username=" + user + "&password=" + pass,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            response = response.replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
                            response = response.replaceAll("<string xmlns=\"http://MobAccount.org/\">", "");
                            response = response.replaceAll("</string>", "");
                            // Display the first 500 characters of the response string.
                            try {
                                String data = response.substring(40);
                                Log.d("First Array Json : ", data);
                                JSONObject json;
                                json = new JSONObject(data);
                                Log.d("Json : ", json.toString());
                                try {
                                    BestDriverDataModel item = new BestDriverDataModel(Parcel.obtain());
                                    SharedPreferences myPrefs = getBaseContext().getSharedPreferences("myPrefs", 0);
                                    SharedPreferences.Editor editor = myPrefs.edit();

                                    editor.putString("account_id", String.valueOf(json.getInt("ID")));
                                    editor.putString("account_type", json.getString("AccountStatus"));

                                    editor.commit();
                                    item.setID(json.getInt("ID"));
                                    item.setName(json.getString("FirstName"));
                                    item.setPhotoURL(json.getString("PhotoPath"));
                                    item.setNationality(json.getString("NationalityEnName"));
                                    item.setRating(json.getInt("PrefferedLanguage"));
                                    Intent in = new Intent(getBaseContext(), HomePage.class);
                                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    getBaseContext().startActivity(in);
                                    Log.d("Item Json : ", item.getName());
                                } catch (JSONException | NullPointerException e) {
                                    e.printStackTrace();
                                }                            } catch (StringIndexOutOfBoundsException e) {
                                Toast.makeText(getBaseContext(), "Wrong Username Or Passsword", Toast.LENGTH_SHORT).show();
                                LoginApproved.pDialog.dismiss();
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getBaseContext(), "Wrong Username Or Passsword", Toast.LENGTH_SHORT).show();
                                LoginApproved.pDialog.dismiss();


                                Log.d("Error Json : ", e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (LoginApproved.pDialog != null) {
                        LoginApproved.pDialog.dismiss();
                        LoginApproved.pDialog = null;
                    }
                    Toast.makeText(getBaseContext(), "Network Error !", Toast.LENGTH_SHORT).show();
                    Log.d("Error Json : ", error.toString());
                }
            });
            VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest);
            return null;
        }
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


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView) toolbar.findViewById(R.id.mytext_appbar);
        textView.setText("Login");
//        toolbar.setElevation(10);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
