package com.example.nezarsaleh.shareknitest.OnBoardDir;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nezarsaleh.shareknitest.Arafa.Activities.BestDriversBeforeLogin;
import com.example.nezarsaleh.shareknitest.Arafa.Activities.BestRideBeforeLogin;
import com.example.nezarsaleh.shareknitest.R;


/**
 * Created by nezar on 8/11/2015.
 */



public class OnboardingFragment2 extends Fragment {

    ImageView im_best_rides;
    ImageView im_best_drivers;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.onboard_new_3
                ,
                container,
                false
        );
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        im_best_drivers= (ImageView) view.findViewById(R.id.im_best_drivers);
        im_best_rides = (ImageView) view.findViewById(R.id.im_best_rides);

        im_best_rides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BestRideBeforeLogin.class);
                startActivity(intent);
            }
        });

        im_best_drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BestDriversBeforeLogin.class);
                startActivity(intent);
            }
        });

// im_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(getActivity(), RegisterNewTest.class);
//                startActivity(intent);
//            }
//        });
//
//        im_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(getActivity(), LoginApproved.class);
//                startActivity(intent);
//            }
//        });
    }
}