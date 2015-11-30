package com.example.mikechen.ma4.classfinder3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by groge on 11/25/2015.
 */
public class Information extends Activity implements View.OnClickListener {

    TextView mCourseName;
    TextView mCourseDepartment;
    TextView mCourseNumber;
    TextView mCourseTeacher;
    TextView mCourseTimes;
    TextView mCourseEnrolment;
    TextView mCourseBuilding;
    TextView mCourseInformation;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        mCourseName = (TextView) findViewById(R.id.course_name);
        mCourseName.setText("Name: " + Find_Class.classInformation.Name);

        mCourseDepartment = (TextView) findViewById(R.id.course_department);
        mCourseDepartment.setText("Department: " + Find_Class.classInformation.Department);

        mCourseNumber = (TextView) findViewById(R.id.course_number);
        mCourseNumber.setText("Number: " + Find_Class.classInformation.CourseNumber);

        mCourseTeacher = (TextView) findViewById(R.id.course_teacher);
        mCourseTeacher.setText("Teacher: " + Find_Class.classInformation.Teacher);

        mCourseTimes = (TextView) findViewById(R.id.course_times);
        mCourseTimes.setText("Times: " + Find_Class.classInformation.Times);

        mCourseEnrolment = (TextView) findViewById(R.id.course_enrollment);
        mCourseEnrolment.setText("Enrollment: " + Find_Class.classInformation.Enrolled + " of " +
            Find_Class.classInformation.Limit);

        mCourseBuilding = (TextView) findViewById(R.id.course_building);
        mCourseBuilding.setText("Building: " + Find_Class.classInformation.Building);

        mCourseInformation = (TextView) findViewById(R.id.course_information);
    }

    @Override
    public void onClick(View v) {

    }
}
