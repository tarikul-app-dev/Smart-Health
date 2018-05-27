package com.example.aasims.smarthealth.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.database.DatabaseHelper;
import com.example.aasims.smarthealth.model.Patient;

import java.util.ArrayList;
import java.util.List;

import static com.example.aasims.smarthealth.utill.SaveInLocalMemory.getValueFromSharedPreferences;


public class PatientProfile extends Fragment {

    String patientId = "";
    DatabaseHelper databaseHelper;
    ArrayList<Patient> patientList;
    EditText edtPatientName,edtAddress,edtContactNo,edtEmailID;
    View rootView;
    int position = 0 ;
    Button btnEdit,btnUpdate,btnCancel;
    LinearLayout linLayoutEdit;
    private Patient user;

    public PatientProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patientId = getValueFromSharedPreferences("patient_id",getActivity());
        databaseHelper = new DatabaseHelper(getActivity());
        patientList = new ArrayList<Patient>();
        user = new Patient();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        rootView = inflater.inflate(R.layout.fragment_patient_profile, container, false);
        edtPatientName = rootView.findViewById(R.id.edt_user_name);
        edtAddress = rootView.findViewById(R.id.edt_address);
        edtContactNo = rootView.findViewById(R.id.edt_contact);
        edtEmailID = rootView.findViewById(R.id.edt_email_id);
        linLayoutEdit = (LinearLayout)rootView.findViewById(R.id.linear_layout_edit);
        btnEdit = (Button)rootView.findViewById(R.id.btn_edit);
        btnUpdate = (Button)rootView.findViewById(R.id.btn_update);
        btnCancel = (Button)rootView.findViewById(R.id.btn_cancel);

        patientList = databaseHelper.getUser(patientId);
        String patientName = patientList.get(position).getName();
        edtPatientName.setText(patientName);

        String address = patientList.get(position).getAddress();
        edtAddress.setText(address);
        String contactNo = patientList.get(position).getContactNo();
        edtContactNo.setText(contactNo);

        String email = patientList.get(position).getEmail();
        edtEmailID.setText(email);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linLayoutEdit.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.GONE);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientName = edtPatientName.getText().toString();
                String address = edtAddress.getText().toString();
                String contactNo = edtContactNo.getText().toString();
                String email = edtEmailID.getText().toString();

                user.setName(patientName);
                user.setAddress(address);
                user.setContactNo(contactNo);
                user.setEmail(email);
                user.setPatientId(patientId);
                if(!email.equals("")){
                    boolean isUpdate = databaseHelper.updateUser(user);
                    if (isUpdate){
                        Toast.makeText(getActivity(),"Your Info Updated Success",Toast.LENGTH_SHORT).show();
                        edtPatientName.setText("");
                        edtAddress.setText("");
                        edtContactNo.setText("");
                        edtEmailID.setText("");
                    }
                }else {
                    Toast.makeText(getActivity(),"Empty field not allowed",Toast.LENGTH_SHORT).show();
                }



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linLayoutEdit.setVisibility(View.GONE);
                btnEdit.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }



    }
