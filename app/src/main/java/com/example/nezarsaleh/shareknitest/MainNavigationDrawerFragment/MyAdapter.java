package com.example.nezarsaleh.shareknitest.MainNavigationDrawerFragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nezarsaleh.shareknitest.R;
import com.example.nezarsaleh.shareknitest.TopDriversDir.DataListModel;


/**
 * Created by nezar on 8/4/2015.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private DataListModel[] mDataset;

    RecyclerView recyclerView;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            imageView = (ImageView) v.findViewById(R.id.icon);
            imageView.setOnClickListener(this);
            txtHeader.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Item clicked at" + getLayoutPosition(), Toast.LENGTH_SHORT).show();

            delete(getAdapterPosition());
        }
    } // View Holder Class


    /*
    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }




    */


    public void delete(int position){
        mDataset[position]=null;
        notifyItemRemoved(position);


    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(DataListModel[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_drawer_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Log.d("Nezar", "onCreateHolder called");
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        DataListModel dataListModel = mDataset[position];
        holder.txtHeader.setText(dataListModel.getTitle());
        holder.imageView.setImageResource(dataListModel.getImage());


        Log.d("Nezar", "onBindViewHolder called" + position);


        //  First Method To use on Click Listener
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Item clicked at"+position, Toast.LENGTH_SHORT).show();
//            }
//        });


    } // onBind View

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}