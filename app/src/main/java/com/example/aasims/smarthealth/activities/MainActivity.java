package com.example.aasims.smarthealth.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.aasims.smarthealth.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }
            public void Patient (View v){
             Intent i = new Intent(MainActivity.this, PatientLoginActivity.class);
             startActivity(i);
            }
            public void Doctor (View v){
            Intent a = new Intent(MainActivity.this, DoctorLoginActivity.class);
            startActivity(a);
            }

}
