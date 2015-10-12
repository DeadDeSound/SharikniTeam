package com.example.nezarsaleh.shareknitest.Arafa.DataModelAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.nezarsaleh.shareknitest.Arafa.Activities.Profile;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.AppController;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.CircularNetworkImageView;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestDriverDataModel;
import com.example.nezarsaleh.shareknitest.R;


import java.util.List;

    public class BestDriverDataModelAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<BestDriverDataModel> driverItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String URL = "http://www.sharekni-web.sdg.ae/uploads/personalphoto/";

    public BestDriverDataModelAdapter(Activity activity, List<BestDriverDataModel> driverItems) {
        this.activity = activity;
        this.driverItems = driverItems;
    }

    @Override
    public int getCount() {
        return driverItems.size();
    }

    @Override
    public Object getItem(int location) {
        return driverItems.get(location);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.best_drivers_list_item, null);


        if (imageLoader == null) imageLoader = AppController.getInstance().getImageLoader();

            CircularNetworkImageView Photo = (CircularNetworkImageView) convertView.findViewById(R.id.ivProfile);
        TextView Name = (TextView) convertView.findViewById(R.id.tvName);
        TextView Nat = (TextView) convertView.findViewById(R.id.tvNat);
        TextView Rat = (TextView) convertView.findViewById(R.id.Best_Drivers_Item_rate);

        //RatingBar rating = (RatingBar) convertView.findViewById(R.id.ratingBar);

        Button Best_Drivers_Item_Details= (Button) convertView.findViewById(R.id.Best_Drivers_Item_Details);

        final BestDriverDataModel m = driverItems.get(position);
        Photo.setImageUrl(URL + m.getPhotoURL(), imageLoader);
        Name.setText(m.getName());
        Nat.setText(m.getNationality());
        Rat.setText(Integer.toString(m.getRating()));

        Best_Drivers_Item_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Profile.class);
                intent.putExtra("DriverID",m.getID());
                activity.startActivity(intent);
            }
        });
        return convertView;
    }

}