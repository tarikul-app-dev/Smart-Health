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
import com.example.aasims.smarthealth.adapter.DiseaseAdapter;
import com.example.aasims.smarthealth.adapter.PatientAdapter;
import com.example.aasims.smarthealth.database.DatabaseHelper;
import com.example.aasims.smarthealth.model.Patient;
import com.example.aasims.smarthealth.model.ViewDiseaseM;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDisease extends Fragment {
    private RecyclerView recyclerView;
    private DiseaseAdapter adapter;
    private List<ViewDiseaseM> mDiseaseList;
    DatabaseHelper databaseHelper;
    private RecyclerView.LayoutManager layoutManager;

    public ViewDisease() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView= inflater.inflate(R.layout.fragment_view_disease, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        mDiseaseList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getActivity());

         ViewDiseaseM viewDiseaseM1 = new ViewDiseaseM();
        viewDiseaseM1.setDisId("2001");
        viewDiseaseM1.setName("Heart Valve Disease");
        viewDiseaseM1.setSymptom("palpitition,chest,pounding");
        viewDiseaseM1.setType("Heart");
         mDiseaseList.add(viewDiseaseM1);

        ViewDiseaseM viewDiseaseM2 = new ViewDiseaseM();
        viewDiseaseM2.setDisId("2002");
        viewDiseaseM2.setName("Heart Valve Disease");
        viewDiseaseM2.setSymptom("palpitition,chest,pounding");
        viewDiseaseM2.setType("Heart");
        mDiseaseList.add(viewDiseaseM2);

        ViewDiseaseM viewDiseaseM3 = new ViewDiseaseM();
        viewDiseaseM3.setDisId("2002");
        viewDiseaseM3.setName("Heart Valve Disease");
        viewDiseaseM3.setSymptom("palpitition,chest,pounding");
        viewDiseaseM3.setType("Heart");
        mDiseaseList.add(viewDiseaseM3);


        adapter = new DiseaseAdapter(getActivity(), mDiseaseList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

       return rootView;
    }

}
