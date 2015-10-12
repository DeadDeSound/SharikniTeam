package com.example.nezarsaleh.shareknitest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.AppController;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.CircularNetworkImageView;

import java.util.List;

/**
 * Created by nezar on 9/20/2015.
 */
public class QuickSearchResultAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<QuickSearchDataModel> searchItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String URL = "http://www.sharekni-web.sdg.ae/uploads/personalphoto/";


    public QuickSearchResultAdapter(Activity activity, List<QuickSearchDataModel> searchItems) {
        this.activity = activity;
        this.searchItems = searchItems;
    }


    @Override
    public int getCount() {
        return searchItems.size();
    }

    @Override
    public Object getItem(int position) {
        return searchItems.get(position);
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
            convertView = inflater.inflate(R.layout.quick_search_list_item_2, null);


        if (imageLoader == null) imageLoader = AppController.getInstance().getImageLoader();

        CircularNetworkImageView Photo = (CircularNetworkImageView) convertView.findViewById(R.id.search_list_photo);
        TextView SDG_Route_Start_FromTime = (TextView) convertView.findViewById(R.id.SDG_Route_Start_FromTime);
        TextView DriverEnName = (TextView) convertView.findViewById(R.id.DriverEnName);
        TextView Nationality_en = (TextView) convertView.findViewById(R.id.Nationality_en);
        TextView SDG_RouteDays = (TextView) convertView.findViewById(R.id.search_results_days);
        TextView Best_Drivers_Item_rate= (TextView) convertView.findViewById(R.id.Best_Drivers_Item_rate);

        final QuickSearchDataModel item = searchItems.get(position);
        Photo.setImageUrl(URL + item.getAccountPhoto(), imageLoader);
        SDG_Route_Start_FromTime.setText(item.getSDG_Route_Start_FromTime());
        DriverEnName.setText(item.getAccountName());
        Nationality_en.setText(item.getNationality_en());
        SDG_RouteDays.setText(item.getSDG_RouteDays());
        Best_Drivers_Item_rate.setText(item.getRating());



        return convertView;


    }
}
