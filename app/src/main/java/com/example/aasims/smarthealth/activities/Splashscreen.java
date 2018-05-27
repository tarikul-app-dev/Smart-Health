package com.example.aasims.smarthealth.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aasims.smarthealth.R;

import static com.example.aasims.smarthealth.utill.SaveInLocalMemory.getBoleanValueSharedPreferences;

public class Splashscreen extends AppCompatActivity {
     boolean isLoginPatient;
     boolean isLoginDoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        isLoginPatient = getBoleanValueSharedPreferences("islogin_patient", Splashscreen.this);
        isLoginDoctor = getBoleanValueSharedPreferences("islogin_doctor", Splashscreen.this);

        Thread mythread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);

                    if(isLoginPatient){
                        Intent intent = new Intent(Splashscreen.this,PatientMenu.class);
                        startActivity(intent);
                        ActivityCompat.finishAffinity(Splashscreen.this);
                    }else if(isLoginDoctor){
                        Intent intent = new Intent(Splashscreen.this,DoctorMenu.class);
                        startActivity(intent);
                        ActivityCompat.finishAffinity(Splashscreen.this);
                    } else {
                        Intent homescreen = new Intent(Splashscreen.this, MainActivity.class);
                        startActivity(homescreen);
                        finish();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        mythread.start();

    }
}
