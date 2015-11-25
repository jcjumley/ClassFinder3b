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
    static DBHelper DB;
    private static ArrayList<Course> Classes;

    public Schedule_Classes(Context context){
        DB = DBHelper.getHelper(context);
        Classes = new ArrayList<Course>();
        SQLiteDatabase db = DB.getWritableDatabase();
        SyncAllClasses();
    }

    public void SyncAllClasses(){
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor c = db.query(DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES, new String[]{"number"}, null, null, null, null, null);
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                int courseNumber = c.getInt(0);
                SyncClasses(courseNumber);
                c.moveToNext();
            }
        }
    }

    public static void AddClass(int CourseNumber){
        int i = getCount(CourseNumber);
        SQLiteDatabase db = DB.getWritableDatabase();
        if (i == 0) {
            String[] columns = {"*"};
            String[] selectionArgs = {CourseNumber + ""};
            Cursor cursor = db.query(DBHelper.DATABASE_TABLE_COURSE, columns, "class_num=?", selectionArgs, null, null, null);
            cursor.moveToFirst();
            int count = cursor.getCount();
            if (count == 0) {
                Log.d("Schedule", "No class with the number " + CourseNumber);
            }else {
                ContentValues values = new ContentValues();
                values.put("number", CourseNumber);
                try {
                    db.insert(DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES, null, values);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                SyncClasses(CourseNumber);
            }
        } else {
            Log.d("Schedule", CourseNumber + " is already in the schedule");
        }
        //Classes.add(scheduleClass);
    }

    private static int getCount(int courseNumber) {
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = DB.getReadableDatabase();
            //String query = "select count(*) from " + DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES + " where number = "
                    //+ courseNumber;
            //c = db.rawQuery(query, new String[] {"number"});
            c = db.query(DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES, new String[] {"number"}, "number=?", new String[] {courseNumber + ""},
                    null, null, null);
            if (c.moveToFirst()) {
                return c.getCount();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void SyncClasses(int course_number){
        SQLiteDatabase db = DB.getReadableDatabase();
        String[] columns = {"*"};
        String[] selectionArgs = {course_number + ""};
        Cursor cursor = db.query(DBHelper.DATABASE_TABLE_COURSE, columns, "class_num=?", selectionArgs, null, null, null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        if (count == 0) {
            Log.d("Schedule", "No class with the number " + course_number);
        } else {
            int course = cursor.getInt(0);
            String teacher = cursor.getString(1);
            String name = cursor.getString(2);
            String times = cursor.getString(3);
            int enrolled = cursor.getInt(4);
            int limit = cursor.getInt(5);
            String department = cursor.getString(6);
            String building = cursor.getString(7);
            Course c = new Course(department, course, teacher, name, times, enrolled, limit, building, null);
            Classes.add(c);
            cursor.moveToNext();
        }
    }

    public static void DropClass(int courseNumber, SQLiteDatabase db){
        db.delete(DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES, "number = " + courseNumber, null);
    }

    public static void ClearClasses(SQLiteDatabase db){
        String delete = "delete from " + DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES;
        db.execSQL(delete);
    }

    public ArrayList<Course> GetClasses(){
        return Classes;
    }
}
