package com.example.nezarsaleh.shareknitest.LogIN;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.VolleySingleton;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestDriverDataModel;
import com.example.nezarsaleh.shareknitest.HomePage;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nezar on 9/6/2015.
 */
public class RegisterJsonParse {
    String url2 = "http://www.sharekni-web.sdg.ae/_mobfiles/CLS_MobAccount.asmx/Get?id=";
    final JSONArray[] myJsonArray = new JSONArray[1];
    JSONObject jsonObject = null;

    String Nat = null;
    String Lang = null;
    String datatest = null;

    public void stringRequest(String url, final Context context, String nat, final String lang) {
        this.Nat = nat;
        this.Lang = lang;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response = response.replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
                        response = response.replaceAll("<string xmlns=\"http://MobAccount.org/\">\"", "");
                        response = response.replaceAll("\"</string>", "");
                        // Display the first 500 characters of the response string.
                        String data = response.substring(40);
                        if (!data.equals("-2")&& !data.equals("-1")){
                            SharedPreferences myPrefs = context.getSharedPreferences("myPrefs", 0);
                            SharedPreferences.Editor editor = myPrefs.edit();
                            editor.putString("account_id", String.valueOf(data));
                            editor.putString("account_type", lang);
                            editor.commit();
                            Intent in = new Intent(context, HomePage.class);
                            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(in);
                        }else {
                            Toast.makeText(context, "Cannot Register , Check Phone Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error : ", error.toString());
            }
        });
//      Add a request (in this example, called stringRequest) to your RequestQueue.
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);



    }

    public void stringRequest2(String url, final Context context) {
        // Request a string response from the provided URL.


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2 + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.replaceAll("<string xmlns=\"http://MobAccount.org/\">", "");
                        response = response.replaceAll("</string>", "");
                        String data = response.substring(40);
                        try {

                            myJsonArray[0] = new JSONArray(data);
                            Log.d("First Array : ", myJsonArray[0].toString());
                            jsonObject = myJsonArray[0].getJSONObject(0);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonParse(jsonObject, context);

                        Log.d("Responce get id  : ", data);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error : ", error.toString());
            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void jsonParse(JSONObject json, final Context context) {
        try {
            BestDriverDataModel item = new BestDriverDataModel(Parcel.obtain());
            item.setID(json.getInt("ID"));
            item.setName(json.getString("FirstName"));
            item.setPhotoURL(json.getString("PhotoPath"));
            item.setNationality(Nat);
            item.setLanguage(Lang);
            item.setRating(json.getInt("PrefferedLanguage"));
            Intent in = new Intent(context, HomePage.class);
            Bundle b = new Bundle();
            b.putParcelable("Data", item);
            in.putExtras(b);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(in);
            Log.d("Item Json : ", item.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


}
