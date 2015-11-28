package com.example.mikechen.ma4.classfinder3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

//import com.opencsv.CSVReader;


//import my.DataBase.R;

public class MainActivity extends Activity implements OnClickListener
{

    Button mLogin;
    Button mRegister;
    TextView showCourse;
    EditText muname;
    EditText mpassword;
    private SQLiteDatabase db;
    private Spinner spMainSelectCategory;
    private ArrayList<String> categoryList = new ArrayList<String>();


    DBHelper DB = null;

    private static final String TAG="MainActivity";

    /** Called when the activity is first created. */
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()called");
    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        Log.d(TAG,"onCreate Called!!!");
        setContentView(R.layout.main);

        DB = new DBHelper(getBaseContext());
        DB.onCreate(DB.getWritableDatabase());
        GetClasses classes = new GetClasses(getBaseContext());
        Schedule_Classes schedule = new Schedule_Classes(getBaseContext());

        mRegister = (Button)findViewById(R.id.register);
        mRegister.setOnClickListener(this);

        mLogin = (Button)findViewById(R.id.login);
        mLogin.setOnClickListener(this);

        showCourse=(TextView)findViewById(R.id.showCourse);

        db = DB.getWritableDatabase();
        String count = "SELECT count(*) FROM courseTB";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount<=0) {
            insertCourse();
        }

    }
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()called");
    }
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()called");
    }

    public void insertCourse(){
        db = DB.getWritableDatabase();
        List<String[]> list = new ArrayList<String[]>();
        String next[] = {};
        try {
            InputStreamReader csvStreamReader = new InputStreamReader(
                    MainActivity.this.getAssets().open(
                            "course.csv"));
            CSVReader reader = new CSVReader(csvStreamReader);
            String str[];
            String tableName = "courseTB";
            String columns = "class_num, prof_name, course_name, times, enrld_ple, limit_ple, department, building ";
            String str1 = "INSERT INTO " + tableName + " (" + columns + ") VALUES (";
            String str2 = ");";
//------------- It can read the content in CSV file, showcourse textfile will show it, but can't insert into DB
            db.beginTransaction();
            while ((str = reader.readNext()) != null) {
                StringBuilder sb = new StringBuilder(str1);
//                String[] str = line.split(",");
                sb.append(str[0] + ", '");//class num
                sb.append(str[1] + "','");//prof nam
                sb.append(str[2] + "','");//course nam
                sb.append(str[3] + "',");//times
                sb.append(str[4] + ",");//enrld people
                sb.append(str[5] + ",'");//limit peo
                sb.append(str[6] + "','"); //department
                sb.append(str[7] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
            for (;;) {
                next = reader.readNext();
                if (next != null) {
                    list.add(next);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
//        showCourse.setText(list.get(1)[1]);
    }


    public void onClick(View v)
    {
        switch(v.getId())
        {

            case R.id.register:
                Intent i = new Intent(getBaseContext(), Registration.class);
                startActivity(i);
                break;


            case R.id.login:

                muname = (EditText)findViewById(R.id.Ledituname);
                mpassword = (EditText)findViewById(R.id.Leditpw);

                String username = muname.getText().toString();
                String password = mpassword.getText().toString();



                if(username.equals("") || username == null)
                {
                    Toast.makeText(getApplicationContext(), "Please enter User Name", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("") || password == null)
                {
                    Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean validLogin = validateLogin(username, password, getBaseContext());
                    if(validLogin)
                    {
                        System.out.println("In Valid");
                        Intent in = new Intent(getBaseContext(), Home.class);
                        in.putExtra("UserName", muname.getText().toString());
                        startActivity(in);
                        //finish();
                    }
                }
                break;

        }

    }


    private boolean validateLogin(String username, String password, Context baseContext)
    {
        SQLiteDatabase db = DB.getReadableDatabase();

        String[] columns = {"_id"};

        String selection = "username=? AND password=?";
        String[] selectionArgs = {username,password};

        Cursor cursor = null;
        try{

            cursor = db.query(DBHelper.DATABASE_TABLE_REGISTER, columns, selection, selectionArgs, null, null, null);
            startManagingCursor(cursor);
        }
        catch(Exception e)

        {
            e.printStackTrace();
        }
        int numberOfRows = cursor.getCount();

        if(numberOfRows <= 0)
        {

            Toast.makeText(getApplicationContext(), "User Name and Password miss match..\nPlease Try Again", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            return false;
        }

        return true;

    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy()called");
        DB.close();
    }
}
