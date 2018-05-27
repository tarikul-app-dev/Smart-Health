package com.example.aasims.smarthealth.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.model.Doctor;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder>  {
    private Context mContext;
    private List<Doctor> mDoctorList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, type,address,contactNo;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txv_doctor_name);
            type = (TextView) view.findViewById(R.id.txv_type);
            address = (TextView) view.findViewById(R.id.txv_address);
            contactNo = (TextView) view.findViewById(R.id.txv_contact_no);
        }
    }


    public DoctorAdapter(Context mContext, List<Doctor> doctorList) {
        this.mContext = mContext;
        this.mDoctorList = doctorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Doctor doctor = mDoctorList.get(position);
        holder.name.setText(doctor.getName());

        holder.type.setText(doctor.getType());
        holder.address.setText(doctor.getAddress());
        holder.contactNo.setText(doctor.getMob());
    }


    @Override
    public int getItemCount() {
        return mDoctorList.size();
    }
}