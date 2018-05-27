package com.example.aasims.smarthealth.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.aasims.smarthealth.utill.InputValidation;
import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.database.DatabaseHelper;
import com.example.aasims.smarthealth.model.Doctor;

import java.util.Random;

import static com.example.aasims.smarthealth.utill.SaveInLocalMemory.saveToSharedPreferences;

public class DoctorSignup extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = DoctorSignup.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutMobile;
    private TextInputLayout textInputLayoutAddress;

    private TextInputEditText textInputEditTextName,textInputEditTextType;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextMobile;
    private TextInputEditText textInputEditTextAddress;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Doctor doc;
    String doctorId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsignup);

        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutMobile = (TextInputLayout) findViewById(R.id.textInputLayoutMob);
        textInputLayoutAddress = (TextInputLayout) findViewById(R.id.textInputLayoutAddress);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextType = (TextInputEditText) findViewById(R.id.textInputEditTextType);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextMobile = (TextInputEditText) findViewById(R.id.textInputEditTextMob);
        textInputEditTextAddress = (TextInputEditText) findViewById(R.id.textInputEditTextAddress);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        doc = new Doctor();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }
//        if (!inputValidation.isInputEditTextEmail(textInputEditTextMobile, textInputLayoutMobile, getString(R.string.error_message_mobile))) {
//            return;
//        }

//        if (!inputValidation.isInputEditTextEmail(textInputEditTextAddress, textInputLayoutAddress, getString(R.string.error_message_address))) {
//            return;
//        }

        if (!databaseHelper.checkDoctor(textInputEditTextEmail.getText().toString().trim())) {
            Random generator = new Random();
            int  n = 10000;
            n = generator.nextInt(n);
            doctorId = String.valueOf(n);
            saveToSharedPreferences("doctor_id",doctorId,DoctorSignup.this);
            doc.setDoctorId(doctorId);
            doc.setName(textInputEditTextName.getText().toString().trim());
            doc.setType(textInputEditTextType.getText().toString().trim());
            doc.setEmail(textInputEditTextEmail.getText().toString().trim());
            doc.setPassword(textInputEditTextPassword.getText().toString().trim());
            doc.setMob(textInputEditTextMobile.getText().toString().trim());
            doc.setAddress(textInputEditTextAddress.getText().toString().trim());

            boolean isInsert  = databaseHelper.addDoctor(doc);
            if (isInsert){
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(DoctorSignup.this,DoctorLoginActivity.class);
                startActivity(intent);
                ActivityCompat.finishAffinity(DoctorSignup.this);
                emptyInputEditText();
            }

//            // Snack Bar to show success message that record saved successfully
//            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
//            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextType.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditTextMobile.setText(null);
        textInputEditTextAddress.setText(null);


    }

}