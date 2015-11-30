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
    CheckBox mOption2;
    CheckBox mOption3;

    Button mSaveButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        mLockRotation = (CheckBox) findViewById(R.id.lock_rotation);

        mOption2 = (CheckBox) findViewById(R.id.option_2);

        mOption3 = (CheckBox) findViewById(R.id.option_3);

        mSaveButton = (Button) findViewById(R.id.save_settings);
        mSaveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_settings:
                if (mLockRotation.isChecked()) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                }
                Toast.makeText(getApplicationContext(), "Settings Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
