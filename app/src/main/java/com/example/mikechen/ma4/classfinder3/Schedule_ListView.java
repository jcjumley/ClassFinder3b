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
    ArrayList<Schedule_Class> mSchedule;

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

        Schedule_Class c1 = new Schedule_Class(5000, "Alex", "M,W,F", 30, 50, null);
        Schedule_Class c2 = new Schedule_Class(3000, "Anna", "T,Th", 25, 25, null);
        Schedule_Class c3 = new Schedule_Class(5326, "Adam", "M,W,F", 30, 35, null);
        Schedule_Class c4 = new Schedule_Class(5324, "Ben", "M,W,F", 25, 40, null);
        Schedule_Class c5 = new Schedule_Class(5911, "Zach", "T,Th", 25, 30, null);
        addItems(c1);
        addItems(c2);
        addItems(c3);
        addItems(c4);
        addItems(c5);
    }

    private void setScheduleArray() {
        mSchedule = sSchedule.GetClasses();
        Iterator iterator = mSchedule.iterator();
        mScheduleArray.clear();
        while (iterator.hasNext()){
            Schedule_Class sClass = (Schedule_Class) iterator.next();
            mScheduleArray.add("CSE " + sClass.CourseNumber + "          " + sClass.Times);
        }
    }

    public void addItems(Schedule_Class c) {
        sSchedule.AddClass(c);
        setScheduleArray();
        i = 5236;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.schedule_add:

            //    break;
        }
    }
}
