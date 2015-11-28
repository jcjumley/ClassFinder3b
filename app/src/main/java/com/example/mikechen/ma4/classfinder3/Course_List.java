package com.example.mikechen.ma4.classfinder3;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mikechen on 11/9/15.
 */
public class Course_List extends ListActivity {

    DBHelper DB;
    private SQLiteDatabase db;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        DB = new DBHelper(getBaseContext());

        List<String> values = GetClasses.getDepartments();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = getListAdapter().getItem(position).toString();
        if (isDepartment(item)) {
            List<Course> ClassList = GetClasses.getClassesFromDepartment(item);
            ArrayList<String> values = new ArrayList<>();
            Iterator<Course> it = ClassList.iterator();
            while (it.hasNext()){
                values.add(it.next().Name);
            }
            ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);
            Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
        } else {
            Course sc = GetClasses.getClass(item);
            if (sc != null) {
                Schedule_Classes.AddClass(sc.CourseNumber);
                Toast.makeText(this, sc.Name + " Added to Schedule", Toast.LENGTH_LONG).show();
                Intent in = new Intent(getBaseContext(), Scheduler.class);
                startActivity(in);
            }
            else {
                Toast.makeText(this, "No Such Class" + sc.Name, Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isDepartment(String item){
        boolean bool;
        List<String> departments = GetClasses.getDepartments();
        bool = departments.contains(item);
        return bool;




    }
}