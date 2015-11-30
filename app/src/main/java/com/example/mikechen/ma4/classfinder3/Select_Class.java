package com.example.mikechen.ma4.classfinder3;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by groge on 11/23/2015.
 */
public class Select_Class extends ListActivity {

    DBHelper DB;

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
            Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
        } else {
            Course sc = GetClasses.getClass(item);
            if (sc != null) {
                Find_Class.setClassInformation(sc);
                Toast.makeText(this, sc.Name + " Added for Information", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getBaseContext(), Find_Class.class);
                startActivity(in);
            }
            else {
                Toast.makeText(this, "No Such Class" + sc.Name, Toast.LENGTH_SHORT).show();
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
