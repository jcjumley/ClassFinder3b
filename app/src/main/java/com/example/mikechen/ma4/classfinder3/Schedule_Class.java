package com.example.mikechen.ma4.classfinder3;

import java.util.ArrayList;

/**
 * Created by groge on 11/1/2015.
 */
public class Schedule_Class {
    public int CourseNumber;
    public String Teacher;
    public int Time;
    public int Enrold;
    public int Limit;
    public ArrayList<Schedule_Class> Prerequisits;

    public Schedule_Class(int courseNumber, String teacher, int time, int enrold, int limit, ArrayList<Schedule_Class> prerequisits){
        CourseNumber = courseNumber;
        Teacher = teacher;
        Time = time;
        Enrold = enrold;
        Limit = limit;
        Prerequisits = prerequisits;
    }
}
