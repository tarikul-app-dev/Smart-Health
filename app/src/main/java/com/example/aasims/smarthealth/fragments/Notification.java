package com.example.aasims.smarthealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.adapter.NotificationAdapter;
import com.example.aasims.smarthealth.adapter.PatientAdapter;
import com.example.aasims.smarthealth.database.DatabaseHelper;
import com.example.aasims.smarthealth.model.NotificationM;
import com.example.aasims.smarthealth.model.Patient;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {
    private RecyclerView recyclerView;

    private List<NotificationM> notificationList;
    DatabaseHelper databaseHelper;
    private RecyclerView.LayoutManager layoutManager;
    NotificationAdapter adapter;

    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView= inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        notificationList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getActivity());



        notificationList = databaseHelper.getAllNotification();

        if(notificationList.size()>0){
            adapter = new NotificationAdapter(getActivity(),notificationList);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }


       return rootView;
    }

}
