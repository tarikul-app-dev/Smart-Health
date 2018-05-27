package com.example.aasims.smarthealth.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aasims.smarthealth.model.NotificationM;
import com.example.aasims.smarthealth.model.Patient;
import com.example.aasims.smarthealth.model.Doctor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";

    private static final String TABLE_NOTIFICATION = "notification";

    private static final String TABLE_DOCTOR = "doctor";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_GENDER = "user_gender";
    private static final String COLUMN_USER_AGE = "user_age";
    private static final String COLUMN_USER_ADDRESS = "user_address";
    private static final String COLUMN_CONTACT_NO= "contact_no";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String PATIENT_ID = "patient_id";


    //Doc table
    private static final String COLUMN_DOC_ID = "doc_id";
    private static final String COLUMN_DOC_NAME = "doc_name";
    private static final String COLUMN_DOC_EMAIL = "doc_email";
    private static final String COLUMN_DOC_PASSWORD = "doc_password";
    private static final String COLUMN_DOC_MOBILE = "doc_mobile";
    private static final String COLUMN_DOC_ADDRESS = "doc_address";
    private static final String COLUMN_DOC_TYPE = "doc_type";
    private static final String DOCTOR_ID = "doctor_id";

    //Notification table
    private static final String NOTIFICATION_ID = "notification_id";
    private static final String COLUMN_SYMPTOM = "not_symptom";
    private static final String COLUMN_PREDICT_DISEASE = "not_predict_disease";
    private static final String COLUMN_DISEASE_TYPE = "not_disease_type";
    private static final String COLUMN_DATE = "not_date";

    // create table sql query
    private String CREATE_USER_TABLE = " CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PATIENT_ID + " TEXT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_GENDER + " TEXT,"
            + COLUMN_USER_AGE + " TEXT,"
            + COLUMN_USER_ADDRESS + " TEXT,"
            + COLUMN_CONTACT_NO + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT " + ")";


    private String CREATE_DOC_TABLE = " CREATE TABLE " + TABLE_DOCTOR + "("
            + COLUMN_DOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DOCTOR_ID + " TEXT,"
            + COLUMN_DOC_NAME + " TEXT,"
            + COLUMN_DOC_EMAIL + " TEXT,"
            + COLUMN_DOC_PASSWORD + " TEXT,"
            + COLUMN_DOC_MOBILE + " TEXT,"
            + COLUMN_DOC_TYPE + " TEXT,"
            + COLUMN_DOC_ADDRESS + " TEXT " + ")";

    private String CREATE_NOTIFICATION_TABLE = " CREATE TABLE " + TABLE_NOTIFICATION + "("
            + NOTIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PATIENT_ID + " TEXT,"
            + COLUMN_SYMPTOM + " TEXT,"
            + COLUMN_PREDICT_DISEASE + " TEXT,"
            + COLUMN_DISEASE_TYPE + " TEXT,"
            + COLUMN_DATE + " TEXT " + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_DOCTOR_TABLE = "DROP TABLE IF EXISTS " + TABLE_DOCTOR;
    private String DROP_NOTIFICATION_TABLE = "DROP TABLE IF EXISTS " + TABLE_NOTIFICATION;

    /**
     * Constructor
     * 
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_DOC_TABLE);
        db.execSQL(CREATE_NOTIFICATION_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_DOCTOR_TABLE);
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_NOTIFICATION_TABLE);
        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public boolean addUser(Patient user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PATIENT_ID, user.getPatientId());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_GENDER, user.getGender());
        values.put(COLUMN_USER_AGE, user.getAge());
        values.put(COLUMN_USER_ADDRESS, user.getAddress());
        values.put(COLUMN_CONTACT_NO, user.getContactNo());
        // Inserting Row
       long dbInsert =  db.insert(TABLE_USER, null, values);
          db.close();
          if(dbInsert!=-1){
              return true;
          }else {
              return false;
          }


    }

    public boolean addNotification(NotificationM notificationM) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PATIENT_ID, notificationM.getPatientId());
        values.put(COLUMN_SYMPTOM, notificationM.getSymptom());
        values.put(COLUMN_PREDICT_DISEASE, notificationM.getPredictedDisease());
        values.put(COLUMN_DISEASE_TYPE, notificationM.getDiseaseType());
        values.put(COLUMN_DATE, notificationM.getDate());


        // Inserting Row
        long dbInsert =  db.insert(TABLE_NOTIFICATION, null, values);
        db.close();
        if(dbInsert!=-1){
            return true;
        }else {
            return false;
        }


    }

    public List<NotificationM> getAllNotification() {
        // array of columns to fetch
        String[] columns = {
                PATIENT_ID,
                COLUMN_SYMPTOM,
                COLUMN_PREDICT_DISEASE,

                COLUMN_DISEASE_TYPE,
                COLUMN_DATE
        };
        // sorting orders
        String sortOrder =
                COLUMN_SYMPTOM + " ASC";
        List<NotificationM> userList = new ArrayList<NotificationM>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_NOTIFICATION, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NotificationM user = new NotificationM();
                user.setPatientId( cursor.getString(cursor.getColumnIndex(PATIENT_ID) ));
                user.setSymptom(cursor.getString(cursor.getColumnIndex(COLUMN_SYMPTOM)));
                user.setPredictedDisease(cursor.getString(cursor.getColumnIndex(COLUMN_PREDICT_DISEASE)));
                user.setDiseaseType(cursor.getString(cursor.getColumnIndex(COLUMN_DISEASE_TYPE)));
                user.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));

                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
    public boolean addDoctor(Doctor doc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put(DOCTOR_ID, doc.getDoctorId());
        values1.put(COLUMN_DOC_NAME, doc.getName());
        values1.put(COLUMN_DOC_EMAIL, doc.getEmail());
        values1.put(COLUMN_DOC_PASSWORD, doc.getPassword());
        values1.put(COLUMN_DOC_MOBILE, doc.getMob());
        values1.put(COLUMN_DOC_ADDRESS, doc.getAddress());
        values1.put(COLUMN_DOC_TYPE, doc.getType());

        // Inserting Row
        long dbInsert =  db.insert(TABLE_DOCTOR, null, values1);
        db.close();
        if(dbInsert!=-1){
            return true;
        }else {
            return false;
        }

    }





    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Patient> getAllPatient() {
        // array of columns to fetch
        String[] columns = {
                PATIENT_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_GENDER,
                COLUMN_USER_AGE,
                COLUMN_USER_ADDRESS,
                COLUMN_CONTACT_NO
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<Patient> userList = new ArrayList<Patient>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Patient user = new Patient();
                user.setPatientId( cursor.getString(cursor.getColumnIndex(PATIENT_ID) ));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)));
                user.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_USER_AGE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ADDRESS)));
                user.setContactNo(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NO)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
    public ArrayList<Patient> getUser(String patientId) {
        ArrayList<Patient> userList = new ArrayList<Patient>();
        String select_query = "SELECT * FROM " + TABLE_USER + " WHERE " + PATIENT_ID + " = '" + patientId + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Patient user = new Patient();
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ADDRESS)));
                user.setContactNo(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NO)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));

                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
    public ArrayList<Doctor> getDoctor(String doctorId) {
        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
        String select_query = "SELECT * FROM " + TABLE_DOCTOR + " WHERE " + DOCTOR_ID + " = '" + doctorId + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor user = new Doctor();
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_NAME)));
                user.setType(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_TYPE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_ADDRESS)));
                user.setMob(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_MOBILE)));

                // Adding user record to list
                doctorList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return doctorList;
    }

    public ArrayList<Doctor> getDoctorList() {
        ArrayList<Doctor> userList = new ArrayList<Doctor>();
        String select_query = "SELECT * FROM " + TABLE_DOCTOR ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor user = new Doctor();
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_NAME)));
                user.setType(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_TYPE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_ADDRESS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_EMAIL)));
                user.setMob(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_MOBILE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public ArrayList<Doctor> getDoctorListN(String searchItemName) {
        ArrayList<Doctor> userList = new ArrayList<Doctor>();
        String select_query = "SELECT * FROM " + TABLE_DOCTOR
                               + " WHERE " + COLUMN_DOC_NAME + " like '%" + searchItemName
                + "%'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor user = new Doctor();
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_NAME)));
                user.setType(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_TYPE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_ADDRESS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_EMAIL)));
                user.setMob(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_MOBILE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public ArrayList<Doctor> getDoctorListT(String searchItemType) {
        ArrayList<Doctor> userList = new ArrayList<Doctor>();
        String select_query = "SELECT * FROM " + TABLE_DOCTOR
                + " WHERE " + COLUMN_DOC_TYPE + " like '%" + searchItemType
                + "%'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor user = new Doctor();
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_NAME)));
                user.setType(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_TYPE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_ADDRESS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_EMAIL)));
                user.setMob(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_MOBILE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
    public ArrayList<Doctor> getDoctorListA(String searchItemAdd) {
        ArrayList<Doctor> userList = new ArrayList<Doctor>();
        String select_query = "SELECT * FROM " + TABLE_DOCTOR
                + " WHERE " + COLUMN_DOC_ADDRESS + " like '%" + searchItemAdd
                + "%'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor user = new Doctor();
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_NAME)));
                user.setType(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_TYPE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_ADDRESS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_EMAIL)));
                user.setMob(cursor.getString(cursor.getColumnIndex(COLUMN_DOC_MOBILE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    public boolean updateUser(Patient user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_ADDRESS, user.getAddress());
        values.put(COLUMN_CONTACT_NO, user.getContactNo());
        values.put(COLUMN_USER_EMAIL, user.getEmail());

        // updating row
       long updateInfo = db.update(TABLE_USER, values, PATIENT_ID + " = ?",
                new String[]{String.valueOf(user.getPatientId())});
        db.close();

        if (updateInfo!=-1){
            return true;
        }else {
            return false;
        }
    }
    public boolean updateDoctor(Doctor doctor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DOC_NAME, doctor.getName());
        values.put(COLUMN_DOC_ADDRESS, doctor.getAddress());
        values.put(COLUMN_DOC_MOBILE, doctor.getMob());
        values.put(COLUMN_DOC_TYPE, doctor.getType());

        // updating row
        long updateInfo = db.update(TABLE_USER, values, DOCTOR_ID + " = ?",
                new String[]{String.valueOf(doctor.getDoctorId())});
        db.close();

        if (updateInfo!=-1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(Patient user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getPatientId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check doctor exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkDoctor(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DOC_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_DOC_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

















    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkDoctor(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DOC_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_DOC_EMAIL + " = ?" + " AND " + COLUMN_DOC_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}
