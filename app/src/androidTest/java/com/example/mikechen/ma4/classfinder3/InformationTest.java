package com.example.mikechen.ma4.classfinder3;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * Created by mikechen on 12/2/15.
 */
public class InformationTest extends ActivityInstrumentationTestCase2 <Information>{
    private  Information informationActivity;
    private Select_Class selectClassActivity;

    private TextView buildingText;
    private Instrumentation informationActivityInstrumentation =null;

    public InformationTest(){
        super("com.example.mikechen.ma4.classfinder3.Information",Information.class);

    }
    public void setUp() throws Exception {
        super.setUp();
        informationActivity=getActivity();
    }
    public void testImformation(){
        buildingText = (TextView)informationActivity.findViewById(R.id.course_building);

        //tend to choose CSE,then choose Networkprogramming
//        selectClassActivity.onListItemClick();

        assertEquals( "Building: University Hall" ,buildingText.getText().toString());
    }
    protected void tearDown() throws Exception { // cleans up
        informationActivity.finish();
    }
}
