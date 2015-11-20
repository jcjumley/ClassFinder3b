package com.example.mikechen.ma4.classfinder3;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mikechen on 10/25/15.
 */
public class Scheduler extends Activity implements OnClickListener{

    private Button addBtn;
    private Button dropBtn;
    private Button clearBtn;
    private EditText courseNumber;

    protected DBHelper DB = new DBHelper(Scheduler.this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduler);

        addBtn = (Button)findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(this);

        dropBtn = (Button)findViewById(R.id.btnDrop);
        dropBtn.setOnClickListener(this);

        clearBtn = (Button)findViewById(R.id.clrbtn);
        clearBtn.setOnClickListener(this);

        
        courseNumber = (EditText)findViewById(R.id.EditCourseNum);

    }

    public void onClick(View v){
        SQLiteDatabase db;
        switch (v.getId()){
            case R.id.btnAdd:
                String course_num=courseNumber.getText().toString();

                boolean invalid = false;

                if(course_num.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter the course number", Toast.LENGTH_SHORT).show();
                }
                else if (invalid==false){
                    addCourse(course_num);

                }
                break;
            case R.id.btnDrop:
                db = DB.getWritableDatabase();
                Schedule_Classes.DropClass(Integer.parseInt(courseNumber.getText().toString()), db);
                Toast.makeText(getApplicationContext(), courseNumber.getText().toString() + " dropped successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clrbtn:
                db = DB.getWritableDatabase();
                Schedule_Classes.ClearClasses(db);
                Toast.makeText(getApplicationContext(), "All classes dropped successfully", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    public void addCourse(String coursenum){
        Schedule_Classes.AddClass(Integer.parseInt(coursenum));

        Toast.makeText(getApplicationContext(), "your details submitted Successfully...", Toast.LENGTH_SHORT).show();

    }

    }


