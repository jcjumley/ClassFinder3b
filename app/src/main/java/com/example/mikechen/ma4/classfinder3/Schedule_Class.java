package com.example.mikechen.ma4.classfinder3;

import java.util.ArrayList;

/**
 * Created by groge on 11/1/2015.
 */
public class Schedule_Class {
    public int CourseNumber;
    public String Teacher;
    public String Times;
    public int Enrold;
    public int Limit;
    public String Department;
    public String Building;
    public ArrayList<Schedule_Class> Prerequisits;

    public Schedule_Class(int courseNumber, String teacher, String times, int enrold, int limit, String department, String building, ArrayList<Schedule_Class> prerequisits){
        CourseNumber = courseNumber;
        Teacher = teacher;
        Times = times;
        Enrold = enrold;
        Limit = limit;
        Building = building;
        Department = department;
        Prerequisits = prerequisits;
    }
}
