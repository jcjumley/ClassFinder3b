package com.example.mikechen.ma4.classfinder3;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.junit.Test;


/**
 * Created by mikechen on 12/4/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mMainActivity;
    EditText muname;
    EditText mpassword;
    private Instrumentation informationActivityInstrumentation = null;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public MainActivityTest() {
        super("com.example.mikechen.ma4.classfinder3.MainActivity", MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mMainActivity = this.getActivity();
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    muname = (EditText) mMainActivity.findViewById(R.id.Ledituname);
                    mpassword = (EditText) mMainActivity.findViewById(R.id.Leditpw);
                    muname.setText(null);
                    mpassword.setText(null);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }




    @Test
    public void testInsertion() {
        assertEquals("Please enter User Name", "", muname.getText().toString());
        assertEquals("Please enter your Password", "", mpassword.getText().toString());
    }

    /*-
    @Test
    public void testInvalidLogin() {
        ParseUser.logInInBackground(muname.getText().toString(), mpassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, com.parse.ParseException e) {
                if (user != null) {
                    org.junit.Assert.fail("passed");
                } else {
                    org.junit.Assert.fail("failed");
                }
            }


        });
    }
    */
}
