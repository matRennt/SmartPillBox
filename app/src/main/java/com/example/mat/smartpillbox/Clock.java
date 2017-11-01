package com.example.mat.smartpillbox;

/**
 * Created by mat on 21.07.17.
 */

import android.app.Activity; // Required to create an activity.
import android.os.Bundle; // A mapping from String values to various Parcelable types.
import android.widget.AnalogClock;  // Required to create an analog clock


public class Clock extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {  // all classes should extend activity
        super.onCreate(savedInstanceState); // Create a new activity
        //setContentView(R.layout.activity_clock);// Displays the main screen for Clocks
        AnalogClock analog = (AnalogClock) findViewById(R.id.analog_clock);
        analog.bringToFront();
    }
}