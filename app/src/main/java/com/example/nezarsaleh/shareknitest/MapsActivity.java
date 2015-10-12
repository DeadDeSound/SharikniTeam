package com.example.nezarsaleh.shareknitest;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Map.MapJsonParse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                (new LatLng(25.0014511,55.3588621), 8.25f));


        MapJsonParse mapJsonParse =  new MapJsonParse();
        String urlmap = "http://sharekni-web.sdg.ae/_mobfiles/CLS_MobRoute.asmx/GetMapLookup";
        mapJsonParse.stringRequest(urlmap,mMap,this);


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            View v;

            @Override
            public View getInfoWindow(Marker marker) {

                v = getLayoutInflater().inflate(R.layout.info_window_approved, null);
                LatLng latLng = marker.getPosition();
                String title = marker.getTitle();
                String snippet = marker.getSnippet();

                Log.d("Reg Ar2", marker.getTitle());
                Log.d("Reg En2",marker.getSnippet());




                TextView emirateArName = (TextView) v.findViewById(R.id.emirateAr_name_id);
                TextView emirateEnName = (TextView) v.findViewById(R.id.emirateEn_name_id);
                TextView emirateLat = (TextView) v.findViewById(R.id.txt_map_lat);
                TextView emiratelong = (TextView) v.findViewById(R.id.txt_map_long);

                String lat = String.valueOf(latLng.latitude).substring(0, 7);
                String lon = String.valueOf(latLng.longitude).substring(0, 7);
//                placename.setText(marker.getTitle());
//                tv_snippet.setText(marker.getSnippet());
                emirateLat.setText(lat);
                emiratelong.setText(lon);
                emirateArName.setText(title);
                emirateEnName.setText(snippet);

                
                // Toast.makeText(MapJsonParse.this, title, Toast.LENGTH_SHORT).show();
                //emirateEnName.setText("Dubai");
//                im_info.setImageResource(imageplace);


                return v;

            }


            @Override
            public View getInfoContents(Marker marker) {


                return v;
            }
        });



    }











}
