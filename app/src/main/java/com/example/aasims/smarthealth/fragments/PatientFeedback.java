package com.example.aasims.smarthealth.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aasims.smarthealth.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientFeedback extends Fragment {

    Button sub;


    public PatientFeedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_patient_feedback, container, false);
       sub = (Button) v.findViewById(R.id.submit);
       sub.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Snackbar.make(getActivity().findViewById(R.id.submit), "Feedback is submited", Snackbar.LENGTH_SHORT).show();
           }
       });
       return v;
    }

}
