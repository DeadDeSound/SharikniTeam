package com.example.nezarsaleh.shareknitest.Arafa.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Fantom on 05/09/2015.
 */
public class ImageDecoder {

    public Bitmap stringRequest(String url, final ImageView im, final Context context) {
        String url1 = "http://www.sharekni-web.sdg.ae/_mobfiles/CLS_MobAccount.asmx/GetPhotoPath?s_FileName=";
        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(context.getApplicationContext()).getRequestQueue();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1 + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.replaceAll("<base64Binary xmlns=\"http://tempuri.org/\">", "");
                        response = response.replaceAll("</base64Binary>", "");
                        // Display the first 500 characters of the response string.
                        try {
                            String data = response.substring(40);
                            //decodeBase64(data);
                            byte[] decodedByte = Base64.decode(data, Base64.DEFAULT);
                            Bitmap decoded = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                            im.setImageBitmap(decoded);
                            try {
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error : ", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //Add a request (in this example, called stringRequest) to your RequestQueue.
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        return null;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, Base64.DEFAULT);
        Bitmap decoded = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        try {
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return decoded;
    }
}
