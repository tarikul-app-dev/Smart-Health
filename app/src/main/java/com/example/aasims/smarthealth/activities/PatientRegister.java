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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.aasims.smarthealth.R;
import com.example.aasims.smarthealth.database.DatabaseHelper;
import com.example.aasims.smarthealth.model.Patient;
import com.example.aasims.smarthealth.utill.InputValidation;
import com.example.aasims.smarthealth.utill.SaveInLocalMemory;

import java.util.Random;

import static com.example.aasims.smarthealth.utill.SaveInLocalMemory.saveToSharedPreferences;

public class PatientRegister extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = PatientRegister.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName,edtAge,edtAddress,edtMobile;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Patient user;
    SaveInLocalMemory saveInLocalMemory;
    RadioGroup rgGender;
    RadioButton selectedGender;
    String patientId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientregisterr);

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

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);


        edtAge = (TextInputEditText) findViewById(R.id.textInputEditTextAge);
        edtAddress = (TextInputEditText) findViewById(R.id.textInputEditTextAdd);
        edtMobile = (TextInputEditText) findViewById(R.id.textInputEditTextMob);
        rgGender = (RadioGroup)findViewById(R.id.rg_gender) ;

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);



       //saveInLocalMemory = new SaveInLocalMemory();

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
        user = new Patient();

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

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            selectedGender =(RadioButton)findViewById(rgGender.getCheckedRadioButtonId());
            String gender = selectedGender.getText().toString();
            user.setGender(gender);
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            saveToSharedPreferences("email",textInputEditTextEmail.getText().toString().trim(),PatientRegister.this);

            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            Random generator = new Random();
            int  n = 10000;
            n = generator.nextInt(n);
            patientId = String.valueOf(n);
            saveToSharedPreferences("patient_id",patientId,PatientRegister.this);

            user.setPatientId(patientId);
            user.setAge(edtAge.getText().toString().trim());
            user.setAddress(edtAddress.getText().toString().trim());
            user.setContactNo(edtMobile.getText().toString().trim());

            boolean isInsert = databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            if (isInsert){
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(PatientRegister.this,PatientLoginActivity.class);
                startActivity(intent);
                ActivityCompat.finishAffinity(PatientRegister.this);
                emptyInputEditText();
            }




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
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);

    }
}
