package com.example.aasims.smarthealth.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.adapter.DoctorAdapter;
import com.example.aasims.smarthealth.adapter.PatientAdapter;
import com.example.aasims.smarthealth.database.DatabaseHelper;
import com.example.aasims.smarthealth.model.Doctor;
import com.example.aasims.smarthealth.model.Patient;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPatient extends Fragment {
    private RecyclerView recyclerView;
    private PatientAdapter adapter;
    private List<Patient> mPatientList;
    DatabaseHelper databaseHelper;
    private RecyclerView.LayoutManager layoutManager;

    public ViewPatient() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView= inflater.inflate(R.layout.fragment_view_patient, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        mPatientList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getActivity());

        mPatientList = databaseHelper.getAllPatient();
        adapter = new PatientAdapter(getActivity(), mPatientList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

       return rootView;
    }

}
