package com.example.mikechen.ma4.classfinder3;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;

/**
 * Created by groge on 10/22/2015.
 */
public class Home extends Activity implements OnClickListener {

    Button mFindClass;
    Button mAddDrop;
    Button mSchedule;
    Button mSettings;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        if (Settings.rotationLock)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        mFindClass = (Button)findViewById(R.id.Find_Class);
        mFindClass.setOnClickListener(this);

        mAddDrop = (Button)findViewById(R.id.Add_Drop);
        mAddDrop.setOnClickListener(this);

        mSchedule = (Button)findViewById(R.id.Schedule);
        mSchedule.setOnClickListener(this);

        mSettings = (Button)findViewById(R.id.Settings);
        mSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Find_Class:
                Intent in = new Intent(getBaseContext(), Find_Class.class);
                startActivity(in);
                break;
            case R.id.Add_Drop:
                Intent ad=new Intent(getBaseContext(), Scheduler.class);
                startActivity(ad);

                break;
            case R.id.Schedule:
                Intent sc=new Intent(getBaseContext(), Schedule_ListView.class);
                startActivity(sc);
                break;

            case R.id.Settings:
                Intent settings = new Intent(getBaseContext(), Settings.class);
                startActivity(settings);
                break;
        }
    }

}
