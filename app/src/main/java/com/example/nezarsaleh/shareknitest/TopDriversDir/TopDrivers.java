package com.example.nezarsaleh.shareknitest.TopDriversDir;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nezarsaleh.shareknitest.R;


public class TopDrivers extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_drivers);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final DataListModel[] dataListModel = new DataListModel[8];
        DataListModel object1 = new DataListModel();

        object1.setTitle("Nezar");
        object1.setDesc("Devops");
        object1.setImage(R.drawable.ic_launcher);
        dataListModel[0]=object1;


        DataListModel object2 =  new DataListModel();
        object2.setTitle("Nezar");
        object2.setDesc("Devops");
        object2.setImage(R.drawable.ic_launcher);
        dataListModel[1]=object2;


        DataListModel object3 =  new DataListModel();
        object3.setTitle("Nezar");
        object3.setDesc("Devops");
        object3.setImage(R.drawable.ic_launcher);
        dataListModel[2]=object3;

        DataListModel object4 =  new DataListModel();
        object4.setTitle("Nezar");
        object4.setDesc("Devops");
        object4.setImage(R.drawable.ic_launcher);
        dataListModel[3]=object4;


        DataListModel object5 =  new DataListModel();
        object5.setTitle("Nezar");
        object5.setDesc("Devops");
        object5.setImage(R.drawable.ic_launcher);
        dataListModel[4]=object5;

        DataListModel object6 =  new DataListModel();
        object6.setTitle("Nezar");
        object6.setDesc("Devops");
        object6.setImage(R.drawable.ic_launcher);
        dataListModel[5]=object6;



        DataListModel object7 =  new DataListModel();
        object7.setTitle("Nezar");
        object7.setDesc("Devops");
        object7.setImage(R.drawable.ic_launcher);
        dataListModel[6]=object7;


        DataListModel object8 =  new DataListModel();
        object8.setTitle("Nezar");
        object8.setDesc("Devops");
        object8.setImage(R.drawable.ic_launcher);
        dataListModel[7]=object8;

        // specify an adapter (see also next example)
        mAdapter = new TopDriversRecyclerAdapter(dataListModel);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_drivers, menu);
        return true;
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
}
