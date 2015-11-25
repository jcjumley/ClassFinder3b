package com.example.mikechen.ma4.classfinder3;

import java.util.ArrayList;

/**
 * Created by groge on 11/1/2015.
 */
public class Course {
    public String Department;
    public int CourseNumber;
    public String Teacher;
    public String Name;
    public String Times;
    public int Enrold;
    public int Limit;
    public String Building;
    public ArrayList<Course> Prerequisits;

    public Course(String department, int courseNumber, String teacher, String name, String times, int enrold, int limit, String building, ArrayList<Course> prerequisits){
        Department = department;
        CourseNumber = courseNumber;
        Teacher = teacher;
        Name = name;
        Times = times;
        Enrold = enrold;
        Limit = limit;
        Building = building;
        Prerequisits = prerequisits;
    }

    @Override
    public String toString(){
        return Name;
    }
}
