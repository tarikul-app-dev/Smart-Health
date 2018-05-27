package com.example.aasims.smarthealth.fragments;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.adapter.DoctorAdapter;
import com.example.aasims.smarthealth.database.DatabaseHelper;
import com.example.aasims.smarthealth.model.Doctor;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDoctor extends Fragment {

    private RecyclerView recyclerView;
    private DoctorAdapter adapter;
    private List<Doctor> mDoctorList;
    Spinner spinSearchDoctor;
    Button  btnSearchDoctor;
    DatabaseHelper databaseHelper;
    private RecyclerView.LayoutManager layoutManager;
    EditText edtSearchDoc;

    public SearchDoctor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_doctor, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        spinSearchDoctor = (Spinner)rootView.findViewById(R.id.spin_search_by_doctor);
        btnSearchDoctor = (Button)rootView.findViewById(R.id.btn_search_doctor);
        edtSearchDoc = (EditText)rootView.findViewById(R.id.edt_search_doctor);
        databaseHelper = new DatabaseHelper(getActivity());
        mDoctorList = new ArrayList<>();

        //defualt view doctor list
//        mDoctorList = databaseHelper.getDoctorList( );
//        adapter = new DoctorAdapter(getActivity(), mDoctorList);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);

        btnSearchDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Search by category
                String searchCtg = spinSearchDoctor.getSelectedItem().toString();
                String searchItem = edtSearchDoc.getText().toString();

                if(searchCtg.equals("Name")){
                    mDoctorList = databaseHelper.getDoctorListN(searchItem);
                }
                if(searchCtg.equals("Type")){
                    mDoctorList = databaseHelper.getDoctorListT(searchItem);
                }
                if(searchCtg.equals("Address")){
                    mDoctorList = databaseHelper.getDoctorListA(searchItem);
                }
                adapter = new DoctorAdapter(getActivity(), mDoctorList);
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        });








        return rootView;
    }


}
