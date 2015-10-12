package com.example.nezarsaleh.shareknitest.Map;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.VolleySingleton;
import com.example.nezarsaleh.shareknitest.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;



/**
 * Created by nezar on 9/4/2015.
 */


public class MapJsonParse extends Activity {

    final JSONArray[] myJsonArray = new JSONArray[1];
    HashMap<String, MarkerHolder> markerHolderMap = new HashMap<String, MarkerHolder>();

    public void stringRequest(String url, final GoogleMap map, final Context context) {
        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(context.getApplicationContext()).getRequestQueue();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
                        response = response.replaceAll("<string xmlns=\"http://tempuri.org/\">", "");
                        response = response.replaceAll("</string>", "");
                        // Display the first 500 characters of the response string.
                        String data = response.substring(40);
                        try {
                            myJsonArray[0] = new JSONArray(data);
                            Log.d("First Array : ", myJsonArray[0].toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonParse(myJsonArray[0], map, context);
                        //tv.setText("Response is: " + response.substring(40));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error : ", error.toString());
                //tv.setText("That didn't work! : " + error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
//      Add a request (in this example, called stringRequest) to your RequestQueue.
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

//        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//
//            @Override
//            public View getInfoWindow(Marker marker) {
//                return null;
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                View v = getLayoutInflater().inflate(R.layout.info_window_approved, null);
//                LatLng latLng = marker.getPosition();
//                String title = marker.getTitle();
//                TextView emirateArName = (TextView) v.findViewById(R.id.emirateAr_name_id);
//                TextView emirateEnName = (TextView) v.findViewById(R.id.emirateEn_name_id);
//                TextView emirateLat = (TextView) v.findViewById(R.id.txt_map_lat);
//                TextView emiratelong = (TextView) v.findViewById(R.id.txt_map_long);
//                String lat = String.valueOf(latLng.latitude).substring(0, 7);
//                String lon = String.valueOf(latLng.longitude).substring(0, 7);
////                placename.setText(marker.getTitle());
////                tv_snippet.setText(marker.getSnippet());
//                emirateLat.setText(lat);
//                emiratelong.setText(lon);
//                emirateArName.setText(title);
//                // Toast.makeText(MapJsonParse.this, title, Toast.LENGTH_SHORT).show();
//                //emirateEnName.setText("Dubai");
////                im_info.setImageResource(imageplace);
//                return v;
//            }
//        });
    }


    public void jsonParse(JSONArray jArray, GoogleMap map, final Context context) {
        final MapDataModel[] data = new MapDataModel[jArray.length()];
        try {
            JSONObject json = null;
            for (int i = 0; i < jArray.length(); i++) {
                try {

                    MapDataModel item = new MapDataModel();

                    json = jArray.getJSONObject(i);
                    item.setFromRegionArName(json.getString("FromRegionArName"));
                    item.setFromRegionEnName(json.getString("FromRegionEnName"));
                    item.setLongitude(json.getDouble("StartLongitude"));
                    item.setLatitude(json.getDouble("StartLatitude"));

                    Log.d("Reg Ar", json.getString("FromRegionArName"));
                    Log.d("Reg En",json.getString("FromRegionEnName"));


                    data[i] = item;
                    final Marker markerZero = map.addMarker(new MarkerOptions().
                            title(item.getFromRegionArName()).snippet(item.getFromRegionEnName()).
                            position(new LatLng(data[i].latitude, data[i].longitude))

                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.anchor)));


                    map.moveCamera(CameraUpdateFactory.newLatLngZoom
                            (new LatLng(data[i].latitude, data[i].longitude), 12.0f));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        } catch (NullPointerException e) {

        }
    }

    public class MarkerHolder {
        public String nameAr;
        public String nameEn;


        public MarkerHolder(String na, String ne) {
            nameAr = na;
            nameEn = ne;
        }
    }

}
