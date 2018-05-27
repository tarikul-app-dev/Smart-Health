package com.example.aasims.smarthealth.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.model.NotificationM;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder>  {
    private Context mContext;
    private List<NotificationM> mNotificationList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView patientId,symptom, predictedDis,diseaseType,dateTime;


        public MyViewHolder(View view) {
            super(view);
            patientId = (TextView) view.findViewById(R.id.txv_patient_id);
            symptom = (TextView) view.findViewById(R.id.txv_symptom);
            predictedDis = (TextView) view.findViewById(R.id.txv_predict_dis);
            diseaseType = (TextView) view.findViewById(R.id.txv_dis_type);
            dateTime = (TextView) view.findViewById(R.id.txv_date_time);

        }
    }


    public NotificationAdapter(Context mContext, List<NotificationM> notificationList) {
        this.mContext = mContext;
        this.mNotificationList = notificationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NotificationM notification = mNotificationList.get(position);
        holder.patientId.setText(notification.getPatientId());
        holder.symptom.setText(notification.getSymptom());

        holder.predictedDis.setText(notification.getPredictedDisease());
        holder.diseaseType.setText(notification.getDiseaseType());
        holder.dateTime.setText(notification.getDate());
    }


    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }
}