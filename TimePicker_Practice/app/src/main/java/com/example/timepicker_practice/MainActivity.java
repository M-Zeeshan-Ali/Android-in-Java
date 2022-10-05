package com.example.timepicker_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
//AppCompatActivity is a parent Class inherited using Extends keyword in java ,MainActivity is a child class ,to access anywhere in program using Public access modifier
public class MainActivity extends AppCompatActivity {
    TextView time;  //on textView Calss time variable is declared
    TimePicker simpleTime;  //on TimePicker Class simpleTime variable is declared.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time= findViewById(R.id.textview);
        simpleTime=findViewById(R.id.picker);

        //simpleTime is a varibale ,(. operator is a reference calling of simpleTime) ,setIS24HourView is a function(boolean Expression,false is set for 24 hour time set.)
        simpleTime.setIs24HourView(false);
        time.setText("The Time is: "+simpleTime.getCurrentHour()+ " :"+simpleTime.getCurrentMinute());

        //on simpleTime setonTimeChangedListener method is used ,in button setOnClick Listener apply.
        simpleTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            //onTimeChanged function used with (argument)
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time.setText("The Time is: "+hourOfDay+ " :"+minute);   //setText method ("statement" +(concatenate) parameter pass + minute pass.
            }
        });


    }
}