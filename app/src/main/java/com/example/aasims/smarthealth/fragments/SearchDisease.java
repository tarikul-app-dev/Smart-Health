package com.example.aasims.smarthealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.database.DatabaseHelper;
import com.example.aasims.smarthealth.model.NotificationM;
import com.example.aasims.smarthealth.model.Patient;
import com.example.aasims.smarthealth.utill.SaveInLocalMemory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import static com.example.aasims.smarthealth.utill.SaveInLocalMemory.getValueFromSharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDisease extends Fragment {
    EditText edtSearchDis;
    Button btnNext,btnNextTwo;
    LinearLayout linShowSymp,linComplete;

    Map<String, List<String>> diseaseMap ;
    Spinner spinSymptom;
    RelativeLayout rLShowSymptom;
    String[] feverSymptom;
    String[] jointPoints;
    LinearLayout.LayoutParams lparams;
    List<String> feverSymptomList;
    List<String> jointPointsSymptomList;

    DatabaseHelper databaseHelper;
    NotificationM notificationM;
    String patientId = "";
    private static String LIST_SEPARATOR = ":";
    public SearchDisease() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feverSymptom = getResources().getStringArray(R.array.fever_symptom);
        jointPoints = getResources().getStringArray(R.array.joint_symptom);
        databaseHelper = new DatabaseHelper(getActivity());
         diseaseMap = new HashMap<String, List<String>>();
        diseaseMap.put("headeche", new ArrayList<String>(Arrays.asList("chills","fever")));
        diseaseMap.put("diabetes", new ArrayList<String>(Arrays.asList("feeling very thirsty","feeling very tired","weight loss and loss of muscle bulk")));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_disease, container, false);
        edtSearchDis = (EditText)rootView.findViewById(R.id.edt_search_disease);
        btnNext = (Button)rootView.findViewById(R.id.btn_next);
        btnNextTwo = (Button)rootView.findViewById(R.id.btn_next_two);

        linShowSymp = (LinearLayout)rootView.findViewById(R.id.linear_show_symptom);
        linComplete = (LinearLayout)rootView.findViewById(R.id.lin_complete);
        spinSymptom = (Spinner)rootView.findViewById(R.id.spin_symptom);
        rLShowSymptom = (RelativeLayout)rootView.findViewById(R.id.relative_show_symptom);
        patientId = SaveInLocalMemory.getValueFromSharedPreferences("patient_id",getActivity());
        notificationM = new NotificationM();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linComplete.setVisibility(View.GONE);
                linShowSymp.removeAllViewsInLayout();
                rLShowSymptom.setVisibility(View.VISIBLE);
                String searchItem = edtSearchDis.getText().toString();
                notificationM.setPatientId(patientId);


                //databaseHelper.addNotification()

                List specificSymptom = getSymptom(searchItem);
                String symptom = convertListToString(specificSymptom);
                notificationM.setSymptom(symptom);
                if(searchItem.equals("headeche")){
                    notificationM.setPredictedDisease("Dengue");
                    notificationM.setDiseaseType("Infectious");
                }
                if(searchItem.equals("diabetes")){
                    notificationM.setPredictedDisease("Diphtheria");
                    notificationM.setDiseaseType("Infectious");
                }
                notificationM.setDate(getCurrentDate());
                databaseHelper.addNotification(notificationM);

                 lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                if(specificSymptom!=null){
                    if(specificSymptom.size()>0){
                        for (int i = 0; i < specificSymptom.size(); i++)
                        {
                            TextView tv = new TextView(getActivity());
                            tv.setLayoutParams(lparams);
                            tv.setGravity(Gravity.CENTER);
                            tv.setText(specificSymptom.get(i).toString());
                            linShowSymp.addView(tv);

                        }
                        //specificSymptom.clear();

                    }
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, specificSymptom);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                spinSymptom.setAdapter(spinnerArrayAdapter);
               // specificSymptom.clear();
            }
        });

        btnNextTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linShowSymp.removeAllViewsInLayout();
                String selectFeverItem = spinSymptom.getSelectedItem().toString();
                lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if(selectFeverItem.equals("fever")) {
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, feverSymptom);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                spinSymptom.setAdapter(spinnerArrayAdapter);

                feverSymptomList = Arrays.asList(feverSymptom);
                if (feverSymptomList.size() > 0) {
                    for (int i = 0; i < feverSymptomList.size(); i++) {
                        TextView tv = new TextView(getActivity());
                        tv.setLayoutParams(lparams);
                        tv.setGravity(Gravity.CENTER);
                        tv.setText(feverSymptomList.get(i).toString());
                        linShowSymp.addView(tv);

                    }
                }
            }

            if (selectFeverItem.equals("joint points")){
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, jointPoints);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                spinSymptom.setAdapter(spinnerArrayAdapter);
                jointPointsSymptomList = Arrays.asList(jointPoints);
                if (jointPointsSymptomList.size() > 0) {
                    for (int i = 0; i < jointPointsSymptomList.size(); i++) {
                        TextView tv = new TextView(getActivity());
                        tv.setLayoutParams(lparams);
                        tv.setGravity(Gravity.CENTER);
                        tv.setText(jointPointsSymptomList.get(i).toString());
                        linShowSymp.addView(tv);

                    }
                }
            }
                if (selectFeverItem.equals("measel like rushes")){
                    linComplete.setVisibility(View.VISIBLE);

                }


            }
        });

        return rootView;
    }

    public List getSymptom(String disease){
        List<String> symptomList = null;
        for (Map.Entry<String, List<String>> entry : diseaseMap.entrySet()) {
            String key = entry.getKey();
            if(key.equals(disease)){
                symptomList = entry.getValue();
            }


        }

        return  symptomList;
    }
    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String convertListToString(List<String> stringList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : stringList) {
            stringBuffer.append(str).append(LIST_SEPARATOR);
        }
        try {
            // Remove last separator
            int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
            stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);
        }catch (Exception e){
            e.getStackTrace();

        }


        return stringBuffer.toString();
    }
}
