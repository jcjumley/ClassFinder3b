package com.example.mikechen.ma4.classfinder3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by groge on 10/29/2015.
 */
public class Schedule_ListView extends Activity implements View.OnClickListener{

    public ListView mScheduleList;
    Button mAdd;

    ArrayAdapter<String> mAdapter;
    ArrayList<String> mScheduleArray;
    ArrayList<Course> mSchedule;

    public static Schedule_Classes sSchedule;

    int i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_listview);

        sSchedule = new Schedule_Classes(getBaseContext());

        i = 0;

        mScheduleArray = new ArrayList<String>();
        setScheduleArray();
        mScheduleList = (ListView) findViewById(R.id.schedule_list);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mScheduleArray);
        mScheduleList.setAdapter(mAdapter);

        //mAdd = (Button)findViewById(R.id.schedule_add);
        //mAdd.setOnClickListener(this);
    }

    private void setScheduleArray() {
        mSchedule = sSchedule.GetClasses();
        Iterator iterator = mSchedule.iterator();
        mScheduleArray.clear();
        while (iterator.hasNext()){
            Course sClass = (Course) iterator.next();
            mScheduleArray.add(sClass.Department + "     " + sClass.CourseNumber + "          " + sClass.Times);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.schedule_add:

            //    break;
        }
    }
}
