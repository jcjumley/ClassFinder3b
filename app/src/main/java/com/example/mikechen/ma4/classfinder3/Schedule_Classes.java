package com.example.mikechen.ma4.classfinder3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by groge on 11/3/2015.
 */
public class Schedule_Classes {
    DBHelper DB;
    private ArrayList<Schedule_Class> Classes;

    public Schedule_Classes(Context context){
        DB = DBHelper.getHelper(context);
        Classes = new ArrayList<Schedule_Class>();
        SQLiteDatabase db = DB.getWritableDatabase();
    }

    public void AddClass(int CourseNumber){
        //int i = getCount(scheduleClass.CourseNumber);

        SQLiteDatabase db = DB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("number", CourseNumber);

        if (1 != 0) {
            try {
                db.insert(DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES, null, values);

            } catch (Exception e) {
                e.printStackTrace();
            }
            SyncClasses(CourseNumber);
        }
        //Classes.add(scheduleClass);
    }

    private int getCount(int courseNumber) {
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = DB.getReadableDatabase();
            String query = "select count(*) from " + DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES + " where number = "
                    + courseNumber;
            c = db.rawQuery(query, new String[] {"number"});
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void SyncClasses(int course_number){
        SQLiteDatabase db = DB.getReadableDatabase();
        String[] columns = {"*"};
        String[] selectionArgs = {course_number + ""};
        Cursor cursor = db.query(DBHelper.DATABASE_TABLE_COURSE, columns, "class_num=?", selectionArgs, null, null, null );
        cursor.moveToFirst();
        int count = cursor.getCount();
        int i = 0;
        if (count == 0) {
            Log.d("Schedule", "No class with that number");
        } else {
            while (i < count) {
                String department = cursor.getString(0);
                int course = cursor.getInt(1);
                String teacher = cursor.getString(2);
                String times = cursor.getString(3);
                int enrolled = cursor.getInt(4);
                int limit = cursor.getInt(5);
                String building = cursor.getString(6);
                Schedule_Class c = new Schedule_Class(department, course, teacher, times, enrolled, limit, building, null);
                Classes.add(c);
                cursor.moveToNext();
                i++;
            }
        }
    }

    public void DropClasses(){

    }

    public void ClearClasses(){

    }

    public ArrayList<Schedule_Class> GetClasses(){
        return Classes;
    }
}
