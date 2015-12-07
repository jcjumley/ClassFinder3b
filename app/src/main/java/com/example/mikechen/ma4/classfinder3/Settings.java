package com.example.mikechen.ma4.classfinder3;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by groge on 10/22/2015.
 */
public class Settings extends Activity implements OnClickListener{

    CheckBox mLockRotation;

    Button mSaveButton;

    public static boolean rotationLock = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        if (Settings.rotationLock)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        mLockRotation = (CheckBox) findViewById(R.id.lock_rotation);
        mLockRotation.setChecked(rotationLock);

        mSaveButton = (Button) findViewById(R.id.save_settings);
        mSaveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_settings:
                rotationLock = mLockRotation.isChecked();
                Toast.makeText(getApplicationContext(), "Settings Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
