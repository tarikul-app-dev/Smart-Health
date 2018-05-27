package com.example.aasims.smarthealth.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.model.Doctor;
import com.example.aasims.smarthealth.model.Patient;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder>  {
    private Context mContext;
    private List<Patient> mPatientList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id,name, gender,age,address,contactNo;


        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.txv_patient_id);
            name = (TextView) view.findViewById(R.id.txv_patient_name);
            gender = (TextView) view.findViewById(R.id.txv_gender);
            age = (TextView) view.findViewById(R.id.txv_age);
            address = (TextView) view.findViewById(R.id.txv_address);
            contactNo = (TextView) view.findViewById(R.id.txv_contact_no);
        }
    }


    public PatientAdapter(Context mContext, List<Patient> patientList) {
        this.mContext = mContext;
        this.mPatientList = patientList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Patient patient = mPatientList.get(position);
        holder.id.setText(patient.getPatientId());
        holder.name.setText(patient.getName());

        holder.gender.setText(patient.getGender());
        holder.address.setText(patient.getAddress());
        holder.contactNo.setText(patient.getContactNo());
    }


    @Override
    public int getItemCount() {
        return mPatientList.size();
    }
}