package com.example.zeeshan_datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

public class Zeeshan_TimePicker extends AppCompatActivity {
TextView time;
TimePicker simpleTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeeshan_time_picker);

        time=(TextView) findViewById(R.id.textview);
        simpleTime=(TimePicker)findViewById(R.id.picker);

        simpleTime.setIs24HourView(true);
        time.setText("The Time is: "+simpleTime.getCurrentHour()+ " :"+simpleTime.getCurrentMinute());


        simpleTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time.setText("The Time is: "+hourOfDay+ " :"+minute);
            }
        });
    }
}