package com.example.mikechen.ma4.classfinder3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by groge on 10/22/2015.
 */
public class Find_Class extends Activity implements OnClickListener {

    TextView mCourse;

    Button mMap;
    Button mInformation;
    Button mCouseList;

    private static Course classInformation;

    public Find_Class(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_class);

        mCourse = (TextView)findViewById(R.id.selected_course);
        if (classInformation != null) {
            mCourse.setText(classInformation.Name);
        }

        mMap = (Button)findViewById(R.id.Map);
        mMap.setOnClickListener(this);

        mInformation = (Button)findViewById(R.id.Information);
        mInformation.setOnClickListener(this);

        mCouseList = (Button)findViewById(R.id.select_from_course_list);
        mCouseList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent in;
        switch (v.getId()){
            case R.id.Map:
                in = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(in);
                break;
            case R.id.Information:
                in = new Intent(getBaseContext(), Select_Class.class);
                startActivity(in);
                break;
            case R.id.select_from_course_list:
                in = new Intent(getBaseContext(), Select_Class.class);
                startActivity(in);
                break;
        }
    }

    public static void setClassInformation(Course sc){
        classInformation = sc;
    }

    public static Course getClassInformation(){
        return classInformation;
    }
}
