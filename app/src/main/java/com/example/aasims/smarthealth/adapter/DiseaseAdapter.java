package com.example.aasims.smarthealth.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.fragments.ViewDisease;
import com.example.aasims.smarthealth.model.NotificationM;
import com.example.aasims.smarthealth.model.ViewDiseaseM;

import java.util.List;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.MyViewHolder>  {
    private Context mContext;
    private List<ViewDiseaseM> mDiseaseList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView disId,name, symptom,type;


        public MyViewHolder(View view) {
            super(view);
            disId = (TextView) view.findViewById(R.id.txv_disease_id);
            symptom = (TextView) view.findViewById(R.id.txv_symptom);
            name = (TextView) view.findViewById(R.id.txv_disease_name);
            type = (TextView) view.findViewById(R.id.txv_dis_type);
        }
    }


    public DiseaseAdapter(Context mContext, List<ViewDiseaseM> notificationList) {
        this.mContext = mContext;
        this.mDiseaseList = notificationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_disease_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ViewDiseaseM  disease = mDiseaseList.get(position);
        holder.disId.setText(disease.getDisId());
        holder.symptom.setText(disease.getSymptom());

        holder.name.setText(disease.getName());
        holder.type.setText(disease.getType());

    }


    @Override
    public int getItemCount() {
        return mDiseaseList.size();
    }
}