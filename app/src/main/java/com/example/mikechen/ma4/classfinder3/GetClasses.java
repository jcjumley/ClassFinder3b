package com.example.mikechen.ma4.classfinder3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by groge on 11/23/2015.
 */
public class GetClasses {

    static DBHelper DB;
    static SQLiteDatabase db;

    public GetClasses(Context context){
        DB = new DBHelper(context);
        db = DB.getReadableDatabase();
    }

    public static Course getClass(int classNum){
        Cursor c = db.query(DBHelper.DATABASE_TABLE_COURSE, new String[] {"*"}, "course_num=?",
                new String[] {classNum + ""}, null, null, null);
        int courseNum = 0;
        String teacher = "";
        String name = "";
        String times = "";
        int enrolled = 0;
        int limit = 0;
        String department = "";
        String building = "";
        ArrayList<Course> prerequisits = new ArrayList<Course>();
        if (c.moveToFirst()){
            courseNum = c.getInt(0);
            teacher = c.getString(1);
            name = c.getString(2);
            times = c.getString(3);
            enrolled = c.getInt(4);
            limit = c.getInt(5);
            department = c.getString(6);
            building = c.getString(7);
        }
        Course sc = new Course(department, courseNum, teacher, name, times, enrolled, limit, building, prerequisits);
        return sc;
    }

    public static Course getClass(String Name){
        Cursor c = db.query(DBHelper.DATABASE_TABLE_COURSE, new String[] {"*"}, DBHelper.KEY_COURSE + "=?",
                new String[] {Name}, null, null, null);
        int courseNum = 0;
        String teacher = "";
        String name = "";
        String times = "";
        int enrolled = 0;
        int limit = 0;
        String department = "";
        String building = "";
        ArrayList<Course> prerequisits = new ArrayList<Course>();
        if (c.moveToFirst()){
            courseNum = c.getInt(0);
            teacher = c.getString(1);
            name = c.getString(2);
            times = c.getString(3);
            enrolled = c.getInt(4);
            limit = c.getInt(5);
            department = c.getString(6);
            building = c.getString(7);
        }
        Course sc = new Course(department, courseNum, teacher, name, times, enrolled, limit, building, prerequisits);
        return sc;
    }

    public static ArrayList<Course> getAllClasses(){
        Cursor c = db.query(DBHelper.DATABASE_TABLE_COURSE, new String[] {"*"}, null,
                null, null, null, null);
        int courseNum = 0;
        String teacher = "";
        String name = "";
        String times = "";
        int enrolled = 0;
        int limit = 0;
        String department = "";
        String building = "";
        ArrayList<Course> prerequisits = new ArrayList<Course>();
        ArrayList<Course> classes = new ArrayList<>();
        if (c.moveToFirst()){
            while (!c.isAfterLast()) {
                courseNum = c.getInt(0);
                teacher = c.getString(1);
                name = c.getString(2);
                times = c.getString(3);
                enrolled = c.getInt(4);
                limit = c.getInt(5);
                department = c.getString(6);
                building = c.getString(7);
                Course sc = new Course(department, courseNum, teacher, name, times, enrolled, limit, building, prerequisits);
                classes.add(sc);
                c.moveToNext();
            }
        }
        return classes;
    }

    public static ArrayList<Course> getClassesFromDepartment(String dep){
        Cursor c = db.query(DBHelper.DATABASE_TABLE_COURSE, new String[] {"*"}, "department=?",
                new String[] {dep} , null, null, null);
        int courseNum = 0;
        String teacher = "";
        String name = "";
        String times = "";
        int enrolled = 0;
        int limit = 0;
        String department = "";
        String building = "";
        ArrayList<Course> prerequisits = new ArrayList<Course>();
        ArrayList<Course> classes = new ArrayList<>();
        if (c.moveToFirst()){
            while (!c.isAfterLast()) {
                courseNum = c.getInt(0);
                teacher = c.getString(1);
                name = c.getString(2);
                times = c.getString(3);
                enrolled = c.getInt(4);
                limit = c.getInt(5);
                department = c.getString(6);
                building = c.getString(7);
                Course sc = new Course(department, courseNum, teacher, name, times, enrolled, limit, building, prerequisits);
                classes.add(sc);
                c.moveToNext();
            }
        }
        return classes;
    }

    public static ArrayList<String> getDepartments(){
        Cursor c = db.query(true, DBHelper.DATABASE_TABLE_COURSE, new String[] {"department"}, null,
                null, null, null, null, null);
        ArrayList<String> departements = new ArrayList<String>();
        String dep = "";
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                dep = c.getString(0);
                departements.add(dep);
                c.moveToNext();
            }
        }
        return departements;
    }

}
