package com.example.aasims.smarthealth.fragments;

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
import com.example.aasims.smarthealth.model.Doctor;
import com.example.aasims.smarthealth.model.Patient;

import java.util.ArrayList;

import static com.example.aasims.smarthealth.utill.SaveInLocalMemory.getValueFromSharedPreferences;


public class DoctorProfile extends Fragment {

    String doctorId = "";
    DatabaseHelper databaseHelper;
    ArrayList<Doctor> doctorList;
    EditText edtDocName,edtDocAddress,edtDocContactNo,edtDocType;
    View rootView;
    int position = 0 ;
    Button btnEdit,btnUpdate,btnCancel;
    LinearLayout linLayoutEdit;
    private Doctor doctor;

    public DoctorProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorId = getValueFromSharedPreferences("doctor_id",getActivity());
        databaseHelper = new DatabaseHelper(getActivity());
        doctorList = new ArrayList<Doctor>();
        doctor = new Doctor();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        rootView = inflater.inflate(R.layout.fragment_doctor_profile, container, false);
        edtDocName = rootView.findViewById(R.id.edt_doc_name);
        edtDocAddress = rootView.findViewById(R.id.edt_doc_address);
        edtDocContactNo = rootView.findViewById(R.id.edt_doc_contact);
        edtDocType = rootView.findViewById(R.id.edt_doc_type);
        linLayoutEdit = (LinearLayout)rootView.findViewById(R.id.linear_layout_edit);
        btnEdit = (Button)rootView.findViewById(R.id.btn_edit);
        btnUpdate = (Button)rootView.findViewById(R.id.btn_update);
        btnCancel = (Button)rootView.findViewById(R.id.btn_cancel);

        doctorList = databaseHelper.getDoctor(doctorId);
        String docterName = doctorList.get(position).getName();
        edtDocName.setText(docterName);

        String address = doctorList.get(position).getAddress();
        edtDocAddress.setText(address);

        String contactNo = doctorList.get(position).getMob();
        edtDocContactNo.setText(contactNo);

        String type = doctorList.get(position).getType();
        edtDocType.setText(type);


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
                String docterName = edtDocName.getText().toString();
                String address = edtDocAddress.getText().toString();
                String contactNo = edtDocContactNo.getText().toString();
                String type = edtDocType.getText().toString();

                doctor.setName(docterName);
                doctor.setAddress(address);
                doctor.setMob(contactNo);
                doctor.setType(type);
                doctor.setDoctorId(doctorId);
                if(!contactNo.equals("")){
                    boolean isUpdate = databaseHelper.updateDoctor(doctor);
                    if (isUpdate){
                        Toast.makeText(getActivity(),"Your Info Updated Success",Toast.LENGTH_SHORT).show();
                        edtDocName.setText("");
                        edtDocAddress.setText("");
                        edtDocContactNo.setText("");
                        edtDocType.setText("");
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
