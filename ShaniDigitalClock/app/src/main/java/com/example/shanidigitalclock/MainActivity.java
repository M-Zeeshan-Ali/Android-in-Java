package com.example.shanidigitalclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AnalogClock ac;
    DigitalClock dc;
    TextClock textclock;

    //Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ac=(AnalogClock)findViewById(R.id.cnalogclock);
       dc=(DigitalClock)findViewById(R.id.digitalclock);
        textclock=(TextClock) findViewById(R.id.textClock);

        //  btn=(Button) findViewById(R.id.button2);
      //  text=(TextView) findViewById(R.id.text);



        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"You Clicked on AnalogClock",Toast.LENGTH_LONG).show();
            }
        });

        dc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You Clicked on DigitalClock",Toast.LENGTH_SHORT).show();
    }
        });

        textclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You Clicked on TextClock",Toast.LENGTH_LONG).show();
            }
        });

    }



         }






