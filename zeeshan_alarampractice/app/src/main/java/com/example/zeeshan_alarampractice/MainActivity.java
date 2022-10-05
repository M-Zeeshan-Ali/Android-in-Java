package com.example.zeeshan_alarampractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker alaramTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alaramTimePicker=findViewById(R.id.timepicker);
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
    }
        public void OnToggleClicked(View view){
        long time;

        if(((ToggleButton)view).isChecked()){
            Toast.makeText(this, "ALARAM ON", Toast.LENGTH_LONG).show();
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,alaramTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE,alaramTimePicker.getCurrentMinute());

            Intent intent=new Intent(this,Alaram_Receiver.class);
            pendingIntent=pendingIntent.getBroadcast(this,0,intent,0);

            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
            if(System.currentTimeMillis()>time){

                if (calendar.AM_PM==0)
                time=time+(1000*60*60*12);
                else
                    time=time+(1000+60*60*24);
            }
            alarmManager.setRepeating((AlarmManager.RTC_WAKEUP),time,10000,pendingIntent);
        }
            else{
                alarmManager.cancel(pendingIntent);
                Toast.makeText(MainActivity.this,"ALARAM OFF",Toast.LENGTH_LONG).show();
        }
        }

}