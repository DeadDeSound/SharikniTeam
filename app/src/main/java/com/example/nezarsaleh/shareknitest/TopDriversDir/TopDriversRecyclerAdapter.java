package com.example.nezarsaleh.shareknitest.TopDriversDir;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.R;


/**
 * Created by nezar on 8/11/2015.
 */
public class TopDriversRecyclerAdapter extends RecyclerView.Adapter<TopDriversRecyclerAdapter.ViewHolder> {
    private  DataListModel[] mDataset;


    RecyclerView recyclerView;

    public TopDriversRecyclerAdapter(DataListModel[] myDataset) {
        mDataset = myDataset;
    }




    public TopDriversRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_drivers_recycler_custom_row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {


        DataListModel dataListModel = mDataset[position];
        holder.txtHeader.setText(dataListModel.getTitle());
        holder.txtFooter.setText(dataListModel.getDesc());
        holder.imageView.setImageResource(dataListModel.getImage());



    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            imageView = (ImageView) v.findViewById(R.id.icon);


        }
    }










    public int getItemCount() {
        return mDataset.length;
    }




}
