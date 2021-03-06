package com.example.mikechen.ma4.classfinder3;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;


public class Registration extends Activity implements OnClickListener, OnItemSelectedListener


{
    // Variable Declaration should be in onCreate()
    private Button mSubmit;
    private Button mCancel;

    private EditText mFname;
    private EditText mLname;
    private EditText mUsername;
    private EditText mPassword;
//    private EditText mEmail;
    private Spinner mGender;
    private String Gen;

    protected DBHelper DB = new DBHelper(Registration.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        if (Settings.rotationLock)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        //Assignment of UI fields to the variables
        mSubmit = (Button)findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);

        mCancel = (Button)findViewById(R.id.cancel);
        mCancel.setOnClickListener(this);

        mFname = (EditText)findViewById(R.id.efname);
        mLname = (EditText)findViewById(R.id.elname);

        mUsername = (EditText)findViewById(R.id.reuname);
        mPassword = (EditText)findViewById(R.id.repass);
//        mEmail = (EditText)findViewById(R.id.eemail);


        mGender = (Spinner)findViewById(R.id.spinner1);

        // Spinner method to read the on selected value
        ArrayAdapter<State> spinnerArrayAdapter = new ArrayAdapter<State>(this,
                android.R.layout.simple_spinner_item, new State[] {
                new State("Male"),
                new State("Female")});
        mGender.setAdapter(spinnerArrayAdapter);
        mGender.setOnItemSelectedListener(this);
    }
    private Date mStartDate;
    public Date getStartDate(){
        return mStartDate;
    }

    public void onClick(View v)
    {

        switch(v.getId()){

            case R.id.cancel:
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                //finish();
                break;

            case R.id.submit:


                String fname = mFname.getText().toString();
                String lname = mLname.getText().toString();
                String uname = mUsername.getText().toString();
                String pass = mPassword.getText().toString();
//                String email = mEmail.getText().toString();


                boolean invalid = false;

                if(fname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter your Firstname", Toast.LENGTH_SHORT).show();
                }
                else

                if(lname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Lastname", Toast.LENGTH_SHORT).show();
                }
                else

                if(uname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Username", Toast.LENGTH_SHORT).show();
                }
                else


                if(pass.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();

                }
                else
//                if(email.equals(""))
//                {
//                    invalid = true;
//                    Toast.makeText(getApplicationContext(), "Please enter your Email ID", Toast.LENGTH_SHORT).show();
//                }
//                else
                if(invalid == false)
                {
                    addEntry(fname, lname, Gen, uname, pass);
                    Intent i_register = new Intent(Registration.this, MainActivity.class);
                    startActivity(i_register);
                    //finish();
                }

                break;
        }
    }





    public void onDestroy()
    {
        super.onDestroy();
        DB.close();
    }



    private void addEntry(String fname, String lname, String Gen, String uname, String pass)
    {

        SQLiteDatabase db = DB.getWritableDatabase();
//        SQLiteDatabaseDatabase db= DD.createRegister(values);


        ContentValues values = new ContentValues();
        values.put("firstname", fname);
        values.put("lastname", lname);
        values.put("gender", Gen);
        values.put("username", uname);
        values.put("password", pass);
//        values.put("email", email);

        try
        {
            db.insert(DBHelper.DATABASE_TABLE_REGISTER, null, values);

            Toast.makeText(getApplicationContext(), "your details submitted Successfully...", Toast.LENGTH_SHORT).show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // Get the currently selected State object from the spinner
        State st = (State)mGender.getSelectedItem();

        // Show it via a toast
        toastState( "onItemSelected", st );
    }


    public void toastState(String name, State st)
    {
        if ( st != null )
        {
            Gen = st.name;
            //Toast.makeText(getBaseContext(), Gen, Toast.LENGTH_SHORT).show();

        }

    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}